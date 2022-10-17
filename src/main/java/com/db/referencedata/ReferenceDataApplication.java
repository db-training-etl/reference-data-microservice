package com.db.referencedata;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.service.CounterpartyService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class ReferenceDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReferenceDataApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CounterpartyService counterpartyService) {
        return args -> {
            // read json and write to db
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Counterparty>> typeReference = new TypeReference<List<Counterparty>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/counterparty.json");
            try {
                List<Counterparty> counterparties = mapper.readValue(inputStream,typeReference);
                counterpartyService.saveAll(counterparties);
                System.out.println("Counterparties Saved!");
            } catch (IOException e){
                System.out.println("Unable to save counterparties: " + e.getMessage());
            }
        };
    }

}
