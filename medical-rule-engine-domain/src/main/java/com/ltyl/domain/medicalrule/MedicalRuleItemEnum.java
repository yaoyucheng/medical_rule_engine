package com.ltyl.domain.medicalrule;

/**
 * 医疗规则项 枚举
 *
 * @author yuchengyao
 */
public enum MedicalRuleItemEnum {

    /**
     * code_code
     * 药品_诊断
     */
    DRUG_DIAGNOSTIC,

    /**
     * code_code_proportion
     * 药品_药品_比例
     */
    DRUG_DRUG_PROPORTION,

    /**
     * code_code
     * 药品_医嘱
     */
    DRUG_MEDICAL_ORDER,

    /**
     * code_measure
     * 药品_计量
     */
    DRUG_MEASURE,

    /**
     * code_gender
     * 药品_性别
     */
    DRUG_GENDER,

    /**
     * code_duration
     * 药品_时长
     */
    DRUG_DURATION,

    /**
     * code_age
     * 药品_年龄
     */
    DRUG_AGE,

    //------------------------------------------------------------------------------------------

    /**
     * code_code
     * 诊疗项目_诊断
     */
    MEDICAL_PROJECT_DIAGNOSTIC,

    /**
     * code_code
     * 诊疗项目_医嘱
     */
    MEDICAL_PROJECT_MEDICAL_ORDER,

    /**
     * code_code
     * 诊疗项目_诊疗项目
     */
    MEDICAL_PROJECT_MEDICAL_PROJECT,

    /**
     * code_duration
     * 诊疗项目_时长
     */
    MEDICAL_PROJECT_DURATION,

    /**
     * code_age
     * 诊疗项目_年龄
     */
    MEDICAL_PROJECT_AGE,


    /**
     * code_gender
     * 诊疗项目_性别
     */
    MEDICAL_PROJECT_GENDER,


    //------------------------------------------------------------------------------------------

    /**
     * 耗材(暂时没有)
     */
    CONSUMABLES,

    ;
}
