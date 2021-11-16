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
@Table(name = "cmn_user")
public class CmnUser implements Serializable {

  private static final long serialVersionUID = 8899850934724972431L;

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "esntl_id")
  private String esntlId;

  @Column(name = "user_id")
  private String userId;

  @Column(name = "user_nm")
  private String userNm;

  @Column(name = "user_pw")
  private String userPw;

  @Column(name = "author_cd")
  private String authorCd;

  @Column(name = "pw_updt_yn")
  private String pwUpdtYn;

  @Column(name = "pw_updt_dt")
  private Timestamp pwUpdtDt;

  @Column(name = "pw_failr_cnt")
  private long pwFailrCnt;

  @Column(name = "last_conect_dt")
  private Timestamp lastConectDt;

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
