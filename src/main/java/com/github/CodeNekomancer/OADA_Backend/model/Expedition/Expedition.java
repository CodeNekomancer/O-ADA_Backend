package com.github.CodeNekomancer.OADA_Backend.model.Expedition;

import com.github.CodeNekomancer.OADA_Backend.model.EP.EP;
import com.github.CodeNekomancer.OADA_Backend.model.ResourceComp.ResourceComp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Expedition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expedition_ID;

    private Date datetime;
    private String cords;
    private String event;

    @ManyToOne
    private EP itsEP;

    @OneToOne(mappedBy = "itsExpedition")
    private ResourceComp resourcesObtained;
}
