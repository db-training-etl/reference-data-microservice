package com.db.referencedata.service;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.exception.NoValuesFoundException;
import com.db.referencedata.repository.CounterpartyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.db.referencedata.utils.ReferenceDataUtils.getExampleCounterparties;
import static com.db.referencedata.utils.ReferenceDataUtils.getExampleCounterparty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CounterpartyServiceTest {

    @Mock
    CounterpartyRepository counterpartyRepository;
    @InjectMocks
    CounterpartyService counterpartyService;

    List<Counterparty> counterparties;


    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void findOneCounterpartyByIdTest() throws Exception {
        Counterparty counterparty = getExampleCounterparty(1,"Pepe", "Something", "Sevilla");

        when(counterpartyRepository.findById(1)).thenReturn(Optional.ofNullable(counterparty));

        assertEquals(counterpartyService.findById(1).get(),counterparty);
    }

    @Test
    public void findAllCounterpartiesTest(){
        counterparties = getExampleCounterparties();

        when(counterpartyRepository.findAll()).thenReturn(counterparties);

        assertNotNull(counterpartyService.findAll());
        assertEquals(counterpartyService.findAll(),counterparties);
    }
    @Test
    public void saveOneCounterpartyTest(){
        Counterparty counterparty = getExampleCounterparty(1,"Pepe", "Something", "Sevilla");

        when(counterpartyRepository.save(any(Counterparty.class))).thenReturn(counterparty);

        assertNotNull(counterpartyService.save(new Counterparty()));
        assertEquals(counterpartyService.save(counterparty),counterparty);
    }

    @Test
    public void saveMultipleCounterpartiesTest() throws NoValuesFoundException {
        counterparties = getExampleCounterparties();

        when(counterpartyRepository.saveAll(any())).thenReturn(counterparties);

        assertNotNull(counterpartyService.saveAll(new LinkedList<>()));
        assertEquals(counterpartyService.saveAll(counterparties),counterparties);
    }

}
