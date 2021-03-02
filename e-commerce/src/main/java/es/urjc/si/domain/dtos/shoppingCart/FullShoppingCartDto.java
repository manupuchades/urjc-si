package es.urjc.si.domain.dtos.shoppingCart;

import java.util.ArrayList;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullShoppingCartDto {
	
	private long id;
	
	private String customer;
		
	private boolean finalized;
	
	@Builder.Default
	private ArrayList<OrderDto> orders = new ArrayList<>();

}
