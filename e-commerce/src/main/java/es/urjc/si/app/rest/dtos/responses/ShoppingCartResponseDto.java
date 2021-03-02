package es.urjc.si.app.rest.dtos.responses;

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
public class ShoppingCartResponseDto {

	private long id;

	private String customer;

	private boolean finalized;

	@Builder.Default
	private ArrayList<OrderResponseDto> orders = new ArrayList<>();

}
