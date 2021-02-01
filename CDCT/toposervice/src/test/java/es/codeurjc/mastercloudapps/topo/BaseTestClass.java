package es.codeurjc.mastercloudapps.topo;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.codeurjc.mastercloudapps.topo.controller.TopoController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest
public abstract class BaseTestClass {
    @Autowired
    TopoController topoController;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(topoController);
    }
}