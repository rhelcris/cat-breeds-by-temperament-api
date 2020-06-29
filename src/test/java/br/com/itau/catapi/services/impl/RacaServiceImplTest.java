package br.com.itau.catapi.services.impl;

import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.repositories.RacaRepository;
import br.com.itau.catapi.services.RacaService;
import br.com.itau.catapi.services.impl.RacaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class RacaServiceImplTest {

    private RacaService racaService;

    @MockBean
    private RacaRepository repository;

    @BeforeEach
    public void setUp() {
        this.racaService = new RacaServiceImpl(repository);
    }


    @Test
    @DisplayName("Deve buscar as raças dado um temperamento")
    public void deveBuscarUmaRacaPeloTemperamentoTest() {
        // Cenário
        String temperamento = "Energetic";

        Optional<List<Raca>> racas = retornarRacasParaMock();
        BDDMockito.when( repository.findByTemperamentoContainsIgnoreCase(Mockito.anyString()) ).thenReturn( racas );

        // Ação
        Optional<List<Raca>> returnedRaca = racaService.buscarPeloTemperamento(temperamento);

        // Verificação
        assertThat( returnedRaca.isPresent() ).isTrue();
        assertThat( returnedRaca.get().size() ).isEqualTo( 4 );

        Mockito.verify(repository, times(1)).findByTemperamentoContainsIgnoreCase( Mockito.anyString() );
    }

    public Optional<List<Raca>> retornarRacasParaMock() {
        Raca raca1 = Raca.builder().id("abys").nome("Abyssinian").origem("Egypt").temperamento("Active, Energetic, Independent, Intelligent, Gentle").build();
        Raca raca2 = Raca.builder().id("beng").nome("Bengal").origem("United States").temperamento("Alert, Agile, Energetic, Demanding, Intelligent").build();
        Raca raca3 = Raca.builder().id("orie").nome("Oriental").origem("United States").temperamento("Energetic, Affectionate, Intelligent, Social, Playful, Curious").build();
        Raca raca4 = Raca.builder().id("siam").nome("Siamese").origem("Thailand").temperamento("Active, Agile, Clever, Sociable, Loving, Energetic").build();

        return Optional.of(Arrays.asList(raca1, raca2, raca3, raca4));
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia quando não encontrar raças pelo temperamento")
    public void deveRetornarListaVaziaQuandoNaoEncontrarRacasPeloTemperamento() {
        // Cenário
        String temperamento = "Energetic";
        BDDMockito.when( repository.findByTemperamentoContainsIgnoreCase(temperamento) ).thenReturn( Optional.empty() );

        // Ação
        Optional<List<Raca>> returnedListRacas = racaService.buscarPeloTemperamento(temperamento);

        // Verificação
        assertThat( returnedListRacas.isPresent() ).isFalse();
        Mockito.verify(repository, times(1)).findByTemperamentoContainsIgnoreCase(temperamento);
    }

}
