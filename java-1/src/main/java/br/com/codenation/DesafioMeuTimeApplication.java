package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {
    private final Hashtable<Long, Time> meusTimes;
    private final Hashtable<Long, Jogador> meusJogadores;

    DesafioMeuTimeApplication() {
        this.meusTimes = new Hashtable<>();
        this.meusJogadores = new Hashtable<>();
    }

    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        Optional.of(this.meusTimes)
                .filter(times -> !times.containsKey(id))
                .orElseThrow(IdentificadorUtilizadoException::new)
                .put(id, new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
    }

    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        Optional.ofNullable(this.meusTimes.get(idTime))
                .orElseThrow(TimeNaoEncontradoException::new)
                .incluirJogador(id);

        this.meusJogadores.put(id, new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
    }

    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) {
        Optional.ofNullable(this.meusJogadores.get(idJogador))
                .orElseThrow(JogadorNaoEncontradoException::new)
                .time.setCapitao(idJogador);
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
                .stream().collect(Collectors.toList());
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        Stream<Jogador> jogadorStream = Optional.ofNullable(this.meusTimes.get(idTime))
                .orElseThrow(TimeNaoEncontradoException::new)
                .getJogadores()
                .stream()
                .map(this.meusJogadores::get);

        return jogadorStream.max(Comparator.comparingInt(Jogador::getNivelHabilidade))
                .get()
                .id;
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) {
        Stream<Jogador> jogadorStream = Optional.ofNullable(this.meusTimes.get(idTime))
                .orElseThrow(TimeNaoEncontradoException::new)
                .getJogadores()
                .stream()
                .map(this.meusJogadores::get);

        Comparator<Jogador> comparator = new Comparator<Jogador>(){
            @Override
            public int compare(final Jogador o1, final Jogador o2){
                // let your comparator look up your car's color in the custom order
                return Integer.valueOf(
                        definedOrder.indexOf(o1.getColor()))
                        .compareTo(
                                Integer.valueOf(
                                        definedOrder.indexOf(o2.getColor())));
            }
        };
        Comparator.
        return jogadorStream.max(comparator) //TODO custom com desempate
                .get()
                .id;
    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {
        return meusTimes.keySet()
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) {
        Stream<Jogador> jogadorStream = Optional.ofNullable(this.meusTimes.get(idTime))
                .orElseThrow(TimeNaoEncontradoException::new)
                .getJogadores()
                .stream()
                .map(this.meusJogadores::get);

        return jogadorStream.max(Comparator.comparing(Jogador::getSalario)) //TODO custom com desempate
                .get()
                .id;
    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        return Optional.ofNullable(meusJogadores.get(idJogador))
                .orElseThrow(JogadorNaoEncontradoException::new)
                .getSalario();
    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {
        return meusJogadores.values().stream().sorted(Comparator.comparing(TODO)).limit(top).collect(Collectors.toList());
    }

    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
        Time casa = meusTimes.get(timeDaCasa);
        Time fora = meusTimes.get(timeDeFora);

        String corTimeDeFora = fora.getCorUniformePrincipal();
        if(casa.getCorUniformePrincipal().equals(corTimeDeFora)) corTimeDeFora = fora.getCorUniformeSecundario();
        return corTimeDeFora;
    }
}
