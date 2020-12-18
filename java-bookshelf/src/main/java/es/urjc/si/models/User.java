package es.urjc.si.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	@Column(unique = true)
	private String nick;
	@JsonManagedReference
	@OneToMany(mappedBy = "user")
	private List<Review> reviews;

	public User() {
		super();
	}

	public User(String email, String nick) {
		super();
		this.email = email;
		this.nick = nick;
	}
	
	public User(String email, String nick, List<Review> reviews) {
		this(email, nick);
		this.reviews = new ArrayList<>(reviews);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", nick=" + nick + ", reviews=" + reviews + "]";
	}

}
