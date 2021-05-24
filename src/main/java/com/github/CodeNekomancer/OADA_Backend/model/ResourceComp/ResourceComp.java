package com.github.CodeNekomancer.OADA_Backend.model.ResourceComp;

import com.github.CodeNekomancer.OADA_Backend.model.Expedition.Expedition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResourceComp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceComp_ID;

    private Integer metal;
    private Integer crystal;
    private Integer duty;
    private Integer MO;
    private Integer energy;

    @OneToOne(cascade = CascadeType.ALL)
    private Expedition itsExpedition;
}
