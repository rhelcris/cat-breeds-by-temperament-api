package br.com.itau.catapi.services;

import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.repositories.RacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RacaService {

    private RacaRepository racaRepository;

    @Autowired
    RacaService(RacaRepository racaRepository) {
        this.racaRepository = racaRepository;
    }

    public Optional<List<Raca>> buscarPeloTemperamento(String temperamento) {
        Optional<List<Raca>> temperamentosOptional = this.racaRepository.findByTemperamentoContains(temperamento);
        return temperamentosOptional;
    }
}
