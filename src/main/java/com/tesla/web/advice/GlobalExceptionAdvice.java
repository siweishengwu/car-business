package com.tesla.web.advice;

import com.alibaba.fastjson.JSON;
import com.tesla.qo.JsonResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class GlobalExceptionAdvice {

//    //处理异常的方法,方法需要贴@ExceptionHander注解
//    @ExceptionHandler(RuntimeException.class)
//    public String handlerException(RuntimeException e, Model model, HandlerMethod handlerMethod,
//                                   HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        ResponseBody annotation = handlerMethod.getMethodAnnotation(ResponseBody.class);
//        if (annotation!=null) {
//            JsonResult jsonResult = new JsonResult(false,e.getMessage());
//            response.setContentType("application/json;charset=utf-8");
//            PrintWriter writer = response.getWriter();
//            writer.write(JSON.toJSONString(jsonResult));
//            return  null;
//        }
//
//        model.addAttribute("errorMsg",e.getMessage());
//        System.out.println(e.getMessage());
//
//        return "common/error";
//    }


}
