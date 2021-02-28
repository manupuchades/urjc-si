package es.urjc.si.app.rest.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import es.urjc.si.app.rest.dtos.requests.ProductRequestDto;
import es.urjc.si.app.rest.dtos.responses.ProductResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Products", description = "the Products API")
public interface IProductController {

	@Operation(summary = "Get all products.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products were returned.", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductResponseDto.class))) }) })
	public Collection<ProductResponseDto> getProducts();

	@Operation(summary = "Create new product.")
	@RequestBody(description = "Product to be created", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductRequestDto.class)))
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Product created succesfully."),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content) })
	public ResponseEntity<ProductResponseDto> createProduct(
			@Parameter(description = "the product") @Valid @RequestBody ProductRequestDto productRequestDto);

	@Operation(summary = "Get a product by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the product", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
	public ResponseEntity<ProductResponseDto> getProduct(
			@Parameter(description = "the product id") long id);

	@Operation(summary = "Delete product by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Product deleted succesfully."),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
	public ResponseEntity<ProductResponseDto> deleteProduct(
			@Parameter(description = "the product id") long id);

}
