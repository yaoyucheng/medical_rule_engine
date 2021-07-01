package com.ltyl.domain.medicalrule;

import com.ltyl.domain.medicalrule.data.MedicalData;
import com.ltyl.domain.medicalrule.external.ExternalMedicalRule;

/**
 * @author yuchengyao
 */
public interface Handleable {

    /**
     * 处理项目
     *
     * @param medicalData 数据
     * @return
     */
    MedicalRuleResult dealWithItem(MedicalData medicalData);
}
