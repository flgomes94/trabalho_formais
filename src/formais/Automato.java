package formais;

import java.util.Set;

public class Automato {
	Boolean eh_inicial;
	Boolean eh_final;
	Set<Integer> estados_iniciais;
	Set<Integer> transicoes;
	public Automato(Boolean eh_inicial, Boolean eh_final, Set<Integer> estados_iniciais, Set<Integer> transicoes) {
		super();
		this.eh_inicial = eh_inicial;
		this.eh_final = eh_final;
		this.estados_iniciais = estados_iniciais;
		this.transicoes = transicoes;
	}
	@Override
	public String toString() {
		return "Automato [eh_inicial=" + eh_inicial + ", eh_final=" + eh_final + ", estados_iniciais="
				+ estados_iniciais + ", transicoes=" + transicoes + "]";
	}
	
	
	
}
