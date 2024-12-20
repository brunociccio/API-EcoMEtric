package br.com.ecometric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecometric.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
