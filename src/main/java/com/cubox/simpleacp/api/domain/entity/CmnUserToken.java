package com.cubox.simpleacp.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "cmn_user_token")
public class CmnUserToken implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "cmn_user_esntl_id")
  private CmnUser cmnUser;

  @Column(name = "token")
  private String token;

  @Column(name = "expired_dt")
  private Instant expiredDt;

  @Column(name = "refresh_token")
  private String refreshToken;

  @Column(name = "refresh_expired_dt")
  private Instant refreshExpiredDt;

  @Column(name = "regist_dt")
  private Instant registDt;

  @Column(name = "updt_dt")
  private Instant updtDt;

}
