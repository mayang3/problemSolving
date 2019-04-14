package leetcode;

/**
 * @author baejunbeom
 */
public class NimGame {
	public boolean canWinNim(int n) {
		return n % 4 != 0;
	}

	public static void main(String[] args) {
		NimGame nimGame = new NimGame();
		nimGame.canWinNim(4);
	}
}
