package com.github.CodeNekomancer.OADA_Backend.persistence.service;

import com.github.CodeNekomancer.OADA_Backend.model.EP.EP;
import com.github.CodeNekomancer.OADA_Backend.model.UAcc.UAcc;
import com.github.CodeNekomancer.OADA_Backend.persistence.repository.EPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EPService extends BaseService<EP, Long, EPRepository> {

    @Autowired
    private UAccService uaccSrvc;

    public Boolean genEPSrvc(UAcc uAcc) {
        EP ep = new EP();
        ep.setItsUAcc(uAcc);
        ep.setName(uAcc.getItsUniverse().getServerId() + "_" + uAcc.getItsADAcc().getUsername());
        this.repo.save(ep);
        return this.repo.findById(ep.getEp_ID()).isPresent();
    }

    public Boolean delEPSrvc(Long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }
}
