package com.ltyl.domain.medicalrule.internal;

import com.ltyl.domain.medicalrule.MedicalRule;
import com.ltyl.domain.medicalrule.MedicalRuleResult;
import com.ltyl.domain.medicalrule.data.MedicalData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 年龄规则
 * 药品_年龄
 * 诊疗项目_年龄
 *
 * @author yuchengyao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgeMedicalRule extends InternalMedicalRule{

    //  DRUG_AGE
    //  MEDICAL_PROJECT_AGE

    /**
     * 年龄上限
     */
    private Integer age;

    @Override
    public MedicalRuleResult dealWithItem( MedicalData medicalData) {
        return null;
    }
}
