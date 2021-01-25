package es.urjc.code.daw.library.rest.mock.mvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.urjc.code.daw.library.book.Book;
import es.urjc.code.daw.library.book.BookService;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Unit tests with MockMVC")
class BookRestControllerUnitTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private BookService service;

	@Test
	@DisplayName("Recuperar libros con usuario no autenticado")
	void testGetBooks() throws Exception {
		List<Book> books = Arrays.asList(new Book("title1", "desc1"), new Book("title2", "desc2"));
		when(service.findAll()).thenReturn(books);

		mvc.perform(get("/api/books/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].title", equalTo("title1")))
				.andExpect(jsonPath("$[0].description", equalTo("desc1")))
				.andExpect(jsonPath("$[1].title", equalTo("title2")))
				.andExpect(jsonPath("$[1].description", equalTo("desc2")));

		verify(service, times(1)).findAll();

	}

	@Test
	@DisplayName("Añadir libro como usuario")
	@WithMockUser(username = "user", password = "pass", roles = "USER")
	void testCreateBook() throws Exception {
		Book book = new Book("title1", "desc1");
		when(service.save(any(Book.class))).thenReturn(book);

		mvc.perform(post("/api/books/").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(book))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.title", equalTo("title1")))
				.andExpect(jsonPath("$.description", equalTo("desc1")));

		verify(service, times(1)).save(any(Book.class));
	}

	@Test
	@DisplayName("Borrar libro como administrador")
	@WithMockUser(username = "admin", password = "pass", roles = "ADMIN")
	void testDeleteBook() throws Exception {

		mvc.perform(delete("/api/books/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(""));

		verify(service, times(1)).delete(Long.valueOf(1));
	}
}
