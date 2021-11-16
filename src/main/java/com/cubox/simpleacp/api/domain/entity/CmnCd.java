package com.cubox.simpleacp.api.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cmn_cd")
public class CmnCd implements Serializable {

  private static final long serialVersionUID = 475076185755099509L;

  @EmbeddedId
  private CmnCdId cmnCdId;

//  @Column(name = "esntl_cd")
//  private String esntlCd;

  @Column(name = "cd_nm")
  private String cdNm;

  @Column(name = "cd_desc")
  private String cdDesc;

  @Column(name = "cd_dp")
  private long cdDp;

//  @Column(name = "upper_cd")
//  private String upperCd;

  @Column(name = "use_yn")
  private String useYn;

  @Column(name = "sort_ordr")
  private long sortOrdr;

  @Column(name = "regist_id")
  private String registId;

  @Column(name = "regist_dt")
  private Timestamp registDt;

  @Column(name = "updt_id")
  private String updtId;

  @Column(name = "updt_dt")
  private Timestamp updtDt;


}
