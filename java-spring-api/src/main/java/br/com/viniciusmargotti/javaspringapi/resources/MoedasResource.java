package br.com.viniciusmargotti.javaspringapi.resources;

import br.com.viniciusmargotti.javaspringapi.models.Moedas;
import br.com.viniciusmargotti.javaspringapi.repositories.MoedasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api")
@Description(value="API REST Moedas")
public class MoedasResource {
	
	@Autowired
	MoedasRepository moedasRepository;
	
	@Description(value="Retorna uma lista de moedas")
	@GetMapping("/moedas")
	public List<Moedas> listaCidades(){
		return moedasRepository.findAll();
	}

	
	@Description(value="Salva uma moeda")
	@PostMapping("/moeda")
	public Moedas salvaCidade(@RequestBody @Valid Moedas cidade) {
		return moedasRepository.save(cidade);
	}

}