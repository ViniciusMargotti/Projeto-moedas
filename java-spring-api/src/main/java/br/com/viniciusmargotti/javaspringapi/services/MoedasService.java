package br.com.viniciusmargotti.javaspringapi.services;

import br.com.viniciusmargotti.javaspringapi.models.Moedas;
import br.com.viniciusmargotti.javaspringapi.repositories.MoedasRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class MoedasService {

    @Autowired
    private MoedasRepository moedasRepository;

    @Autowired
    RestTemplate restTemplate;

    @Scheduled(fixedRate = 1000)
    public void popularMoedas() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("x-rapidapi-host", "coinranking1.p.rapidapi.com");
        headers.add("x-rapidapi-key", "2073b6ab69mshd1078d4e0db9b62p1ab23bjsn93af20c5ad49");
        HttpEntity<String> entity=new HttpEntity<String>(headers);
        String json = restTemplate.exchange("https://coinranking1.p.rapidapi.com/coin/1",
                HttpMethod.GET,entity,String.class).getBody();

        HashMap o = objectMapper.readValue(json,HashMap.class);
        HashMap moeda = (HashMap) o.get("data");
        HashMap coin = (HashMap) moeda.get("coin");
        Moedas moedas = new Moedas(coin.get("name").toString(),
                coin.get("description").toString(),
                coin.get("color").toString(),
                Double.parseDouble(coin.get("price").toString()));

        moedasRepository.save(moedas);

        String json2 = restTemplate.exchange("https://coinranking1.p.rapidapi.com/coin/2",
                HttpMethod.GET,entity,String.class).getBody();

        HashMap o2 = objectMapper.readValue(json2,HashMap.class);
        HashMap moeda2 = (HashMap) o2.get("data");
        HashMap coin2 = (HashMap) moeda2.get("coin");
        Moedas moedas2 = new Moedas(coin2.get("name").toString(),
                coin2.get("description").toString(),
                coin2.get("color").toString(),
                Double.parseDouble(coin2.get("price").toString()));

        moedasRepository.save(moedas2);
    }
}
