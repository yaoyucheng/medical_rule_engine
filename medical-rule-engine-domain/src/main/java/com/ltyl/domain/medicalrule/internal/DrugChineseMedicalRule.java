package com.ltyl.domain.medicalrule.internal;

import com.ltyl.domain.medicalrule.MedicalRuleResult;
import com.ltyl.domain.medicalrule.data.MedicalData;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DrugChineseMedicalRule extends InternalMedicalRule {

    @Override
    public MedicalRuleResult dealWithItem(MedicalData medicalData) {
        if (this.getItemCode() == null) {

            return MedicalRuleResult.noViolation(
                    this.getItemCode(),
                    this.getReceiptMessage(),
                    medicalData.getLimitType()
            );
        }

        return MedicalRuleResult.violation(
                this.getItemCode(),
                this.getReceiptMessage(),
                medicalData.getLimitType()
        );
    }

    /**
     * 只要大于 medicalRuleResultList 数量大于1，就不违规
     *
     * @param medicalRuleResultList
     * @return
     */
    @Override
    public List<MedicalRuleResult> dealWithMedicalRuleResult(List<MedicalRuleResult> medicalRuleResultList) {

        List<MedicalRuleResult> results = medicalRuleResultList.stream().collect(Collectors.groupingBy(MedicalRuleResult::getViolation)).get(true);

        List<MedicalRuleResult> medicalRuleResults = new ArrayList<>();

        if (results.size() == 1) {
            medicalRuleResults.add(results.get(0));
        }

        return medicalRuleResults;
    }
}
