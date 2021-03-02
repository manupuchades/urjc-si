package es.urjc.si.app.rest.controllers;

import org.springframework.http.ResponseEntity;

import es.urjc.si.app.rest.dtos.responses.ShoppingCartResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ShoppingCart", description = "the Shopping Cart API")
public interface IShoppingCartQueryController {

	@Operation(summary = "Get a shopping cart by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the shopping cart", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCartResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "404", description = "Shopping cart not found", content = @Content) })
	public ResponseEntity<ShoppingCartResponseDto> getShoppingCart(
			@Parameter(description = "the shoppingCart id") long id);

}
