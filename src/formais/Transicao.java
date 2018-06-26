package formais;

public class Transicao implements Comparable<Transicao> {

	int de;
	String lendo;
	int para;
	
	public Transicao(int de, String lendo, int para) {
		this.de = de;
		this.para = para;
		this.lendo = lendo;
	}
	
	@Override
	public int compareTo(Transicao o) {
		if (this.de < o.de)	return -1;
		if (this.de > o.de) return 1;
		if (this.de == o.de) return this.lendo.compareTo(o.lendo);
		return 0;
	}
}
