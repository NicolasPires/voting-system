package com.nksolucoes.votingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tab_schedule")
public class Schedule implements Serializable {@Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "cod_schedule")
    private Long id;
    @Column(name = "nom_schedule")
    private String name;
    @Column(name = "des_schedule")
    private String description;

}
