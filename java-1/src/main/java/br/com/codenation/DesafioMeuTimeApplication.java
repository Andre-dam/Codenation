package br.com.codenation;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
    private final HashMap<Long, Time> meusTimes = new HashMap<>();
    private final HashMap<Long, Jogador> meusJogadores = new HashMap<>();

    @Desafio("incluirTime")
    public void incluirTime(
            Long id,
            String nome,
            LocalDate dataCriacao,
            String corUniformePrincipal,
            String corUniformeSecundario) {
        Optional.of(this.meusTimes)
                .filter(times -> !times.containsKey(id))
                .orElseThrow(IdentificadorUtilizadoException::new)
                .put(id, new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
    }

    @Desafio("incluirJogador")
    public void incluirJogador(
            Long id,
            Long idTime,
            String nome,
            LocalDate dataNascimento,
            Integer nivelHabilidade,
            BigDecimal salario) {

        if (meusJogadores.containsKey(id)) throw new IdentificadorUtilizadoException();
        Time time = Optional.ofNullable(this.meusTimes.get(idTime))
                .orElseThrow(TimeNaoEncontradoException::new);

        this.meusJogadores.put(id, new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
        time.incluirJogador(id);
    }

    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) {
        Long idTime = Optional.ofNullable(this.meusJogadores.get(idJogador))
                .map(Jogador::getIdTime)
                .orElseThrow(JogadorNaoEncontradoException::new);

        Optional.ofNullable(this.meusTimes.get(idTime))
                .ifPresent(time -> time.setCapitao(idJogador));
    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) {
        return Optional.ofNullable(this.meusTimes.get(idTime))
                .orElseThrow(TimeNaoEncontradoException::new)
                .getCapitao();
    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) {
        return Optional.ofNullable(this.meusJogadores.get(idJogador))
                .orElseThrow(JogadorNaoEncontradoException::new)
                .getNome();
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) {
        return Optional.ofNullable(this.meusTimes.get(idTime))
                .orElseThrow(TimeNaoEncontradoException::new)
                .getNome();
    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) {
        return Optional.ofNullable(this.meusTimes.get(idTime))
                .orElseThrow(TimeNaoEncontradoException::new)
                .getJogadores()
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        Stream<Jogador> jogadorStream = Optional.ofNullable(this.meusTimes.get(idTime))
                .orElseThrow(TimeNaoEncontradoException::new)
                .getJogadores()
                .stream()
                .map(this.meusJogadores::get);

        return jogadorStream
                .max(Comparator.comparing(Jogador::getNivelHabilidade))
                .map(Jogador::getId)
                .get();
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) {
        Stream<Jogador> jogadorStream = Optional.ofNullable(this.meusTimes.get(idTime))
                .orElseThrow(TimeNaoEncontradoException::new)
                .getJogadores()
                .stream()
                .map(this.meusJogadores::get);

        return jogadorStream
                .sorted(Comparator.comparing(Jogador::getDataNascimento).thenComparing(Jogador::getId)) //ok
                .map(Jogador::getId)
                .findFirst()
                .get();
    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {
        return meusTimes.keySet().stream().sorted().collect(Collectors.toList());
    }

    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) {
        Stream<Jogador> jogadorStream = Optional.ofNullable(this.meusTimes.get(idTime))
                .orElseThrow(TimeNaoEncontradoException::new)
                .getJogadores()
                .stream()
                .map(this.meusJogadores::get);

        return jogadorStream
                .sorted(Comparator.comparing(Jogador::getSalario).reversed().thenComparing(Jogador::getId))
                .map(Jogador::getId)
                .findFirst()
                .get();
    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        return Optional.ofNullable(meusJogadores.get(idJogador))
                .orElseThrow(JogadorNaoEncontradoException::new)
                .getSalario();
    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {
        return meusJogadores.values()
                .stream()
                .sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed().thenComparing(Jogador::getId))
                .map(Jogador::getId)
                .limit(top)
                .collect(Collectors.toList());
    }

    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
        Time casa = Optional.ofNullable(meusTimes.get(timeDaCasa)).orElseThrow(TimeNaoEncontradoException::new);
        Time fora = Optional.ofNullable(meusTimes.get(timeDeFora)).orElseThrow(TimeNaoEncontradoException::new);

        return Optional.of(fora.getCorUniformePrincipal())
                .filter(uniforme -> !casa.getCorUniformePrincipal().equals(uniforme))
                .orElse(fora.getCorUniformeSecundario());
    }
}
