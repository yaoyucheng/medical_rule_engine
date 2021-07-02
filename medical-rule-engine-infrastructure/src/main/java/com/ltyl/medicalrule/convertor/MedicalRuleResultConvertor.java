package com.ltyl.medicalrule.convertor;

import com.ltyl.domain.medicalrule.MedicalRuleResult;
import com.ltyl.medicalrule.dto.clientobject.MedicalActionBeforeCO;
import com.ltyl.utils.CollectionCopyUtil;

import java.util.List;

/**
 * @author yuchengyao
 */
public class MedicalRuleResultConvertor {

    public static List<MedicalActionBeforeCO> toMedicalActionBeforeCOList(List<MedicalRuleResult> medicalRuleResultList) {

        return CollectionCopyUtil.copyList(medicalRuleResultList, MedicalActionBeforeCO.class);

    }
}
