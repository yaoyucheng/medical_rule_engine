package com.ltyl.domain.medicalrule.internal;

import com.ltyl.domain.medicalrule.MedicalRule;
import com.ltyl.domain.medicalrule.MedicalRuleResult;
import com.ltyl.domain.medicalrule.data.MedicalData;
import com.ltyl.domain.medicalrule.init.MedicalRuleInitData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 性别规则
 * 药品_性别
 * 诊疗项目_性别
 *
 * @author yuchengyao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenderMedicalRule extends InternalMedicalRule {

    //  DRUG_GENDER
    //  MEDICAL_PROJECT_GENDER

    private String gender;

    @Override
    public MedicalRuleResult dealWithItem(MedicalData medicalData) {
        return null;
    }

}
