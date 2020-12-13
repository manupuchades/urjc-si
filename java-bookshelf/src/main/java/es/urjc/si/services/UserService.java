package es.urjc.si.services;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.si.exceptions.UserNotFoundException;
import es.urjc.si.models.Review;
import es.urjc.si.models.User;
import es.urjc.si.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(long id) {
		return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User deleteById(long id) {
		User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
		this.userRepository.deleteById(id);
		return user;
	}
	
	public User update(User input) {
		User user = userRepository.findByNick(input.getNick()).orElseThrow(UserNotFoundException::new);
		user.setEmail(input.getEmail());
		return userRepository.save(user);
	}
	
	public Collection<Review> getReviews(long id) {
		return findById(id).getReviews();
	}
	
	public User findByNick(String nick) {
		return userRepository.findByNick(nick).orElseThrow(UserNotFoundException::new);
	}
}
