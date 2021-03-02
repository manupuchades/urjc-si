package es.urjc.si.app.rest.controllers;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import es.urjc.si.app.rest.dtos.responses.CartExpenditureResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CartExpenditure", description = "the Carts Expenditure API")
public interface IShoppingCartExpenditureQueryController {
	
	@Operation(summary = "Get all carts expenditure.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Expenditures were returned.", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CartExpenditureResponseDto.class))) }) })
	public Collection<CartExpenditureResponseDto> getCartsExpenditure();
	
	@Operation(summary = "Get cart expenditure by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the cart", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = CartExpenditureResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "404", description = "Shopping cart not found", content = @Content) })
	public ResponseEntity<CartExpenditureResponseDto> getProduct(
			@Parameter(description = "the shopping cart id") long id);

}
