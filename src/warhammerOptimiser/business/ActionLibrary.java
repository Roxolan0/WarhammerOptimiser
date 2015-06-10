package warhammerOptimiser.business;
import static warhammerOptimiser.business.TargetType.*;
import static warhammerOptimiser.business.ResultType.*;

import java.io.File;

public class ActionLibrary {
	public static final Action UNIVERSAL_ACTION;
	public static final Action UNIVERSAL_ATTACK;
	public static EffectLine weaponCritical(Weapon weapon) {
		return weaponCritical(weapon.getCr());
	}
	public static EffectLine weaponCritical(int cr) {
		return new EffectLine(new Cost(EffectType.O, cr), new Effect[] {
			new Effect(DEAL, CRITICAL)
		});
	}
	public static EffectLine soak(int soak) {
		return new EffectLine(new Cost(EffectType.H, 0), new Effect[] {
			new Effect(FACE, DAMAGE, new Amount(soak), 1, true)
		});
	}
	
	static {
		UNIVERSAL_ACTION = new Action();
		UNIVERSAL_ACTION.addEffectLine(new EffectLine(new Cost(EffectType.O, 2), new Effect[] {
			new Effect(GAIN, FATIGUE)
		}));
		UNIVERSAL_ACTION.addEffectLine(new EffectLine(new Cost(EffectType.A, 2), new Effect[] {
			new Effect(SUFFER, FATIGUE)
		}));
		UNIVERSAL_ACTION.addEffectLine(new EffectLine(new Cost(EffectType.D, 1), new Effect[] {
			new Effect(SUFFER, RECHARGE_ANY, new Amount(2))
		}));
		UNIVERSAL_ACTION.addEffectLine(new EffectLine(new Cost(EffectType.E, 1), new Effect[] {
			new Effect(SUFFER, FATIGUE)
		}));

		UNIVERSAL_ATTACK = new Action();
		UNIVERSAL_ATTACK.addAllEffectLines(UNIVERSAL_ACTION);
		UNIVERSAL_ATTACK.addEffectLine(new EffectLine(new Cost(EffectType.C, 1), new Effect[] {
			new Effect(DEAL, CRITICAL)
		}));
		
	}
	
	public static String[] getAllActions() {
		//TODO place File stuff out of the Business package
		//TODO make it work
//		File folder = new File("cards/");
//		String[] actions = folder.list();
//		System.out.println(folder.list());
//		for(int i = 0; i < actions.length; ++i) {
//			actions[i] = actions[i].substring(0, (actions[i].length()-4));
//		}
//		return actions;
		String[] test = {"bleh","blub"};
		return test;
	}
	
	public static String turnToPath(String name) {
//		WarhammerOptimiserApp\src\cards\Acrobatic Strike.txt
		return ("cards/" + name + ".txt");
	}
}



//		Cost cost = new Cost(EffectType.O, cha.getMyWeapon().getCr(), true);
//		action.addEffectLine(new EffectLine(cost, new Effect[] {
//			new Effect(DEAL, CRITICAL)
//		}));
//		action.addEffectLine(new EffectLine(new Cost(EffectType.C, 1, true), new Effect[] {
//			new Effect(DEAL, CRITICAL)
//		}));
//		return action;
//	}
//	
//	public static Action meleeStrike(Character cha) {
//		Action action = new Action();
//		action.setTitle("Melee Strike");
//		action.addAllEffectLines(universalAttack(cha));
//		action.addEffectLine(new EffectLine(new Cost(EffectType.S, 1), new Effect[] {
//			new Effect(DEAL, DAMAGE, cha.getMyWeapon().getDr() + cha.getMyStrength())
//		}));
//		action.addEffectLine(new EffectLine(new Cost(EffectType.S, 3), new Effect[] {
//			new Effect(DEAL, DAMAGE, cha.getMyWeapon().getDr() + cha.getMyStrength() +2)
//		}));
//		action.addEffectLine(new EffectLine(new Cost(EffectType.O, 2), new Effect[] {
//			new Effect(GAIN, MANOEUVRE)
//		}));
//		action.addEffectLine(new EffectLine(new Cost(EffectType.A, 2), new Effect[] {
//			new Effect(FACE, DISENGAGE)
//		}));
//		return action;
//	}
//	
//	public static Action doubleStrike(Character cha) {
//		Action action = new Action();
//		action.setTitle("Double Strike");
//		action.addAllEffectLines(universalAttack(cha));
//		action.setRecharge(2);
//		action.setMisfortune(1);
//		action.addEffectLine(new EffectLine(new Cost(EffectType.S, 1), new Effect[] {
//			new Effect(DEAL, DAMAGE, cha.getMyWeapon().getDr() + cha.getMyStrength())
//		}));
//		action.addEffectLine(new EffectLine(new Cost(EffectType.A, 2), new Effect[] {
//			new Effect(SUFFER, FATIGUE)
//		}));
//
//		action.addEffectLine(true, new EffectLine(new Cost(EffectType.S, 3), new Effect[] {
//			new Effect(DEAL, DAMAGE, cha.getMyWeapon().getDr() + cha.getMyWeaponSecondary().getDr() + 2*cha.getMyStrength())
//		}));
//		action.addEffectLine(true, new EffectLine(new Cost(EffectType.O, 1, true), new Effect[] {
//			new Effect(DEAL, DAMAGE)
//		}));
//		action.addEffectLine(true, new EffectLine(new Cost(EffectType.O, 2, true), new Effect[] {
//			new Effect(DEAL, DAMAGE)
//		}));
//		
//		action.addEffectLine(false, new EffectLine(new Cost(EffectType.S, 2), new Effect[] {
//			new Effect(DEAL, DAMAGE, cha.getMyWeapon().getDr() + cha.getMyWeaponSecondary().getDr() + 2*cha.getMyStrength())
//		}));
//		action.addEffectLine(false, new EffectLine(new Cost(EffectType.O, 2, true), new Effect[] {
//			new Effect(DEAL, DAMAGE, 2)
//		}));
//		action.addEffectLine(false, new EffectLine(new Cost(EffectType.C, 1), new Effect[] {
//			new Effect(DEAL, DAMAGE, cha.getMyWeapon().getDr() + cha.getMyStrength())
//		}));
//		
//		return action;
//	}
	
//	private ArrayList<Action> actionList;
//	public static final Dice CHA = new Dice (new ResultPool[] {N,N,S,S,S,S,O,O});

//public static Action cutAndRun(Character cha) {		//TODO fix
//Action action = new Action(
//	"Cut & Run", 
//	new ActionStance(4, 1, 0, new EffectLine[]{
//			new EffectLine(new Cost(EffectType.S, 1), new Effect[] {
//				new Effect(ResultType.DEAL_DAMAGE, cha.getMyWeapon().getDr() + cha.getMyStrength()),
//				new Effect(ResultType.GAIN_DISENGAGE)
//			}),
//			new EffectLine(new Cost(EffectType.O, 1), new Effect[] {
//				new Effect(ResultType.DEAL_EXPOSED, 1, 2)
//			}),
//			new EffectLine(new Cost(EffectType.A, 2), new Effect[] {
//				new Effect(ResultType.TAKE_RECHARGE_ANY)
//			})
//	}), 
//	new ActionStance(4, 1, 0, new EffectLine[]{
//			new EffectLine(new Cost(EffectType.S, 1), new Effect[] {
//				new Effect(ResultType.DEAL_DAMAGE, cha.getMyWeapon().getDr() + cha.getMyStrength()),
//				new Effect(ResultType.GAIN_DISENGAGE)
//			})
//	})
//);
//action.addAllEffectLines(universal(cha));
//return action;
//}
//
//public ActionLibrary() {
//	actionList = new ArrayList<Action>();
//}
//
//public ActionLibrary(final File folder) {
//	actionList = new ArrayList<Action>();
//	readAllActions(folder);
//}
//
//private void readAllActions(final File folder) {
//	for(final File fileEntry : folder.listFiles()) {
//		if(fileEntry.isFile()) {
//			actionList.add(read(fileEntry.getName()));
//		}
//	}
//}
//
//public static void write(Action action) {
//	FileOutputStream fos = null;
//	ObjectOutputStream out = null;
//	try {
//		fos = new FileOutputStream(action.getTitle() + ".act");
//		out = new ObjectOutputStream(fos);
//		out.writeObject(action);
//		out.close();
//	} catch(IOException ex) {
//		//TODO bonne gestion
//		ex.printStackTrace();
//	}
//}
//
//public static Action read(String fileName) {
//	Action action = null;
//	FileInputStream fis = null;
//	ObjectInputStream in = null;
//	try {
//		fis = new FileInputStream(fileName);
//		in = new ObjectInputStream(fis);
//		action = (Action)in.readObject();
//		in.close();
//	} catch(IOException ex) {
//		//TODO bonne gestion
//		ex.printStackTrace();
//	} catch(ClassNotFoundException ex) {
//		//TODO bonne gestion
//		ex.printStackTrace();
//	}
//	return action;
//}

