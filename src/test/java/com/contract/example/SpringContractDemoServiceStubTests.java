package com.contract.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.WireMockRestServiceServer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext
public class SpringContractDemoServiceStubTests {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Service service;

    @Test
    public void contextLoads() {
        MockRestServiceServer server = WireMockRestServiceServer.with(this.restTemplate)
                .baseUrl("http://example.org")
                .stubs("classpath:/stubs").build();

        String res = this.service.go();

        assertThat(res).isEqualTo("A Test Of Spring Contract");
        server.verify();
    }
}
