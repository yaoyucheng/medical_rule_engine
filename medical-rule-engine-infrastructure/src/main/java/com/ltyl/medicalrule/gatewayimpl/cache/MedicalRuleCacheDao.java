package com.ltyl.medicalrule.gatewayimpl.cache;

import cn.hutool.core.thread.ThreadUtil;
import com.ltyl.domain.medicalrule.MedicalRule;
import com.ltyl.domain.medicalrule.MedicalRuleDistributionHandler;
import com.ltyl.domain.medicalrule.external.DrugProportionMedicalRule;
import com.ltyl.domain.medicalrule.external.ExternalMedicalRule;
import com.ltyl.domain.medicalrule.external.MedicalProjectMedicalRule;
import com.ltyl.domain.medicalrule.internal.*;
import com.ltyl.medicalrule.gatewayimpl.database.dao.intf.MedicalRuleDao;
import com.ltyl.medicalrule.gatewayimpl.database.entity.MedicalRuleDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * @author yuchengyao
 */
@Slf4j
@Repository
public class MedicalRuleCacheDao implements InitializingBean {

    /**
     * code 与 code 相关
     */
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, ExternalMedicalRule>> externalMedicalRuleCache = new ConcurrentHashMap<>();

    /**
     * 药品计量
     */
    private static ConcurrentHashMap<String, DrugProportionMedicalRule> drugProportionMedicalRuleCache = new ConcurrentHashMap<>();

    /**
     * 年龄规则
     */
    private static ConcurrentHashMap<String, AgeMedicalRule> ageMedicalRuleCache = new ConcurrentHashMap<>();

    /**
     * 性别规则
     */
    private static ConcurrentHashMap<String, GenderMedicalRule> genderMedicalRuleCache = new ConcurrentHashMap();

    /**
     * 药品计量规则
     */
    private static ConcurrentHashMap<String, DrugMeasureMedicalRule> drugMeasureMedicalRuleCache = new ConcurrentHashMap<>();

    /**
     * 时长规则
     */
    private static ConcurrentHashMap<String, DurationMedicalRule> durationMedicalRuleCache = new ConcurrentHashMap<>();

    /**
     * 药品等级
     */
    private static ConcurrentHashMap<String, DrugLevelMedicalRule> drugLevelMedicalRuleCache = new ConcurrentHashMap<>();

    /**
     * 中药
     */
    private static ConcurrentHashMap<String, DrugChineseMedicalRule> drugChineseMedicalRuleCache = new ConcurrentHashMap<>();

    /**
     * --------------------------------------------------------------------------------------------
     */

    /**
     * 初始化线程池，同时执行500个线程
     */
    private static ExecutorService executor = ThreadUtil.newExecutor(50);

    /**
     * 多线程处理code 数量
     */
    private static Integer executorCount = 100;

    /**
     * 数据库实现
     */
    @Resource
    private MedicalRuleDao medicalRuleDao;

    /**
     * code 与 code 相关
     *
     * @param code
     * @return
     */
    public MedicalRule getExternalMedicalRule(String code, String relatedItemCode) {

        if (CollectionUtils.isEmpty(externalMedicalRuleCache.get(code))) {
            return new ExternalMedicalRule();
        }

        if (StringUtils.isEmpty(externalMedicalRuleCache.get(code)) || StringUtils.isEmpty(relatedItemCode)) {

            ExternalMedicalRule receiptMessageExternalMedicalRule = externalMedicalRuleCache.get(code).get(externalMedicalRuleCache.get(code).keySet().stream().collect(Collectors.toList()).get(0));

            ExternalMedicalRule externalMedicalRule = new ExternalMedicalRule();
            externalMedicalRule.setItemCode(code);
            externalMedicalRule.setReceiptMessage(receiptMessageExternalMedicalRule.getReceiptMessage());

            return externalMedicalRule;
        }
        return externalMedicalRuleCache.get(code).get(relatedItemCode);
    }

    /**
     * 获取 药品_药品_比例  的规则
     *
     * @param code
     * @return
     */
    public MedicalRule getDrugProportionMedicalRule(String code) {

        return StringUtils.isEmpty(drugProportionMedicalRuleCache.get(code)) ?
                new DrugProportionMedicalRule() : drugProportionMedicalRuleCache.get(code);
    }

    /**
     * 获取年龄规则
     *
     * @param code
     * @return
     */
    public AgeMedicalRule getAgeMedicalRule(String code) {
        return StringUtils.isEmpty(ageMedicalRuleCache.get(code)) ?
                new AgeMedicalRule() : ageMedicalRuleCache.get(code);
    }

    /**
     * 获取性别规则
     *
     * @param code
     * @return
     */
    public GenderMedicalRule getGenderMedicalRule(String code) {

        return StringUtils.isEmpty(genderMedicalRuleCache.get(code)) ?
                new GenderMedicalRule() : genderMedicalRuleCache.get(code);
    }

    /**
     * 药品计量规则
     *
     * @param code
     * @return
     */
    public DrugMeasureMedicalRule getDrugMeasureMedicalRule(String code) {
        return StringUtils.isEmpty(drugMeasureMedicalRuleCache.get(code)) ?
                new DrugMeasureMedicalRule() : drugMeasureMedicalRuleCache.get(code);
    }

    /**
     * 获取时长规则
     *
     * @param code
     * @return
     */
    public DurationMedicalRule getDurationMedicalRule(String code) {
        return StringUtils.isEmpty(durationMedicalRuleCache.get(code)) ?
                new DurationMedicalRule() : durationMedicalRuleCache.get(code);
    }

    /**
     * 获取药品等级
     *
     * @param code
     * @return
     */
    public DrugLevelMedicalRule getDrugLevelMedicalRule(String code) {
        return StringUtils.isEmpty(drugLevelMedicalRuleCache.get(code)) ?
                new DrugLevelMedicalRule() : drugLevelMedicalRuleCache.get(code);
    }

    /**
     * 获取药品等级
     *
     * @param code
     * @return
     */
    public DrugChineseMedicalRule getDrugChineseMedicalRule(String code) {
        return StringUtils.isEmpty(drugChineseMedicalRuleCache.get(code)) ?
                new DrugChineseMedicalRule() : drugChineseMedicalRuleCache.get(code);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        initCache();
    }

    /**
     * 初始化缓存
     */
    private void initCache() {
        Collection<String> medicalRuleItemCodeCode = medicalRuleDao.getMedicalRuleItemCodes();

        if (CollectionUtils.isEmpty(medicalRuleItemCodeCode)) {
            log.info("暂时无可以加载的数据");
            return;
        }

        ArrayList<String> medicalRuleItemCodeCodeList = new ArrayList<>(medicalRuleItemCodeCode);

        for (int i = 0; i <= (medicalRuleItemCodeCodeList.size() / executorCount); i++) {
            List<String> subList = new ArrayList<>();
            if (i == (medicalRuleItemCodeCodeList.size() / executorCount)) {
                subList = medicalRuleItemCodeCodeList.subList(i * executorCount, medicalRuleItemCodeCodeList.size());
            } else {
                subList = medicalRuleItemCodeCodeList.subList(i * executorCount, (i * executorCount) + executorCount);
            }
            executor.submit(new BuildCache(subList));
        }

        executor.shutdown();
    }

    class BuildCache implements Runnable {

        private List<String> itemCodeList;

        BuildCache(List<String> itemCodeList) {
            this.itemCodeList = itemCodeList;
        }

        @Override
        public void run() {
            dealWith(itemCodeList);
        }
    }

    private void dealWith(List<String> itemCodeList) {

        Collection<MedicalRuleDO> medicalRuleItemByItemCode = medicalRuleDao.getMedicalRuleItemByItemCode(itemCodeList);

        medicalRuleItemByItemCode.forEach(medicalRuleDO -> {

            MedicalRule medicalRule = MedicalRuleDistributionHandler.handler(medicalRuleDO.getLimitType());

            buildMedicalRule(medicalRule, medicalRuleDO);
        });
    }

    private void buildMedicalRule(MedicalRule medicalRule, MedicalRuleDO medicalRuleDO) {

        if (medicalRule instanceof MedicalProjectMedicalRule) {
            buildMedicalProjectMedicalRuleCache(medicalRuleDO);
            return;
        }

        if (medicalRule instanceof ExternalMedicalRule) {
            buildExternalMedicalRuleCache(medicalRuleDO);
            return;
        }

        if (medicalRule instanceof DrugProportionMedicalRule) {
            buildDrugProportionMedicalRuleCache(medicalRuleDO);
            return;
        }

        if (medicalRule instanceof AgeMedicalRule) {
            buildAgeMedicalRuleCache(medicalRuleDO);
            return;
        }

        if (medicalRule instanceof DurationMedicalRule) {
            buildDurationMedicalRuleCache(medicalRuleDO);
            return;
        }

        if (medicalRule instanceof DrugMeasureMedicalRule) {
            buildDrugMeasureMedicalRuleCache(medicalRuleDO);
            return;
        }
        if (medicalRule instanceof GenderMedicalRule) {
            buildGenderMedicalRuleCache(medicalRuleDO);
            return;
        }

        if (medicalRule instanceof DrugLevelMedicalRule) {
            buildDrugLevelMedicalRuleCache(medicalRuleDO);
            return;
        }

        if (medicalRule instanceof DrugChineseMedicalRule) {
            buildDrugChineseMedicalRuleCache(medicalRuleDO);
        }

    }

    private void buildExternalMedicalRuleCache(MedicalRuleDO medicalRuleDO) {
        if (externalMedicalRuleCache.containsKey(medicalRuleDO.getItemCode())) {
            ExternalMedicalRule externalMedicalRule = new ExternalMedicalRule();
            BeanUtils.copyProperties(medicalRuleDO, externalMedicalRule);
            externalMedicalRuleCache.get(medicalRuleDO.getItemCode()).put(medicalRuleDO.getRelatedItemCode(), externalMedicalRule);
            return;
        }
        ExternalMedicalRule externalMedicalRule = new ExternalMedicalRule();
        BeanUtils.copyProperties(medicalRuleDO, externalMedicalRule);

        ConcurrentHashMap<String, ExternalMedicalRule> data = new ConcurrentHashMap<>();
        data.put(medicalRuleDO.getRelatedItemCode(), externalMedicalRule);
        externalMedicalRuleCache.put(medicalRuleDO.getItemCode(), data);
    }


    private void buildMedicalProjectMedicalRuleCache(MedicalRuleDO medicalRuleDO) {
        if (externalMedicalRuleCache.containsKey(medicalRuleDO.getItemCode())) {
            MedicalProjectMedicalRule medicalProjectMedicalRule = new MedicalProjectMedicalRule();
            BeanUtils.copyProperties(medicalRuleDO, medicalProjectMedicalRule);
            externalMedicalRuleCache.get(medicalRuleDO.getItemCode()).put(medicalRuleDO.getRelatedItemCode(), medicalProjectMedicalRule);
            return;
        }
        MedicalProjectMedicalRule medicalProjectMedicalRule = new MedicalProjectMedicalRule();
        BeanUtils.copyProperties(medicalRuleDO, medicalProjectMedicalRule);

        ConcurrentHashMap<String, ExternalMedicalRule> data = new ConcurrentHashMap<>();
        data.put(medicalRuleDO.getRelatedItemCode(), medicalProjectMedicalRule);
        externalMedicalRuleCache.put(medicalRuleDO.getItemCode(), data);
    }


    private void buildDrugProportionMedicalRuleCache(MedicalRuleDO medicalRuleDO) {

        DrugProportionMedicalRule drugProportionMedicalRule = new DrugProportionMedicalRule();
        BeanUtils.copyProperties(medicalRuleDO, drugProportionMedicalRule);

        drugProportionMedicalRuleCache.put(medicalRuleDO.getItemCode(), drugProportionMedicalRule);

    }

    private void buildAgeMedicalRuleCache(MedicalRuleDO medicalRuleDO) {

        AgeMedicalRule ageMedicalRule = new AgeMedicalRule();
        BeanUtils.copyProperties(medicalRuleDO, ageMedicalRule);

        ageMedicalRuleCache.put(medicalRuleDO.getItemCode(), ageMedicalRule);

    }

    private void buildDrugMeasureMedicalRuleCache(MedicalRuleDO medicalRuleDO) {

        DrugMeasureMedicalRule drugMeasureMedicalRule = new DrugMeasureMedicalRule();
        BeanUtils.copyProperties(medicalRuleDO, drugMeasureMedicalRule);

        drugMeasureMedicalRuleCache.put(medicalRuleDO.getItemCode(), drugMeasureMedicalRule);

    }

    private void buildDurationMedicalRuleCache(MedicalRuleDO medicalRuleDO) {

        DurationMedicalRule durationMedicalRule = new DurationMedicalRule();
        BeanUtils.copyProperties(medicalRuleDO, durationMedicalRule);

        durationMedicalRuleCache.put(medicalRuleDO.getItemCode(), durationMedicalRule);

    }

    private void buildGenderMedicalRuleCache(MedicalRuleDO medicalRuleDO) {

        GenderMedicalRule genderMedicalRule = new GenderMedicalRule();
        BeanUtils.copyProperties(medicalRuleDO, genderMedicalRule);

        genderMedicalRuleCache.put(medicalRuleDO.getItemCode(), genderMedicalRule);
    }


    private void buildDrugLevelMedicalRuleCache(MedicalRuleDO medicalRuleDO) {

        DrugLevelMedicalRule drugLevelMedicalRule = new DrugLevelMedicalRule();
        BeanUtils.copyProperties(medicalRuleDO, drugLevelMedicalRule);

        drugLevelMedicalRuleCache.put(medicalRuleDO.getItemCode(), drugLevelMedicalRule);
    }

    private void buildDrugChineseMedicalRuleCache(MedicalRuleDO medicalRuleDO) {

        DrugChineseMedicalRule drugChineseMedicalRule = new DrugChineseMedicalRule();
        BeanUtils.copyProperties(medicalRuleDO, drugChineseMedicalRule);

        drugChineseMedicalRuleCache.put(medicalRuleDO.getItemCode(), drugChineseMedicalRule);
    }


}

