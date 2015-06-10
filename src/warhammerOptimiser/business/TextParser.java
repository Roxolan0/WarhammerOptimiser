package warhammerOptimiser.business;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import static warhammerOptimiser.business.TargetType.*;
import static warhammerOptimiser.business.ResultType.*;
import static com.roxolan.warhammeroptimiserapp.AppFileManager.*;

public class TextParser {
	
	public static Action parseFile(BufferedReader reader) throws IllegalArgumentException, FileNotFoundException {
		Scanner scanner = new Scanner(reader);
		return parseFile(scanner);
	}
	public static Action parseFile(String fileName) throws IllegalArgumentException, FileNotFoundException {
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		return parseFile(scanner);
	}
	public static Action parseFile(Scanner scanner) throws IllegalArgumentException, FileNotFoundException {
		//TODO meilleure gestion de FileNotFoundException
		Action action;
		ActionStance conservative = new ActionStance();
		ActionStance reckless = new ActionStance();
		try {
			String title = scanner.nextLine();
			conservative = parseStance(scanner);
			conservative.setTitle(title);
			//TODO find a way to read the reckless title if there's one.
			reckless = parseStance(scanner);
			reckless.fillNonEffects(conservative);
		} finally {
			scanner.close();
		}
		action = new Action(conservative, reckless);
//		System.out.println("test " + action.stance(true).getFortune());	//TODO remove
//		action.addAllEffectLines(ActionLibrary.universalAttack(character));	//TODO improve
//		System.out.println(action.toCardString());	//TODO remove
		return action;
	}
	
	private static ActionStance parseStance(Scanner scanner) throws IllegalArgumentException {
		ActionStance stance = new ActionStance();
		String curLine = scanner.nextLine();
		String curWord;
		while(!curLine.toLowerCase().startsWith("s")) {
			Scanner smallScan = new Scanner(curLine);
			curWord = smallScan.next();
			if(curWord.toLowerCase().startsWith("chl") || curWord.toLowerCase().startsWith("cha")) {
				curWord = smallScan.next();
				stance.setChallenge(Integer.parseInt(curWord));
			} else if(curWord.toLowerCase().startsWith("exp")) {
				curWord = smallScan.next();
				stance.setExpertise(Integer.parseInt(curWord));
			} else if(curWord.toLowerCase().startsWith("for")) {
				curWord = smallScan.next();
				stance.setFortune(Integer.parseInt(curWord));
			} else if(curWord.toLowerCase().startsWith("mis")) {
				curWord = smallScan.next();
				stance.setMisfortune(Integer.parseInt(curWord));
			} else if(curWord.toLowerCase().startsWith("rec")) {
				curWord = smallScan.next();
				stance.setRecharche(Integer.parseInt(curWord));
			} else {
				throw new IllegalArgumentException("Could not parse the line's header " + curWord);
			}
			curLine = scanner.nextLine();
		}
		EffectLine firstLine = parseEffectLine(curLine);
		stance.addEffectLine(firstLine);
		curLine = scanner.nextLine();
		while(scanner.hasNext() && !curLine.toLowerCase().startsWith("-")) {
			stance.addEffectLine(parseEffectLine(curLine, firstLine));
			if(scanner.hasNext()) {
				curLine = scanner.nextLine();
			}
		}
		return stance;
	}
	
	
	private static Cost parseCost(String s) throws IllegalArgumentException {
		EffectType type = parseEffectType(s);
		int amount = s.length();
		Cost cost = new Cost(type, amount);
		return cost;
	}
	
	private static EffectType parseEffectType(String type) throws IllegalArgumentException {
		if(type.toLowerCase().startsWith("s")) {
			return EffectType.S;
		} else if (type.toLowerCase().startsWith("o")) {
			return EffectType.O;
		} else if (type.toLowerCase().startsWith("a")) {
			return EffectType.A;
		} else if (type.toLowerCase().startsWith("c")) {
			return EffectType.C;
		} else if (type.toLowerCase().startsWith("h")) {
			return EffectType.H;
		} else if (type.toLowerCase().startsWith("d")) {
			return EffectType.D;
		} else if (type.toLowerCase().startsWith("e")) {
			return EffectType.E;
		}
		throw new IllegalArgumentException("Could not parse EffectType " + type);
	}
	
	public static EffectLine parseEffectLine(String line) 
	throws IllegalArgumentException {
		return parseEffectLine(line, new EffectLine(new Cost(EffectType.S, 0), new ArrayList<Effect>()));
	}
	public static EffectLine parseEffectLine(String line, EffectLine firstLine) 
	throws IllegalArgumentException {
		ArrayList<Effect> effects = new ArrayList<Effect>();
		line = line.replace(":", "");
		line = line.replace(", "," | ");
		Scanner scanner = new Scanner(line);
		String curWord = scanner.next();
		Cost cost = parseCost(curWord);
		int targetType, resultType, duration;
		Amount amount;
		boolean needsSuccess;
		try {
			while(scanner.hasNext()) {
				targetType = DEAL;
				amount = new Amount(1);
				duration = 1;
				needsSuccess = false;
				curWord = scanner.next();
				if(curWord.toLowerCase().startsWith("sam")) {
					effects.addAll(firstLine.getEffects());
					if(scanner.hasNext()) {
						curWord = scanner.next();
					}
					while(scanner.hasNext() && curWord.toLowerCase().startsWith("|")) {
						curWord = scanner.next();
					}
				} else {
					if(curWord.toLowerCase().startsWith("+")) {
						needsSuccess = true;
						curWord = curWord.substring(1);
					}
					if(parseTargetType(curWord) != -1) {
						targetType = parseTargetType(curWord);
						if(!TargetType.isValid(targetType)) {
							throw new IllegalArgumentException("Could not parseTargetType " + curWord);
						}
						curWord = scanner.next();
					}
					if(parseAmount(curWord) != null) {
						amount = parseAmount(curWord);
						curWord = scanner.next();
					}
					resultType = parseResultType(curWord);
					if(!ResultType.isValid(resultType)) {
						throw new IllegalArgumentException("Could not parseResultType " + curWord);
					}
					if(scanner.hasNext()) {
						curWord = scanner.next();
					}
					if(parseDuration(curWord) > 0) {
						duration = parseDuration(curWord);
						if(scanner.hasNext()) {
							curWord = scanner.next();
						}
						while(scanner.hasNext() 
								&& (curWord.toLowerCase().startsWith("rou") 
								|| curWord.toLowerCase().startsWith("rnd")
								|| curWord.toLowerCase().startsWith("|"))) {
							curWord = scanner.next();
						}
					}
					Effect effect = new Effect(targetType, resultType, amount, duration, needsSuccess);
					effects.add(effect);
				}
			}
		} finally {
			scanner.close();
		}
		EffectLine effectLine = new EffectLine(cost, effects);
		return effectLine;
	}
	
	
	public static int parseTargetType(String s1) {
		if(s1.toLowerCase().startsWith("gai")
				|| s1.toLowerCase().startsWith("hea")
				|| s1.toLowerCase().startsWith("rec")) {
			return GAIN;
		} 
		else if(s1.toLowerCase().startsWith("los")
				|| s1.toLowerCase().startsWith("tak")
				|| s1.toLowerCase().startsWith("suf")) {
			return SUFFER;
		}
		else if(s1.toLowerCase().startsWith("fac")
				|| s1.toLowerCase().startsWith("ene")) {
			return FACE;
		}
		else if(s1.toLowerCase().startsWith("dea")
				|| s1.toLowerCase().startsWith("inf")) {
			return DEAL;
		}
//		System.out.println("Tried to parseTarget " + s1 + "; got nothing.");	//TODO remove
		return -1;
	}
	
	public static int parseDuration(String s) throws IllegalArgumentException {
		try {
			return Integer.parseInt(s);
		} catch(NumberFormatException e) {
			return -1;
		}
	}
	
	
	public static Amount parseAmount(String s) throws IllegalArgumentException {
		try {
			return new Amount(s);
		} catch(IllegalArgumentException e) {
			return null;
		}
	}

//	private static final int MAX_RESULT_LENGTH = 10;
}

//tit Disorienting Strike
//rec 2
//mis 2
//eff S 1 : deal normal damage
//eff S 3 : deal normal damage, deal staggered 1
//eff O 1 suc : deal stag 1 1
//eff O 2 suc : deal soak
//eff A 2 : self drop weapon
//eff H 1 : self exposed 1 2

//public static ActionStance parseStance(String fileName) throws IllegalArgumentException, FileNotFoundException {
//ActionStance stance = new ActionStance();
//File file = new File(fileName);
//Scanner scanner = new Scanner(file);
//try {
//	while(scanner.hasNextLine()) {
//		parseLine(stance, scanner.nextLine());
//	}
//} finally {
//	scanner.close();
//}
//return stance;
//}

//private int parseNumber(String s) {
//try {
//	return Integer.parseInt(scanner.next());
//} catch(NumberFormatException e) {
//	return -1;
//}
//}

//public static int parseNumber(String s, Character character) throws IllegalArgumentException {
//	String s1 = s;
//	s1 = s1.replace("str", Integer.toString(character.getMyStrength()));
//	s1 = s1.replace("tou", Integer.toString(character.getMyToughness()));
//	s1 = s1.replace("agi", Integer.toString(character.getMyAgility()));
//	s1 = s1.replace("int", Integer.toString(character.getMyIntelligence()));
//	s1 = s1.replace("wil", Integer.toString(character.getMyWillpower()));
//	s1 = s1.replace("fel", Integer.toString(character.getMyFellowship()));
//	s1 = s1.replace("normal", Integer.toString(character.normalDamage()));
//	s1 = s1.replace("nor", Integer.toString(character.normalDamage()));
//	s1 = s1.replace("allies", Integer.toString(character.getAllies()));
//	s1 = s1.replace("group", Integer.toString(character.getGroup()));
//	s1 = s1.replace("grp", Integer.toString(character.getGroup()));
//	ScriptEngineManager mgr = new ScriptEngineManager();
//    ScriptEngine engine = mgr.getEngineByName("JavaScript");
//	try {
////		System.out.println(Double.parseDouble(engine.eval(s1).toString()));	//TODO remove
//	    return (int)Double.parseDouble(engine.eval(s1).toString());	//Integer.parseInt(engine.eval(s1).toString());
//	} catch(Exception e) {
//		return -1;
////		throw new IllegalArgumentException("Couldn't parseAmount " + s);
//	}
//}
