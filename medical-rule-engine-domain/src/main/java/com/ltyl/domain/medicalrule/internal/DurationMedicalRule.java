package com.ltyl.domain.medicalrule.internal;

import com.ltyl.domain.medicalrule.MedicalRuleResult;
import com.ltyl.domain.medicalrule.data.MedicalData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 时长规则
 * 药品_时长
 * 诊疗项目_时长
 *
 * @author yuchengyao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DurationMedicalRule extends InternalMedicalRule {

    //  DRUG_DURATION
    //  MEDICAL_PROJECT_DURATION

    //  TODO:属性
    private String duration;

    private String timeUnit;

    @Override
    public MedicalRuleResult dealWithItem( MedicalData medicalData) {
        return null;
    }

}
