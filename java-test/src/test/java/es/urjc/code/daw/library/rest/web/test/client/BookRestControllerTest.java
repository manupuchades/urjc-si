package es.urjc.code.daw.library.rest.web.test.client;

import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Tests con WebTestClient")
class BookRestControllerTest {

	@Test
	@DisplayName("Recuperar libros con usuario no autenticado")
	void testGetBooks() throws Exception {
		fail("not implemented yet");
	}

	@Test
	@DisplayName("Añadir libro como usuario")
	void testCreateBook() throws Exception {
		fail("not implemented yet");
	}

	@Test
	@DisplayName("Borrar libro como administrador")
	void testDeleteBook() throws Exception {
		fail("not implemented yet");
	}
}
