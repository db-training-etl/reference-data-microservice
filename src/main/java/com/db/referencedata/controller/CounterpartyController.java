package com.db.referencedata.controller;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.exception.NoValuesFoundException;
import com.db.referencedata.exception.ResourceNotFoundException;
import com.db.referencedata.service.CounterpartyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public List<Counterparty> findAll() throws NoValuesFoundException {
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
            List<@Valid Counterparty> counterparties) throws NoValuesFoundException {
        counterpartyService.saveAll(counterparties);
        return new ResponseEntity<List<Counterparty>>(counterparties, HttpStatus.OK);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolationException(ConstraintViolationException exception,
                                                   ServletWebRequest webRequest) throws IOException {
        webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
