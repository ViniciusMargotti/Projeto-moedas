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
	public List<Moedas> listaMoedas(){
		return moedasRepository.findAll();
	}

	@Description(value="Retorna uma lista de moedas pelo identificador")
	@GetMapping("/moedas/{name}")
	public List<Moedas> listaMoedas( @PathVariable("name") String name){
		return moedasRepository.findByName(name);
	}

	@Description(value="Salva uma moeda")
	@PostMapping("/moeda")
	public Moedas salvaMoeda(@RequestBody @Valid Moedas moeda) {
		return moedasRepository.save(moeda);
	}

}