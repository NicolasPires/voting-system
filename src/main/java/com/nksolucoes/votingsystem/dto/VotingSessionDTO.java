package com.nksolucoes.votingsystem.dto;

import com.nksolucoes.votingsystem.entity.Schedule;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class VotingSessionDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Integer timeOfDuration;
    private boolean sessionOpened;
    private Schedule schedule;

}
