package com.github.CodeNekomancer.OADA_Backend.persistence.service;

import com.github.CodeNekomancer.OADA_Backend.configurations.ExceptionManager.NotAuth.NotAuthForThisResource;
import com.github.CodeNekomancer.OADA_Backend.configurations.ExceptionManager.NotFound.ADAccNotFoundException;
import com.github.CodeNekomancer.OADA_Backend.configurations.ExceptionManager.NotFound.EPNotFoundException;
import com.github.CodeNekomancer.OADA_Backend.configurations.ExceptionManager.NotFound.UAccNotFoundException;
import com.github.CodeNekomancer.OADA_Backend.configurations.security.IAuthenticationFacade;
import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.DTOs.getADAccOutputDTO;
import com.github.CodeNekomancer.OADA_Backend.model.Expedition.DTOs.ExpeditionOutputDTO;
import com.github.CodeNekomancer.OADA_Backend.model.Expedition.Expedition;
import com.github.CodeNekomancer.OADA_Backend.persistence.repository.ExpeditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExpeditionService extends BaseService<Expedition, Long, ExpeditionRepository> {
    @Autowired
    ADAccService adAccService;
    @Autowired
    EPService epService;
    @Autowired
    UAccService uAccService;
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    public boolean genExpeditionSrvc(String data, String authName) {
//        Expedition expedition = new Expedition();
//
//        String[] expeditionData = data.split(" ");
//
//        List<String> sentenceMaker = new ArrayList<>();
//        for (String word : expeditionData) {
//            if (word.equals("expedición")) sentenceMaker.add(word);
//            if (word.contains("[")) sentenceMaker.add(word);
//        }


        return false;
    }

    public List<ExpeditionOutputDTO> getOwnExpeditionSrvc(Long EPid, String authName) {
        if (!authenticationFacade
                .getAuthentication()
                .getName()
                .equals(new getADAccOutputDTO(adAccService
                        .findById(uAccService
                                .findById(epService
                                        .findById(EPid)
                                        .orElseThrow(EPNotFoundException::new)
                                        .getItsUAcc()
                                        .getUacc_ID())
                                .orElseThrow(UAccNotFoundException::new)
                                .getItsADAcc()
                                .getAdacc_ID())
                        .orElseThrow(ADAccNotFoundException::new))
                        .getUsername())) throw new NotAuthForThisResource();

        return epService.findById(EPid).orElseThrow(EPNotFoundException::new).getExpeditions().stream().map(ExpeditionOutputDTO::new).toList();
    }
}
