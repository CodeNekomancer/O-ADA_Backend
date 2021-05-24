package com.github.CodeNekomancer.OADA_Backend.persistence.service;

import com.github.CodeNekomancer.OADA_Backend.model.Expedition.Expedition;
import com.github.CodeNekomancer.OADA_Backend.persistence.repository.ExpeditionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExpeditionService extends BaseService<Expedition, Long, ExpeditionRepository> {

    public boolean genExpeditionSrvc(String data, String authName) {
        Expedition expedition = new Expedition();

        String[] expeditionData = data.split(" ");

        List<String> sentenceMaker = new ArrayList<>();
        for (String word : expeditionData) {
            if (word.equals("expedición")) sentenceMaker.add(word);
            if (word.contains("[")) sentenceMaker.add(word);
        }

//            public static void main(String[] args) {
//            ExpeditionReportReader ERR = new ExpeditionReportReader();
//            ERR.analyzeThoseBadBois(REPORT_DUMP);
//            ERR.metalMed(USEFUL_DATA);
//            ERR.cristalMed(USEFUL_DATA);
//            ERR.dutyMed(USEFUL_DATA);
//            ERR.MOMed(USEFUL_DATA);
//        }

        return false;
    }
}
