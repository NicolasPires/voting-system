package com.nksolucoes.votingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tab_vote")
public class Vote implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_vote", nullable = false)
    private Long id;
    @Column(name = "des_vote")
    private String descriptionVote;
    @ManyToOne
    @JoinColumn(name = "cod_associate")
    private Associate associate;

    @OneToOne
    private Schedule schedule;


}
