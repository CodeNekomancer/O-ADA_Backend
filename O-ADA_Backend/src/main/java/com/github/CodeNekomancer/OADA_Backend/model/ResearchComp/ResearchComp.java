package com.github.CodeNekomancer.OADA_Backend.model.ResearchComp;

import com.github.CodeNekomancer.OADA_Backend.model.UAcc.UAcc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ResearchComp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long research_comp_ID;

    private Integer energyLvl;
    private Integer laserLvl;
    private Integer ionLvl;
    private Integer hyperspaceLvl;
    private Integer plasmaLvl;
    private Integer computationLvl;
    private Integer espionageLvl;
    private Integer astrophysicsLvl;
    private Integer gravitationLvl;
    private Integer intergalacticLVl;
    private Integer combustionLvl;
    private Integer impulseLVl;
    private Integer hyperDriveLvl;
    private Integer weaponsLvl;
    private Integer shieldingLvl;
    private Integer armourLvl;

    @ManyToOne
    private UAcc itsUAcc;
}
