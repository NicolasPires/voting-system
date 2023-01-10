package com.nksolucoes.votingsystem.dto;

import com.nksolucoes.votingsystem.entity.VotingSession;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class ScheduleDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "Preenchimento Obrigatório.")
    @Length(min = 3, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String name;
    private String description;

}
