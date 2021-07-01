package com.ltyl.domain.medicalrule;

import lombok.Builder;
import lombok.Data;

/**
 * @author yuchengyao
 */
@Builder
@Data
public class MedicalRuleResult {

    /**
     * 是否违规
     */
    private boolean isViolation = false;

    /**
     * 项目code
     */
    private String itemCode;

    /**
     * 相关项目code
     */
    private String relatedItemCode;
    /**
     * 回执消息
     */
    private String receiptMessage;

    /**
     * 违规封装
     *
     * @return
     */
    public static MedicalRuleResult violation(MedicalRuleResult medicalRuleResult) {
        return MedicalRuleResult.builder()
                .itemCode(medicalRuleResult.getItemCode())
                .relatedItemCode(medicalRuleResult.getRelatedItemCode())
                .receiptMessage(medicalRuleResult.getReceiptMessage())
                .isViolation(true).build();
    }

    /**
     * 无违规封装
     *
     * @return
     */
    public static MedicalRuleResult noViolation() {
        return MedicalRuleResult.builder().build();
    }

    public static boolean isViolation(MedicalRuleResult medicalRuleResult) {
        return medicalRuleResult.isViolation;
    }
}
