package com.ltyl.medicalrule.gatewayimpl.database.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import java.io.Serializable;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * MedicalRuleDO: 数据映射实体定义
 *
 * @author Powered By Fluent Mybatis
 */
@Data
@Accessors(
    chain = true
)
@EqualsAndHashCode(
    callSuper = false
)
@FluentMybatis(
    table = "medical_rule",
    suffix = "DO"
)
public class MedicalRuleDO extends RichEntity {
  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId(
      value = "id",
      auto = false
  )
  private String id;

  /**
   * 创建时间
   */
  @TableField("create_time")
  private Date createTime;

  /**
   * 项目code
   */
  @TableField("item_code")
  private String itemCode;

  /**
   * 项目计量
   */
  @TableField("item_measure")
  private BigDecimal itemMeasure;

  /**
   * 项目与相关项目的比例下限
   */
  @TableField("item_related_proportion")
  private BigDecimal itemRelatedProportion;

  /**
   * 限制类型   药品与诊断，药品以药品比例，药品与医嘱等等
   */
  @TableField("limit_type")
  private String limitType;

  /**
   * 限制性用药
   */
  @TableField("receipt_message")
  private String receiptMessage;

  /**
   * 相关项目code
   */
  @TableField("related_item_code")
  private String relatedItemCode;

  /**
   * 更新时间
   */
  @TableField("update_time")
  private Date updateTime;

  @Override
  public Serializable findPk() {
    return this.id;
  }

  @Override
  public final Class<? extends IEntity> entityClass() {
    return MedicalRuleDO.class;
  }
}
