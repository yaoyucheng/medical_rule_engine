package com.ltyl.domain.medicalrule.external;

import com.alibaba.cola.exception.ExceptionFactory;
import com.ltyl.domain.medicalrule.MedicalRule;
import com.ltyl.domain.medicalrule.MedicalRuleResult;
import com.ltyl.domain.medicalrule.data.ExternalMedicalRuleData;
import com.ltyl.domain.medicalrule.data.MedicalData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 外部医疗规则
 * 药品_诊断
 * MedicalRuleItemEnum.DRUG_DIAGNOSTIC,
 * 药品_医嘱
 * MedicalRuleItemEnum.DRUG_MEDICAL_ORDER,
 * 诊疗项目_诊断
 * MedicalRuleItemEnum.MEDICAL_PROJECT_DIAGNOSTIC,
 * 诊疗项目_医嘱
 * MedicalRuleItemEnum.MEDICAL_PROJECT_MEDICAL_ORDER,
 * <p>
 * 结构： code_code
 *
 * @author yuchengyao
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalMedicalRule extends MedicalRule {

    private String relatedItemCode;

    @Override
    public MedicalRuleResult dealWithItem(MedicalData medicalData) {

        if (!(medicalData instanceof ExternalMedicalRuleData)) {
            throw ExceptionFactory.bizException("调用异常！");
        }

        ExternalMedicalRuleData externalMedicalRuleData = (ExternalMedicalRuleData) medicalData;

        if (this.getItemCode() == null) {
            //  在缓存中没有找到项目，不违规
            return MedicalRuleResult.noViolation(
                    externalMedicalRuleData.getItemCode(),
                    externalMedicalRuleData.getRelatedItemCode(),
                    this.getReceiptMessage(),
                    externalMedicalRuleData.getLimitType()
            );
        }

        if (this.getRelatedItemCode() != null) {
            //  不违规
            return MedicalRuleResult.noViolation(
                    externalMedicalRuleData.getItemCode(),
                    externalMedicalRuleData.getRelatedItemCode(),
                    this.getReceiptMessage(),
                    externalMedicalRuleData.getLimitType()
            );
        }

        //  违规
        return MedicalRuleResult.violation(
                externalMedicalRuleData.getItemCode(),
                externalMedicalRuleData.getRelatedItemCode(),
                this.getReceiptMessage(),
                externalMedicalRuleData.getLimitType());

    }

    @Override
    public List<MedicalRuleResult> dealWithMedicalRuleResult(List<MedicalRuleResult> medicalRuleResultList) {

        if (this instanceof ExternalMedicalRule) {
            return super.dealWithMedicalRuleResult(medicalRuleResultList);
        }

        List<MedicalRuleResult> results = new ArrayList<>();

        Map<String, List<MedicalRuleResult>> collect = medicalRuleResultList.stream().collect(Collectors.groupingBy(MedicalRuleResult::getItemCode));

        collect.forEach((key, value) -> {

            //  因为是取反操作，所以只要有一个不违规的，就不违规

            //  不违规的
            MedicalRuleResult noViolationMedicalRuleResult = value.stream().collect(Collectors.toMap(MedicalRuleResult::isViolation, data -> data, (a, b) -> a)).get(false);

            if (noViolationMedicalRuleResult != null) {
                //  不违规
                return;
            }

            results.add(value.stream().collect(Collectors.toMap(MedicalRuleResult::isViolation, data -> data, (a, b) -> a)).get(true));

        });

        return results;
    }

}
