package com.tesla.service;

import com.github.pagehelper.PageInfo;
import com.tesla.domain.SystemDictionary;
import com.tesla.qo.QueryObject;

import java.util.List;

public interface ISystemDictionaryService {
    void save(SystemDictionary systemDictionary);
    void update(SystemDictionary systemDictionary);
    void delete(Long id);
    SystemDictionary get(Long id);
    List<SystemDictionary> listAll();

    PageInfo<SystemDictionary> query(QueryObject qo);

    List<SystemDictionary> queryTree();

    List<SystemDictionary> queryBySn(String sn);

    List<SystemDictionary> queryItemById(Long id);
}
