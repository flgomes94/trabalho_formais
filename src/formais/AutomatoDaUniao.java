package formais;

import java.util.Set;

public class AutomatoDaUniao {
	//objeto autômato. Contém um conjunto de estados, uma lista de transições, 
	//um conjunto de estados finais e um estado inicial.
	//A diferença de um autômato normal é que aqui estados e estado inicial é um par com key:value
	Set<String> alfabeto;
	Set<Par> estados;
	Set<Transicoes> transicoes;
	Set<Par> estados_finais;
	Par estado_inicial;
	
	public AutomatoDaUniao(Set<String> alfabeto) {
		this.alfabeto = alfabeto;
	}
}
