package com.ltyl.domain.medicalrule.internal;

import com.ltyl.domain.medicalrule.MedicalRuleResult;
import com.ltyl.domain.medicalrule.data.MedicalData;
import lombok.Data;

/**
 * @author yuchengyao
 */
@Data
public class DrugLevelMedicalRule extends InternalMedicalRule {

    @Override
    public MedicalRuleResult dealWithItem(MedicalData medicalData) {
        if (this.getItemCode() == null) {
            return MedicalRuleResult.noViolation(
                    this.getItemCode(),
                    this.getReceiptMessage(),
                    medicalData.getLimitType());
        }
        return MedicalRuleResult.violation(
                this.getItemCode(),
                this.getReceiptMessage(),
                medicalData.getLimitType());
    }
}
