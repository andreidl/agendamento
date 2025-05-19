package com.duarte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duarte.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
}
