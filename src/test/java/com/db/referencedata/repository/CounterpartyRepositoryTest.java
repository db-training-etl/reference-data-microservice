package com.db.referencedata.repository;


import com.db.referencedata.entity.Counterparty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.w3c.dom.css.Counter;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CounterpartyRepositoryTest {

    @Autowired
    CounterpartyRepository counterpartyRepository;

    List<Counterparty> counterparties;

    @Test
    public void findOneCounterpartyByIdTest(){
        Counterparty expectedCounterparty = getExampleCounterparty(1, "AAAAAA", "Source1", "Santander");

        Counterparty actualCounterparty = counterpartyRepository.findById(1).get();

        assertThat(actualCounterparty).isEqualTo(expectedCounterparty);
    }

    @Test
    public void findAllCounterpartiesTest(){
        setExampleCounterparties();
        List<Counterparty> ActualCounterparties = counterpartyRepository.findAll();
        assertThat(ActualCounterparties).isEqualTo(counterparties);
    }

    @Test
    public void saveOneCounterpartyTest(){
        Counterparty expectedCounterparty = getExampleCounterparty(6,"Pepa", "Source6", "Caixa");
        Counterparty actualCounterparty = counterpartyRepository.save(expectedCounterparty);
        assertThat(actualCounterparty).hasFieldOrPropertyWithValue("counterpartyId", 6);
        assertThat(actualCounterparty).hasFieldOrPropertyWithValue("counterpartyName", "Pepa");
        assertThat(actualCounterparty).hasFieldOrPropertyWithValue("entity", "Caixa");
    }

    @Test
    public void saveAllTest(){
        setExampleCounterparties();
        List<Counterparty> actualCounterparties = counterpartyRepository.saveAll(counterparties);
        assertThat(actualCounterparties).isEqualTo(counterparties);
    }


    public void setExampleCounterparties(){
        counterparties = new LinkedList<>();
        counterparties.add(getExampleCounterparty(1,"AAAAAA", "Source1", "Santander"));
        counterparties.add(getExampleCounterparty(2,"BBB", "Source2", "BBVA"));
        counterparties.add(getExampleCounterparty(3,"CCC", "Source3", "CAIXABANK"));

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
