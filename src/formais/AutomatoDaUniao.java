package formais;

import java.util.Set;

public class AutomatoDaUniao {
	Set<String> alfabeto;
	Set<Par> estados;
	Set<Transicoes> transicoes;
	Set<Par> estados_finais;
	Par estado_inicial;
	
	public AutomatoDaUniao(Set<String> alfabeto) {
		this.alfabeto = alfabeto;
	}
}
