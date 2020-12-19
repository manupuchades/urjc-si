package es.urjc.si.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.urjc.si.dtos.requests.review.ReviewRequestDto;
import es.urjc.si.dtos.responses.review.BookReviewResponseDto;
import es.urjc.si.dtos.responses.review.UserReviewResponseDto;
import es.urjc.si.models.Book;
import es.urjc.si.models.Review;
import es.urjc.si.models.User;

@Component
public class ReviewMapper {
		
	UserMapper userMapper;
	
	public ReviewMapper (UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
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
		return BookReviewResponseDto.builder().id(review.getId()).comment(review.getComment()).rating(review.getRating()).user(userMapper.mapToReviewUserResponseDto(review.getUser())).build();
	}
	
	public UserReviewResponseDto mapUserReview(Review review) {
		return UserReviewResponseDto.builder().id(review.getId()).comment(review.getComment()).rating(review.getRating()).bookId(review.getBook().getId()).build();
	}
	
	public Review map(ReviewRequestDto dto) {
		Book book = Book.builder().id(dto.getBookId()).build();
		User user = User.builder().nick(dto.getUserNick()).build();
		
		return Review.builder().book(book).comment(dto.getComment()).rating(dto.getRating()).user(user).build();
	}
}
