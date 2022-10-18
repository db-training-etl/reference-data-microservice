package controller;

import com.db.referencedata.controller.CounterpartyController;
import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.service.CounterpartyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.css.Counter;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CounterpartyControllerTest {

    CounterpartyService counterpartyService;
    CounterpartyController counterpartyController;
    List<Counterparty> counterparties;

    @BeforeEach
    void setUp() {
        counterpartyService = mock(CounterpartyService.class);
        counterpartyController = new CounterpartyController(counterpartyService);


    }

    @Test
    public void findAllTest(){
        setExampleCounterparties();
        given(counterpartyService.findAll()).willReturn(counterparties);
        assertEquals(counterparties, counterpartyController.findAll());
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
