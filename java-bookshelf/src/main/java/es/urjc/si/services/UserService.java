package es.urjc.si.services;

import static java.util.Collections.emptyList;

import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.urjc.si.exceptions.UserNotFoundException;
import es.urjc.si.models.Review;
import es.urjc.si.models.User;
import es.urjc.si.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{

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
		User user = findByNick(input.getNick());
		user.setEmail(input.getEmail());
		return userRepository.save(user);
	}
	
	public Collection<Review> getReviews(long id) {
		return findById(id).getReviews();
	}
	
	public User findByNick(String nick) {
		return userRepository.findByNick(nick).orElseThrow(UserNotFoundException::new);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(">>>>> USER LOADUSER: " + username);
		User user = findByNick(username);
		return new org.springframework.security.core.userdetails.User(user.getNick(), user.getPassword(), emptyList());
	}
}
