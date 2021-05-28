package com.github.CodeNekomancer.OADA_Backend.model.UAcc.DTOs;

import com.github.CodeNekomancer.OADA_Backend.model.UAcc.UAcc;
import com.github.CodeNekomancer.OADA_Backend.model.Universe.DTOs.UniverseOutputDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UAccOwnOutDTO {
    private String name;
    private String alliance;
    private Long ogameUniverseAccountId;
    private Integer darkMatter;
    private String gameStyleClass;
    private String itsADAcc;
    private UniverseOutputDTO itsUniverse;

    public UAccOwnOutDTO(UAcc uAcc) {
        this.name = uAcc.getName();
        this.alliance = uAcc.getAlliance();
        this.ogameUniverseAccountId = uAcc.getOgameUniverseAccountId();
        this.darkMatter = uAcc.getDarkMatter();
        this.gameStyleClass = uAcc.getGameStyleClass();
        this.itsADAcc = uAcc.getItsADAcc().getAdacc_ID();
        this.itsUniverse = new UniverseOutputDTO(uAcc.getItsUniverse());
    }
}
