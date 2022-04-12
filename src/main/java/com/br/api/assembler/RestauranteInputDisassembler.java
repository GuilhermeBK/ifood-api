package com.br.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.api.model.input.RestauranteInput;
import com.br.domain.model.Cidade;
import com.br.domain.model.Cozinha;
import com.br.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);
	}
	
	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// com.br.domain.model.Cozinha was altered from 1 to 2
		restaurante.setCozinha(new Cozinha());
		
		if (restaurante.getEndereco() != null) {
			restaurante.getEndereco().setCidade(new Cidade());
		}
		
		modelMapper.map(restauranteInput, restaurante);
	}
	
}
