package com.ltyl.domain.medicalrule.data;

import com.ltyl.domain.medicalrule.internal.GenderMedicalRule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuchengyao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenderMedicalRuleData extends MedicalData {

    private Class ruleClass = GenderMedicalRule.class;

    private String gender;

}
