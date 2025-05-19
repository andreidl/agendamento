package com.duarte.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.duarte.model.Transferencia;

@Service
public class TaxaService {
	public Transferencia calcularTaxa(Transferencia dto) {
		dto.setDataAgendamento(LocalDate.now());
		long dias = ChronoUnit.DAYS.between(dto.getDataAgendamento(), dto.getDataTransferencia());

		if (dias == 0) {
			dto.setTaxa(BigDecimal.valueOf(3).add(dto.getValor().multiply(BigDecimal.valueOf(0.025))));
			return dto;
		}
		if (dias >= 1 && dias <= 10) {
			dto.setTaxa(BigDecimal.valueOf(12));
			return dto;
		}
		if (dias >= 11 && dias <= 20) {
			dto.setTaxa(dto.getValor().multiply(BigDecimal.valueOf(0.082)));
			return dto;
		}
		if (dias >= 21 && dias <= 30) {
			dto.setTaxa(dto.getValor().multiply(BigDecimal.valueOf(0.069)));
			 return dto;
		}
		if (dias >= 31 && dias <= 40) {
			dto.setTaxa(dto.getValor().multiply(BigDecimal.valueOf(0.047)));
			 return dto;
		}
		if (dias >= 41 && dias <= 50) {
			dto.setTaxa(dto.getValor().multiply(BigDecimal.valueOf(0.017)));
			return dto;
		}

		throw new IllegalArgumentException("Intervalo de dias inválido para cálculo de taxa");
	}
}
