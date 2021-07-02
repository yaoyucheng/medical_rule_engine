package com.ltyl.domain.medicalrule;

import com.ltyl.domain.medicalrule.data.MedicalData;
import com.ltyl.domain.medicalrule.init.MedicalRuleInitData;

import java.util.List;

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

    /**
     * 处理结果
     *
     * @param medicalRuleResultList
     * @return
     */
    List<MedicalRuleResult> dealWithMedicalRuleResult(List<MedicalRuleResult> medicalRuleResultList);
}
