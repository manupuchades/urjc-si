package es.urjc.si.infra.db.h2.entities;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SHOPPING_CART")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String customer;
	
	@Column(columnDefinition = "boolean default false")
	private boolean finalized;
	
	@Builder.Default
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<Order> product_orders = Collections.emptyList();

}
