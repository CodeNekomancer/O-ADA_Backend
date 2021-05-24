package com.github.CodeNekomancer.OADA_Backend.model.ResourceComp.DTOs;

import com.github.CodeNekomancer.OADA_Backend.model.ResourceComp.ResourceComp;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceCompOutputDTOforExpeditionOutputDTO {
    private Long resourceComp_ID;
    private Long metal;
    private Long crystal;
    private Long duty;
    private Integer MO;
    private Integer energy;

    public ResourceCompOutputDTOforExpeditionOutputDTO(ResourceComp resourceComp) {
        this.resourceComp_ID = resourceComp.getResourceComp_ID();
        this.metal = resourceComp.getMetal();
        this.crystal = resourceComp.getCrystal();
        this.duty = resourceComp.getDuty();
        this.MO = resourceComp.getMO();
        this.energy = resourceComp.getEnergy();
    }
}
