package com.cubox.simpleacp.api.domain.entity;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Data
@NoArgsConstructor
@Embeddable
public class CmnCdId implements Serializable {

    private static final long serialVersionUID = -364562007647720925L;

    @Column(name = "esntl_cd")
    private String esntlCd;

    @Column(name = "upper_cd")
    private String upperCd;

}
