package com.ltyl.medicalrule.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 事前请求参数
 *
 * @author yuchengyao
 */
@Data
public class MedicalActionBeforeQry {

    /**
     * 患者信息
     */
    @NotNull
    private PatientInformationQry patientInformationQry;

    /***
     * 医嘱信息
     */
    private List<MedicalOrderInformationQry> medicalOrderInformationQryList;

    /**
     * 诊断信息
     */
    private List<DiagnosticInformationQry> diagnosticInformationQryList;

    /**
     * 药品信息
     */
    private List<DrugInformationQry> drugInformationQryList;

    /**
     * 诊疗项目信息
     */
    private List<MedicalProjectInformationQry> medicalProjectInformationQryList;

    /**
     * 耗材信息
     */
    private List<ConsumablesInformationQry> consumablesInformationQryList;
}

