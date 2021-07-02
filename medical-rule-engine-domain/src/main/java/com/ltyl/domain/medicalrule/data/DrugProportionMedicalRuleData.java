package com.ltyl.domain.medicalrule.data;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yuchengyao
 */
@Data
public class DrugProportionMedicalRuleData extends ExternalMedicalRuleData {

    /**
     * 药品计量
     */
    BigDecimal itemMeasure;

    /**
     * 相关药品的计量
     */
    BigDecimal relatedItemMeasure;
}
