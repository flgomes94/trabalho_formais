package formais;

public class Transicao {
	int estado_atual;
	String transicao;
	//int estado_destino;
	
	public Transicao(int estado_atual, String transicao) {
		this.estado_atual = estado_atual;
		this.transicao = transicao;
		//this.estado_destino = estado_destino;
	}

	public int getEstado_atual() {
		return estado_atual;
	}

	public void setEstado_atual(int estado_atual) {
		this.estado_atual = estado_atual;
	}

	public String getTransicao() {
		return transicao;
	}

	public void setTransicao(String transicao) {
		this.transicao = transicao;
	}

	@Override
	public String toString() {
		return "Transicao [estado_atual=" + estado_atual + ", transicao=" + transicao + "]";
	}

	/*public int getEstado_destino() {
		return estado_destino;
	}

	public void setEstado_destino(int estado_destino) {
		this.estado_destino = estado_destino;
	}*/
	
	
	
	
}
