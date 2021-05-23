package com.github.CodeNekomancer.OADA_Backend.persistence.service;

import com.github.CodeNekomancer.OADA_Backend.model.Expedition.Expedition;
import com.github.CodeNekomancer.OADA_Backend.persistence.repository.ExpeditionRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpeditionService extends BaseService<Expedition, Long, ExpeditionRepository> {

    public String addExpeditionSrvc(String expedition) {
        Expedition exp = new Expedition();
        exp.dataExtractor(expedition).forEach(e -> this.repo.save(e));

        return exp.dataExtractor(expedition).size() + " expeditions saved.";
    }

    public String getExpeditionByEPSrvc(String EPName, String authName) {
        Expedition exp = new Expedition();
        // TODO: getExpeditionByEPSrvc(String a, String b)
        return null;
    }
}
