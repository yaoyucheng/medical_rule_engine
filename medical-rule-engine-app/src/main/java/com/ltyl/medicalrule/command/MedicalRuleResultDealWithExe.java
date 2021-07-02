package com.ltyl.medicalrule.command;

import com.alibaba.fastjson.JSON;
import com.ltyl.domain.medicalrule.MedicalRule;
import com.ltyl.domain.medicalrule.MedicalRuleDistributionHandler;
import com.ltyl.domain.medicalrule.MedicalRuleResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yuchengyao
 */
@Slf4j
@Component
public class MedicalRuleResultDealWithExe {

    /**
     * 去重等操作
     *
     * @param medicalRuleResultList
     * @return
     */
    public List<MedicalRuleResult> execute(List<MedicalRuleResult> medicalRuleResultList) {

        log.info(JSON.toJSONString(medicalRuleResultList));

        Map<String, List<MedicalRuleResult>> collect = medicalRuleResultList.stream().collect(Collectors.groupingBy(MedicalRuleResult::getLimitType));

        List<MedicalRuleResult> results = new ArrayList<>();

        //  业务处理
        collect.forEach((key, value) -> {
            MedicalRule handler = MedicalRuleDistributionHandler.handler(key);
            results.addAll(handler.dealWithMedicalRuleResult(value));
        });

        //  去重操作
        return results;
    }
}
