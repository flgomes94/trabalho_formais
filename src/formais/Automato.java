package formais;

import java.util.List;
import java.util.Set;

public class Automato {
	//objeto autômato. Contém um conjunto de estados, uma lista de transições, 
	//um conjunto de estados finais e um estado inicial
	Set<Integer> estados;
	List<Transicao> transicoes;
	Set<Integer> estados_finais;
	Integer estado_inicial;	
}
