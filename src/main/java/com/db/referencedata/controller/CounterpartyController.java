package com.db.referencedata.controller;

import com.db.referencedata.service.CounterpartyService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterpartyController {

    private CounterpartyService counterpartyService;

    public CounterpartyController(CounterpartyService counterpartyService) {
        this.counterpartyService = counterpartyService;
    }
}
