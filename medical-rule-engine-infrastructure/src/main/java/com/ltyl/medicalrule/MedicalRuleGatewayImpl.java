package com.ltyl.medicalrule;

import com.alibaba.cola.exception.ExceptionFactory;
import com.ltyl.domain.gateway.MedicalRuleGateway;
import com.ltyl.domain.medicalrule.MedicalRule;
import com.ltyl.domain.medicalrule.MedicalRuleItemEnum;
import com.ltyl.medicalrule.gatewayimpl.cache.MedicalRuleCacheDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 医疗规则网关实现
 *
 * @author yuchengyao
 */
@Slf4j
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
                return medicalRuleCacheDao.getExternalMedicalRule(code, relatedItemCode);
            case DRUG_AGE:
                return medicalRuleCacheDao.getAgeMedicalRule(code);
            case DRUG_GENDER:
                return medicalRuleCacheDao.getGenderMedicalRule(code);
            case DRUG_MEASURE:
                return medicalRuleCacheDao.getDrugMeasureMedicalRule(code);
            case DRUG_DURATION:
                return medicalRuleCacheDao.getDurationMedicalRule(code);
            case DRUG_MEDICAL_ORDER:
                return medicalRuleCacheDao.getExternalMedicalRule(code, relatedItemCode);
            case DRUG_DRUG_PROPORTION:
                return medicalRuleCacheDao.getDrugProportionMedicalRule(code);
            case DRUG_LEVEL:
                return medicalRuleCacheDao.getDrugLevelMedicalRule(code);
            case DRUG_CHINESE:
                return medicalRuleCacheDao.getDrugChineseMedicalRule(code);

            case MEDICAL_PROJECT_AGE:
                return medicalRuleCacheDao.getAgeMedicalRule(code);
            case MEDICAL_PROJECT_GENDER:
                return medicalRuleCacheDao.getGenderMedicalRule(code);
            case MEDICAL_PROJECT_DURATION:
                return medicalRuleCacheDao.getDurationMedicalRule(code);
            case MEDICAL_PROJECT_MEDICAL_ORDER:
                return medicalRuleCacheDao.getExternalMedicalRule(code, relatedItemCode);
            case MEDICAL_PROJECT_MEDICAL_PROJECT:
                return medicalRuleCacheDao.getExternalMedicalRule(code, relatedItemCode);
            case MEDICAL_PROJECT_DIAGNOSTIC:
                return medicalRuleCacheDao.getExternalMedicalRule(code, relatedItemCode);
            default:
                log.info("调用异常......");
        }
        throw ExceptionFactory.bizException("limitType is error......");
    }

    @Override
    public MedicalRule getMedicalRule(String limitType, String code) {
        return getMedicalRule(limitType, code, null);
    }

}
