package warhammerOptimiser.business;

public class DiceResultType {
	public static final ResultPool N = 	new ResultPool(0,0,0,0,0,0,0,0);
	
	public static final ResultPool S = 	new ResultPool(1,0,0,0,0,0,0,0);
	public static final ResultPool SS = new ResultPool(2,0,0,0,0,0,0,0);
	public static final ResultPool SO = new ResultPool(1,0,1,0,0,0,0,0);
	public static final ResultPool SE = new ResultPool(1,0,0,0,0,0,0,1);
	public static final ResultPool SD = new ResultPool(1,0,0,0,0,0,1,0);
	public static final ResultPool R = 	new ResultPool(1,0,0,0,0,0,0,0);
	
	public static final ResultPool F = 	new ResultPool(0,1,0,0,0,0,0,0);
	public static final ResultPool FF = new ResultPool(0,2,0,0,0,0,0,0);

	public static final ResultPool O = 	new ResultPool(0,0,1,0,0,0,0,0);
	public static final ResultPool OO = new ResultPool(0,0,2,0,0,0,0,0);
	public static final ResultPool A = 	new ResultPool(0,0,0,1,0,0,0,0);
	public static final ResultPool AA = new ResultPool(0,0,0,2,0,0,0,0);

	public static final ResultPool C = 	new ResultPool(0,0,0,0,1,0,0,0);
	public static final ResultPool H = 	new ResultPool(0,0,0,0,0,1,0,0);

//	N,					//nothing
//	S,SS,SO,SE,SD,R,	//success (+success, +boon, +exertion, +delay, righteous)
//	F,FF,				//failure (+failure)
//	O,OO,				//boon (+boon)
//	A,AA,				//bane (+bane)
//	C,					//comet
//	H					//chaos
}
