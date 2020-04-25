package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
    private final Hashtable<Long, Time> meusTimes;

    DesafioMeuTimeApplication() {
        this.meusTimes = new Hashtable<>();
    }

    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        if (meusTimes.containsKey(id)) throw new IdentificadorUtilizadoException();

        //build time
        meusTimes.put(id, new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
    }

    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        //Time time = times.get(idTime);
        Optional.ofNullable(meusTimes.get(idTime))
                .map(time -> Optional.of(time.jogadores))
                .orElseThrow(TimeNaoEncontradoException::new)
                    .filter(jogadores -> !jogadores.containsKey(id))
                    .orElseThrow(IdentificadorUtilizadoException::new)
                    .put(id, new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
    }

    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
        throw new UnsupportedOperationException();
    }

}
