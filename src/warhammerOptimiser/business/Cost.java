package warhammerOptimiser.business;

import java.io.Serializable;

public class Cost implements Serializable {
	private static final long serialVersionUID = -2218841148696744421L;
	
	public Cost(EffectType newType, int newNb) {
		if(newNb < 0) {
			throw new IllegalArgumentException("Tried to create a Cost of Nb " + newNb);
		}
		type = newType;
		nb = newNb;
	}
	public EffectType getType() {
		return type;
	}
	public int getNb() {
		return nb;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Cost)) return false;
		Cost other = (Cost) o;
		return this.type == other.type
			&& this.nb == other.nb;
	}
	
	public String toString() {
		return toCardString();
	}
	public String toCardString() {
		String s = "";
		for(int i = 0; i < nb; ++i) {
			s += type;
		}
		return s;
	}
	
	private EffectType type;
	private int nb;
}
