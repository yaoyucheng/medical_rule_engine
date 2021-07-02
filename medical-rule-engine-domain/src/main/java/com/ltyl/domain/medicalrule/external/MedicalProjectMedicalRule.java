package com.ltyl.domain.medicalrule.external;

import com.alibaba.cola.exception.ExceptionFactory;
import com.ltyl.domain.medicalrule.MedicalRuleResult;
import com.ltyl.domain.medicalrule.data.MedicalData;
import com.ltyl.domain.medicalrule.data.MedicalProjectMedicalRuleData;

/**
 * 诊疗项目_诊疗项目
 * MedicalRuleItemEnum.MEDICAL_PROJECT_MEDICAL_PROJECT
 *
 * @author yuchengyao
 */
public class MedicalProjectMedicalRule extends ExternalMedicalRule {

    @Override
    public MedicalRuleResult dealWithItem(MedicalData medicalData) {

        //  TODO
        if (this.getItemCode() == null || this.getRelatedItemCode() == null) {
//            return MedicalRuleResult.noViolation();
        }

        if (medicalData instanceof MedicalProjectMedicalRuleData) {
            return MedicalRuleResult.violation(
                    this.getItemCode(),
                    this.getRelatedItemCode(),
                    this.getReceiptMessage(),
                    medicalData.getLimitType());
        }

        throw ExceptionFactory.bizException("调用异常！");
    }
}
