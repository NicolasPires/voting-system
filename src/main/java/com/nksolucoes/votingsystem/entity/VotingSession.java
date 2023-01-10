package com.nksolucoes.votingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tab_voting_session")
public class VotingSession implements Serializable {@Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_voting_session")
    private Long id;
    @Column(name = "nam_voting_session")
    private String name;
    @Column(name = "dta_creation")
    private LocalDateTime createDate;
    @Column(name = "num_time_duration")
    private Integer timeOfDuration;
    @Column(name = "ind_session_opened")
    private boolean sessionOpened;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cod_schedule")
    private Schedule schedule;

}
