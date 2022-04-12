package com.br.api.openapi.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import com.br.api.model.EstadoModel;

import java.util.List;

@ApiModel("EstadosModel")
@Data
public class EstadosModelOpenApi {

	private EstadosEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("EstadosEmbeddedModel")
	@Data
	public class EstadosEmbeddedModelOpenApi {

		private List<EstadoModel> estados;

	}

}
