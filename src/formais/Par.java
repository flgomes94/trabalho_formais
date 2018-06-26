package formais;

public class Par{
	int key;
	int value;
	
	public Par(int k, int v) {
		this.key = k;
		this.value = v;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + key;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Par other = (Par) obj;
		if (key != other.key && value != other.value )
			return false;
		return true;
	}
	
	
}
