package com.db.referencedata.controller;

import com.db.referencedata.entity.ChunkCounterparties;
import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.exception.ListEmptyException;
import com.db.referencedata.service.CounterpartyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("counterparties")
public class CounterpartyController {

    private final CounterpartyService counterpartyService;

    public CounterpartyController(CounterpartyService counterpartyService) {
        this.counterpartyService = counterpartyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Counterparty> findById(@PathVariable int id) {
        return new ResponseEntity<>(counterpartyService.findById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Counterparty>> findAll() throws ListEmptyException {
        return new ResponseEntity<>(counterpartyService.findAll(), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Counterparty> save(@Valid @RequestBody Counterparty counterparty){
        counterpartyService.save(counterparty);
        return new ResponseEntity<>(counterparty, HttpStatus.OK);
    }

    @PutMapping("bulk")
    public ResponseEntity<List<Counterparty>> saveAll(@RequestBody List<@Valid Counterparty> counterparties) throws ListEmptyException {
        counterpartyService.saveAll(counterparties);
        return new ResponseEntity<>(counterparties, HttpStatus.OK);
    }

    @PutMapping("chunk")
    public ResponseEntity<List<Counterparty>> saveChunk(@Valid @RequestBody ChunkCounterparties chunkCounterparties) throws ListEmptyException {
        counterpartyService.saveChunk(chunkCounterparties.getChunkList());
        return new ResponseEntity<>(chunkCounterparties.getChunkList(), HttpStatus.OK);
    }

}
