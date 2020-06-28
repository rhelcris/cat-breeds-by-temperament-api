package br.com.itau.catapi.controllers;

import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.services.RacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("racas")
public class RacaController {

    private RacaService racaService;

    @Autowired
    public RacaController(RacaService racaService) {
        this.racaService = racaService;
    }

    @GetMapping("{temperamento}")
    public ResponseEntity<List<Raca>> buscarPeloTemperamento(@PathVariable("temperamento") String temperamento) {
        Optional<List<Raca>> racas = racaService.buscarPeloTemperamento(temperamento);
        return racas.isPresent() ? ResponseEntity.ok(racas.get()) : ResponseEntity.notFound().build();
    }


}
