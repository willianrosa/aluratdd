package br.com.caelum.leilao.dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class PalindromoTest {

	@Test
	public void deveRetornarUmPalindromo() {
		Palindromo palindromo = new Palindromo();
		assertEquals(true,palindromo.ehPalindromo("Socorram-me subi no onibus em Marrocos"));
		assertEquals(true,palindromo.ehPalindromo("omo"));
		
	}

}
