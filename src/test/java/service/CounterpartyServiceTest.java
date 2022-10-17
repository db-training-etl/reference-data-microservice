package service;

import com.db.referencedata.service.CounterpartyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CounterpartyServiceTest {

    CounterpartyService counterpartyService;


    @Test
    public void saveCounterpartyToDatabase_Test(){
        String counterparty = getCounterpartyExample();


        //assertEquals(savedCounterparty, counterparty);

    }


    private String getCounterpartyExample(){
        return "";
    }
}
