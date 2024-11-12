package br.com.ecometric.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecometric.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
}
