package com.daocao.dao;

import com.daocao.entity.SellAudit;
import com.daocao.entity.SellAuditExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SellAuditMapper {
    int countByExample(SellAuditExample example);

    int deleteByExample(SellAuditExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SellAudit record);

    int insertSelective(SellAudit record);

    List<SellAudit> selectByExample(SellAuditExample example);

    SellAudit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SellAudit record, @Param("example") SellAuditExample example);

    int updateByExample(@Param("record") SellAudit record, @Param("example") SellAuditExample example);

    int updateByPrimaryKeySelective(SellAudit record);

    int updateByPrimaryKey(SellAudit record);
}