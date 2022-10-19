package com.db.referencedata.service;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.repository.CounterpartyRepository;
import com.db.referencedata.service.CounterpartyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
    public void findOneCounterpartyByIdTest(){
        Counterparty counterparty = getExampleCounterparty(1,"Pepe", "Something", "Sevilla");

        when(counterpartyRepository.findById(1)).thenReturn(Optional.ofNullable(counterparty));

        assertEquals(counterpartyService.findById(1).get(),counterparty);
    }

    @Test
    public void findAllCounterpartiesTest(){
        setExampleCounterparties();
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
    public void saveAllTest(){
        setExampleCounterparties();
        when(counterpartyRepository.saveAll(any())).thenReturn(counterparties);
        assertNotNull(counterpartyService.saveAll(new LinkedList<>()));
        assertEquals(counterpartyService.saveAll(counterparties),counterparties);
    }


    public void setExampleCounterparties(){
        counterparties = new LinkedList<>();
        counterparties.add(getExampleCounterparty(1,"Pepe", "Something", "Sevilla"));
        counterparties.add(getExampleCounterparty(2,"Crea", "Tura", "Italia"));
    }

    public Counterparty getExampleCounterparty(Integer id, String name, String source, String entity){
        Counterparty cpty = new Counterparty();
        cpty.setCounterpartyId(id);
        cpty.setCounterpartyName(name);
        cpty.setSource(source);
        cpty.setEntity(entity);

        return cpty;
    }
}
