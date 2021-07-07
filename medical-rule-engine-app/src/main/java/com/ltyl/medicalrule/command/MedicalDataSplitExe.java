package com.ltyl.medicalrule.command;

import com.ltyl.domain.medicalrule.MedicalRuleItemEnum;
import com.ltyl.domain.medicalrule.data.*;
import com.ltyl.medicalrule.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 上传参数拆分
 *
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
                            medicalActionBeforeQry.getDrugInformationQryList().toArray(
                                    new DrugInformationQry[medicalActionBeforeQry.getDrugInformationQryList().size()]))
            );

            //  药品_诊断
            medicalDataList.addAll(
                    buildExternalMedicalRuleData(
                            x.getCode(),
                            MedicalRuleItemEnum.DRUG_DIAGNOSTIC,
                            medicalActionBeforeQry.getDiagnosticInformationQryList().toArray(
                                    new DiagnosticInformationQry[medicalActionBeforeQry.getDiagnosticInformationQryList().size()]))
            );


            /**
             *  TODO:药品_医嘱
             * code_code
             * 药品_医嘱
             */

            /**
             * TODO:药品_计量
             * code_measure
             * 药品_计量
             */

            /**
             * code_gender
             * 药品_性别
             */
            medicalDataList.addAll(
                    buildGenderMedicalRuleData(
                            x.getCode(),
                            MedicalRuleItemEnum.DRUG_GENDER,
                            medicalActionBeforeQry.getPatientInformationQry()
                    )
            );

            /**
             * TODO:药品_时长
             * code_duration
             * 药品_时长
             */

            /**
             * drug_age
             * 药品_年龄
             */
            medicalDataList.addAll(
                    buildAgeMedicalRuleData(
                            x.getCode(),
                            MedicalRuleItemEnum.DRUG_AGE,
                            medicalActionBeforeQry.getPatientInformationQry()
                    )
            );

            /**
             * DrugLevelMedicalRule
             * 药品_等级
             */
            medicalDataList.addAll(
                    buildDrugLevelMedicalRuleData(
                            x.getCode(),
                            MedicalRuleItemEnum.DRUG_LEVEL
                    )
            );

            /**
             * DrugChineseMedicalRule
             * 中药
             */
            medicalDataList.addAll(
                    buildDrugChineseMedicalRuleData(
                            x.getCode(),
                            MedicalRuleItemEnum.DRUG_CHINESE
                    )
            );

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

            /**
             * TODO:诊疗项目_诊断
             * code_code
             * 诊疗项目_诊断
             */

            /**
             *  TODO:诊疗项目_医嘱
             * code_code
             * 诊疗项目_医嘱
             */

            /**
             * code_code
             * 诊疗项目_诊疗项目
             */
            medicalDataList.addAll(buildMedicalProjectMedicalRuleData(
                    x.getCode(),
                    MedicalRuleItemEnum.MEDICAL_PROJECT_MEDICAL_PROJECT,
                    medicalActionBeforeQry.getMedicalProjectInformationQryList().toArray(
                            new MedicalProjectInformationQry[medicalActionBeforeQry.getMedicalProjectInformationQryList().size()]))
            );

            /**
             * TODO:诊疗项目_时长
             * code_duration
             * 诊疗项目_时长
             */

            /**
             * TODO:诊疗项目_年龄
             * code_age
             * 诊疗项目_年龄
             */
            medicalDataList.addAll(
                    buildAgeMedicalRuleData(
                            x.getCode(),
                            MedicalRuleItemEnum.MEDICAL_PROJECT_AGE,
                            medicalActionBeforeQry.getPatientInformationQry()
                    )
            );

            /**
             * TODO:诊疗项目_性别
             * code_gender
             * 诊疗项目_性别
             */
            medicalDataList.addAll(
                    buildGenderMedicalRuleData(
                            x.getCode(),
                            MedicalRuleItemEnum.MEDICAL_PROJECT_GENDER,
                            medicalActionBeforeQry.getPatientInformationQry()
                    )
            );
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

        if (diagnosticInformationQryList.length == 0) {
            data.add(buildExternalMedicalRuleData(itemCode, null, medicalRuleItemEnum));
        }else{
            Arrays.asList(diagnosticInformationQryList).forEach(x -> {
                data.add(buildExternalMedicalRuleData(itemCode, x.getCode(), medicalRuleItemEnum));
            });
        }


        return data;
    }

    /**
     * code与年龄
     *
     * @param itemCode
     * @param patientInformationQry
     * @return
     */
    private List<MedicalData> buildAgeMedicalRuleData(
            String itemCode,
            MedicalRuleItemEnum medicalRuleItemEnum,
            PatientInformationQry patientInformationQry) {

        List<MedicalData> data = new ArrayList<>();

        AgeMedicalRuleData ageMedicalRuleData = new AgeMedicalRuleData();

        ageMedicalRuleData.setItemCode(itemCode);
        ageMedicalRuleData.setLimitType(medicalRuleItemEnum.name());
        ageMedicalRuleData.setAge(patientInformationQry.getAge());

        data.add(ageMedicalRuleData);

        return data;
    }

    /**
     * code与诊断
     *
     * @param itemCode
     * @param patientInformationQry
     * @return
     */
    private List<MedicalData> buildGenderMedicalRuleData(
            String itemCode,
            MedicalRuleItemEnum medicalRuleItemEnum,
            PatientInformationQry patientInformationQry) {

        List<MedicalData> data = new ArrayList<>();

        GenderMedicalRuleData genderMedicalRuleData = new GenderMedicalRuleData();

        genderMedicalRuleData.setItemCode(itemCode);
        genderMedicalRuleData.setLimitType(medicalRuleItemEnum.name());
        genderMedicalRuleData.setGender(patientInformationQry.getGender());

        data.add(genderMedicalRuleData);

        return data;
    }


    /**
     * code与项目
     *
     * @param itemCode
     * @param medicalProjectInformationQryList
     * @return
     */
    private List<MedicalData> buildMedicalProjectMedicalRuleData(
            String itemCode,
            MedicalRuleItemEnum medicalRuleItemEnum,
            MedicalProjectInformationQry... medicalProjectInformationQryList) {

        List<MedicalData> data = new ArrayList<>();

        Arrays.asList(medicalProjectInformationQryList).forEach(x -> {
            data.add(buildMedicalProjectMedicalRuleData(itemCode, x.getCode(), medicalRuleItemEnum));
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

    private ExternalMedicalRuleData buildExternalMedicalRuleData(
            String itemCode,
            String relatedItemCode,
            MedicalRuleItemEnum medicalRuleItemEnum) {
        ExternalMedicalRuleData externalMedicalRuleData = new ExternalMedicalRuleData();
        externalMedicalRuleData.setItemCode(itemCode);
        externalMedicalRuleData.setRelatedItemCode(relatedItemCode);
        externalMedicalRuleData.setLimitType(medicalRuleItemEnum.name());
        return externalMedicalRuleData;
    }

    private MedicalProjectMedicalRuleData buildMedicalProjectMedicalRuleData(
            String itemCode,
            String relatedItemCode,
            MedicalRuleItemEnum medicalRuleItemEnum) {

        MedicalProjectMedicalRuleData medicalProjectMedicalRuleData = new MedicalProjectMedicalRuleData();
        ExternalMedicalRuleData externalMedicalRuleData = buildExternalMedicalRuleData(itemCode, relatedItemCode, medicalRuleItemEnum);
        BeanUtils.copyProperties(externalMedicalRuleData, medicalProjectMedicalRuleData);
        return medicalProjectMedicalRuleData;
    }

    /**
     * 药品与等级
     *
     * @param itemCode
     * @return
     */
    private List<MedicalData> buildDrugLevelMedicalRuleData(
            String itemCode,
            MedicalRuleItemEnum medicalRuleItemEnum) {

        List<MedicalData> data = new ArrayList<>();

        DrugLevelMedicalRuleData drugLevelMedicalRuleData = new DrugLevelMedicalRuleData();

        drugLevelMedicalRuleData.setItemCode(itemCode);
        drugLevelMedicalRuleData.setLimitType(medicalRuleItemEnum.name());

        data.add(drugLevelMedicalRuleData);

        return data;
    }


    /**
     * 中药
     *
     * @param itemCode
     * @return
     */
    private List<MedicalData> buildDrugChineseMedicalRuleData(
            String itemCode,
            MedicalRuleItemEnum medicalRuleItemEnum) {

        List<MedicalData> data = new ArrayList<>();

        DrugChineseMedicalRuleData drugChineseMedicalRuleData = new DrugChineseMedicalRuleData();

        drugChineseMedicalRuleData.setItemCode(itemCode);
        drugChineseMedicalRuleData.setLimitType(medicalRuleItemEnum.name());

        data.add(drugChineseMedicalRuleData);

        return data;
    }


}
