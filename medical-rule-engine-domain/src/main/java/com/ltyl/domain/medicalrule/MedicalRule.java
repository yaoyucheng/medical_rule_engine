package com.ltyl.domain.medicalrule;

import lombok.Data;

/**
 * 医疗规则领域模型
 *
 * @author yuchengyao
 */
@Data
public abstract class MedicalRule implements Handleable {

    /**
     * 项目code
     */
    private String itemCode;

    /**
     * 回执消息
     */
    private String receiptMessage;
}
