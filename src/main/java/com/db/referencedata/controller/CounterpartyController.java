package com.db.referencedata.controller;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.exception.ListEmptyException;
import com.db.referencedata.exception.ResourceNotFoundException;
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
    public Counterparty findById(@PathVariable int id) {
        return counterpartyService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Counterparty not found: " + id));
    }

    @GetMapping("")
    public List<Counterparty> findAll() throws ListEmptyException {
        return counterpartyService.findAll();
    }

    @PutMapping("")
    public ResponseEntity<Counterparty> save(@Valid @RequestBody Counterparty counterparty){
        counterpartyService.save(counterparty);
        return new ResponseEntity<Counterparty>(counterparty, HttpStatus.OK);
    }

    @PutMapping("bulk")
    public ResponseEntity<List<Counterparty>> saveAll(
            @RequestBody
            List<@Valid Counterparty> counterparties) throws ListEmptyException {
        counterpartyService.saveAll(counterparties);
        return new ResponseEntity<List<Counterparty>>(counterparties, HttpStatus.OK);
    }

}
