package com.ltyl.medicalrule.gatewayimpl.database.dao.impl;

import com.ltyl.medicalrule.gatewayimpl.database.dao.base.MedicalRuleBaseDao;
import com.ltyl.medicalrule.gatewayimpl.database.dao.intf.MedicalRuleDao;
import com.ltyl.medicalrule.gatewayimpl.database.entity.MedicalRuleDO;
import com.ltyl.medicalrule.gatewayimpl.database.mapper.MedicalRuleMapper;
import com.ltyl.medicalrule.gatewayimpl.database.wrapper.MedicalRuleQuery;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MedicalRuleDaoImpl: 数据操作接口实现
 * <p>
 * 这只是一个减少手工创建的模板文件
 * 可以任意添加方法和实现, 更改作者和重定义类名
 * <p/>@author Powered By Fluent Mybatis
 */
@Repository
public class MedicalRuleDaoImpl extends MedicalRuleBaseDao implements MedicalRuleDao {

    @Resource
    private MedicalRuleMapper medicalRuleMapper;

    @Override
    public Collection<String> getMedicalRuleItemCodes() {

        MedicalRuleQuery medicalRuleQuery = new MedicalRuleQuery()
                .select.itemCode().end()
                .groupBy.itemCode()
                .end();

        return medicalRuleMapper.listEntity(medicalRuleQuery).stream().collect(Collectors.toMap(MedicalRuleDO::getItemCode, value -> value, (a, b) -> a)).keySet();

    }

    @Override
    public Collection<MedicalRuleDO> getMedicalRuleItemByItemCode(List<String> itemCodeList) {

        MedicalRuleQuery medicalRuleQuery = new MedicalRuleQuery()
                .where.itemCode().in(itemCodeList).end();

        return medicalRuleMapper.listEntity(medicalRuleQuery);
    }
}
