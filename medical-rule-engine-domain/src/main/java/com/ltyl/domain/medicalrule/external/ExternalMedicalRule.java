package com.ltyl.domain.medicalrule.external;

import com.alibaba.cola.exception.ExceptionFactory;
import com.ltyl.domain.medicalrule.MedicalRule;
import com.ltyl.domain.medicalrule.MedicalRuleResult;
import com.ltyl.domain.medicalrule.data.ExternalMedicalRuleData;
import com.ltyl.domain.medicalrule.data.MedicalData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 外部医疗规则
 * 药品_诊断
 * 药品_医嘱
 * 诊疗项目_诊断
 * 诊疗项目_医嘱
 * 诊疗项目_诊疗项目
 * <p>
 * 结构： code_code
 *
 * @author yuchengyao
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalMedicalRule extends MedicalRule {

//    //  药品_诊断
//    MedicalRuleItemEnum.DRUG_DIAGNOSTIC,
//    //  药品_医嘱
//    MedicalRuleItemEnum.DRUG_MEDICAL_ORDER,
//    //  诊疗项目_诊断
//    MedicalRuleItemEnum.MEDICAL_PROJECT_DIAGNOSTIC,
//    //  诊疗项目_医嘱
//    MedicalRuleItemEnum.MEDICAL_PROJECT_MEDICAL_ORDER,
//    //  诊疗项目_诊疗项目
//    MedicalRuleItemEnum.MEDICAL_PROJECT_MEDICAL_PROJECT

    private String relatedItemCode;

    @Override
    public MedicalRuleResult dealWithItem(MedicalData medicalData) {

        if (!(medicalData instanceof ExternalMedicalRuleData)) {
            throw ExceptionFactory.bizException("调用异常！");
        }

        return MedicalRuleResult.violation(
                MedicalRuleResult.builder()
                        .itemCode(this.getItemCode())
                        .relatedItemCode(this.getRelatedItemCode())
                        .build());
    }

}
