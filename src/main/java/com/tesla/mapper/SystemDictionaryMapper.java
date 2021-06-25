package com.tesla.mapper;

import com.tesla.domain.SystemDictionary;
import com.tesla.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemDictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionary record);

    SystemDictionary selectByPrimaryKey(Long id);

    List<SystemDictionary> selectAll();

    int updateByPrimaryKey(SystemDictionary record);

    List<SystemDictionary> selectForList(QueryObject qo);

    List<SystemDictionary> queryByParentId(@Param("parentId") Long parentId);

    List<SystemDictionary> selectBySn(String sn);

    List<SystemDictionary> selectItemById(Long id);
}