package com.github.CodeNekomancer.OADA_Backend.model.InfrastructureComp;

import com.github.CodeNekomancer.OADA_Backend.model.CelestialBody.CelestialBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class InfrastructureComp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long infrastructure_comp_ID;

    private Integer mmLvl; // metal mine
    private Integer msLvl; // metal storage
    private Integer cmLvl; // cristal mine
    private Integer csLvl; // cristal storage
    private Integer dmLvl; // duty mine
    private Integer dsLvl; // duty storage
    private Integer spLvl; // solar plant
    private Integer frLvl; // fusion reactor
    private Integer roLvl; // robots
    private Integer haLvl; // hangar
    private Integer rlLvl; // research lab
    private Integer adLvl; // alliance depot
    private Integer slLvl; // silo
    private Integer nnLvl; // nano
    private Integer trLvl; // terraform
    private Integer sdLvl; // space deck

    @OneToOne(mappedBy = "infrastructureComp")
    private CelestialBody celestialBody;
}
