package com.duarte;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.duarte.model.Transferencia;
import com.duarte.repository.TransferenciaRepository;
import com.duarte.service.TaxaService;

@SpringBootApplication
public class AgendamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendamentoApplication.class, args);
	}
	
	@Bean
    CommandLineRunner nit(TransferenciaRepository repository, TaxaService taxaService) {
        return args -> {
            
            LocalDate hoje = LocalDate.now();
            BigDecimal valor = new BigDecimal("1000");

            List<LocalDate> datasTransferencia = List.of(
                    LocalDate.parse("2025-05-16"),
                    LocalDate.parse("2025-05-17"),
                    LocalDate.parse("2025-05-27"),
                    LocalDate.parse("2025-06-06"),
                    LocalDate.parse("2025-06-16"),
                    LocalDate.parse("2025-06-26")
            );

            for (LocalDate data : datasTransferencia) {
                Transferencia transferencia = new Transferencia("0123456789", "9876543210", valor, data);
                transferencia.setDataAgendamento(hoje); // explicitamente definir a data de agendamento
                Transferencia comTaxa = taxaService.calcularTaxa(transferencia);
                repository.save(comTaxa);
            }
        };
    }

}
