package br.com.itau.catapi.repositories;

import br.com.itau.catapi.beans.Raca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RacaRepository extends JpaRepository<Raca, String> {

    Optional<List<Raca>> findByTemperamentoContains(String temperamento);

}
