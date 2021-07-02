package com.ltyl.domain.medicalrule.data;

import com.ltyl.domain.medicalrule.MedicalRuleItemEnum;
import lombok.Data;

/**
 * @author yuchengyao
 */
@Data
public class MedicalData {

    /**
     * 数据项目code
     */
    private String itemCode;

    /**
     * 类型
     */
    private String limitType;

}
