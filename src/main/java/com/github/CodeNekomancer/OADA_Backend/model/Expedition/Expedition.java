package com.github.CodeNekomancer.OADA_Backend.model.Expedition;

import com.github.CodeNekomancer.OADA_Backend.model.EP.EP;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
}
