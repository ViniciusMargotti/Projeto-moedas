package br.com.viniciusmargotti.javaspringapi.repositories;

import br.com.viniciusmargotti.javaspringapi.models.Moedas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoedasRepository extends JpaRepository<Moedas, Long> {
	Moedas findById(long id);

	@Query("select m from Moedas m where m.name =?1")
	List<Moedas> findByName(String name);
}