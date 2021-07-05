package com.ltyl.domain.medicalrule.data;

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
public class AgeMedicalRuleData extends MedicalData {

    private Integer age;
}
