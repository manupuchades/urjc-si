package es.urjc.si.app.rest.dtos.responses;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartExpenditureResponseDto {

	private long cartId;

	private double expenditure;

}
