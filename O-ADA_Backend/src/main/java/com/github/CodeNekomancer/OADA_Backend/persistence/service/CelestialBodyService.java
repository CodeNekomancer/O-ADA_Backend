package com.github.CodeNekomancer.OADA_Backend.persistence.service;

import com.github.CodeNekomancer.OADA_Backend.model.CelestialBody.CelestialBody;
import com.github.CodeNekomancer.OADA_Backend.model.UAcc.UAcc;
import com.github.CodeNekomancer.OADA_Backend.persistence.repository.CelestialBodyRepository;
import com.github.CodeNekomancer.OADA_Backend.persistence.repository.UAccRepository;
import lombok.AllArgsConstructor;
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

@Service
@AllArgsConstructor
public class CelestialBodyService
        extends BaseService<CelestialBody, Long, CelestialBodyRepository> {
    private final UAccRepository uAccRepository;

    public boolean genCelestialBodySchemeAuto(UAcc uacc) {
        if (!uAccRepository.findById(uacc.getUacc_ID()).isPresent()) return false;

        UAcc uAcc = uAccRepository.findById(uacc.getUacc_ID()).get();
        String url;
        List<String> urlList =
                new ArrayList<String>() {
                    {
                        add("https://s");
                        add("-");
                        add(".ogame.gameforge.com/api/playerData.xml?id=");
                    }
                };

        if (!uAccRepository.findById(uAcc.getUacc_ID()).isPresent()) {
            System.out.println("uacc id doesnt exist (celestial body)");
            return false;
        }

        url =
                urlList.get(0)
                        + uAcc.getItsUniverse().getServerId().substring(2)
                        + urlList.get(1)
                        + uAcc.getItsUniverse().getServerId().substring(0, 2)
                        + urlList.get(2)
                        + uAcc.getOgameUniverseAccountId();
        System.out.println(url);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Document doc;
        try {
            db = dbf.newDocumentBuilder();
            db.parse(new URL(url).openStream());
            doc = db.parse(url);
            doc.getDocumentElement().normalize();
            System.out.println("got the doc");
            UAccService.XmlUtil.asList(doc.getDocumentElement().getChildNodes()).stream()
                    .filter(planets -> planets.getNodeName().equals("planets"))
                    .forEach(
                            planets -> {
                                System.out.println("stream ON");
                                CelestialBody cb = new CelestialBody();

                                XmlUtil.asList(planets.getChildNodes())
                                        .forEach(
                                                planet -> {
                                                    cb.setCelestial_body_ID(
                                                            Long.valueOf(
                                                                    planet
                                                                            .getAttributes()
                                                                            .getNamedItem("id")
                                                                            .toString()
                                                                            .substring(
                                                                                    planet
                                                                                            .getAttributes()
                                                                                            .getNamedItem("id")
                                                                                            .toString()
                                                                                            .indexOf('"')
                                                                                            + 1,
                                                                                    planet
                                                                                            .getAttributes()
                                                                                            .getNamedItem("id")
                                                                                            .toString()
                                                                                            .lastIndexOf('"'))));
                                                    cb.setName(
                                                            planet
                                                                    .getAttributes()
                                                                    .getNamedItem("name")
                                                                    .toString()
                                                                    .substring(
                                                                            planet
                                                                                    .getAttributes()
                                                                                    .getNamedItem("name")
                                                                                    .toString()
                                                                                    .indexOf('"')
                                                                                    + 1,
                                                                            planet
                                                                                    .getAttributes()
                                                                                    .getNamedItem("name")
                                                                                    .toString()
                                                                                    .lastIndexOf('"')));
                                                    cb.setCords(
                                                            planet
                                                                    .getAttributes()
                                                                    .getNamedItem("coords")
                                                                    .toString()
                                                                    .substring(
                                                                            planet
                                                                                    .getAttributes()
                                                                                    .getNamedItem("coords")
                                                                                    .toString()
                                                                                    .indexOf('"')
                                                                                    + 1,
                                                                            planet
                                                                                    .getAttributes()
                                                                                    .getNamedItem("coords")
                                                                                    .toString()
                                                                                    .lastIndexOf('"')));
                                                    cb.setType('P');
                                                    cb.setItsUAcc(uAcc);
                                                    this.repo.save(cb);

                                                    XmlUtil.asList(planet.getChildNodes()).stream()
                                                            .filter(moon -> moon.getNodeName().equals("moon"))
                                                            .forEach(
                                                                    moon -> {
                                                                        CelestialBody subCb = new CelestialBody();

                                                                        subCb.setName(
                                                                                moon.getAttributes()
                                                                                        .getNamedItem("name")
                                                                                        .toString()
                                                                                        .substring(
                                                                                                moon.getAttributes()
                                                                                                        .getNamedItem("name")
                                                                                                        .toString()
                                                                                                        .indexOf('"')
                                                                                                        + 1,
                                                                                                moon.getAttributes()
                                                                                                        .getNamedItem("name")
                                                                                                        .toString()
                                                                                                        .lastIndexOf('"')));

                                                                        subCb.setSize(
                                                                                Integer.valueOf(
                                                                                        moon.getAttributes()
                                                                                                .getNamedItem("size")
                                                                                                .toString()
                                                                                                .substring(
                                                                                                        moon.getAttributes()
                                                                                                                .getNamedItem("size")
                                                                                                                .toString()
                                                                                                                .indexOf('"')
                                                                                                                + 1,
                                                                                                        moon.getAttributes()
                                                                                                                .getNamedItem("size")
                                                                                                                .toString()
                                                                                                                .lastIndexOf('"'))));
                                                                        subCb.setNaturalFields(
                                                                                (int) Math.round(Math.pow(subCb.getSize() / 1000, 2))
                                                                                        + uAcc.getItsUniverse().getBonusFields());

                                                                        subCb.setType('M');
                                                                        subCb.setCords(cb.getCords());
                                                                        subCb.setCelestial_body_ID(
                                                                                Long.valueOf(
                                                                                        moon.getAttributes()
                                                                                                .getNamedItem("id")
                                                                                                .toString()
                                                                                                .substring(
                                                                                                        moon.getAttributes()
                                                                                                                .getNamedItem("id")
                                                                                                                .toString()
                                                                                                                .indexOf('"')
                                                                                                                + 1,
                                                                                                        moon.getAttributes()
                                                                                                                .getNamedItem("id")
                                                                                                                .toString()
                                                                                                                .lastIndexOf('"'))));
                                                                        this.repo.save(subCb);
                                                                    });
                                                });
                            });
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static final class XmlUtil {
        private XmlUtil() {
        }

        public static List<Node> asList(NodeList n) {
            return n.getLength() == 0 ? Collections.emptyList() : new NodeListWrapper(n);
        }

        static final class NodeListWrapper extends AbstractList<Node> implements RandomAccess {
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
}
