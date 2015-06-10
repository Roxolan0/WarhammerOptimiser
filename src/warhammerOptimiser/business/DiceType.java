package warhammerOptimiser.business;
import static warhammerOptimiser.business.DiceResultType.*;

public class DiceType {
	public static final Dice CHA = new Dice (new ResultPool[] {N,N,S,S,S,S,O,O});
	public static final Dice CON = new Dice (new ResultPool[] {N,SO,S,S,S,S,SD,SD,O,O});
	public static final Dice REC = new Dice (new ResultPool[] {N,N,SS,SS,SO,SE,SE,OO,A,A});
//	public static final Dice EXP = new Dice (new ResultPool[] {N,R,S,O,O,C});	//TODO make it work
	public static final Dice EXP = new Dice (new ResultPool[] {N,SO,S,O,O,C});

	public static final Dice FOR = new Dice (new ResultPool[] {N,N,N,S,S,O});
	public static final Dice MIS = new Dice (new ResultPool[] {N,N,N,F,F,A});
	public static final Dice CHL = new Dice (new ResultPool[] {N,FF,FF,F,F,AA,A,H});
//	CHA, REC, CON, EXP, FOR, MIS, CHL
}
