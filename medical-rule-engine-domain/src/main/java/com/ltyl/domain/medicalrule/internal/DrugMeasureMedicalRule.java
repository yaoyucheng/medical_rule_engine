package com.ltyl.domain.medicalrule.internal;

import com.ltyl.domain.medicalrule.MedicalRule;
import com.ltyl.domain.medicalrule.MedicalRuleResult;
import com.ltyl.domain.medicalrule.data.MedicalData;
import com.ltyl.domain.medicalrule.init.MedicalRuleInitData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 药品计量规则
 * 药品_计量
 * @author yuchengyao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugMeasureMedicalRule extends InternalMedicalRule {

    //  DRUG_MEASURE

    /**
     * 药品计量
     */
    private BigDecimal itemMeasure;

    @Override
    public MedicalRuleResult dealWithItem( MedicalData medicalData) {
        return null;
    }

}
