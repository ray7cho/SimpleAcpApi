package com.cubox.simpleacp.api.domain.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "cmn_menu")
public class CmnMenu implements Serializable {

  private static final long serialVersionUID = 2846618213144789940L;

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "menu_cd")
  private String menuCd;

  @Column(name = "menu_cl_cd")
  private String menuClCd;

  @Column(name = "menu_nm")
  private String menuNm;

  @Column(name = "menu_url")
  private String menuUrl;

  @Column(name = "sort_ordr")
  private long sortOrdr;

  @Column(name = "use_yn")
  private String useYn;

  @Column(name = "regist_id")
  private String registId;

  @Column(name = "regist_dt")
  private Timestamp registDt;

  @Column(name = "updt_id")
  private String updtId;

  @Column(name = "updt_dt")
  private Timestamp updtDt;

}
