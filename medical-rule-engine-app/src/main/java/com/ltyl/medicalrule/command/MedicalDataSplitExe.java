package com.ltyl.medicalrule.command;

import com.ltyl.domain.medicalrule.data.ExternalMedicalRuleData;
import com.ltyl.domain.medicalrule.data.MedicalData;
import com.ltyl.medicalrule.dto.DiagnosticInformationQry;
import com.ltyl.medicalrule.dto.DrugInformationQry;
import com.ltyl.medicalrule.dto.MedicalActionBeforeQry;
import com.ltyl.medicalrule.dto.MedicalProjectInformationQry;
import org.springframework.stereotype.Component;

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

            //  药品_药品
            medicalDataList.addAll(buildExternalMedicalRuleData(x.getCode(), (DrugInformationQry[]) medicalActionBeforeQry.getDrugInformationQryList().toArray()));

            //  药品_诊断
            medicalDataList.addAll(buildExternalMedicalRuleData(x.getCode(), (DiagnosticInformationQry[]) medicalActionBeforeQry.getDiagnosticInformationQry().toArray()));

            //  TODO:

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
     * @param itemCode
     * @param drugInformationQryList
     * @return
     */
    private List<MedicalData> buildExternalMedicalRuleData(
            String itemCode,
            DrugInformationQry... drugInformationQryList) {

        List<MedicalData> data = new ArrayList<>();

        Arrays.asList(drugInformationQryList).forEach(x -> {
            data.add(buildExternalMedicalRule(itemCode, x.getCode()));
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
            DiagnosticInformationQry... diagnosticInformationQryList) {

        List<MedicalData> data = new ArrayList<>();

        Arrays.asList(diagnosticInformationQryList).forEach(x -> {
            data.add(buildExternalMedicalRule(itemCode, x.getCode()));
        });

        return data;
    }

    private ExternalMedicalRuleData buildExternalMedicalRule(String itemCode, String relatedItemCode) {
        ExternalMedicalRuleData externalMedicalRuleData = new ExternalMedicalRuleData();
        externalMedicalRuleData.setItemCode(itemCode);
        externalMedicalRuleData.setRelatedItemCode(relatedItemCode);

        return externalMedicalRuleData;
    }


}
