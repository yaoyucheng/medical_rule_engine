package com.ltyl.domain.medicalrule.internal;

import com.ltyl.domain.medicalrule.MedicalRuleResult;
import com.ltyl.domain.medicalrule.data.AgeMedicalRuleData;
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
public class AgeMedicalRule extends InternalMedicalRule {

    //  DRUG_AGE
    //  MEDICAL_PROJECT_AGE

    /**
     * 年龄上限（包含）
     */
    private Integer maxAge;

    /**
     * 年龄下限（包含）
     */
    private Integer minAge;

    @Override
    public MedicalRuleResult dealWithItem(MedicalData medicalData) {

        AgeMedicalRuleData ageMedicalRuleData = (AgeMedicalRuleData) medicalData;

        if (this.maxAge != null
                && this.minAge != null
                && this.minAge >= ageMedicalRuleData.getAge()
                && this.maxAge <= ageMedicalRuleData.getAge()) {
            //  违规
            return violation(medicalData);
        }
        
        if (this.maxAge != null
                && this.minAge == null
                && this.maxAge <= ageMedicalRuleData.getAge()) {
            //  违规
            return violation(medicalData);
        }

        if (this.maxAge == null
                && this.minAge != null
                && this.minAge >= ageMedicalRuleData.getAge()) {
            //  违规
            return violation(medicalData);
        }

        return MedicalRuleResult.noViolation(
                medicalData.getItemCode(),
                this.getReceiptMessage(),
                medicalData.getLimitType());
    }

    private MedicalRuleResult violation(MedicalData medicalData) {
        // 这样的情况应该不会出现
        return MedicalRuleResult.violation(
                medicalData.getItemCode(),
                this.getReceiptMessage(),
                medicalData.getLimitType()
        );
    }
}
