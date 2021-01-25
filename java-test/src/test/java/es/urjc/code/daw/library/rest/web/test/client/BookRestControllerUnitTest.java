package es.urjc.code.daw.library.rest.web.test.client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.net.ssl.SSLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

import es.urjc.code.daw.library.book.Book;
import es.urjc.code.daw.library.book.BookService;
import reactor.core.publisher.Mono;

@AutoConfigureWebTestClient
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Unit tests with WebTestClient")
class BookRestControllerUnitTest {

	@LocalServerPort
	int port;
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private BookService service;
	
	@Autowired
	private WebTestClient webTestClient;
	
	@BeforeAll
	public void setup() throws SSLException {	
		this.webTestClient = MockMvcWebTestClient.bindTo(mvc).build();
	}

	@Test
	@DisplayName("Recuperar libros con usuario no autenticado")
	void testGetBooks() throws Exception {
		
		List<Book> books = Arrays.asList(new Book("title1", "desc1"), new Book("title2", "desc2"));
		when(service.findAll()).thenReturn(books);

		webTestClient.get().uri("/api/books/").exchange()
			.expectStatus().isOk()
			.expectBody()
				.jsonPath("$[0].title").isEqualTo("title1")
				.jsonPath("$[1].title").isEqualTo("title2");
	}

	@Test
	@DisplayName("Añadir libro como usuario")
	void testCreateBook() throws Exception {

		Book request = new Book("Title", "The description");
		when(service.save(any(Book.class))).thenReturn(request);


		webTestClient.post().uri("/api/books/")
			.headers(headers -> headers.setBasicAuth("user", "pass"))
			.body(Mono.just(request), Book.class).exchange()
				.expectStatus().isCreated()
				.expectBody()
					.jsonPath("$.title").isEqualTo("Title")
					.jsonPath("$.description").isEqualTo("The description")
					.jsonPath("$.id").isNumber();
	}

	@Test
	@DisplayName("Borrar libro como administrador")
	void testDeleteBook() throws Exception {
		
		webTestClient.delete().uri("/api/books/1")
			.headers(headers -> headers.setBasicAuth("admin", "pass")).exchange()
				.expectStatus().isOk();
	}
}
