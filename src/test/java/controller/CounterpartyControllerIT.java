package controller;

import com.db.referencedata.ReferenceDataApplication;
import com.db.referencedata.controller.CounterpartyController;
import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.service.CounterpartyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = ReferenceDataApplication.class)
@AutoConfigureMockMvc(addFilters = false)
public class CounterpartyControllerIT {

    @MockBean
    CounterpartyService counterpartyService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    List<Counterparty> counterparties;

    @Test
    public void findOneCounterpartyByIdTest() throws Exception{
        Counterparty counterparty = getExampleCounterparty(1,"Pepe", "Something", "Sevilla");

        given(counterpartyService.findById(1)).willReturn(Optional.ofNullable(counterparty));

        ResultActions response = mockMvc.perform(get("/counterparties/1"));

        response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.counterpartyName", is("Pepe")));

    }

    @Test
    public void findAllCounterpartiesTest() throws Exception{
        //given
        setExampleCounterparties();
        given(counterpartyService.findAll()).willReturn(counterparties);
        //when
        ResultActions response = mockMvc.perform(get("/counterparties"));
        //then
        response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$[0].counterpartyName", is("Pepe")));

    }

    @Test
    public void saveOneCounterpartyTest() throws Exception{
        Counterparty counterparty = getExampleCounterparty(1,"Pepe", "Something", "Sevilla");

        given(counterpartyService.save(counterparty)).willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(patch("/counterparties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(counterparty)));

        response.andExpect(status().isOk()).andDo(print());

    }

    @Test
    public void saveAllCounterpartTest() throws Exception{
        setExampleCounterparties();

        given(counterpartyService.saveAll(counterparties)).willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(patch("/counterparties/bulk")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(counterparties)));

        response.andExpect(status().isOk()).andDo(print());

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
