package com.github.CodeNekomancer.OADA_Backend.persistence.service;

import com.github.CodeNekomancer.OADA_Backend.configurations.ExceptionManager.NotFound.EPNotFoundException;
import com.github.CodeNekomancer.OADA_Backend.configurations.ExceptionManager.NotFound.UAccNotFoundException;
import com.github.CodeNekomancer.OADA_Backend.model.EP.DTOs.EPOutputDTO;
import com.github.CodeNekomancer.OADA_Backend.model.EP.EP;
import com.github.CodeNekomancer.OADA_Backend.model.UAcc.UAcc;
import com.github.CodeNekomancer.OADA_Backend.persistence.repository.EPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EPService extends BaseService<EP, Long, EPRepository> {

    @Autowired
    private UAccService uaccSrvc;

    public Boolean addSrvc(UAcc uAcc) {
        if (uaccSrvc.findById(uAcc.getUacc_ID()).isEmpty()) throw new UAccNotFoundException();
        EP ep = new EP();
        ep.setItsUAcc(uAcc);
        ep.setName(uAcc.getItsUniverse().getServerId() + "_" + uAcc.getItsADAcc().getUsername());
        this.repo.save(ep);
        return this.repo.findById(ep.getEp_ID()).isPresent();
    }

    public Boolean delSrvc(Long id) {
        if (this.repo.findById(id).isEmpty()) throw new EPNotFoundException();
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

    public EPOutputDTO getSngSrvc(Long id) {
        if (this.repo.findById(id).isEmpty()) throw new EPNotFoundException();
        return new EPOutputDTO(this.repo.findById(id).get());
    }
}
