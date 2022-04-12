package com.br.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.br.api.AlgaLinks;
import com.br.api.controller.CozinhaController;
import com.br.api.model.CozinhaModel;
import com.br.domain.model.Cozinha;

@Component
public class CozinhaModelAssembler
		extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public CozinhaModelAssembler() {
		super(CozinhaController.class, CozinhaModel.class);
	}

	@Override
	public CozinhaModel toModel(Cozinha cozinha) {
		CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
		modelMapper.map(cozinha, cozinhaModel);

		cozinhaModel.add(algaLinks.linkToCozinhas("cozinhas"));

		return cozinhaModel;
	}

}
