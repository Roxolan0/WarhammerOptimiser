package warhammerOptimiser.business;
import java.util.ArrayList;

/**
 * @author H
 * A list of ResultPools. A ResultPool is a set of dice symbols (success, comet etc.).
 */
public class ResultPoolList {
	public ResultPoolList() {
		list = new ArrayList<ResultPool>();
		nb = new ArrayList<Integer>();
	}

	public ResultPool getPool(int i) {
		return list.get(i);
	}
	public int getNb(int i) {
		return nb.get(i);
	}
	public int size() {
		return list.size();
	}

	public void addResultPool(ResultPool pool, int poolNb) {
		int i = list.indexOf(pool);
		if(i != -1) {
			nb.set(i, nb.get(i) + poolNb);
		} else {
			list.add(pool);
			nb.add(poolNb);
		}
	}
	
	public long totalNb() {
		long total = 0;
		for(int i = 0; i < size(); ++i) {
			total += nb.get(i);
		}
		return total;
	}
	
	public void balanceAll() {
		for(int i = 0; i < size(); ++i) {
			list.get(i).balance();
		}
	}
	
	public String toString() {
		String s = "";
		for(int i = 0; i < size(); ++i) {
			s += "i = " + i + "; nb = " + nb.get(i) + "; list = [" + list.get(i).toString() + "] \r\n";
		}
		return s;
	}
	
	public void addDice(Dice dice, int nb) {
		for(int i = 0; i < nb; ++i)
			addDice(dice);
	}
	public void addDice(Dice dice) {
		ResultPoolList newResultPoolList = new ResultPoolList();
		for(ResultPool side : dice.getSides()) {
			if(size() == 0) {
				newResultPoolList.addResultPool(side, 1);
			} else {
				for(int i = 0; i < size(); ++i) {
					ResultPool newResult = new ResultPool(getPool(i));
					newResult.add(side);
					newResult.balance();
					newResultPoolList.addResultPool(newResult, getNb(i));
				}
			}
		}
		list = newResultPoolList.list;
		nb = newResultPoolList.nb;
	}

	private ArrayList<ResultPool> list;
	private ArrayList<Integer> nb;
}
