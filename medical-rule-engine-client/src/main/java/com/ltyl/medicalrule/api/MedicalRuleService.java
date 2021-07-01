package com.ltyl.medicalrule.api;

import com.alibaba.cola.dto.MultiResponse;
import com.ltyl.medicalrule.dto.MedicalActionBeforeQry;
import com.ltyl.medicalrule.dto.clientobject.MedicalActionBeforeCO;

/**
 * @author yuchengyao
 */
public interface MedicalRuleService {

    /**
     * 事前接口
     *
     * @param medicalActionBeforeQry
     * @return
     */
    MultiResponse<MedicalActionBeforeCO> beforeMedicalAction(MedicalActionBeforeQry medicalActionBeforeQry);

}
