package com.db.referencedata.service;

import com.db.referencedata.ReferenceDataApplication;
import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.repository.CounterpartyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

import static com.db.referencedata.utils.ReferenceDataUtils.getExampleCounterparties;
import static com.db.referencedata.utils.ReferenceDataUtils.getExampleCounterparty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ReferenceDataApplication.class)
@AutoConfigureMockMvc(addFilters = false)
public class CounterpartyServiceIT {

    @MockBean
    CounterpartyRepository counterpartyRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    List<Counterparty> counterparties;

    @Test
    public void findOneCounterpartyByIdTest() throws Exception{
        Counterparty counterparty = getExampleCounterparty(1,"AAAAAA", "Source1", "Santander");

        given(counterpartyRepository.findById(1)).willReturn(Optional.ofNullable(counterparty));

        ResultActions response = mockMvc.perform(get("/counterparties/1"));

        response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.counterpartyName", is("AAAAAA")));

    }

    @Test
    public void findAllCounterpartiesTest() throws Exception{
        counterparties = getExampleCounterparties();

        given(counterpartyRepository.findAll()).willReturn(counterparties);

        ResultActions response = mockMvc.perform(get("/counterparties"));

        response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$[0].counterpartyName", is("AAAAAA")));

    }

    @Test
    public void saveOneCounterpartyTest() throws Exception{
        Counterparty counterparty = getExampleCounterparty(1,"AAAAAA", "Source1", "Santander");

        given(counterpartyRepository.save(counterparty)).willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(put("/counterparties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(counterparty)));

        response.andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void saveMultipleCounterpartiesTest() throws Exception{
        counterparties = getExampleCounterparties();

        given(counterpartyRepository.saveAll(counterparties)).willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(put("/counterparties/bulk")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(counterparties)));

        response.andExpect(status().isOk()).andDo(print());

    }

}
