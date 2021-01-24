package es.urjc.code.daw.library.rest.assured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Tests unitarios con RESTAssured")
class BookRestControllerTest {

	@LocalServerPort
	int port;

	@BeforeEach
	public void setUp() {
		RestAssured.port = port;
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = "https://localhost:" + port;
	}
	
	@Test
	@Order(1)
	@DisplayName("Borrar libro existente como administrador")
	void testDeleteExistingBook() throws Exception {

		given().auth().preemptive().basic("admin", "pass").when().get("/api/books/{id}", 1).then()
		.statusCode(200);
		
		given().auth().preemptive().basic("admin", "pass").when().delete("/api/books/1").then().assertThat()
				.statusCode(200);

		given().auth().preemptive().basic("admin", "pass").when().get("/api/books/{id}", 1).then()
				.statusCode(404);
	}

	@Test
	@Order(2)
	@DisplayName("Recuperar libros con usuario no autenticado")
	void testGetBooks() throws Exception {

		when().request("GET", "/api/books/").then().statusCode(200).assertThat().contentType(ContentType.JSON)
				.body("$", hasSize(5)).body("title", hasItems("SUEÑOS DE ACERO Y NEON", "LA VIDA SECRETA DE LA MENTE",
						"CASI SIN QUERER", "TERMINAMOS Y OTROS POEMAS SIN TERMINAR", "LA LEGIÓN PERDIDA"));
	}

	@Test
	@Order(3)
	@DisplayName("Añadir libro como usuario")
	void testCreateBook() throws Exception {
		given().auth().preemptive().basic("user", "pass")
		.contentType("application/json").body("{\"title\":\"The Title\",\"description\":\"Amazing description\" }")
		.when().post("/api/books/").then().statusCode(201)
				.body("title", equalTo("The Title"))
				.body("description", equalTo("Amazing description"));
				
		Response response = given().auth().preemptive().basic("user", "pass").contentType("application/json")
				.body("{\"title\":\"The Title\",\"description\":\"Amazing description\" }").when().post("/api/books/")
				.andReturn();

		int id = from(response.getBody().asString()).get("id");
		
		given().auth().preemptive().basic("user", "pass").when().get("/api/books/{id}", id).then()
		.statusCode(200).body("title", equalTo("The Title"))
		.body("description", equalTo("Amazing description"));
	}
	
	@Test
	@Order(4)
	@DisplayName("Borrar libro nuevo como administrador")
	void testDeleteNewBook() throws Exception {

		Response response = given().auth().preemptive().basic("user", "pass").contentType("application/json")
				.body("{\"title\":\"The Title\",\"description\":\"Amazing description\" }").when().post("/api/books/")
				.andReturn();

		int id = from(response.getBody().asString()).get("id");
		
		given().auth().preemptive().basic("admin", "pass").when().delete("/api/books/{id}", id).then().assertThat()
				.statusCode(200);

		given().auth().preemptive().basic("user", "pass").when().get("/api/books/{id}", id).then()
				.statusCode(404);
	}
}
