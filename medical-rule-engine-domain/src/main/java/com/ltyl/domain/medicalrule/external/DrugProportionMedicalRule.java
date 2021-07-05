package com.ltyl.domain.medicalrule.external;

import com.alibaba.cola.exception.ExceptionFactory;
import com.ltyl.domain.medicalrule.MedicalRuleResult;
import com.ltyl.domain.medicalrule.data.DrugProportionMedicalRuleData;
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

        if (!(medicalData instanceof DrugProportionMedicalRuleData)) {
            throw ExceptionFactory.bizException("调用异常！");
        }

        DrugProportionMedicalRuleData drugProportionMedicalRuleData = (DrugProportionMedicalRuleData) medicalData;

        if (this.getItemCode() == null && this.getItemRelatedProportion() == null) {
            //  无数据，不违规
            return MedicalRuleResult.noViolation(
                    drugProportionMedicalRuleData.getItemCode(),
                    drugProportionMedicalRuleData.getRelatedItemCode(),
                    this.getReceiptMessage(),
                    drugProportionMedicalRuleData.getLimitType());
        }

        //  实际比例
        BigDecimal divide = drugProportionMedicalRuleData.getItemMeasure().divide(
                drugProportionMedicalRuleData.getRelatedItemMeasure());

        //  定义比例大于实际比例
        if (this.itemRelatedProportion.compareTo(divide) == 1) {
            //  正常比例，不违规
            return MedicalRuleResult.noViolation(
                    drugProportionMedicalRuleData.getItemCode(),
                    drugProportionMedicalRuleData.getRelatedItemCode(),
                    this.getReceiptMessage(),
                    drugProportionMedicalRuleData.getLimitType());
        }

        return MedicalRuleResult.violation(
                this.getItemCode(),
                this.getRelatedItemCode(),
                this.getReceiptMessage(),
                medicalData.getLimitType());
    }

}
