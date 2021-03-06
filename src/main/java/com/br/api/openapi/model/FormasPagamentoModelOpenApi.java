package com.br.api.openapi.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import com.br.api.model.FormaPagamentoModel;

import java.util.List;

@ApiModel("FormasPagamentoModel")
@Data
public class FormasPagamentoModelOpenApi {

	private FormasPagamentoEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("FormasPagamentoEmbeddedModel")
	@Data
	public class FormasPagamentoEmbeddedModelOpenApi {

		private List<FormaPagamentoModel> formasPagamento;

	}

}
