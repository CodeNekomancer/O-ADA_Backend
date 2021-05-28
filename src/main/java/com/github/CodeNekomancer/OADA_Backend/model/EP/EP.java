package com.github.CodeNekomancer.OADA_Backend.model.EP;

import com.github.CodeNekomancer.OADA_Backend.model.Expedition.Expedition;
import com.github.CodeNekomancer.OADA_Backend.model.UAcc.UAcc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ep_ID;
    private String name;

    @OneToMany(mappedBy = "itsEP")
    private List<Expedition> expeditions;

    @ManyToOne
    private UAcc itsUAcc;
}
