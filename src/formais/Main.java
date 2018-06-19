package formais;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		int qtd_t_1=0,qtd_t_2=0,qtd_finais_1=0,qtd_finais_2=0;
		Set<String> sigma = new HashSet<String>();
		Set<Integer> estado_final_1 = new HashSet<Integer>();
		Set<Integer> estado_final_2 = new HashSet<Integer>();
		Set<Integer> estados_1 = new HashSet<Integer>();
		Set<Integer> estados_2 = new HashSet<Integer>();
		
		Set<Automato> uniao = new HashSet<Automato>();
		Map<Transicao,Integer> transicoes_a1 = new HashMap<Transicao,Integer>();
		Map<Transicao,Integer> transicoes_a2 = new HashMap<Transicao,Integer>();
		
		
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		
		
		System.out.print("Informe o alfabeto dos autômatos (separados por espaço e ao fim pressione enter): ");
		try {
			String alfabeto = entrada.readLine().toString();
			String[] caracteres = alfabeto.split(" ");
			for (String string : caracteres) {
				sigma.add(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.print("Informe a quantidade de transições do automato 1: ");
		try {
			qtd_t_1 = Integer.parseInt(entrada.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("Informe as transicoes no formado 0 a 1 (leva do estado 0, lendo a para o estado 1): ");
		for (int i = 0; i < qtd_t_1; i++) {
			String transicao=null;
			try {
				transicao = entrada.readLine().toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int estado_atual = Integer.parseInt(transicao.split(" ")[0]);
			estados_1.add(estado_atual);
			String trans = transicao.split(" ")[1];
			int estado_final = Integer.parseInt(transicao.split(" ")[2]);
			Transicao t = new Transicao(estado_atual, trans);
			transicoes_a1.put(t, estado_final);
			
		}
		
		System.out.print("Informe a quantidade de estados finais do automato 1: ");
		try {
			qtd_finais_1 = Integer.parseInt(entrada.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < qtd_finais_1; i++) {
			int estado_final = 0;
			try {
				estado_final = Integer.parseInt(entrada.readLine());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			estado_final_1.add(estado_final);
			
		}
		
		//fim1
		
		System.out.print("Informe a quantidade de transições do automato 2: ");
		try {
			qtd_t_2 = Integer.parseInt(entrada.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("Informe as transicoes no formado 0 a 1 (leva do estado 0, lendo a para o estado 1): ");
		for (int i = 0; i < qtd_t_2; i++) {
			String transicao=null;
			try {
				transicao = entrada.readLine().toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int estado_atual = Integer.parseInt(transicao.split(" ")[0]);
			estados_2.add(estado_atual);
			String trans = transicao.split(" ")[1];
			int estado_final = Integer.parseInt(transicao.split(" ")[2]);
			Transicao t = new Transicao(estado_atual, trans);
			transicoes_a2.put(t, estado_final);
			
		}
		
		System.out.print("Informe a quantidade de estados finais do automato 2: ");
		try {
			qtd_finais_2 = Integer.parseInt(entrada.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < qtd_finais_2; i++) {
			int estado_final = 0;
			try {
				estado_final = Integer.parseInt(entrada.readLine());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			estado_final_2.add(estado_final);
			
		}
		
		//for (Map.Entry<Transicao, Integer> meumapa : transicoes_a1.entrySet()) {
		//	System.out.println(meumapa.getKey()+" "+meumapa.getValue());
		//}
		
		
		for(String letra: sigma) {
			for(Integer estado_1: estados_1) {
				for(Integer estado_2 : estados_2) {
					Set<Integer> estado_atual = new HashSet<Integer>();
					Set<Integer> estado_transicao = new HashSet<Integer>();
					Automato automato= new Automato(null,null, estado_atual,estado_transicao);
					//System.out.println(estado_1 + " haha "+estado_2 + " haha "+ letra);
					//Transicao t1 = new Transicao(estado_1, letra);
					//Transicao t2 = new Transicao(estado_2, letra);
					automato.estados_iniciais.add(estado_1);
					automato.estados_iniciais.add(estado_2);
					
					if(estado_1==0 && estado_2==0) {					//é pq é inicial
						automato.eh_inicial=true;
						automato.eh_final=false;
						
						for (Map.Entry<Transicao, Integer> meumapa : transicoes_a1.entrySet()) {
							//System.out.println(meumapa.getKey().estado_atual + " "+ meumapa.getKey().transicao + estado_1 + " " + letra);
							int x = meumapa.getKey().estado_atual;
							int y = estado_1;
							String a = meumapa.getKey().transicao;
							String b = letra;
							if((x==y) && a.equals(b)) {
								//System.out.println("entrei");
								automato.transicoes.add(meumapa.getValue());
								break;
							}
						}
						
						for (Map.Entry<Transicao, Integer> meumapa : transicoes_a2.entrySet()) {
							//System.out.println(meumapa.getKey().estado_atual + " "+ meumapa.getKey().transicao + estado_1 + " " + letra);
							int x = meumapa.getKey().estado_atual;
							int y = estado_1;
							String a = meumapa.getKey().transicao;
							String b = letra;
							if((x==y) && a.equals(b)) {
								//System.out.println("entrei");
								automato.transicoes.add(meumapa.getValue());
								break;
							}
						}
						
					}else if(estado_final_1.contains(estado_1) || estado_final_2.contains(estado_2)){
						automato.eh_inicial=false;
						automato.eh_final=true;
						
						for (Map.Entry<Transicao, Integer> meumapa : transicoes_a1.entrySet()) {
							//System.out.println(meumapa.getKey().estado_atual + " "+ meumapa.getKey().transicao + estado_1 + " " + letra);
							int x = meumapa.getKey().estado_atual;
							int y = estado_1;
							String a = meumapa.getKey().transicao;
							String b = letra;
							if(x==y && a==b) {
								//System.out.println("entrei");
								automato.transicoes.add(meumapa.getValue());
								break;
							}
						}
						
						for (Map.Entry<Transicao, Integer> meumapa : transicoes_a2.entrySet()) {
							//System.out.println(meumapa.getKey().estado_atual + " "+ meumapa.getKey().transicao + estado_1 + " " + letra);
							int x = meumapa.getKey().estado_atual;
							int y = estado_1;
							String a = meumapa.getKey().transicao;
							String b = letra;
							if((x==y) && a.equals(b)) {
								//System.out.println("entrei");
								automato.transicoes.add(meumapa.getValue());
								break;
							}
						}
					}
					else {
						automato.eh_inicial=false;
						automato.eh_final=false;
						
						for (Map.Entry<Transicao, Integer> meumapa : transicoes_a1.entrySet()) {
							//System.out.println(meumapa.getKey().estado_atual + " "+ meumapa.getKey().transicao + estado_1 + " " + letra);
							int x = meumapa.getKey().estado_atual;
							int y = estado_1;
							String a = meumapa.getKey().transicao;
							String b = letra;
							if((x==y) && a.equals(b)) {
								//System.out.println("entrei");
								automato.transicoes.add(meumapa.getValue());
								break;
							}
						}
						
						for (Map.Entry<Transicao, Integer> meumapa : transicoes_a2.entrySet()) {
							//System.out.println(meumapa.getKey().estado_atual + " "+ meumapa.getKey().transicao + estado_1 + " " + letra);
							int x = meumapa.getKey().estado_atual;
							int y = estado_1;
							String a = meumapa.getKey().transicao;
							String b = letra;
							if((x==y) && a.equals(b)) {
								//System.out.println("entrei");
								automato.transicoes.add(meumapa.getValue());
								break;
							}
						}
						
					}
					uniao.add(automato);
				}
			}
		}
		
		for(Automato um: uniao) {
			System.out.println(um);
		}
		
	}
	
}
