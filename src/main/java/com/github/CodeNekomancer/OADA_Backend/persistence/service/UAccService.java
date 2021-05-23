package com.github.CodeNekomancer.OADA_Backend.persistence.service;

import com.github.CodeNekomancer.OADA_Backend.configurations.ExceptionManager.NotFound.UAccNotFoundException;
import com.github.CodeNekomancer.OADA_Backend.configurations.ExceptionManager.NotFound.UniverseNotFoundException;
import com.github.CodeNekomancer.OADA_Backend.configurations.XMLmanager.XmlUtil;
import com.github.CodeNekomancer.OADA_Backend.model.ADAcc.ADAcc;
import com.github.CodeNekomancer.OADA_Backend.model.UAcc.DTOs.UAccInputDTO;
import com.github.CodeNekomancer.OADA_Backend.model.UAcc.DTOs.UAccInputDTOConverter;
import com.github.CodeNekomancer.OADA_Backend.model.UAcc.DTOs.UAccOutputDTO;
import com.github.CodeNekomancer.OADA_Backend.model.UAcc.UAcc;
import com.github.CodeNekomancer.OADA_Backend.model.Universe.Universe;
import com.github.CodeNekomancer.OADA_Backend.persistence.repository.UAccRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Service
public class UAccService extends BaseService<UAcc, Long, UAccRepository> {
    @Autowired private UAccInputDTOConverter UACCUDTOC;
    @Autowired private UniverseService universe;
    @Autowired private ADAccService adacc;
    @Autowired private EPService EPSrvc;

    public UAccOutputDTO addUAccSrvc(UAccInputDTO uAccInputDTO) {
        Optional<Universe> ou = universe.repo.findById(uAccInputDTO.getItsUniverse());
        Optional<ADAcc> oa = adacc.repo.findById(uAccInputDTO.getItsADAcc());

        if (oa.isEmpty()) throw new UAccNotFoundException();
        if (ou.isEmpty()) throw new UniverseNotFoundException();

        UAcc uAcc = new UAcc();
        uAcc = UACCUDTOC.convertUaccInputDTOtoUAcc(uAcc, uAccInputDTO);
        uAcc.setItsADAcc(oa.get());
        uAcc.setItsUniverse(ou.get());
        uAcc.setOgameUniverseAccountId(getUAccWithOgameId(uAcc).getOgameUniverseAccountId());

        this.repo.save(uAcc);
        EPSrvc.addSrvc(uAcc);

        if (this.repo.findById(uAcc.getUacc_ID()).isEmpty())
            throw new UAccNotFoundException();

        return new UAccOutputDTO(this.repo.findById(uAcc.getUacc_ID()).get());
    }

    private UAccOutputDTO getUAccWithOgameId(UAcc uAcc) {
        String url;
        List<String> urlList =
                new ArrayList<>() {
                    {
                        add("https://s");
                        add("-");
                        add(".ogame.gameforge.com/api/players.xml");
                    }
                };

        url =
                urlList.get(0)
                        + uAcc.getItsUniverse().getNumber()
                        + urlList.get(1)
                        + uAcc.getItsUniverse().getLanguage()
                        + urlList.get(2);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Document doc;
        try {
            db = dbf.newDocumentBuilder();
            db.parse(new URL(url).openStream());
            doc = db.parse(url);
            doc.getDocumentElement().normalize();

            XmlUtil.asList(doc.getDocumentElement().getChildNodes())
                    .forEach(
                            node -> {
                                if (node.getAttributes()
                                        .getNamedItem("name")
                                        .toString()
                                        .substring(
                                                node.getAttributes().getNamedItem("name").toString().indexOf('"') + 1,
                                                node.getAttributes().getNamedItem("name").toString().lastIndexOf('"'))
                                        .equals(uAcc.getName())) {
                                    uAcc.setOgameUniverseAccountId(
                                            Long.valueOf(
                                                    node.getAttributes()
                                                            .getNamedItem("id")
                                                            .toString()
                                                            .substring(
                                                                    node.getAttributes().getNamedItem("id").toString().indexOf('"')
                                                                            + 1,
                                                                    node.getAttributes()
                                                                            .getNamedItem("id")
                                                                            .toString()
                                                                            .lastIndexOf('"'))));
                                }
                            });
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return new UAccOutputDTO(uAcc);
    }

    public UAccOutputDTO getUAccSngSrvc(Long id) {
        if (this.repo.findById(id).isPresent()) return new UAccOutputDTO(this.repo.findById(id).get());
        throw new UAccNotFoundException();
    }

    public boolean delUAccSngSrvc(Long id) {
        if (!this.repo.existsById(id)) throw new UAccNotFoundException();
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }
}
