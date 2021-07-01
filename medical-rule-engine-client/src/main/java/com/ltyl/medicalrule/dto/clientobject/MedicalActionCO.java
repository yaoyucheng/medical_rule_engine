package com.ltyl.medicalrule.dto.clientobject;

import com.alibaba.cola.dto.ClientObject;
import lombok.Data;

/**
 * @author yuchengyao
 */
@Data
public class MedicalActionCO extends ClientObject {

    /**
     * 规则唯一标识
     */
    private String medicalActionId;

    /**
     * 规则类型
     */
    private String medicalActionType;
}
