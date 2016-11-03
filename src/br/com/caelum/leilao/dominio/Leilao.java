package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {	
		
		if(podeDarLance(lance.getUsuario())) lances.add(lance);
	}
	
	public void dobraLance(Lance ultimo){			
			propoe(new Lance(ultimo.getUsuario(), ultimo.getValor()*2));
	}	

	private boolean podeDarLance(Usuario usuario) {
		return !lanceDado(usuario, getUltimoUsuario()) 
				&& lances.stream()
					.filter(l->l.getUsuario().equals(usuario)).count()<5;
	}

	private boolean lanceDado(Usuario atual, Optional<Usuario> ultimoUsuario) {
		return ultimoUsuario.isPresent() && ultimoUsuario.get().equals(atual);
	}

	private Optional<Usuario> getUltimoUsuario() {
		return getUltimoLance()
		.map(l->l.getUsuario());
	}

	private Optional<Lance> getUltimoLance() {
		return lances.stream()
		.reduce((primeiro,segundo)->segundo);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	public void dobraLance(Usuario usuario) {
		getUltimoLanceDo(usuario).ifPresent(this::dobraLance);
		
	}

	private Optional<Lance> getUltimoLanceDo(Usuario usuario) {
		return lances.stream()
				.filter(l->l.getUsuario().equals(usuario))
				.reduce((primeiro,segundo)->segundo);
		
	}

	
	
}
