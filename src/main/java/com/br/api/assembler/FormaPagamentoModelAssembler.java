package com.br.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.br.api.AlgaLinks;
import com.br.api.controller.FormaPagamentoController;
import com.br.api.model.FormaPagamentoModel;
import com.br.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelAssembler
		extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public FormaPagamentoModelAssembler() {
		super(FormaPagamentoController.class, FormaPagamentoModel.class);
	}

	@Override
	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		FormaPagamentoModel formaPagamentoModel =
				createModelWithId(formaPagamento.getId(), formaPagamento);

		modelMapper.map(formaPagamento, formaPagamentoModel);

		formaPagamentoModel.add(algaLinks.linkToFormasPagamento("formasPagamento"));

		return formaPagamentoModel;
	}

	@Override
	public CollectionModel<FormaPagamentoModel> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
		return super.toCollectionModel(entities)
				.add(algaLinks.linkToFormasPagamento());
	}

}
