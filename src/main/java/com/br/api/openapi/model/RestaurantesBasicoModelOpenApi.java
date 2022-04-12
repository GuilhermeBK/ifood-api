package com.br.api.openapi.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import com.br.api.model.RestauranteBasicoModel;

import java.util.List;

@ApiModel("RestaurantesBasicoModel")
@Data
public class RestaurantesBasicoModelOpenApi {

	private RestaurantesEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("RestaurantesEmbeddedModel")
	@Data
	public class RestaurantesEmbeddedModelOpenApi {

		private List<RestauranteBasicoModel> restaurantes;

	}

}
