package com.br.api.openapi.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import com.br.api.model.PedidoResumoModel;

import java.util.List;

@ApiModel("PedidosResumoModel")
@Getter
@Setter
public class PedidosResumoModelOpenApi {

	private PedidosResumoEmbeddedModelOpenApi _embedded;
	private Links _links;
	private PageModelOpenApi page;

	@ApiModel("PedidosResumoEmbeddedModel")
	@Data
	public class PedidosResumoEmbeddedModelOpenApi {

		private List<PedidoResumoModel> pedidos;

	}

}
