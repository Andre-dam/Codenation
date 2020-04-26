package br.com.codenation;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

public class Time {
    public long getId() {
        return id;
    }

    long id;

    String nome;
    LocalDate dataCriacao;

    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    String corUniformePrincipal;

    public String getCorUniformeSecundario() {
        return corUniformeSecundario;
    }

    String corUniformeSecundario;
    private Long capitaoId;

    public HashSet<Long> getJogadores() {
        return jogadores;
    }

    HashSet<Long> jogadores;

    public Time(long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
        this.jogadores = new HashSet<>();
    }

    public void incluirJogador(Long idJogador) {
        throw new UnsupportedOperationException(); //TODO
    }

    public void setCapitao(Long idJogador) {
        this.capitaoId = idJogador;
    }

    public Long getCapitao() {
        return Optional.ofNullable(this.capitaoId)
                .orElseThrow(CapitaoNaoInformadoException::new);
    }

    public String getNome() {
        return nome;
    }
}
