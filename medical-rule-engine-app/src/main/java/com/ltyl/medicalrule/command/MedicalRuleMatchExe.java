package com.ltyl.medicalrule.command;

import com.alibaba.cola.exception.ExceptionFactory;
import com.ltyl.domain.gateway.MedicalRuleGateway;
import com.ltyl.domain.medicalrule.MedicalRule;
import com.ltyl.domain.medicalrule.MedicalRuleResult;
import com.ltyl.domain.medicalrule.data.ExternalMedicalRuleData;
import com.ltyl.domain.medicalrule.data.MedicalData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuchengyao
 */
@Component
public class MedicalRuleMatchExe {

    @Resource
    private MedicalRuleGateway medicalRuleGateway;

    public List<MedicalRuleResult> execute(List<MedicalData> medicalDataList) {

        List<MedicalRuleResult> results = new ArrayList<>();
        medicalDataList.forEach(x -> {
            MedicalRuleResult medicalRuleResult = dealWith(x);
            if (medicalRuleResult.isViolation()) {
                return;
            }
            results.add(medicalRuleResult);
        });
        return results;
    }

    private MedicalRuleResult dealWith(MedicalData medicalData) {
        String itemCode = medicalData.getItemCode();

        if (medicalData instanceof ExternalMedicalRuleData) {
            ExternalMedicalRuleData externalMedicalRuleData = (ExternalMedicalRuleData) medicalData;

            MedicalRule medicalRule = medicalRuleGateway.getExternalMedicalRule(itemCode, externalMedicalRuleData.getRelatedItemCode());

            return medicalRule.dealWithItem(externalMedicalRuleData);
        }

        //  TODO: 其他类型

        throw ExceptionFactory.bizException("MedicalData type error");
    }
}
