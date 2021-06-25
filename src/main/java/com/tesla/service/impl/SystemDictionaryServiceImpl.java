package com.tesla.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.source.tree.Tree;
import com.tesla.domain.SystemDictionary;
import com.tesla.mapper.SystemDictionaryMapper;
import com.tesla.qo.QueryObject;
import com.tesla.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {
    @Autowired
    private SystemDictionaryMapper systemDictionaryMapper;

    @Override
    public void save(SystemDictionary systemDictionary) {
        systemDictionaryMapper.insert(systemDictionary);
    }

    @Override
    public void update(SystemDictionary systemDictionary) {
        systemDictionaryMapper.updateByPrimaryKey(systemDictionary);
    }

    @Override
    public void delete(Long id) {
        //查询这个下面有无子
        List<SystemDictionary> systemDictionaries = systemDictionaryMapper.queryByParentId(id);
        deleteTree(systemDictionaries);
        systemDictionaryMapper.deleteByPrimaryKey(id);
    }

    private void deleteTree(List<SystemDictionary> systemDictionaries){
        for (SystemDictionary systemDictionary : systemDictionaries) {
            List<SystemDictionary> items  = systemDictionaryMapper.queryByParentId(systemDictionary.getId());
            if (items.size()>0) {
                deleteTree(items);
            }
            systemDictionaryMapper.deleteByPrimaryKey(systemDictionary.getId());
        }
    }

    @Override
    public SystemDictionary get(Long id) {
        return systemDictionaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemDictionary> listAll() {
        return systemDictionaryMapper.selectAll();
    }

    @Override
    public PageInfo<SystemDictionary> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<SystemDictionary> systemDictionaries = systemDictionaryMapper.selectForList(qo);
        return new PageInfo(systemDictionaries);
       }

    @Override
    public List<SystemDictionary> queryTree() {
        List<SystemDictionary> systemDictionaries = systemDictionaryMapper.queryByParentId(null);
        tree(systemDictionaries);
        return systemDictionaries;
    }

    @Override
    public List<SystemDictionary> queryBySn(String sn) {
        return systemDictionaryMapper.selectBySn(sn);
    }

    @Override
    public List<SystemDictionary> queryItemById(Long id) {
        return systemDictionaryMapper.selectItemById(id);
    }

    private void tree(List<SystemDictionary> systemDictionaries) {
        for (SystemDictionary systemDictionary : systemDictionaries) {
            Long systemDictionaryId = systemDictionary.getId();
            List<SystemDictionary> items = systemDictionaryMapper.queryByParentId(systemDictionaryId);
            systemDictionary.setItems(items);
            if (items.size()>0){
                tree(items);
            }
        }
    }


}
