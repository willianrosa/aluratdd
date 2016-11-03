package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.omg.Messaging.SyncScopeHelper;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTest {

	private Leilao leilao;
	Avaliador avaliador;
	@Before
	public void setup() {

		Usuario willian = new Usuario("Willian");
		Usuario jose = new Usuario("Jose");
		Usuario marcos = new Usuario("Marcos");

		leilao = new Leilao("carangos");
		leilao.propoe(new Lance(willian, 2000));
		leilao.propoe(new Lance(jose, 5000));
		leilao.propoe(new Lance(marcos, 7500));		
		avaliador = new Avaliador(leilao);
		avaliador.avalia();

	}

	@Test
	public void deveRetornarOMenorValorDeLeilao() {
		assertEquals(Double.valueOf(2000),avaliador.getMenorLance());
	}
	
	@Test
	public void deveRetornarOMaiorValorDeLeilao() {
		assertEquals(Double.valueOf(7500),avaliador.getMaiorLance());		
	}	
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance(){
		
		Leilao leilao = new Leilao("Playstation");
		leilao.propoe(new Lance(new Usuario("willian"),400));
		Avaliador avaliador = new Avaliador(leilao);
		avaliador.avalia();
		
		assertEquals(Double.valueOf(400), avaliador.getMaiorLance());
		assertEquals(Double.valueOf(400), avaliador.getMenorLance());
				
	}
	
	@Test
	public void deveRetornarOsTresMaioresLances(){
		Usuario willian = new Usuario("Willian");
		Usuario jose = new Usuario("Jose");
		Usuario marcos = new Usuario("Marcos");

		leilao = new Leilao("carangos");
		leilao.propoe(new Lance(willian, 1000));		
		leilao.propoe(new Lance(willian, 2000));
		leilao.propoe(new Lance(jose, 5000));
		leilao.propoe(new Lance(marcos, 7500));
		leilao.propoe(new Lance(willian,10000));
		avaliador = new Avaliador(leilao);
		avaliador.avalia();
		
		Double lance1 = avaliador.getTresMaiores().get(0).getValor();
		Double lance2 = avaliador.getTresMaiores().get(1).getValor();
		Double lance3 = avaliador.getTresMaiores().get(2).getValor();
		
		assertEquals(3, avaliador.getTresMaiores().size());
		assertEquals(Double.valueOf(10000),lance1);
		assertEquals(Double.valueOf(7500),lance2);
		assertEquals(Double.valueOf(5000),lance3);
		
		
	}
	
	@Test
	public void deveRetornarOsDoisMaioresLances(){
		Usuario willian = new Usuario("Willian");
		Usuario marcos = new Usuario("Marcos");

		leilao = new Leilao("carangos");
		leilao.propoe(new Lance(marcos, 7500));
		leilao.propoe(new Lance(willian,10000));
		avaliador = new Avaliador(leilao);
		avaliador.avalia();
		
		Double lance1 = avaliador.getTresMaiores().get(0).getValor();
		Double lance2 = avaliador.getTresMaiores().get(1).getValor();
		
		assertEquals(2, avaliador.getTresMaiores().size());
		assertEquals(Double.valueOf(10000),lance1);
		assertEquals(Double.valueOf(7500),lance2);
		
		
	}
	
	@Test
	public void deveRetornarUmaListaVazia(){
		Usuario willian = new Usuario("Willian");
		Usuario marcos = new Usuario("Marcos");

		leilao = new Leilao("carangos");
		avaliador = new Avaliador(leilao);
		avaliador.avalia();
		
		
		assertEquals(0, avaliador.getTresMaiores().size());
		
		
	}
	
	
	
}
