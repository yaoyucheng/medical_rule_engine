package com.ltyl.domain.medicalrule.external;

import com.ltyl.domain.medicalrule.MedicalRuleResult;
import com.ltyl.domain.medicalrule.data.MedicalData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 药品_药品_比例
 *
 * @author yuchengyao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugProportionMedicalRule extends ExternalMedicalRule {

    //  MedicalRuleItemEnum.DRUG_DRUG_PROPORTION

    /**
     * 比例
     */
    private BigDecimal itemRelatedProportion;

    @Override
    public MedicalRuleResult dealWithItem(MedicalData medicalData) {
        return null;
    }
}
