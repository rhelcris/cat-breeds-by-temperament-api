package br.com.itau.catapi.services;

import br.com.itau.catapi.beans.Raca;

import java.util.List;
import java.util.Optional;

public interface RacaService {

    Optional<List<Raca>> buscarPeloTemperamento(String temperamento);

}
