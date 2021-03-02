package es.urjc.si.domain.dtos.shoppingCart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOrderCommandDto {
	
	private long shoppingCartId;
	
	private long productId;

}
