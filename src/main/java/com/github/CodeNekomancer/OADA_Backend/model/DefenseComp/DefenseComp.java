package com.github.CodeNekomancer.OADA_Backend.model.DefenseComp;

import com.github.CodeNekomancer.OADA_Backend.model.CelestialBody.CelestialBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DefenseComp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long defense_comp_ID;

    private Integer rlQ; // rocket launcher
    private Integer slQ; // small laser
    private Integer blQ; // big laser
    private Integer gaQ; // gauss cannon
    private Integer ioQ; // ionic cannon
    private Integer plQ; // plasma cannon
    private Integer sdQ; // small dome
    private Integer dbQ; // big dome
    private Integer amQ; // anti-ballistic missile
    private Integer imQ; // interplanetary missile

    @OneToOne(mappedBy = "defenseComp")
    private CelestialBody celestialBody;
}
