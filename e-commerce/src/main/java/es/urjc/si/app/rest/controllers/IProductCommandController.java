package es.urjc.si.app.rest.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import es.urjc.si.app.rest.dtos.requests.ProductRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product Commands", description = "the Products Commands API")
public interface IProductCommandController {

	@Operation(summary = "Create new product.")
	@RequestBody(description = "Product to be created", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductRequestDto.class)))
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request received")})
	public ResponseEntity<String> createProduct(
			@Parameter(description = "the product") @Valid @RequestBody ProductRequestDto productRequestDto);

	@Operation(summary = "Delete product by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Request received") })
	public ResponseEntity<String> deleteProduct(
			@Parameter(description = "the product id") long id);

}
