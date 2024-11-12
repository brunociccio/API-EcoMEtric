package br.com.ecometric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecometric.model.Projetos;

@Repository
public interface ProjetosRepository extends JpaRepository<Projetos, Long> {
}
