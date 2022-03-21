package com.pix.model;

import com.pix.dtos.UserPixDTO;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity(name = "user_pix")
@NoArgsConstructor
@AllArgsConstructor
public class UserPixEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String typeKey;
    private String keyInformation;
    private String accountType;
    private Integer agencyNumber;
    private Integer accountNumber;
    private String holderName;
    private String holderLastName;
    private LocalDateTime insertTime;
    private LocalDateTime inactivateTime;

    public static UserPixEntity build(UserPixDTO userPixDTO, UserPixEntity userPixEntity) {
        userPixEntity.setAccountNumber(userPixDTO.getAccountNumber());
        userPixEntity.setAgencyNumber(userPixDTO.getAgencyNumber());
        userPixEntity.setHolderLastName(userPixDTO.getHolderLastName());
        userPixEntity.setHolderName(userPixDTO.getHolderName());
        userPixEntity.setInactivateTime(userPixDTO.getInactivateTime() != null ? userPixDTO.getInactivateTime() : null);
        return userPixEntity;
    }

}
