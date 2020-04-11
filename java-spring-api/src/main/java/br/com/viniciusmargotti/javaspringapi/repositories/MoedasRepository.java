package br.com.viniciusmargotti.javaspringapi.repositories;

import br.com.viniciusmargotti.javaspringapi.models.Moedas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoedasRepository extends JpaRepository<Moedas, Long> {
	Moedas findById(long id);
}