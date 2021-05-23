package com.github.CodeNekomancer.OADA_Backend.persistence.service;

import com.github.CodeNekomancer.OADA_Backend.configurations.ExceptionManager.NotFound.ADAccNotFoundException;
import com.github.CodeNekomancer.OADA_Backend.configurations.ExceptionManager.NotFound.UniverseNotFoundException;
import com.github.CodeNekomancer.OADA_Backend.configurations.XMLmanager.XmlUtil;
import com.github.CodeNekomancer.OADA_Backend.model.UAcc.DTOs.UAccOutputDTO;
import com.github.CodeNekomancer.OADA_Backend.model.UAcc.UAcc;
import com.github.CodeNekomancer.OADA_Backend.model.Universe.DTOs.UniverseOutputDTO;
import com.github.CodeNekomancer.OADA_Backend.model.Universe.Universe;
import com.github.CodeNekomancer.OADA_Backend.model.Universe.DTOs.UniverseInputDTO;
import com.github.CodeNekomancer.OADA_Backend.model.Universe.DTOs.UniverseInputDTOConverter;
import com.github.CodeNekomancer.OADA_Backend.persistence.repository.UniverseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniverseService extends BaseService<Universe, Long, UniverseRepository> {
    @Autowired
    private UniverseInputDTOConverter UIDTOC;
    @Autowired
    private ADAccService adAccService;

    public boolean genUniverse(String serverId) {
        Universe uni = new Universe();
        uni.setServerId(serverId);
        this.repo.save(uni);
        modUniverseSrvc(uni.getUniverse_id());
        return this.repo.findById(uni.getUniverse_id()).isPresent();
    }

    public boolean modUniverseSrvc(Long id) {
        if (this.repo.findById(id).isEmpty()) throw new UniverseNotFoundException();
        Universe uni = this.repo.findById(id).get();

        String url = "";
        List<String> urlList = new ArrayList<>() {
            {
                add("https://s");
                add("-");
                add(".ogame.gameforge.com/api/serverData.xml");
            }
        };

        if (!uni.getServerId().isEmpty())
            url = urlList.get(0) +
                    uni.getServerId().substring(2) +
                    urlList.get(1) +
                    uni.getServerId().substring(0, 2) +
                    urlList.get(2);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Document doc;
        try {
            db = dbf.newDocumentBuilder();
            db.parse(new URL(url).openStream());
            doc = db.parse(url);
            doc.getDocumentElement().normalize();

            List<String> a = XmlUtil.asList(doc.getDocumentElement().getChildNodes()).stream()
                    .map(Node::getTextContent).collect(Collectors.toList());
            if (a.size() == 81)
                a.add(0, null);
            a.add(0, a.get(2) + a.get(1));

            UniverseInputDTO UIDTO = new UniverseInputDTO(a);
            uni = UIDTOC.convertUniverseInputDTOToUniverse(uni, UIDTO);
            this.repo.save(uni);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean delUnvierseSrvc(Long id) {
        if (this.repo.findById(id).isEmpty()) throw new UniverseNotFoundException();
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

    public Page<?> getUniversePagSrvc(Pageable pageable) {
        return this.repo.findAll(pageable);
    }

    public Universe getUniverseSngSrvc(Long id) {
        if (this.repo.findById(id).isEmpty()) throw new UniverseNotFoundException();
        return this.repo.findById(id).get();
    }

    public List<UniverseOutputDTO> getUniverseOwnSrvc(String authName) {
        if (adAccService.findByUserName(authName).isEmpty()) throw new ADAccNotFoundException();

        return  adAccService.findByUserName(authName)
                .get()
                .getUniverseAccounts().stream().map(UAccOutputDTO::new).collect(Collectors.toList())
                .stream().map(UAccOutputDTO::getItsUniverse).collect(Collectors.toList())
                .stream().filter(l -> this.repo.findById(l).isPresent()).map(l -> new UniverseOutputDTO(this.repo.getOne(l))).collect(Collectors.toList());
    }
}
