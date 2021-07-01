package com.ltyl.medicalrule.dto;

import lombok.Data;

/**
 * 患者信息
 *
 * @author yuchengyao
 */
@Data
public class PatientInformationQry {

    /**
     * 身份证号
     */
    private String identityNumber;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 住院唯一标识
     */
    private String uniqueIdentification;

}
