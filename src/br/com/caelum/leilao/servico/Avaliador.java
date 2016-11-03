package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	Double menorLance = Double.NEGATIVE_INFINITY;
	Double maiorLance = Double.POSITIVE_INFINITY;
	private Leilao leilao;
	private Double mediaLance;
	private List<Lance> tresMaiores = new ArrayList<Lance>();

	public Avaliador(Leilao leilao) {
		this.leilao = leilao;
	}

	public void avalia() {
		if(Integer.valueOf(0).equals(leilao.getLances().size())) return;
		this.menorLance = leilao.getLances().stream()
				.mapToDouble(Lance::getValor)
				.min()
				.getAsDouble();

		this.maiorLance = leilao.getLances().stream()
				.mapToDouble(Lance::getValor)
				.max()
				.getAsDouble();

		this.mediaLance = leilao.getLances().stream()
				.mapToDouble(Lance::getValor)
				.average()
				.getAsDouble();
		
		this.tresMaiores = leilao.getLances().stream()
				.sorted(Comparator.comparingDouble(Lance::getValor).reversed())
				.limit(3)
				.collect(Collectors.toList());
			

	}

	public Double getMenorLance() {
		return this.menorLance;
	}
	
	public Double getMaiorLance() {
		return this.maiorLance;
	}
	
	public Double getMediaLance(){
		return this.mediaLance;
	}
	
	public List<Lance> getTresMaiores(){
		return this.tresMaiores;
	}

}
