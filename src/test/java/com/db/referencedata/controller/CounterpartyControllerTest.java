package com.db.referencedata.controller;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.exception.ListEmptyException;
import com.db.referencedata.service.CounterpartyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import static com.db.referencedata.utils.ReferenceDataUtils.getExampleCounterparties;
import static com.db.referencedata.utils.ReferenceDataUtils.getExampleCounterparty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CounterpartyControllerTest {

    CounterpartyService counterpartyService;
    CounterpartyController counterpartyController;
    List<Counterparty> counterparties;

    @BeforeEach
    void setup() {
        counterpartyService = mock(CounterpartyService.class);
        counterpartyController = new CounterpartyController(counterpartyService);

    }

    @Test
    public void findOneCounterpartyByIdTest() throws Exception {
        Counterparty counterparty = getExampleCounterparty(1,"Pepe", "Something", "Sevilla");

        given(counterpartyService.findById(1)).willReturn(counterparty);

        assertEquals(counterparty, counterpartyController.findById(1).getBody());
    }

    @Test
    public void findAllCounterpartiesTest() throws ListEmptyException {
        counterparties = getExampleCounterparties();

        given(counterpartyService.findAll()).willReturn(counterparties);

        assertEquals(counterparties, counterpartyController.findAll().getBody());
    }

    @Test
    public void saveOneCounterpartyTest(){
        Counterparty counterparty = getExampleCounterparty(1,"Pepe", "Something", "Sevilla");
        ResponseEntity<Counterparty> response = new ResponseEntity<>(counterparty, HttpStatus.OK);

        given(counterpartyService.save(counterparty)).willAnswer((invocation) -> invocation.getArgument(0));

        assertEquals(response, counterpartyController.save(counterparty));
    }

    @Test
    public void saveMultipleCounterpartiesTest() throws ListEmptyException {
        counterparties = getExampleCounterparties();
        ResponseEntity<List<Counterparty>> response = new ResponseEntity<>(counterparties, HttpStatus.OK);

        given(counterpartyService.saveAll(counterparties)).willAnswer((invocation) -> invocation.getArgument(0));

        assertEquals(response, counterpartyController.saveAll(counterparties));
    }

}
