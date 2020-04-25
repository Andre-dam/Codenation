package br.com.codenation;

import java.time.LocalDate;
import java.util.Hashtable;

public class Time {
    long id;
    String nome;
    LocalDate dataCriacao;
    String corUniformePrincipal;
    String corUniformeSecundario;
    Hashtable<Long, Jogador> jogadores;

    private Jogador capitao;

    public Time(long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
        this.jogadores = new Hashtable<>();
    }

    public void setCapitao(long idJogador){
        this.capitao = jogadores.get(idJogador);
    }
}
