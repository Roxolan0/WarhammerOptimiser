package warhammerOptimiser.business;

import java.io.Serializable;
import java.util.Scanner;
import static warhammerOptimiser.business.Skill.*;
import static warhammerOptimiser.business.Characteristic.*;

public class Amount implements Serializable {
	private static final long serialVersionUID = -8055957361635494563L;

	public Amount() {}
	public Amount(int newBase) {
		base = newBase;
	}
	
	public Amount(Amount amount) {
		this.base = amount.base;
		this.strength = amount.strength;
		this.agility = amount.agility;
		this.intelligence = amount.intelligence;
		this.willpower = amount.willpower;
		this.fellowship = amount.fellowship;
		this.normal = amount.normal;
		this.secondary = amount.secondary;
		this.unarmed = amount.unarmed;
		this.dr = amount.dr;
		this.soak = amount.soak;
		this.conservative = amount.conservative;
		this.reckless = amount.reckless;
		this.group = amount.group;
		this.allies = amount.allies;
		this.enemies = amount.enemies;
	}
	
	public Amount(String s) throws IllegalArgumentException {
		if(ResultType.parseResultType(s) != -1) {
			throw new IllegalArgumentException("Amount/EffectType ambiguity detected.");
		}
		int sign = 1;
		s = s.replace("+", " + ");
		s = s.replace("-", " - ");
		Scanner scanner = new Scanner(s);
		while(scanner.hasNext()) {
			String cur = scanner.next().toLowerCase();
			if(cur.equals("+")) {
				sign = 1;
			} else if(cur.equals("-")) {
				sign = -1;
			} else if(cur.startsWith("agi")) {
				agility = sign;
			} else if(cur.startsWith("all")) {
				allies = sign;
			} else if(cur.startsWith("con")) {
				conservative = sign;
			} else if(cur.equals("dr")) {
				dr = sign;
			} else if(cur.startsWith("ene")) {
				enemies = sign;
			} else if(cur.startsWith("fel")) {
				fellowship = sign;
			} else if(cur.startsWith("gro")) {
				group = sign;
			} else if(cur.startsWith("int")) {
				intelligence = sign;
			} else if(cur.startsWith("nor")) {
				normal = sign;
			} else if(cur.startsWith("rec")) {
				reckless = sign;
			} else if(cur.startsWith("sec")) {
				secondary = sign;
			} else if(cur.startsWith("soa")) {
				soak = sign;
			} else if(cur.startsWith("str") && !cur.startsWith("stress")) {
				strength = sign;
			} else if(cur.startsWith("tou")) {
				toughness = sign;
			} else if(cur.startsWith("una")) {
				unarmed = sign;
			} else if(cur.startsWith("wil")) {
				willpower = sign;
			} else {
				try {
					base = sign * Integer.parseInt(cur);
				} catch(NumberFormatException e) {
					throw new IllegalArgumentException("Could not parse Amount " + s);
				}
			}
		}
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Amount)) return false;
		Amount other = (Amount) o;
		return this.strength == other.strength
			&& this.toughness == other.toughness
			&& this.agility == other.agility
			&& this.intelligence == other.intelligence
			&& this.willpower == other.willpower
			&& this.fellowship == other.fellowship
			&& this.normal == other.normal
			&& this.secondary == other.secondary
			&& this.unarmed == other.unarmed
			&& this.dr == other.dr
			&& this.soak == other.soak
			&& this.conservative == other.conservative
			&& this.reckless == other.reckless
			&& this.group == other.group
			&& this.allies == other.allies
			&& this.enemies == other.enemies
			&& this.base == other.base;
	}
	
	public String toCardString() throws IllegalArgumentException {
		//TODO distinguish bonuses from multiple targets
		String s = "";
		if(strength > 0)		s += "str+";
		if(strength < 0)		s += "-str+";
		if(toughness > 0)		s += "tou+";
		if(toughness < 0)		s += "-tou+";
		if(agility > 0)			s += "agi+";
		if(agility < 0)			s += "-agi+";
		if(intelligence > 0)	s += "int+";
		if(intelligence < 0)	s += "-int+";
		if(willpower > 0)		s += "wil+";
		if(willpower < 0)		s += "-wil+";
		if(fellowship > 0)		s += "fel+";
		if(fellowship < 0)		s += "-fel+";
		if(normal > 0)			s += "normal+";
		if(normal < 0)			s += "-normal+";
		if(secondary > 0)		s += "secondary+";
		if(secondary < 0)		s += "-secondary+";
		if(unarmed > 0)			s += "unarmed+";
		if(unarmed< 0)			s += "-unarmed+";
		if(dr > 0)				s += "dr+";
		if(dr < 0)				s += "-dr+";
		if(conservative > 0)	s += "conservative+";
		if(conservative < 0)	s += "-conservative+";
		if(reckless > 0)		s += "reckless+";
		if(reckless < 0)		s += "-reckless+";
		if(soak > 0)			s += "soak+";
		if(soak < 0)			s += "-soak+";
		if(group > 0)			s += "group+";
		if(group < 0)			s += "-group+";
		if(allies > 0)			s += "allies+";
		if(allies < 0)			s += "-allies+";
		if(enemies > 0)			s += "enemies+";
		if(enemies < 0)			s += "-enemies+";
		if(base != 0) 			s += Integer.toString(base);
		s = s.replace("+-", "-");
		if(s.endsWith("+") || s.endsWith("-")) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}
	public int total(Character c, Character enemy) {
		int total = base;
		total += strength * c.getCharacteristic(STRENGTH)
			+ toughness * c.getCharacteristic(TOUGHNESS)
			+ agility * c.getCharacteristic(AGILITY)
			+ intelligence * c.getCharacteristic(INTELLIGENCE)
			+ willpower * c.getCharacteristic(WILLPOWER)
			+ fellowship * c.getCharacteristic(FELLOWSHIP);
		total += normal * c.normalDamage() 
			+ secondary * c.secondaryDamage()
			+ unarmed * c.unarmedDamage()
			+ dr * c.getWeapon().getDr();
		total += conservative * c.getConservativeValue();
		total += reckless * c.getRecklessValue();
		total += soak * enemy.soak();
		total += group * c.getGroup();
		total += allies * c.getAllies();
		total += enemies * enemy.getGroup();
		return total;
	}
	public int total() {
		int total = base;
		total += strength + toughness + agility + intelligence + willpower + fellowship;
		total += normal + secondary + unarmed + dr;
		total += soak;
//		total += conservative;
		total *= group;
		total *= allies;
		total *= enemies;
		return total;
		}
	
	private int strength, toughness, agility, intelligence, willpower, fellowship;
	private int normal, secondary, unarmed, dr;
	private int soak;
	private int conservative, reckless;
	private int group, allies, enemies;
	private int base;
}
