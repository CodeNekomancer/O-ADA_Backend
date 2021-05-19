package com.github.CodeNekomancer.OADA_Backend.model.UAcc;

import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.ADAcc;
import com.github.CodeNekomancer.OADA_Backend.model.CelestialBody.CelestialBody;
import com.github.CodeNekomancer.OADA_Backend.model.EP.EP;
import com.github.CodeNekomancer.OADA_Backend.model.ResearchComp.ResearchComp;
import com.github.CodeNekomancer.OADA_Backend.model.Universe.Universe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UAcc {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long uacc_ID;

    private Long ogameUniverseAccountId;
    private String name;
    private String alliance;
    private Integer darkMatter;
    private String gameStyleClass;

    @ManyToOne
    private ADAcc itsADAcc;

    @ManyToOne
    private Universe itsUniverse;

    @OneToMany(mappedBy = "itsUAcc")
    private List<EP> expeditionProfiles;

    @OneToMany(mappedBy = "itsUAcc")
    private List<ResearchComp> researchComps;

    @OneToMany(mappedBy = "itsUAcc")
    private List<CelestialBody> celestialBodies;
}
