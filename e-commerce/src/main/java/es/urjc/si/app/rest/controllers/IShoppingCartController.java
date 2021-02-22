package es.urjc.si.app.rest.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.si.app.rest.dtos.requests.ShoppingCartRequestDto;
import es.urjc.si.app.rest.dtos.responses.ShoppingCartResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/api/shoppingcarts")
public interface IShoppingCartController {

	@Operation(summary = "Create new shopping cart.")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Shopping cart to be created", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCartRequestDto.class)))
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Shopping cart created succesfully."),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content) })
	@PostMapping("/")
	public ResponseEntity<ShoppingCartResponseDto> createShoppingCart(
			@Parameter(description = "the shopping cart") @Valid @RequestBody ShoppingCartRequestDto shoppingCart);

	@Operation(summary = "Finalize shopping cart by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Shopping cart finalized", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCartResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "404", description = "Shopping cart not found", content = @Content),
			@ApiResponse(responseCode = "409", description = "Shopping cart could not be finalized", content = @Content) })
	@PatchMapping("/{id}")
	public ResponseEntity<ShoppingCartResponseDto> finalizeShoppingCart(
			@Parameter(description = "the shoppingCart id") @PathVariable long id);
	

	@Operation(summary = "Get a shopping cart by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the shopping cart", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCartResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "404", description = "Shopping cart not found", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<ShoppingCartResponseDto> getShoppingCart(
			@Parameter(description = "the shoppingCart id") @PathVariable long id);

	@Operation(summary = "Delete shopping cart")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Shopping cart deleted succesfully."),
			@ApiResponse(responseCode = "404", description = "Shopping cart not found", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<ShoppingCartResponseDto> deleteShoppingCart(
			@Parameter(description = "the shopping cart id") @PathVariable long id);

	@Operation(summary = "Add product to shopping cart.")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Shopping cart to be created", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCartRequestDto.class)))
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Shopping cart created succesfully."),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content) })
	@PostMapping("/{cart_id}/product/{prod_id}/{prod_quantity}")
	public ResponseEntity<ShoppingCartResponseDto> adProductToShoppingCart(
			@Parameter(description = "the shopping cart id") @PathVariable long cart_id,
			@Parameter(description = "the product id") @PathVariable long prod_id,
			@Parameter(description = "the quantity") @PathVariable long prod_quantity);

	@Operation(summary = "Delete product from shopping cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Product deleted succesfully from shopping cart."),
			@ApiResponse(responseCode = "404", description = "Product or shopping cart not found", content = @Content) })
	@DeleteMapping("/{cart_id}/product/{prod_id}")
	public ResponseEntity<ShoppingCartResponseDto> deleteShoppingCart(
			@Parameter(description = "the shopping cart id") @PathVariable long cart_id,
			@Parameter(description = "the product id") @PathVariable long prod_id);

}
