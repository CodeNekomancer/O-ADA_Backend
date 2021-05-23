package com.github.CodeNekomancer.OADA_Backend.model.UAcc.DTOs;

import com.github.CodeNekomancer.OADA_Backend.model.UAcc.UAcc;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UAccOutputDTO {
    private String name;
    private String alliance;
    private Long ogameUniverseAccountId;
    private Integer darkMatter;
    private String gameStyleClass;
    private String itsADAcc;
    private Long itsUniverse;

    public UAccOutputDTO(UAcc uAcc) {
        this.name = uAcc.getName();
        this.alliance = uAcc.getAlliance();
        this.ogameUniverseAccountId = uAcc.getOgameUniverseAccountId();
        this.darkMatter = uAcc.getDarkMatter();
        this.gameStyleClass = uAcc.getGameStyleClass();
        this.itsADAcc = uAcc.getItsADAcc().getAdacc_ID();
        this.itsUniverse = uAcc.getItsUniverse().getUniverse_id();
    }
}
