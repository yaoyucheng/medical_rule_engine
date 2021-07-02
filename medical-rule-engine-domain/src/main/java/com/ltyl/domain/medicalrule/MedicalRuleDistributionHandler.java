package com.ltyl.domain.medicalrule;

import com.alibaba.cola.exception.BizException;
import com.ltyl.domain.medicalrule.external.DrugProportionMedicalRule;
import com.ltyl.domain.medicalrule.external.ExternalMedicalRule;
import com.ltyl.domain.medicalrule.external.MedicalProjectMedicalRule;
import com.ltyl.domain.medicalrule.internal.AgeMedicalRule;
import com.ltyl.domain.medicalrule.internal.DrugMeasureMedicalRule;
import com.ltyl.domain.medicalrule.internal.DurationMedicalRule;
import com.ltyl.domain.medicalrule.internal.GenderMedicalRule;

/**
 * @author yuchengyao
 */
public class MedicalRuleDistributionHandler {

    public static MedicalRule handler(String limitType) {

        if (MedicalRuleItemEnum.DRUG_DIAGNOSTIC.name().equals(limitType)
                || MedicalRuleItemEnum.DRUG_MEDICAL_ORDER.name().equals(limitType)
                || MedicalRuleItemEnum.MEDICAL_PROJECT_DIAGNOSTIC.name().equals(limitType)
                || MedicalRuleItemEnum.MEDICAL_PROJECT_MEDICAL_ORDER.name().equals(limitType)) {
            return new ExternalMedicalRule();
        }

        if (MedicalRuleItemEnum.MEDICAL_PROJECT_MEDICAL_PROJECT.name().equals(limitType)) {
            return new MedicalProjectMedicalRule();
        }

        if (MedicalRuleItemEnum.DRUG_DRUG_PROPORTION.name().equals(limitType)) {
            return new DrugProportionMedicalRule();
        }

        if (MedicalRuleItemEnum.MEDICAL_PROJECT_AGE.name().equals(limitType)
                || MedicalRuleItemEnum.DRUG_AGE.name().equals(limitType)) {
            return new AgeMedicalRule();
        }

        if (MedicalRuleItemEnum.DRUG_MEASURE.name().equals(limitType)) {
            return new DrugMeasureMedicalRule();
        }

        if (MedicalRuleItemEnum.DRUG_DURATION.name().equals(limitType)
                || MedicalRuleItemEnum.MEDICAL_PROJECT_DURATION.equals(limitType)) {
            return new DurationMedicalRule();
        }

        if (MedicalRuleItemEnum.DRUG_GENDER.name().equals(limitType)
                || MedicalRuleItemEnum.MEDICAL_PROJECT_GENDER.name().equals(limitType)) {
            return new GenderMedicalRule();
        }

        throw new BizException("limitType is error......");

    }

}
