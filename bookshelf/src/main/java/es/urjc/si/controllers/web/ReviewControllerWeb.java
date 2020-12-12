package es.urjc.si.controllers.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.urjc.si.models.Review;
import es.urjc.si.services.ReviewService;
import es.urjc.si.services.UserSession;

@Controller
public class ReviewControllerWeb {

	@Autowired
	private UserSession userSession;
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/review/new")
	public String newReview(Model model, Review review) throws IOException {
		review.setBook_id(userSession.getBook().getId());
		reviewService.save(review);
		userSession.setUser(review.getUser());
		model.addAttribute("user", userSession.getUser());

		return "redirect:/book/" + userSession.getBook().getId();
	}
	
	@GetMapping("/review/{id}/delete")
	public String deleteReview(Model model, @PathVariable long id) throws IOException {
		reviewService.deleteById(id);
		model.addAttribute("user", userSession.getUser());
		
		return "redirect:/book/" + userSession.getBook().getId();
	}
}
