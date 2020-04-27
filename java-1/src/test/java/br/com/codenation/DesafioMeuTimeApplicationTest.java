package br.com.codenation;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DesafioMeuTimeApplicationTest {
    DesafioMeuTimeApplication desafioMeuTimeApplication;

    @Before
    public void setUp() throws Exception {
        desafioMeuTimeApplication = new DesafioMeuTimeApplication();
    }

    @Test
    public void incluirTime() {
        desafioMeuTimeApplication.incluirTime(1L, "Nautico", LocalDate.now(), "Vermelho", "Branco");
        assertEquals(desafioMeuTimeApplication.buscarTimes().get(0), (Long) 1L);
        try{
            desafioMeuTimeApplication.incluirTime(1L, "Nautico", LocalDate.now(), "Vermelho", "Branco");
            fail("expected exception was not occured.");
        }catch (IdentificadorUtilizadoException e){
        }
    }

    @Test
    public void incluirJogador() {
        try{
            desafioMeuTimeApplication.incluirJogador(1L, 1L, "asda", LocalDate.now(), 50, BigDecimal.valueOf(1000.0));
            fail("expected exception was not occured.");
        }catch (TimeNaoEncontradoException e){
        }

        desafioMeuTimeApplication.incluirTime(1L, "Nautico", LocalDate.now(), "Vermelho", "Branco");
        desafioMeuTimeApplication.incluirJogador(1L, 1L, "asda", LocalDate.now(), 50, BigDecimal.valueOf(1000.0));

        try{
            desafioMeuTimeApplication.incluirJogador(1L, 1L, "asda", LocalDate.now(), 50, BigDecimal.valueOf(1000.0));
            fail("expected exception was not occured.");
        }catch (IdentificadorUtilizadoException e){
        }
    }

    @Test
    public void definirCapitao() {
        desafioMeuTimeApplication.incluirTime(1L, "Nautico", LocalDate.now(), "Vermelho", "Branco");
        try{
            desafioMeuTimeApplication.definirCapitao(1L);
            fail("expected exception was not occured.");
        }catch (JogadorNaoEncontradoException e){
        }
        desafioMeuTimeApplication.incluirJogador(1L, 1L, "asda", LocalDate.now(), 50, BigDecimal.valueOf(1000.0));
        desafioMeuTimeApplication.definirCapitao(1L);
    }

    @Test
    public void buscarCapitaoDoTime() {
        try{
            desafioMeuTimeApplication.buscarCapitaoDoTime(1L);
            fail("expected exception was not occured.");
        }catch (TimeNaoEncontradoException e){
        }
        desafioMeuTimeApplication.incluirTime(1L, "Nautico", LocalDate.now(), "Vermelho", "Branco");
        try{
            desafioMeuTimeApplication.buscarCapitaoDoTime(1L);
            fail("expected exception was not occured.");
        }catch (CapitaoNaoInformadoException e){
        }
        desafioMeuTimeApplication.incluirJogador(1L, 1L, "asda", LocalDate.now(), 50, BigDecimal.valueOf(1000.0));
        desafioMeuTimeApplication.definirCapitao(1L);
        assertEquals(desafioMeuTimeApplication.buscarCapitaoDoTime(1L), (Long)1L);
    }

    @Test
    public void buscarNomeJogador() {
        try{
            desafioMeuTimeApplication.buscarNomeJogador(1L);
        }catch (JogadorNaoEncontradoException e){
        }

        desafioMeuTimeApplication.incluirTime(1L, "Nautico", LocalDate.now(), "Vermelho", "Branco");
        desafioMeuTimeApplication.incluirJogador(1L, 1L, "asda", LocalDate.now(), 50, BigDecimal.valueOf(1000.0));
        assertEquals(desafioMeuTimeApplication.buscarNomeJogador(1L), "asda");
    }

    @Test
    public void buscarNomeTime() {
        try{
            desafioMeuTimeApplication.buscarNomeTime(1L);
        }catch (TimeNaoEncontradoException e){
        }

        desafioMeuTimeApplication.incluirTime(1L, "Nautico", LocalDate.now(), "Vermelho", "Branco");
        assertEquals(desafioMeuTimeApplication.buscarNomeTime(1L), "Nautico");
    }

    @Test
    public void buscarJogadoresDoTime() {
        try{
            desafioMeuTimeApplication.buscarJogadoresDoTime(1L);
        }catch (TimeNaoEncontradoException e){
        }
        desafioMeuTimeApplication.incluirTime(1L, "Nautico", LocalDate.now(), "Vermelho", "Branco");
        desafioMeuTimeApplication.incluirJogador(2L, 1L, "asda", LocalDate.now(), 50, BigDecimal.valueOf(1000.0));
        desafioMeuTimeApplication.incluirJogador(1L, 1L, "asda", LocalDate.now(), 50, BigDecimal.valueOf(1000.0));
        desafioMeuTimeApplication.incluirJogador(3L, 1L, "asda", LocalDate.now(), 50, BigDecimal.valueOf(1000.0));

        List<Long> expected = new ArrayList<>();
        expected.add(1L);
        expected.add(2L);
        expected.add(3L);

        assertEquals(desafioMeuTimeApplication.buscarJogadoresDoTime(1L), expected);
    }

    @Test
    public void buscarMelhorJogadorDoTime() {
        try{
            desafioMeuTimeApplication.buscarMelhorJogadorDoTime(1L);
        }catch(TimeNaoEncontradoException e){}

        desafioMeuTimeApplication.incluirTime(1L, "Nautico", LocalDate.now(), "Vermelho", "Branco");
        desafioMeuTimeApplication.incluirJogador(2L, 1L, "asda", LocalDate.now(), 50, BigDecimal.valueOf(1000.0));
        desafioMeuTimeApplication.incluirJogador(1L, 1L, "asda", LocalDate.now(), 51, BigDecimal.valueOf(1000.0));

        assertEquals(desafioMeuTimeApplication.buscarMelhorJogadorDoTime(1L), (Long)1L);
    }

    @Test
    public void buscarJogadorMaisVelho() {
        try{
            desafioMeuTimeApplication.buscarJogadorMaisVelho(1L);
        }catch(TimeNaoEncontradoException e){}

        desafioMeuTimeApplication.incluirTime(1L, "Nautico", LocalDate.now(), "Vermelho", "Branco");
        LocalDate now =  LocalDate.now();
        desafioMeuTimeApplication.incluirJogador(1L, 1L, "asda", now, 50, BigDecimal.valueOf(1000.0));
        desafioMeuTimeApplication.incluirJogador(3L, 1L, "asda", now.minusDays(1L), 51, BigDecimal.valueOf(1000.0));

        assertEquals(desafioMeuTimeApplication.buscarMelhorJogadorDoTime(1L), (Long)3L);
        desafioMeuTimeApplication.incluirJogador(2L, 1L, "asda", now.minusDays(1L), 51, BigDecimal.valueOf(1000.0));
        assertEquals(desafioMeuTimeApplication.buscarMelhorJogadorDoTime(1L), (Long)2L);
    }

    @Test
    public void buscarTimes() {
        List<Long> expected = new ArrayList<>();
        assertEquals(desafioMeuTimeApplication.buscarTimes(), expected);

        desafioMeuTimeApplication.incluirTime(1L, "Nautico", LocalDate.now(), "Vermelho", "Branco");
        desafioMeuTimeApplication.incluirTime(3L, "Nautico", LocalDate.now(), "Vermelho", "Branco");
        desafioMeuTimeApplication.incluirTime(2L, "Nautico", LocalDate.now(), "Vermelho", "Branco");

        expected.add(1L);
        expected.add(2L);
        expected.add(3L);

        assertEquals(desafioMeuTimeApplication.buscarTimes(), expected);
    }

    @Test
    public void buscarJogadorMaiorSalario() {
        try{
            desafioMeuTimeApplication.buscarJogadorMaiorSalario(1L);
        }catch(TimeNaoEncontradoException e){}

        desafioMeuTimeApplication.incluirTime(1L, "Nautico", LocalDate.now(), "Vermelho", "Branco");
        LocalDate now =  LocalDate.now();
        desafioMeuTimeApplication.incluirJogador(1L, 1L, "asda", now, 50, BigDecimal.valueOf(1000.0));
        desafioMeuTimeApplication.incluirJogador(3L, 1L, "asda", now, 51, BigDecimal.valueOf(1001.0));

        assertEquals(desafioMeuTimeApplication.buscarJogadorMaiorSalario(1L), (Long)3L);
        desafioMeuTimeApplication.incluirJogador(2L, 1L, "asda", now, 51, BigDecimal.valueOf(1001.0));
        assertEquals(desafioMeuTimeApplication.buscarJogadorMaiorSalario(1L), (Long)2L);
    }

    @Test
    public void buscarSalarioDoJogador() {
        try{
            desafioMeuTimeApplication.buscarSalarioDoJogador(1L);
        }catch(JogadorNaoEncontradoException e){
        }

        desafioMeuTimeApplication.incluirTime(1L, "Nautico", LocalDate.now(), "Vermelho", "Branco");
        desafioMeuTimeApplication.incluirJogador(2L, 1L, "asda", LocalDate.now(), 50, BigDecimal.valueOf(1234.5));

        assertEquals(desafioMeuTimeApplication.buscarSalarioDoJogador(2L), BigDecimal.valueOf(1234.5));
    }

    @Test
    public void buscarTopJogadores() {
        desafioMeuTimeApplication.incluirTime(1L, "Nautico", LocalDate.now(), "Vermelho", "Branco");
        desafioMeuTimeApplication.incluirTime(2L, "Santa", LocalDate.now(), "Vermelho", "Branco");

        List<Long> expected = new ArrayList<>();
        assertEquals(desafioMeuTimeApplication.buscarTopJogadores(3), expected);

        desafioMeuTimeApplication.incluirJogador(1L, 1L, "asda", LocalDate.now(), 50, BigDecimal.valueOf(1234.5));
        desafioMeuTimeApplication.incluirJogador(2L, 1L, "asda", LocalDate.now(), 51, BigDecimal.valueOf(1234.5));
        desafioMeuTimeApplication.incluirJogador(3L, 2L, "asda", LocalDate.now(), 51, BigDecimal.valueOf(1234.5));
        desafioMeuTimeApplication.incluirJogador(4L, 2L, "asda", LocalDate.now(), 52, BigDecimal.valueOf(1234.5));
        desafioMeuTimeApplication.incluirJogador(5L, 2L, "asda", LocalDate.now(), 53, BigDecimal.valueOf(1234.5));

        expected.add(5L);
        expected.add(4L);
        expected.add(2L);

        assertEquals(desafioMeuTimeApplication.buscarTopJogadores(3), expected);
    }

    @Test
    public void buscarCorCamisaTimeDeFora() {
        desafioMeuTimeApplication.incluirTime(1L, "Nautico", LocalDate.now(), "Vermelho", "Branco");
        desafioMeuTimeApplication.incluirTime(2L, "Santa", LocalDate.now(), "Vermelho", "Azul");
        assertEquals(desafioMeuTimeApplication.buscarCorCamisaTimeDeFora(1L, 2L), "Azul");

        desafioMeuTimeApplication.incluirTime(3L, "Sport", LocalDate.now(), "Amarela", "Azul");
        assertEquals(desafioMeuTimeApplication.buscarCorCamisaTimeDeFora(1L, 3L), "Amarela");
    }
}