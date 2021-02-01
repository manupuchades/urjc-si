package es.codeurjc.mastercloudapps.planner.proto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.web.client.RestTemplate;

import es.codeurjc.mastercloudapps.planner.models.LandscapeResponse;

@SpringBootTest
@AutoConfigureStubRunner(ids={"es.codeurjc.mastercloudapps.mpuchades2020:toposervice:+:stubs:8080"},
	stubsMode = StubsMode.LOCAL)
public class TopoClientTest {
    @Test
	void verify_topo_service() {
		RestTemplate restTemplate = new RestTemplate();

		LandscapeResponse topographicdetails = restTemplate.getForObject("http://localhost:8080/api/topographicdetails/Madrid", LandscapeResponse.class);

		assertEquals("Madrid", topographicdetails.getId());
		assertEquals("Flat", topographicdetails.getLandscape());
	}
}
