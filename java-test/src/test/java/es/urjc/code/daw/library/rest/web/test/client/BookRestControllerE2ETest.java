package es.urjc.code.daw.library.rest.web.test.client;

import javax.net.ssl.SSLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import es.urjc.code.daw.library.book.Book;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;


@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("End to End tests with WebTestClient")
class BookRestControllerE2ETest {

	@LocalServerPort
	int port;
	
	@Autowired
	private WebTestClient webTestClient;
	
	@BeforeAll
	public void setup() throws SSLException {
		SslContext sslContext = SslContextBuilder.forClient()
									.trustManager(InsecureTrustManagerFactory.INSTANCE).build();
		
		HttpClient httpClient = HttpClient.create()
				.secure(ssl -> { ssl.sslContext(sslContext);})
				.baseUrl("https://localhost:" + port);
		
		ClientHttpConnector httpConnector = new ReactorClientHttpConnector(httpClient);

		this.webTestClient = WebTestClient.bindToServer(httpConnector).build();
	}

	@Test
	@DisplayName("Get all books as guest")
	void testGetBooks() throws Exception {

		webTestClient.get().uri("/api/books/").exchange()
			.expectStatus().isOk()
			.expectBody()
				.jsonPath("$[0].title").isEqualTo("SUEÑOS DE ACERO Y NEON")
				.jsonPath("$[1].title").isEqualTo("LA VIDA SECRETA DE LA MENTE")
				.jsonPath("$[2].title").isEqualTo("CASI SIN QUERER")
				.jsonPath("$[3].title").isEqualTo("TERMINAMOS Y OTROS POEMAS SIN TERMINAR")
				.jsonPath("$[4].title").isEqualTo("LA LEGIÓN PERDIDA");
	}

	@Test
	@DisplayName("Create new book as user")
	void testCreateBook() throws Exception {

		Book request = new Book("Title", "The description");

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
	@DisplayName("Delete book as admin")
	void testDeleteBook() throws Exception {
		
		Book request = new Book("Title", "The description");

		EntityExchangeResult<Book> response = webTestClient.post().uri("/api/books/")
			.headers(headers -> headers.setBasicAuth("user", "pass"))
			.body(Mono.just(request), Book.class).exchange()
				.expectStatus().isCreated().expectBody(Book.class).returnResult();
		
		long id = response.getResponseBody().getId();
		
		webTestClient.delete().uri("/api/books/{id}", id)
			.headers(headers -> headers.setBasicAuth("admin", "pass")).exchange()
				.expectStatus().isOk();
	}
}
