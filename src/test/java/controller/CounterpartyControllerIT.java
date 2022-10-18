package controller;

import com.db.referencedata.ReferenceDataApplication;
import com.db.referencedata.controller.CounterpartyController;
import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.service.CounterpartyService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    List<Counterparty> counterparties;

    @Test
    public void findAllCounterparties() throws Exception{
        //given
        setExampleCounterparties();
        given(counterpartyService.findAll()).willReturn(counterparties);

        //when
        ResultActions response = mockMvc.perform(get("/counterparties"));
        System.out.println("LAS CONTERTPARTIS --> " + counterparties);
        //then
        response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$[0].counterpartyName", is("Pepe")));
        
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
