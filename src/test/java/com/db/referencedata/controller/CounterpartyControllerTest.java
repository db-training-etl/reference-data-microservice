package com.db.referencedata.controller;

import com.db.referencedata.entity.Book;
import com.db.referencedata.entity.ChunkBooks;
import com.db.referencedata.entity.ChunkCounterparties;
import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.exception.ListEmptyException;
import com.db.referencedata.service.CounterpartyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.db.referencedata.utils.ReferenceDataUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CounterpartyControllerTest {

    CounterpartyService counterpartyService;
    CounterpartyController counterpartyController;
    List<Counterparty> counterparties;

    ChunkCounterparties chunk;

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

    @Test
    public void saveChunkTest(){
        chunk = getExampleChunkCounterparties();
        ResponseEntity<List<Counterparty>> response = new ResponseEntity<>(chunk.getChunkList(), HttpStatus.OK);

        given(counterpartyService.saveChunk(any(List.class))).willAnswer((invocation) -> invocation.getArgument(0));

        assertEquals(response, counterpartyController.saveChunk(chunk));
    }

    public ChunkCounterparties getExampleChunkCounterparties(){
        ChunkCounterparties chunk = new ChunkCounterparties();

        chunk.setProcessId(1);
        chunk.setNumChunks(1);
        chunk.setSize(10);
        chunk.setChunkList(getExampleCounterparties());

        return chunk;
    }
}
