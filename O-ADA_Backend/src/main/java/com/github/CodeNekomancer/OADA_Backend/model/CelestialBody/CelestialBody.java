package com.github.CodeNekomancer.OADA_Backend.model.CelestialBody;

import com.github.CodeNekomancer.OADA_Backend.model.DefenseComp.DefenseComp;
import com.github.CodeNekomancer.OADA_Backend.model.InfrastructureComp.InfrastructureComp;
import com.github.CodeNekomancer.OADA_Backend.model.ShipComp.ShipComp;
import com.github.CodeNekomancer.OADA_Backend.model.UAcc.UAcc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CelestialBody {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long celestial_body_ID;

    private Character type; // (P: planet, M: moon, D: debris(?)) // TODO: enum
    private String name;
    private String cords;
    private Integer size;
    private Integer naturalFields;
    private Integer maxTemp;

    @ManyToOne
    private UAcc itsUAcc;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "infrastructure_comp_ID")
    private InfrastructureComp infrastructureComp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "defense_comp_ID")
    private DefenseComp defenseComp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ship_comp_ID")
    private ShipComp shipComp;
}
