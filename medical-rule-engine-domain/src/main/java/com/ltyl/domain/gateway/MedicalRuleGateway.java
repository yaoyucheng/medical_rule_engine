package com.ltyl.domain.gateway;

import com.ltyl.domain.medicalrule.MedicalRule;

/**
 * @author yuchengyao
 */
public interface MedicalRuleGateway {

    /**
     * 获取code 相关规则
     *
     * @param code
     * @param relatedItemCode
     * @return
     */
    MedicalRule getExternalMedicalRule(String code, String relatedItemCode);

    /**
     * 获取 药品_药品_比例  的规则
     *
     * @param code
     * @return
     */
    MedicalRule getDrugProportionMedicalRule(String code);

    /**
     * 获取年龄规则
     *
     * @param code
     * @return
     */
    MedicalRule getAgeMedicalRule(String code);

    /**
     * 获取性别规则
     *
     * @param code
     * @return
     */
    MedicalRule getGenderMedicalRule(String code);

    /**
     * 药品计量规则
     *
     * @param code
     * @return
     */
    MedicalRule getDrugMeasureMedicalRule(String code);

    /**
     * 获取时长规则
     *
     * @param code
     * @return
     */
    MedicalRule getDurationMedicalRule(String code);
}
