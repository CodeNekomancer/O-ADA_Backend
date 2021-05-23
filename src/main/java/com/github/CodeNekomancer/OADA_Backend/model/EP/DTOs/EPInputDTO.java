package com.github.CodeNekomancer.OADA_Backend.model.EP.DTOs;

import com.github.CodeNekomancer.OADA_Backend.model.EP.EP;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EPInputDTO {
    private Long ep_ID;
    private String name;

    public EP converter(EPInputDTO dto) {
        EP ep = new EP();

        if (dto.getEp_ID() != null) ep.setEp_ID(dto.getEp_ID());
        if (dto.getName() != null) ep.setName(dto.getName());

        return ep;
    }
}
