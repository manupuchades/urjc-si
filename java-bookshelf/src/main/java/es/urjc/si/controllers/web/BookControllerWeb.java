package es.urjc.si.controllers.web;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import es.urjc.si.models.Book;
import es.urjc.si.services.BookService;
import es.urjc.si.services.ImageService;
import es.urjc.si.services.ReviewService;
import es.urjc.si.services.UserSession;

@Controller
public class BookControllerWeb {

	private static final String BOOKS_FOLDER = "books";

	@Autowired
	private UserSession userSession;

	@Autowired
	private ImageService imageService;

	@Autowired
	private BookService bookService;

	@Autowired
	private ReviewService reviewService;

	@GetMapping("/")
	public String showBooks(Model model, HttpSession session) {
		model.addAttribute("books", bookService.findAll());
		model.addAttribute("welcome", session.isNew());
		return "index";
	}

	@GetMapping("/book/new")
	public String newbookForm() {
		return "new_book";
	}

	@PostMapping("/book/new")
	public String newBook(Model model, Book book, MultipartFile image) throws IOException {
		bookService.save(book);
		imageService.saveImage(BOOKS_FOLDER, book.getId(), image);
		model.addAttribute("title", book.getTitle());
		return "saved_book";
	}

	@GetMapping("/book/{id}")
	public String showBook(Model model, @PathVariable long id) {
		Book book = bookService.findById(id);
		model.addAttribute("book", book);
		model.addAttribute("reviews", reviewService.findAll(id));
		model.addAttribute("user", userSession.getUser());
		userSession.setBook(book);
		return "show_book";
	}

	@GetMapping("/book/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable int id) throws MalformedURLException {
		return imageService.createResponseFromImage(BOOKS_FOLDER, id);
	}

	@GetMapping("/book/{id}/delete")
	public String deleteBook(Model model, @PathVariable long id) throws IOException {
		bookService.deleteById(id);
		return "deleted_book";
	}
}
