package com.pix.dtos;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserPixDTO {

    private UUID id;
    private String typeKey;

    @javax.validation.constraints.NotEmpty(message = "A chave é obrigatória")
    private String keyInformation;

    @NotBlank(message = "O tipo de conta é obrigatório")
    private String accountType;

    @NotNull(message = "O número da agência é obrigatório")
    private Integer agencyNumber;

    @NotNull(message = "O número da conta é obrigatório")
    private Integer accountNumber;

    @NotBlank(message = "O nome do correntista é obrigatório")
    @Size(max = 30, message = "O valor do nome do correntista não pode ultrapassar 30 caracteres")
    private String holderName;

    @Size(max = 45, message = "O valor do sobrenome do correntista não pode ultrapassar 45 caracteres")
    private String holderLastName;
    private LocalDateTime insertTime;
    private LocalDateTime inactivateTime;

}
