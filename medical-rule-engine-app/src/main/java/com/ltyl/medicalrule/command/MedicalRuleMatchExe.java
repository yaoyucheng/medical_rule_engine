package com.ltyl.medicalrule.command;

import com.alibaba.cola.exception.ExceptionFactory;
import com.ltyl.domain.gateway.MedicalRuleGateway;
import com.ltyl.domain.medicalrule.MedicalRule;
import com.ltyl.domain.medicalrule.MedicalRuleResult;
import com.ltyl.domain.medicalrule.data.ExternalMedicalRuleData;
import com.ltyl.domain.medicalrule.data.MedicalData;
import com.ltyl.domain.medicalrule.data.MedicalProjectMedicalRuleData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuchengyao
 */
@Slf4j
@Component
public class MedicalRuleMatchExe {

    @Resource
    private MedicalRuleGateway medicalRuleGateway;

    public List<MedicalRuleResult> execute(List<MedicalData> medicalDataList) {

        List<MedicalRuleResult> results = new ArrayList<>();
        medicalDataList.forEach(x -> {
            MedicalRuleResult medicalRuleResult = dealWith(x);
            results.add(medicalRuleResult);
        });
        return results;
    }

    private MedicalRuleResult dealWith(MedicalData medicalData) {
        String itemCode = medicalData.getItemCode();

        MedicalRule medicalRule = null;

        if (medicalData instanceof ExternalMedicalRuleData
                || medicalData instanceof MedicalProjectMedicalRuleData) {

            ExternalMedicalRuleData externalMedicalRuleData = (ExternalMedicalRuleData) medicalData;

            medicalRule = medicalRuleGateway.getExternalMedicalRule(itemCode, externalMedicalRuleData.getRelatedItemCode());
        }

        //  TODO: 其他类型

        //  错误
        if (StringUtils.isEmpty(medicalRule)) {
            throw ExceptionFactory.bizException("MedicalData type error");
        }

        return medicalRule.dealWithItem(medicalData);

    }
}
