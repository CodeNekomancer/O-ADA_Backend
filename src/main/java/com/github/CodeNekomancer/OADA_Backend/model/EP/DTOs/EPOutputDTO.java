package com.github.CodeNekomancer.OADA_Backend.model.EP.DTOs;

import com.github.CodeNekomancer.OADA_Backend.model.EP.EP;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EPOutputDTO {
    private Long ep_ID;
    private String name;

    public EPOutputDTO(EP ep) {
        this.setEp_ID(ep.getEp_ID());
        this.setName(ep.getName());
    }
}
