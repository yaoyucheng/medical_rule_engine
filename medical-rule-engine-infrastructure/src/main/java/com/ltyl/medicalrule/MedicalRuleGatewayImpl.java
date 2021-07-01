package com.ltyl.medicalrule;

import com.ltyl.domain.gateway.MedicalRuleGateway;
import com.ltyl.domain.medicalrule.MedicalRule;
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
    public MedicalRule getExternalMedicalRule(String itemCode, String relatedItemCode) {
        return medicalRuleCacheDao.getExternalMedicalRule(itemCode, relatedItemCode);
    }

    /**
     * 获取 药品_药品_比例  的规则
     *
     * @param code
     * @return
     */
    @Override
    public MedicalRule getDrugProportionMedicalRule(String code) {
        return medicalRuleCacheDao.getDrugProportionMedicalRule(code);
    }

    /**
     * 获取年龄规则
     *
     * @param code
     * @return
     */
    @Override
    public MedicalRule getAgeMedicalRule(String code) {
        return medicalRuleCacheDao.getAgeMedicalRule(code);
    }

    /**
     * 获取性别规则
     *
     * @param code
     * @return
     */
    @Override
    public MedicalRule getGenderMedicalRule(String code) {
        return medicalRuleCacheDao.getGenderMedicalRule(code);
    }

    /**
     * 药品计量规则
     *
     * @param code
     * @return
     */
    @Override
    public MedicalRule getDrugMeasureMedicalRule(String code) {
        return medicalRuleCacheDao.getDrugMeasureMedicalRule(code);
    }

    /**
     * 获取时长规则
     *
     * @param code
     * @return
     */
    @Override
    public MedicalRule getDurationMedicalRule(String code) {
        return medicalRuleCacheDao.getDurationMedicalRule(code);
    }


}
