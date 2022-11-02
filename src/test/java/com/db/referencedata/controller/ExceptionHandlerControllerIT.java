package com.db.referencedata.controller;

import com.db.referencedata.ReferenceDataApplication;
import com.db.referencedata.repository.CounterpartyRepository;
import com.db.referencedata.repository.ExceptionRepository;
import com.db.referencedata.service.CounterpartyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ReferenceDataApplication.class)
@AutoConfigureMockMvc(addFilters = false)
public class ExceptionHandlerControllerIT {

    ExceptionHandlerController exceptionHandlerController;

    CounterpartyController counterpartyController;
    CounterpartyService counterpartyService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CounterpartyRepository counterpartyRepository;
    @MockBean
    ExceptionRepository exceptionRepository;


    @BeforeEach
    public void setup(){
        counterpartyService = new CounterpartyService(counterpartyRepository);
        counterpartyController = new CounterpartyController(counterpartyService);
    }

    @Test
    public void findAllCounterparties_ListEmpty_Test() throws Exception {
        //HashMap<String, Object> responseBody = getExampleResponseBody("ListEmptyException");
        //ResponseEntity<HashMap<String, Object>> response = new ResponseEntity<>(responseBody, HttpStatus.NO_CONTENT);

        //given(counterpartyController.findAll()).willAnswer((invocation) -> invocation.getArgument(0));


        ResultActions response = mockMvc.perform(get("/counterparties"));

        response.andExpect(status().isNoContent()).andDo(print()).andExpect(jsonPath("$name", is("ListEmptyException")));

        //assertEquals(response.getBody().get("name"),
          //      exceptionHandlerController.listEmptyException(new ListEmptyException("")).getBody().get("name"));
    }

    public HashMap<String, Object> getExampleResponseBody(String exceptionClass){

        HashMap<String,Object> exceptionLog = new HashMap<>();
        exceptionLog.put("name",exceptionClass);
        exceptionLog.put("type",exceptionClass);
        exceptionLog.put("message","message");
        exceptionLog.put("trace","trace");
        exceptionLog.put("cobDate","date");

        return exceptionLog;
    }
}
