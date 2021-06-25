package com.tesla.web.controller;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.Business;
import com.tesla.qo.BusinessQueryObject;
import com.tesla.qo.QueryObject;
import com.tesla.service.IBusinessService;
import com.tesla.util.FileUploadUtil;
import com.tesla.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Controller
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private IBusinessService businessService;

    //处理查询所有门店请求
    @RequestMapping("/list")
    @RequiredPermission(name = "门店查询",expression = "business:list")
    public String list(Model model,@ModelAttribute("qo")BusinessQueryObject qo){
        PageInfo<Business> pageInfo = businessService.query(qo);
        model.addAttribute("pageInfo",pageInfo);
        return "business/list";
    }

    //删除
    @RequestMapping("/delete")
    @RequiredPermission(name = "门店删除",expression = "business:delete")
    public String delete(Long id){
        if (id != null) {
            businessService.delete(id);
        }
        return "redirect:/business/list";
    }

    //点击input返回input页面
    @RequestMapping("/input")
    public String input(Long id,Model model){
        if (id != null){
            Business business = businessService.get(id);
            model.addAttribute("business",business);
        }
        return "/business/input";
    }

    @Autowired
    private ServletContext servletContext;

    //新增
    @RequestMapping("/saveOrUpdate")
    @RequiredPermission(name = "门店新增或删除",expression = "business:saveOrUpdate")
    public String saveOrUpdate(Business business, MultipartFile file) throws Exception {
        //把图片保存到项目当中 /static/upload 目录下
        //把这个图片项目路径设置business对象的licenseImg 属性上

        //用户有可能传递营业执照的图片
            //保存磁盘中
            //从磁盘删除就有营业制造图片
            //把新营业执照路径设置到 business 对象的licenseImg属性上
            //指向修改操作
        //用户也可能没有传递营业执照图片
            //保证营业执照图片路径改完还在,要不就不要改,要不还是改成原来的

        if (file != null && file.getSize() > 0) { //若上传了图片

            if (StringUtils.hasLength(business.getLicenseImg())) {
                //使用工具类 调用servletContext删除
                //把图片的项目路径转成磁盘绝对路径,之后调用工具方法作为参数,执行删除
                FileUploadUtil.deleteFile(servletContext.getRealPath(business.getLicenseImg()));
            }


            //保存上传图片
            //通过 servletContext 对象 getRealPath 方法返回项目 对应绝对路径
            // /static/upload -> K:/idea-workspace/project01/day01/car/src/main/webapp/static/upload
            // / -> K:/idea-workspace/project01/day01/car/src/main/webapp

            String realPath = servletContext.getRealPath("/");
            System.out.println(realPath);

            String imgUrl = FileUploadUtil.uploadFile(file, servletContext.getRealPath("/"));
            business.setLicenseImg(imgUrl);
        }


        if (business.getId() != null) {
            //有id修改
            businessService.update(business);
        }else {
            //新增
            businessService.save(business);
        }
        return "redirect:/business/list";
    }
}
