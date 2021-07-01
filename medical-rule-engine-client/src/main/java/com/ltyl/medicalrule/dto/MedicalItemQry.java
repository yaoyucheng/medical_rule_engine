package com.ltyl.medicalrule.dto;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Query;
import lombok.Data;

/**
 * @author yuchengyao
 */
@Data
public class MedicalItemQry extends Query {

    private String code;

    private String name;
}
