package es.urjc.si.app.rest.controllers;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import es.urjc.si.app.rest.dtos.responses.ProductResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product Queries", description = "the Products Queries API")
public interface IProductQueryController {

	@Operation(summary = "Get all products.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products were returned.", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductResponseDto.class))) }) })
	public Collection<ProductResponseDto> getProducts();

	@Operation(summary = "Get a product by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the product", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
	public ResponseEntity<ProductResponseDto> getProduct(
			@Parameter(description = "the product id") long id);

}
