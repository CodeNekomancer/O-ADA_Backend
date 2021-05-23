package com.github.CodeNekomancer.OADA_Backend.model.UAcc.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UAccInputDTO {
    private String name;
    private String alliance;
    private Long ogameUniverseAccountId;
    private Integer darkMatter;
    private String gameStyleClass;
    private String itsADAcc;
    private Long itsUniverse;
}
