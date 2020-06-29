package br.com.itau.catapi.controllers.impl;

import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.controllers.RacaController;
import br.com.itau.catapi.services.RacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RacaControllerImpl implements RacaController {

    private RacaService racaService;

    @Autowired
    public RacaControllerImpl(RacaService racaService) {
        this.racaService = racaService;
    }

    @Override
    public ResponseEntity<List<Raca>> buscarPeloTemperamento(String temperamento) {
        Optional<List<Raca>> racas = racaService.buscarPeloTemperamento(temperamento);
        return racas.isPresent() ? ResponseEntity.ok(racas.get()) : ResponseEntity.notFound().build();
    }

}
