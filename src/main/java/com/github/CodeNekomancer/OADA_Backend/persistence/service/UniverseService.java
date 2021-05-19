package com.github.CodeNekomancer.OADA_Backend.persistence.service;

import com.github.CodeNekomancer.OADA_Backend.model.Universe.Universe;
import com.github.CodeNekomancer.OADA_Backend.model.Universe.UniverseInputDTO;
import com.github.CodeNekomancer.OADA_Backend.model.Universe.UniverseInputDTOConverter;
import com.github.CodeNekomancer.OADA_Backend.persistence.repository.UniverseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UniverseService extends BaseService<Universe, Long, UniverseRepository> {
    private final UniverseInputDTOConverter UIDTOC;

    public boolean genUniverse(Universe uni) {
        this.repo.save(uni);
        modUniverseSrvc(uni);
        return this.repo.findById(uni.getUniverse_id()).isPresent();
    }

    public boolean modUniverseSrvc(Universe uni) {
        Universe u = new Universe();

        String url = "";
        List<String> urlList = new ArrayList<String>() {
            {
                add("https://s");
                add("-");
                add(".ogame.gameforge.com/api/serverData.xml");
            }
        };

        if (this.repo.findById(uni.getUniverse_id()).isPresent())
            u = this.repo.findById(uni.getUniverse_id()).get();

        if (!u.getServerId().isEmpty())
            url = urlList.get(0) +
                    u.getServerId().substring(2) +
                    urlList.get(1) +
                    u.getServerId().substring(0, 2) +
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
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

    public static final class XmlUtil {
        private XmlUtil() {
        }

        public static List<Node> asList(NodeList n) {
            return n.getLength() == 0 ?
                    Collections.emptyList() : new NodeListWrapper(n);
        }

        static final class NodeListWrapper extends AbstractList<Node>
                implements RandomAccess {
            private final NodeList list;

            NodeListWrapper(NodeList l) {
                list = l;
            }

            public Node get(int index) {
                return list.item(index);
            }

            public int size() {
                return list.getLength();
            }
        }
    }

    public Page<?> getUniverseSrvc(Pageable pageable) {
        return this.repo.findAll(pageable);
    }

}
