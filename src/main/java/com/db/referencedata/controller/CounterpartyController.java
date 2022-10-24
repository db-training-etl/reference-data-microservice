package com.db.referencedata.controller;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.exception.NoValuesFoundException;
import com.db.referencedata.service.CounterpartyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("counterparties")
public class CounterpartyController {

    private final CounterpartyService counterpartyService;

    public CounterpartyController(CounterpartyService counterpartyService) {
        this.counterpartyService = counterpartyService;
    }

    @GetMapping("{id}")
    public Counterparty findById(@PathVariable int id) throws Exception {
        return counterpartyService.findById(id);
    }

    @GetMapping("")
    public List<Counterparty> findAll() throws NoValuesFoundException {
        return counterpartyService.findAll();
    }

    @PutMapping("")
    public ResponseEntity<Counterparty> save(@RequestBody Counterparty counterparty) {
        counterpartyService.save(counterparty);
        return new ResponseEntity<Counterparty>(counterparty, HttpStatus.OK);
    }

    @PutMapping("bulk")
    public ResponseEntity<List<Counterparty>> saveAll(@RequestBody List<Counterparty> counterparties) {
        counterpartyService.saveAll(counterparties);
        return new ResponseEntity<List<Counterparty>>(counterparties, HttpStatus.OK);
    }
}
