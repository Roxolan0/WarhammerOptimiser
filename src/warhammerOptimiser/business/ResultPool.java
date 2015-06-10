package warhammerOptimiser.business;

/**
 * @author H
 * Manages a single "result": the number of times each symbol (success, comet etc.) appears.
 */
public class ResultPool {
	public ResultPool(int newS, int newF, int newO, int newA, int newC, int newH, int newD, int newE) {
		S = newS;
		F = newF;
		O = newO;
		A = newA;
		C = newC;
		H = newH;
		D = newD;
		E = newE;
	}
	public ResultPool(ResultPool o) {
		this(o.getS(), o.getF(), o.getO(), o.getA(), o.getC(), o.getH(), o.getD(), o.getE());
	}
	public ResultPool() {
		this(0,0,0,0,0,0,0,0);
	}
	public int getS(){
		return S;
	}
	public int getF(){
		return F;
	}
	public int getO(){
		return O;
	}
	public int getA(){
		return A;
	}
	public int getC(){
		return C;
	}
	public int getH(){
		return H;
	}
	public int getD(){
		return D;
	}
	public int getE(){
		return E;
	}
	public void setS(int newS) {
		S = newS;
	}
	public void setF(int newF) {
		F = newF;
	}
	public void setO(int newO) {
		O = newO;
	}
	public void setA(int newA) {
		A = newA;
	}
	public void setC(int newC) {
		C = newC;
	}
	public void setH(int newH) {
		H = newH;
	}
	public void setD(int newD) {
		D = newD;
	}
	public void setE(int newE) {
		E = newE;
	}
	public int getEffectType(EffectType e) {
		if(e == EffectType.S) {
			return S;
		} else if(e == EffectType.O) {
			return O;
		} else if(e == EffectType.A) {
			return A;
		} else if(e == EffectType.C) {
			return C;
		} else {	//if(e == EffectType.H)
			return H;
		}
	}
	
	public void add(ResultPool other) {
		S += other.getS();
		F += other.getF();
		O += other.getO();
		A += other.getA();
		C += other.getC();
		H += other.getH();
		D += other.getD();
		E += other.getE();
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof ResultPool)) return false;
		ResultPool other = (ResultPool) o;
		return this.S == other.S
			&& this.F == other.F
			&& this.O == other.O
			&& this.A == other.A
			&& this.C == other.C
			&& this.H == other.H
			&& this.D == other.D
			&& this.E == other.E;
	}

	//TODO limit it by the power's maximum. No need to keep extra O if the power can only use 2.
	public void balance() {
		if(S >= F) {
			S -= F;
			F = 0;
		} else {
			F -= S;
			S = 0;
		}
		if(O >= A) {
			O -= A;
			A = 0;
		} else {
			A -= O;
			O = 0;
		}
	}
	
	public String toString() {
		String s = "";
		s += "S " + S + ", F " + F + ", O " + O + ", A " + A + ", C " + C 
			+ ", H " + H + ", D " + D + ", E " + E;
		return s;
	}
	
	private int S;
	private int F;
	private int O;
	private int A;
	private int C;
	private int H;
	private int D;
	private int E;
//	private int R;
}
