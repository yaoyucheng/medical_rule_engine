package com.ltyl.medicalrule;

import com.alibaba.cola.exception.ExceptionFactory;
import com.ltyl.domain.gateway.MedicalRuleGateway;
import com.ltyl.domain.medicalrule.MedicalRule;
import com.ltyl.domain.medicalrule.MedicalRuleItemEnum;
import com.ltyl.medicalrule.gatewayimpl.cache.MedicalRuleCacheDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 医疗规则网关实现
 *
 * @author yuchengyao
 */
@Component
public class MedicalRuleGatewayImpl implements MedicalRuleGateway {

    /**
     * 缓存实现
     */
    @Resource
    private MedicalRuleCacheDao medicalRuleCacheDao;

    @Override
    public MedicalRule getMedicalRule(String limitType, String code, String relatedItemCode) {

        MedicalRuleItemEnum medicalRuleItemEnum = MedicalRuleItemEnum.getMedicalRuleItemEnum(limitType);

        switch (medicalRuleItemEnum) {
            case DRUG_DIAGNOSTIC:
                return getExternalMedicalRule(code, relatedItemCode);
            case DRUG_AGE:
                return getAgeMedicalRule(code);
            case DRUG_GENDER:
                return getGenderMedicalRule(code);
            case DRUG_MEASURE:
                return getDrugMeasureMedicalRule(code);
            case DRUG_DURATION:
                return getDurationMedicalRule(code);
            case DRUG_MEDICAL_ORDER:
                return getExternalMedicalRule(code, relatedItemCode);
            case DRUG_DRUG_PROPORTION:
                return getDrugProportionMedicalRule(code);

            case MEDICAL_PROJECT_AGE:
                return getAgeMedicalRule(code);
            case MEDICAL_PROJECT_GENDER:
                return getGenderMedicalRule(code);
            case MEDICAL_PROJECT_DURATION:
                return getDurationMedicalRule(code);
            case MEDICAL_PROJECT_MEDICAL_ORDER:
                return getExternalMedicalRule(code, relatedItemCode);
            case MEDICAL_PROJECT_MEDICAL_PROJECT:
                return getExternalMedicalRule(code, relatedItemCode);
            case MEDICAL_PROJECT_DIAGNOSTIC:
                return getExternalMedicalRule(code, relatedItemCode);
        }
        throw ExceptionFactory.bizException("limitType is error......");
    }

    @Override
    public MedicalRule getMedicalRule(String limitType, String code) {
        return getMedicalRule(limitType, code, null);
    }

    /**
     * @param itemCode
     * @param relatedItemCode
     * @return
     */
    public MedicalRule getExternalMedicalRule(String itemCode, String relatedItemCode) {
        return medicalRuleCacheDao.getExternalMedicalRule(itemCode, relatedItemCode);
    }

    /**
     * 获取 药品_药品_比例  的规则
     *
     * @param code
     * @return
     */
    public MedicalRule getDrugProportionMedicalRule(String code) {
        return medicalRuleCacheDao.getDrugProportionMedicalRule(code);
    }

    /**
     * 获取年龄规则
     *
     * @param code
     * @return
     */
    public MedicalRule getAgeMedicalRule(String code) {
        return medicalRuleCacheDao.getAgeMedicalRule(code);
    }

    /**
     * 获取性别规则
     *
     * @param code
     * @return
     */
    public MedicalRule getGenderMedicalRule(String code) {
        return medicalRuleCacheDao.getGenderMedicalRule(code);
    }

    /**
     * 药品计量规则
     *
     * @param code
     * @return
     */
    public MedicalRule getDrugMeasureMedicalRule(String code) {
        return medicalRuleCacheDao.getDrugMeasureMedicalRule(code);
    }

    /**
     * 获取时长规则
     *
     * @param code
     * @return
     */
    public MedicalRule getDurationMedicalRule(String code) {
        return medicalRuleCacheDao.getDurationMedicalRule(code);
    }


}
