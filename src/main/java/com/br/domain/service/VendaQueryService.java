package com.br.domain.service;

import java.util.List;

import com.br.domain.filter.VendaDiariaFilter;
import com.br.domain.model.dto.VendaDiaria;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
	
}
