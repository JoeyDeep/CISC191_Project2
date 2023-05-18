/**
 * Lead Author(s):
 * @author Jeorge Vorce
 * @author Braulio Ochoa
 *  
 * Version/date: 05/17/2023
 * 
 * Responsibilities of class:
 * - Hold the players' wins losses, and mark symbol
 * - Contains method to retrieve mark symbol by Player object
 * - Contains method to increment wins and losses
 */
public class Player {
	private int wins; // Player HAS-A wins
    private int losses; // Player HAS-A losses
    private char mark; // Player HAS-A mark
    public Player(char plrMark) {
    	mark = plrMark;
        wins = 0;
        losses = 0;
    }

    public void addWin() {
        wins++;
    }

    public void addLoss() {
        losses++;
    }

    public char getMark() {
        return mark;
    }
}