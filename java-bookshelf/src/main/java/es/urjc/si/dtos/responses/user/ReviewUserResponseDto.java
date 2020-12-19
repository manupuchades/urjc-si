package es.urjc.si.dtos.responses.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewUserResponseDto {
	
    private String nick;
    private String email;

}
