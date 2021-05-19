package com.github.CodeNekomancer.OADA_Backend.model.ShipComp;

import com.github.CodeNekomancer.OADA_Backend.model.CelestialBody.CelestialBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ShipComp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ship_comp_ID;

    private String name;

    private Integer lfQ; // light fighter
    private Integer hfQ; // heavy fighter
    private Integer crQ; // cruiser
    private Integer bsQ; // battleship
    private Integer brQ; // battlecruiser
    private Integer bbQ; // bomber
    private Integer dtQ; // destroyer
    private Integer dsQ; // death star
    private Integer rpQ; // reaper
    private Integer xpQ; // explorer
    private Integer scQ; // small cargo
    private Integer lcQ; // large cargo
    private Integer coQ; // colonizer
    private Integer reQ; // recycler
    private Integer epQ; // espionage probe
    private Integer ssQ; // solar satellite
    private Integer cwQ; // crawler

    @OneToOne(mappedBy = "shipComp")
    private CelestialBody celestialBody;
}
