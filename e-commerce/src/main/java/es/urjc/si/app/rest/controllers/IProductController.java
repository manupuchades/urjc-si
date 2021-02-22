package es.urjc.si.app.rest.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

@RequestMapping("/api/products")
public interface IProductController {

	@Operation(summary = "Get all products.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products were returned.", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductResponseDto.class))) }) })
	@GetMapping("/")
	public Collection<ProductResponseDto> getProducts();

	@Operation(summary = "Create new product.")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Product to be created", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductRequestDto.class)))
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Product created succesfully."),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content) })
	@PostMapping("/")
	public ResponseEntity<ProductResponseDto> createProduct(
			@Parameter(description = "the product") @Valid @RequestBody ProductRequestDto product);

	@Operation(summary = "Get a product by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the product", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> getProduct(
			@Parameter(description = "the product id") @PathVariable long id);

	@Operation(summary = "Delete product by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Product deleted succesfully."),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<ProductResponseDto> deleteProduct(
			@Parameter(description = "the product id") @PathVariable long id);

}
