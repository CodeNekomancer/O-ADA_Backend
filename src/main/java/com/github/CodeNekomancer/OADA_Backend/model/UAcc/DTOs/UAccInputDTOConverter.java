package com.github.CodeNekomancer.OADA_Backend.model.UAcc.DTOs;

import com.github.CodeNekomancer.OADA_Backend.model.UAcc.UAcc;
import org.springframework.stereotype.Component;

@Component
public class UAccInputDTOConverter {
    public UAcc convertUaccInputDTOtoUAcc(UAcc uAcc, UAccInputDTO uAccInputDTO) {
        uAcc.setOgameUniverseAccountId(uAccInputDTO.getOgameUniverseAccountId());
        uAcc.setName(uAccInputDTO.getName());
        uAcc.setAlliance(uAccInputDTO.getAlliance());
        uAcc.setDarkMatter(uAccInputDTO.getDarkMatter());
        uAcc.setGameStyleClass(uAccInputDTO.getGameStyleClass());
        return uAcc;
    }
}
