package es.urjc.si.domain.business.models;

import es.urjc.si.infra.db.h2.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	private Long id;

	private Product product;

	private Long quantity;

	private double amount;

}
