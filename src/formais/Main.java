package formais;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
	/*Função que escreve texto no arquivo*/
	public static void escritor(String path, String text) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        buffWrite.append(text + "\n");
        buffWrite.close();
    }
	
	/*Função de auxílio de leitura de autômato. Lê todas as informações
	 * da entrada e armazena em um objeto do tipo Automato 
	 * */
	public static Automato ler(Automato automato) {
		int qtd_t_1=0,qtd_finais_1=0;
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		automato.estados = new HashSet<Integer>();
		automato.estados_finais = new HashSet<Integer>();
		automato.transicoes = new ArrayList<Transicao>();
		
		System.out.println("Informe a quantidade de estados do automato: ");
		try {
			qtd_t_1 = Integer.parseInt(entrada.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		qtd_t_1 = (int) Math.pow(automato.qtd_alfabeto,qtd_t_1);
		System.out.println("Informe as transicoes no formato 0 a 1 (leva do estado 0, lendo a para o estado 1): ");
		for (int i = 0; i <  qtd_t_1; i++) {
			String transicao=null;
			try {
				transicao = entrada.readLine().toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int estado_atual = Integer.parseInt(transicao.split(" ")[0]);
			String trans = transicao.split(" ")[1];
			int estado_final = Integer.parseInt(transicao.split(" ")[2]);
			
			if (estado_atual == 0)
				automato.estado_inicial = estado_atual;
			
			
			automato.estados.add(estado_atual);
			automato.estados.add(estado_final);		
			Transicao delta = new Transicao(estado_atual, trans, estado_final);
			automato.transicoes.add(delta);
			
		}
		
		System.out.println("Informe a quantidade de estados finais do automato : ");
		try {
			qtd_finais_1 = Integer.parseInt(entrada.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Informe os estados finais do automato : ");
		for (int i = 0; i < qtd_finais_1; i++) {
			int estado_final = 0;
			try {
				estado_final = Integer.parseInt(entrada.readLine());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			automato.estados_finais.add(estado_final);
			
		}
		
		return automato;
		
	}
	
	
	public static void main(String[] args) {
		/*Função principal. Le alfabeto, gera os automatos separados
		 * depois une de acordo o livro
		 * no caso, pega todos os pares de cada autômato, e ai:
		 * Se ambos forem um estado inicial, aquele será um estado inicial
		 * Se algum dos dois forem um estado final, aquele será um estado final.
		 * Por fim, a última função gera o arquivo final para leitura do JFLAP.*/
		Set<String> sigma = new HashSet<String>();		
		
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		
		
		System.out.println("Informe o alfabeto dos autômatos (separados por espaço e ao fim pressione enter): ");
		try {
			String alfabeto = entrada.readLine().toString();
			String[] caracteres = alfabeto.split(" ");
			for (String string : caracteres) {
				sigma.add(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Automato automato1 = new Automato();
		automato1.qtd_alfabeto = sigma.size();
		automato1 = ler(automato1);
		
		Automato automato2 = new Automato();
		automato2.qtd_alfabeto = sigma.size();
		automato2 = ler(automato2);
		
		//for (Map.Entry<Transicao, Integer> meumapa : transicoes_a1.entrySet()) {
		//	System.out.println(meumapa.getKey()+" "+meumapa.getValue());
		//}
		
		AutomatoDaUniao automatos_unidos = new AutomatoDaUniao(sigma);
		automatos_unidos.estados = new HashSet<Par>();
		automatos_unidos.estados_finais = new HashSet<Par>();
		automatos_unidos.transicoes = new HashSet<Transicoes>();
		
		
		for (Transicao transicao1: automato1.transicoes) {
			for (Transicao transicao2: automato2.transicoes) {
				String lendo = "";
				Par par = new Par(transicao1.de, transicao2.de);
				Par novo = new Par(transicao1.para, transicao2.para);	
				
				if (transicao1.de == 0 && transicao2.de == 0)
					automatos_unidos.estado_inicial = par;
				
				if (automato1.estados_finais.contains(transicao1.de) || automato2.estados_finais.contains(transicao2.de))
					automatos_unidos.estados_finais.add(par);
				
				if (transicao1.lendo.equals(transicao2.lendo)) {
					automatos_unidos.estados.add(par);
					automatos_unidos.estados.add(novo);
					lendo = transicao1.lendo;
					Transicoes delta = new Transicoes(par, lendo, novo);
					automatos_unidos.transicoes.add(delta);
				} else {
					continue;
				}
			}
		}
			
		String arquivo_jflap = "<?xml version='1.0' encoding='UTF-8' standalone='no'?>\n <structure>&#13;\n\t<type>fa</type>&#13;\n \t<automaton>&#13;\n		<!--The list of states.-->\n";
		int cont = 0;
		for (Par par: automatos_unidos.estados) {
			
			arquivo_jflap += "\t\t<state id=\"" + par.key + par.value + "\" name=\"q"+cont+"\">&#13;\n";
			float conta, conta2;
			if (cont%2 == 0) {
				 conta = (float)231+50*(cont);
				 conta2 = (float)132 + 20*cont;
			}else {
				conta = (float)231+50*(cont+1);
				conta2 = (float)132 + 24*cont;
			}
			arquivo_jflap += "\t\t\t<x>"+ conta +"</x>&#13;\n";
			arquivo_jflap += "\t\t\t<y>" +conta2+ "</y>&#13;\n";
			
			
			if (par.equals(automatos_unidos.estado_inicial)){
				arquivo_jflap += "\t\t\t<initial/>&#13;\n";
			}
			
			if(automatos_unidos.estados_finais.contains(par)){
				arquivo_jflap += "\t\t\t<final/>&#13;\n";
			}
			
			arquivo_jflap += "\t\t</state>&#13;\n";	
			cont++;
		}
		
		for (Transicoes delta: automatos_unidos.transicoes) {
			Par from = delta.de;
			Par to = delta.para;
			String read = delta.lendo;
				
			arquivo_jflap += "\t\t<transition>&#13;\n";
			arquivo_jflap += "\t\t\t<from>"+from.key + from.value +"</from>&#13;\n";
			arquivo_jflap += "\t\t\t<to>"+ to.key + to.value +"</to>&#13;\n";
			arquivo_jflap += "\t\t\t<read>"+read+"</read>&#13;\n";
			arquivo_jflap += "\t\t</transition>&#13;\n";
		}
		
		arquivo_jflap += "\t</automaton>&#13;\n";
		arquivo_jflap += "</structure>";
		try {
			escritor("uniao.jff", arquivo_jflap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Arquivo uniao.jff gerado!");
	}
}
