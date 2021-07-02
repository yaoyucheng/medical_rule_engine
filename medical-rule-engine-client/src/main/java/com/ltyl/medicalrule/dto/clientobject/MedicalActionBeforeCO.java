package com.ltyl.medicalrule.dto.clientobject;

import lombok.Data;

/**
 * 事前医疗规则请求客户对象
 *
 * @author yuchengyao
 */
@Data
public class MedicalActionBeforeCO extends MedicalActionCO {

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
}
