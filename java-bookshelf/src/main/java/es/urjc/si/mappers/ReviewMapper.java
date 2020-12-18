package es.urjc.si.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.urjc.si.dtos.requests.ReviewRequestDto;
import es.urjc.si.dtos.responses.BookReviewResponseDto;
import es.urjc.si.dtos.responses.UserReviewResponseDto;
import es.urjc.si.models.Book;
import es.urjc.si.models.Review;
import es.urjc.si.models.User;

@Component
public class ReviewMapper {
		
	@Autowired
	UserMapper userMapper;
	
	public Collection<UserReviewResponseDto> mapUserReview(Collection<Review> reviews) {
		Collection<UserReviewResponseDto> responseDto = new ArrayList<>();

		for (Review review : reviews) {
			responseDto.add(mapUserReview(review));
		}

		return responseDto;
	}
	
	public Collection<BookReviewResponseDto> mapBookReview(Collection<Review> reviews) {
		Collection<BookReviewResponseDto> responseDto = new ArrayList<>();

		for (Review review : reviews) {
			responseDto.add(mapBookReview(review));
		}

		return responseDto;
	}
	
	public BookReviewResponseDto mapBookReview(Review review) {
		return new BookReviewResponseDto(review.getId(), review.getComment(), review.getRating(), userMapper.map(review.getUser()));
	}
	
	public UserReviewResponseDto mapUserReview(Review review) {
		return new UserReviewResponseDto(review.getId(), review.getComment(), review.getRating(), review.getBook().getId());
	}
	
	public Review map(ReviewRequestDto dto) {
		Book book = new Book ();
		book.setId(dto.getBookId());
		
		User user = new User();
		user.setNick(dto.getUserNick());
		
		return new Review(book, dto.getComment(), dto.getRating(), user);
	}
}
