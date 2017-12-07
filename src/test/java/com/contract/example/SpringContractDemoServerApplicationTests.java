package com.contract.example;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.WireMockSpring;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "app.baseUrl=http://localhost:8080", webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext
public class SpringContractDemoServerApplicationTests {

	@ClassRule
	public static WireMockClassRule wireMock = new WireMockClassRule(WireMockSpring.options().port(8080));

	@Autowired
	private Service service;

	@Test
	public void contextLoads() {
		stubFor(get(urlEqualTo("/resource"))
				.willReturn(aResponse().withHeader("Content-Type", "text/plain").withBody("A Test Of Spring Contract")));
		String res = this.service.go();
		assertThat(res).isEqualTo("A Test Of Spring Contract");


	}

}
