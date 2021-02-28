package es.urjc.si.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInputDto {
	
	private long shoppingCartId;
	
	private long productId;

	private int quantity;

}
