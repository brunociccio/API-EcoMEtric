package br.com.ecometric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecometric.model.Monitoramento;

@Repository
public interface MonitoramentoRepository extends JpaRepository<Monitoramento, Long> {
}
