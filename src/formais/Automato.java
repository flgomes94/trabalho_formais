package formais;

import java.util.Set;

public class Automato {
	Boolean eh_inicial;
	Boolean eh_final;
	Set<Integer> estados;
	

	public Automato(Boolean eh_inicial, Boolean eh_final, Set<Integer> estados) {
		super();
		this.eh_inicial = eh_inicial;
		this.eh_final = eh_final;
		this.estados = estados;
	}

	@Override
	public String toString() {
		return "Automato [eh_inicial=" + eh_inicial + ", eh_final=" + eh_final + ", estados=" + estados.size() + "]";
	}

	public Boolean getEh_inicial() {
		return eh_inicial;
	}

	public void setEh_inicial(Boolean eh_inicial) {
		this.eh_inicial = eh_inicial;
	}

	public Boolean getEh_final() {
		return eh_final;
	}

	public void setEh_final(Boolean eh_final) {
		this.eh_final = eh_final;
	}

	public Set<Integer> getEstados() {
		return estados;
	}

	public void setEstados(Set<Integer> estados) {
		this.estados = estados;
	}
	
	public void appendEstados(Set<Integer> estados) {
		for(Integer estado: estados) {
			this.estados.add(estado);
		}
	}
	
	
	
	
}
