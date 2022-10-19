package controller;

import com.db.referencedata.controller.CounterpartyController;
import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.service.CounterpartyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
    public void findOneCounterpartyByIdTest(){
        Counterparty counterparty = getExampleCounterparty(1,"Pepe", "Something", "Sevilla");

        given(counterpartyService.findById(1)).willReturn(Optional.ofNullable(counterparty));

        assertEquals(counterparty, counterpartyController.findById(1).get());
    }

    @Test
    public void findAllCounterpartiesTest(){
        setExampleCounterparties();

        given(counterpartyService.findAll()).willReturn(counterparties);

        assertEquals(counterparties, counterpartyController.findAll());
    }

    @Test
    public void saveOneCounterpartyTest(){
        Counterparty counterparty = getExampleCounterparty(1,"Pepe", "Something", "Sevilla");
        ResponseEntity<Counterparty> response = new ResponseEntity<Counterparty>(counterparty, HttpStatus.OK);

        given(counterpartyService.save(counterparty)).willAnswer((invocation) -> invocation.getArgument(0));

        assertEquals(response, counterpartyController.save(counterparty));
    }

    @Test
    public void saveAllTest(){
        setExampleCounterparties();
        ResponseEntity<List<Counterparty>> response = new ResponseEntity<List<Counterparty>>(counterparties, HttpStatus.OK);

        given(counterpartyService.saveAll(counterparties)).willAnswer((invocation) -> invocation.getArgument(0));

        assertEquals(response, counterpartyController.saveAll(counterparties));
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
