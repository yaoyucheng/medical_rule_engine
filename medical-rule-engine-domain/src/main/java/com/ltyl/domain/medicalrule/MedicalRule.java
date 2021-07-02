package com.ltyl.domain.medicalrule;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

    @Override
    public List<MedicalRuleResult> dealWithMedicalRuleResult(List<MedicalRuleResult> medicalRuleResultList) {
        List<MedicalRuleResult> results = medicalRuleResultList.stream().collect(Collectors.groupingBy(MedicalRuleResult::getViolation)).get(true);

        if (results == null || results.isEmpty()) {
            return new ArrayList<>();
        }

        ArrayList<MedicalRuleResult> collect1 = results.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                                Comparator.comparing(
                                        p -> p.getItemCode() + ";" + p.getLimitType() + ";" + p.getRelatedItemCode()
                                )
                        )
                ), ArrayList::new));


        return collect1;
    }


}
