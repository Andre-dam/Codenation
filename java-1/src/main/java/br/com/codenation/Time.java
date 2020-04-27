package br.com.codenation;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

public class Time {
  private final long id;
  private final String nome;
  private final LocalDate dataCriacao;
  private final String corUniformePrincipal;
  private final String corUniformeSecundario;
  private final HashSet<Long> jogadores;

  private Long capitaoId;

  public Time(
      long id,
      String nome,
      LocalDate dataCriacao,
      String corUniformePrincipal,
      String corUniformeSecundario) {
    this.id = id;
    this.nome = nome;
    this.dataCriacao = dataCriacao;
    this.corUniformePrincipal = corUniformePrincipal;
    this.corUniformeSecundario = corUniformeSecundario;
    this.jogadores = new HashSet<>();
  }

  public void incluirJogador(Long idJogador) {
    this.jogadores.add(idJogador);
  }

  public void setCapitao(Long idJogador) {
    this.capitaoId = idJogador;
  }

  public Long getCapitao() {
    return Optional.ofNullable(this.capitaoId).orElseThrow(CapitaoNaoInformadoException::new);
  }

  public String getNome() {
    return nome;
  }

  public HashSet<Long> getJogadores() {
    return jogadores;
  }

  public String getCorUniformeSecundario() {
    return corUniformeSecundario;
  }

  public String getCorUniformePrincipal() {
    return corUniformePrincipal;
  }

  public long getId() {
    return id;
  }
}
