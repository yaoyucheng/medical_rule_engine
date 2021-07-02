package com.ltyl.medicalrule.command;

import com.ltyl.domain.medicalrule.MedicalRuleItemEnum;
import com.ltyl.domain.medicalrule.data.DrugProportionMedicalRuleData;
import com.ltyl.domain.medicalrule.data.ExternalMedicalRuleData;
import com.ltyl.domain.medicalrule.data.MedicalData;
import com.ltyl.medicalrule.dto.DiagnosticInformationQry;
import com.ltyl.medicalrule.dto.DrugInformationQry;
import com.ltyl.medicalrule.dto.MedicalActionBeforeQry;
import com.ltyl.medicalrule.dto.MedicalProjectInformationQry;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuchengyao
 */
@Component
public class MedicalDataSplitExe {

    /**
     * 拆分上传参数
     *
     * @param medicalActionBeforeQry
     * @return
     */
    public List<MedicalData> execute(MedicalActionBeforeQry medicalActionBeforeQry) {

        List<MedicalData> medicalDataList = new ArrayList<>();

        medicalDataList.addAll(buildDrugInformationData(medicalActionBeforeQry));

        medicalDataList.addAll(buildMedicalProjectInformationData(medicalActionBeforeQry));

        return medicalDataList;
    }

    /**
     * 构建药品信息数据
     *
     * @param medicalActionBeforeQry
     * @return
     */
    private List<MedicalData> buildDrugInformationData(MedicalActionBeforeQry medicalActionBeforeQry) {
        List<MedicalData> medicalDataList = new ArrayList<>();

        //  药品
        List<DrugInformationQry> drugInformationQryList = medicalActionBeforeQry.getDrugInformationQryList();

        drugInformationQryList.forEach(x -> {

            //  药品_药品_比例
            medicalDataList.addAll(
                    buildExternalMedicalRuleData(
                            x,
                            MedicalRuleItemEnum.DRUG_DRUG_PROPORTION,
                            medicalActionBeforeQry.getDrugInformationQryList().toArray(new DrugInformationQry[medicalActionBeforeQry.getDrugInformationQryList().size()])));

            //  药品_诊断
            medicalDataList.addAll(
                    buildExternalMedicalRuleData(
                            x.getCode(),
                            MedicalRuleItemEnum.DRUG_DIAGNOSTIC,
                            medicalActionBeforeQry.getDiagnosticInformationQryList().toArray(new DiagnosticInformationQry[medicalActionBeforeQry.getDiagnosticInformationQryList().size()])));

            //  TODO:其他实现

            //  药品_
        });

        return medicalDataList;
    }

    /**
     * 构建诊疗项目数据
     *
     * @param medicalActionBeforeQry
     * @return
     */
    private List<MedicalData> buildMedicalProjectInformationData(MedicalActionBeforeQry medicalActionBeforeQry) {
        List<MedicalData> medicalDataList = new ArrayList<>();

        //  诊疗项目
        List<MedicalProjectInformationQry> medicalProjectInformationQryList = medicalActionBeforeQry.getMedicalProjectInformationQryList();

        medicalProjectInformationQryList.forEach(x -> {

        });

        return medicalDataList;
    }


    /**
     * code与药品
     *
     * @param drugInformationQry
     * @param drugInformationQryList
     * @return
     */
    private List<MedicalData> buildExternalMedicalRuleData(
            DrugInformationQry drugInformationQry,
            MedicalRuleItemEnum medicalRuleItemEnum,
            DrugInformationQry... drugInformationQryList) {

        List<MedicalData> data = new ArrayList<>();

        Arrays.asList(drugInformationQryList).forEach(x -> {
            if (StringUtils.isEmpty(drugInformationQry.getMeasure())
                    || StringUtils.isEmpty(x.getMeasure())) {
                return;
            }
            data.add(buildDrugProportionMedicalRuleData(drugInformationQry, x, medicalRuleItemEnum));
        });

        return data;
    }

    /**
     * code与诊断
     *
     * @param itemCode
     * @param diagnosticInformationQryList
     * @return
     */
    private List<MedicalData> buildExternalMedicalRuleData(
            String itemCode,
            MedicalRuleItemEnum medicalRuleItemEnum,
            DiagnosticInformationQry... diagnosticInformationQryList) {

        List<MedicalData> data = new ArrayList<>();

        Arrays.asList(diagnosticInformationQryList).forEach(x -> {
            data.add(buildExternalMedicalRule(itemCode, x.getCode(), medicalRuleItemEnum));
        });

        return data;
    }

    private DrugProportionMedicalRuleData buildDrugProportionMedicalRuleData(
            DrugInformationQry drugInformationQry,
            DrugInformationQry relatedDrugInformationQry,
            MedicalRuleItemEnum medicalRuleItemEnum) {

        DrugProportionMedicalRuleData drugProportionMedicalRuleData = new DrugProportionMedicalRuleData();
        drugProportionMedicalRuleData.setItemCode(drugInformationQry.getCode());
        drugProportionMedicalRuleData.setItemMeasure(drugInformationQry.getMeasure());
        drugProportionMedicalRuleData.setRelatedItemCode(relatedDrugInformationQry.getCode());
        drugProportionMedicalRuleData.setRelatedItemMeasure(relatedDrugInformationQry.getMeasure());

        drugProportionMedicalRuleData.setLimitType(medicalRuleItemEnum.name());
        return drugProportionMedicalRuleData;
    }

    private ExternalMedicalRuleData buildExternalMedicalRule(
            String itemCode,
            String relatedItemCode,
            MedicalRuleItemEnum medicalRuleItemEnum) {
        ExternalMedicalRuleData externalMedicalRuleData = new ExternalMedicalRuleData();
        externalMedicalRuleData.setItemCode(itemCode);
        externalMedicalRuleData.setRelatedItemCode(relatedItemCode);
        externalMedicalRuleData.setLimitType(medicalRuleItemEnum.name());
        return externalMedicalRuleData;
    }


}
