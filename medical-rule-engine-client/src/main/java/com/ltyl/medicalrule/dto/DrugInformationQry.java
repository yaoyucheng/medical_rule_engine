package com.ltyl.medicalrule.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 药品信息
 *
 * @author yuchengyao
 */
@Data
public class DrugInformationQry extends MedicalItemQry{

    /**
     * 药品计量
     */
    BigDecimal measure;
}
