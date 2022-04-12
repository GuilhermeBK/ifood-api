package com.br.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.domain.exception.PermissaoNaoEncontradaException;
import com.br.domain.model.Permissao;
import com.br.domain.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public Permissao buscarOuFalhar(Long permissaoId) {
		return permissaoRepository.findById(permissaoId)
			.orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
	}
	
}
