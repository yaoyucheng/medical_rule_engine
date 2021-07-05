package com.ltyl.domain.gateway;

import com.ltyl.domain.medicalrule.MedicalRule;

/**
 * @author yuchengyao
 */
public interface MedicalRuleGateway {

    /**
     * 获取规则
     *
     * @param limitType
     * @param code
     * @param relatedItemCode
     * @return
     */
    MedicalRule getMedicalRule(String limitType, String code, String relatedItemCode);

    /**
     * 获取规则
     *
     * @param limitType
     * @param code
     * @return
     */
    MedicalRule getMedicalRule(String limitType, String code);
}
