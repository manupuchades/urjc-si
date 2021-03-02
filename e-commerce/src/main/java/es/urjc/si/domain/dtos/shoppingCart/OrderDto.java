package es.urjc.si.domain.dtos.shoppingCart;

import es.urjc.si.domain.dtos.product.FullProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
	
	private long id;

	private FullProductDto product;
	
	private int quantity;

}
