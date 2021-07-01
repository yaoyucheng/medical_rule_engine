package com.ltyl.medicalrule.gatewayimpl.database.dao.intf;

import cn.org.atool.fluent.mybatis.base.IBaseDao;
import com.ltyl.medicalrule.gatewayimpl.database.entity.MedicalRuleDO;

import java.util.Collection;
import java.util.List;

/**
 * MedicalRuleDao: 数据操作接口
 * <p>
 * 这只是一个减少手工创建的模板文件
 * 可以任意添加方法和实现, 更改作者和重定义类名
 * <p/>@author Powered By Fluent Mybatis
 */
public interface MedicalRuleDao extends IBaseDao<MedicalRuleDO> {

    /**
     * 获取所有规则的
     *
     * @return
     */
    Collection<String> getMedicalRuleItemCodes();

    /**
     * 通过项目获取项目
     *
     * @param itemCodeList 项目code 列表
     * @return
     */
    Collection<MedicalRuleDO> getMedicalRuleItemByItemCode(List<String> itemCodeList);
}
