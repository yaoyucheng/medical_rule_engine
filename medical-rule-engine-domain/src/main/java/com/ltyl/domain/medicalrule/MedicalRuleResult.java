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
    private Boolean violation = false;

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
     * 违规类型
     */
    private String limitType;

    /**
     * 违规封装
     *
     * @return
     */
    public static MedicalRuleResult violation(
            String itemCode,
            String relatedItemCode,
            String receiptMessage,
            String limitType) {
        return MedicalRuleResult.builder()
                .itemCode(itemCode)
                .relatedItemCode(relatedItemCode)
                .receiptMessage(receiptMessage)
                .limitType(limitType)
                .violation(true)
                .build();
    }

    /**
     * 违规封装
     *
     * @return
     */
    public static MedicalRuleResult violation(
            String itemCode,
            String receiptMessage,
            String limitType) {
        return MedicalRuleResult.violation(itemCode, null, receiptMessage, limitType);
    }


    /**
     * 无违规封装
     *
     * @return
     */
    public static MedicalRuleResult noViolation(
            String itemCode,
            String relatedItemCode,
            String receiptMessage,
            String limitType) {

        return MedicalRuleResult.builder()
                .itemCode(itemCode)
                .relatedItemCode(relatedItemCode)
                .receiptMessage(receiptMessage)
                .limitType(limitType)
                .violation(false)
                .build();
    }

    /**
     * 无违规封装
     *
     * @return
     */
    public static MedicalRuleResult noViolation(
            String itemCode,
            String receiptMessage,
            String limitType) {

        return MedicalRuleResult.noViolation(itemCode, null, receiptMessage, limitType);
    }

    public static boolean isViolation(MedicalRuleResult medicalRuleResult) {
        return medicalRuleResult.violation;
    }

}
