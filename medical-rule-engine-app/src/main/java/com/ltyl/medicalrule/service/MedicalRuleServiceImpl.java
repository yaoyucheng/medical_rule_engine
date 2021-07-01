package com.ltyl.medicalrule.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.ltyl.domain.medicalrule.MedicalRuleResult;
import com.ltyl.domain.medicalrule.data.MedicalData;
import com.ltyl.medicalrule.api.MedicalRuleService;
import com.ltyl.medicalrule.command.MedicalDataSplitExe;
import com.ltyl.medicalrule.command.MedicalRuleMatchExe;
import com.ltyl.medicalrule.command.MedicalRuleResultDealWithExe;
import com.ltyl.medicalrule.dto.MedicalActionBeforeQry;
import com.ltyl.medicalrule.dto.clientobject.MedicalActionBeforeCO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuchengyao
 */
@Service
@CatchAndLog
public class MedicalRuleServiceImpl implements MedicalRuleService {

    @Resource
    private MedicalDataSplitExe medicalDataSplitExe;

    @Resource
    private MedicalRuleMatchExe medicalRuleMatchExe;

    @Resource
    private MedicalRuleResultDealWithExe medicalRuleResultDealWithExe;

    @Override
    public MultiResponse<MedicalActionBeforeCO> beforeMedicalAction(MedicalActionBeforeQry medicalActionBeforeQry) {

        List<MedicalData> medicalDataList = medicalDataSplitExe.execute(medicalActionBeforeQry);

        List<MedicalRuleResult> medicalRuleResultList = medicalRuleMatchExe.execute(medicalDataList);

        List<MedicalRuleResult> execute = medicalRuleResultDealWithExe.execute(medicalRuleResultList);

        //  TODO:转换结果
        return null;
    }
}
