package com.db.referencedata.service;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.exception.ListEmptyException;
import com.db.referencedata.repository.CounterpartyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.db.referencedata.utils.ReferenceDataUtils.*;
import static org.junit.jupiter.api.Assertions.*;
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
    public void findOneCounterpartyById_Ok_Test() throws Exception {
        Counterparty counterparty = getExampleCounterparty(1,"Pepe", "Something", "Sevilla");

        when(counterpartyRepository.findById(1)).thenReturn(Optional.of(counterparty));

        assertEquals(counterpartyService.findById(1),counterparty);
    }

    @Test
    public void findAllCounterparties_Ok_Test() throws ListEmptyException {
        counterparties = getExampleCounterparties();

        when(counterpartyRepository.findAll()).thenReturn(counterparties);

        assertNotNull(counterpartyService.findAll());
        assertEquals(counterpartyService.findAll(),counterparties);
    }

    @Test
    public void findAllCounterparties_EmptyList_Test() throws ListEmptyException {
        counterparties = new LinkedList<>();

        when(counterpartyRepository.findAll()).thenReturn(counterparties);

        assertThrows(ListEmptyException.class, () -> counterpartyService.findAll());
    }

    @Test
    public void saveOneCounterparty_Ok_Test(){
        Counterparty counterparty = getExampleCounterparty(1,"Pepe", "Something", "Sevilla");

        when(counterpartyRepository.save(any(Counterparty.class))).thenReturn(counterparty);

        assertNotNull(counterpartyService.save(new Counterparty()));
        assertEquals(counterpartyService.save(counterparty),counterparty);
    }

    @Test
    public void saveMultipleCounterparties_Ok_Test() throws ListEmptyException {
        counterparties = getExampleCounterparties();

        when(counterpartyRepository.saveAll(any())).thenReturn(counterparties);

        assertEquals(counterpartyService.saveAll(counterparties),counterparties);
    }

    @Test
    public void saveMultipleCounterparties_ListEmpty_Test(){
        counterparties = new LinkedList<>();

        when(counterpartyRepository.saveAll(any())).thenReturn(counterparties);

        assertThrows(ListEmptyException.class, () -> counterpartyService.saveAll(counterparties));
    }

    @Test
    public void saveChunkTest() throws ListEmptyException {
        counterparties = getExampleCounterparties();

        when(counterpartyRepository.saveAll(any(List.class))).thenReturn(counterparties);

        assertEquals(counterpartyService.saveChunk(counterparties),counterparties);
    }

    @Test
    public void saveChunk_EmptyList_Test() throws ListEmptyException {
        counterparties = new LinkedList<>();

        when(counterpartyRepository.saveAll(any(List.class))).thenReturn(counterparties);

        assertThrows(ListEmptyException.class, () -> counterpartyService.saveChunk(counterparties));
    }
}
