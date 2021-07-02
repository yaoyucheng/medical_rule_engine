package com.ltyl.medicalrule.web;

import com.alibaba.cola.dto.MultiResponse;
import com.ltyl.medicalrule.api.MedicalRuleService;
import com.ltyl.medicalrule.dto.MedicalActionBeforeQry;
import com.ltyl.medicalrule.dto.clientobject.MedicalActionBeforeCO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author yuchengyao
 */
@RestController
public class MedicalRuleController {

    @Resource
    private MedicalRuleService medicalRuleService;

    /**
     * 医疗动作之前的请求（事前）
     *
     * @param medicalActionBeforeQry
     * @return
     */
    @PostMapping("medical-action-before")
    public MultiResponse<MedicalActionBeforeCO> beforeMedicalAction(@Valid @RequestBody MedicalActionBeforeQry medicalActionBeforeQry) {
        return medicalRuleService.beforeMedicalAction(medicalActionBeforeQry);
    }


    /**
     * 医疗动作之前的请求（事中）
     */
    @PostMapping("medical-action-doing")
    public MultiResponse<Object> doingMedicalAction() {

        return MultiResponse.buildSuccess();
    }

    /**
     * 医疗动作之前的请求（事后）
     */
    @PostMapping("medical-action-after")
    public MultiResponse<Object> afterMedicalAction() {

        return MultiResponse.buildSuccess();
    }

}
