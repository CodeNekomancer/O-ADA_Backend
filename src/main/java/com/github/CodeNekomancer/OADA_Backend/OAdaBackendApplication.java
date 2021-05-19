package com.github.CodeNekomancer.OADA_Backend;

import com.github.CodeNekomancer.OADA_Backend.model.Universe.Universe;
import com.github.CodeNekomancer.OADA_Backend.persistence.service.UniverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class OAdaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAdaBackendApplication.class, args);
    }

    @Component
    public class Monitor {

        @Autowired
        private UniverseService us;

        @PostConstruct
        public void init() {
            // TODO: is this necesary when DB prop configured?
            if (us.findById(1L).isEmpty()) {
                us.genUniverse(new Universe("es1"));
                us.genUniverse(new Universe("es170"));
                us.genUniverse(new Universe("jp101"));
                us.genUniverse(new Universe("mx115"));
                us.genUniverse(new Universe("nl3"));
                us.genUniverse(new Universe("nl124"));
                us.genUniverse(new Universe("pl68"));
                us.genUniverse(new Universe("us1"));
            }
        }
    }
}
