package com.ltyl.domain.medicalrule.internal;

import com.ltyl.domain.medicalrule.MedicalRuleResult;
import com.ltyl.domain.medicalrule.data.GenderMedicalRuleData;
import com.ltyl.domain.medicalrule.data.MedicalData;
import lombok.AllArgsConstructor;
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

        GenderMedicalRuleData genderMedicalRuleData = (GenderMedicalRuleData) medicalData;

        if (this.gender != null
                && !this.gender.equals(genderMedicalRuleData.getGender())) {

            return MedicalRuleResult.violation(
                    medicalData.getItemCode(),
                    this.getReceiptMessage(),
                    medicalData.getLimitType());
        }

        //  不违规
        return MedicalRuleResult.noViolation(
                medicalData.getItemCode(),
                this.getReceiptMessage(),
                medicalData.getLimitType());
    }

}
