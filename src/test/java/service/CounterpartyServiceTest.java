package service;

import com.db.referencedata.entity.Counterparty;
import com.db.referencedata.service.CounterpartyService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CounterpartyServiceTest {

    CounterpartyService counterpartyService;
    public MockWebServer mockBackEnd;
    HashMap<String, Object> expectedResponse;

    @AfterEach
    void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize() throws IOException {

        mockBackEnd = new MockWebServer();

        //counterpartyService = new CounterpartyService(mockBackEnd.url("/").url().toString());

        expectedResponse = new HashMap<>();
        expectedResponse.put("images", 0);
        expectedResponse.put("change_keys", 0);

    }

    @Test
    public void getCounterpartyBulk_Test(){
        mockBackEnd.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                );


        Iterable<Counterparty> actual = counterpartyService.findAll();


        assertEquals(expectedResponse.toString(),actual.toString());

    }


    private String getCounterpartyExample(){
        return "";
    }
}
