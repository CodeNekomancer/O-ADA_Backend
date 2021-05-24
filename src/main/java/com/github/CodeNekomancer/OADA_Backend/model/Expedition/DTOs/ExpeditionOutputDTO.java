package com.github.CodeNekomancer.OADA_Backend.model.Expedition.DTOs;

import com.github.CodeNekomancer.OADA_Backend.model.Expedition.Expedition;
import com.github.CodeNekomancer.OADA_Backend.model.ResourceComp.ResourceComp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExpeditionOutputDTO {
    private Long expedition_ID;
    private Date datetime;
    private String cords;
    private String event;
    private ResourceComp resourcesObtained;

    public ExpeditionOutputDTO(Expedition expedition) {
        this.expedition_ID = expedition.getExpedition_ID();
        this.datetime = expedition.getDatetime();
        this.cords = expedition.getCords();
        this.event = expedition.getEvent();
        this.resourcesObtained = expedition.getResourcesObtained();
    }
}
