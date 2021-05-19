package com.github.CodeNekomancer.OADA_Backend.persistence.service;

import com.github.CodeNekomancer.OADA_Backend.model.Expedition.Expedition;
import com.github.CodeNekomancer.OADA_Backend.persistence.repository.ExpeditionRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpeditionService extends BaseService<Expedition, Long, ExpeditionRepository> {

    public boolean genExpeditionSrvc(Expedition expedition) {
        this.repo.save(expedition);
        return this.repo.findById(expedition.getExpedition_ID()).isPresent();
    }
}
