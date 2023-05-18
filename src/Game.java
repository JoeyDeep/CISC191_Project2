import java.util.LinkedList;

/**
 * Lead Author(s):
 * @author Jeorge Vorce
 * @author Braulio Ochoa
 *  
 * Version/date: 05/17/2023
 * 
 * Responsibilities of class:
 * - The central processing class of the game. Responsible for initializing the game.
 * - Contains game state variables including currentPlayer, turnSequence, and number of moves.
 * - Manages GUI and Board as input is processed
 * - Processes user input passed by GUI button input for backend board, then reflects changes to GUI.
 * - Capable of switching the current turn's player using a LinkedList
 * - Capable of resetting the game and relevant connected classes for a fresh experience backend and frontend.
 */

public class Game {
	//game has moves, this is a counter
    private int moves;
    //game has a current player
    private Player currentPlayer;
    private Player playerX; // Game HAS-MANY Player(s)
    private Player playerO; // Game HAS-MANY Player(s)
    private Board board; // Game HAS-A Board
    private TicTacToeGUI gui; // Game HAS-A GUI
    //game has a turn sequence
    private LinkedList<Player> turnSequence = new LinkedList<Player>();

    // initialize the game by creating player objects, setting the default player, initiating the board gui, and turnSequence
    public Game() {
        moves = 0;
        playerX = new Player('X');
        playerO = new Player('O');
        currentPlayer = playerX;
        board = new Board();
        gui = new TicTacToeGUI(this);
        turnSequence.addLast(playerO);
    }

    // reset relevant variables & classes to faciliate a fresh match
    public void restart() {
        moves = 0;
        currentPlayer = playerX;
        board.clear();
        gui.resetBoard();
        turnSequence.clear();
        turnSequence.addLast(playerO);
    }
    
    // using LinkedList here to swap players each turn
    // game is initiated with turnSequence containing one element: playerO
    
    public void switchPlayerTurn() {
    	// each switch, currentPlayer is set to the first element in turnSequence
    	currentPlayer = turnSequence.getFirst();
    	// added to the end of the list is the opposite player, then to clean up removes the first element
    	turnSequence.addLast((currentPlayer == playerX) ? playerO : playerX);
    	turnSequence.removeFirst();
    }

    // obtain input of row and column from GUI input
    public void playTurn(int row, int column) {
    	// verify integrity of input
    	// (in theory shouldn't be possible to reach as input on a button is disabled after it's been used)
        if (!board.isValidMove(row, column)) {
        	gui.updateStatusLabel("Invalid move! Try again.");
        	// go no further with this method as the turn is invalid
            return;
        }
        
        // increment the number of turns for use in determining a tie
        moves++;

        // mark the matrix space in the board with the player's mark
        board.markSpace(row, column, currentPlayer.getMark());
        // update the board UI to reflect the changes in the board
        gui.updateBoard(row, column, currentPlayer.getMark());

        // after a turn has been and process, determine if an end condition has been met
        
        // determine if the end condition that a player has won is met
        if (board.checkWin(currentPlayer.getMark())) {
            currentPlayer.addWin();
            gui.updateStatusLabel("Player " + currentPlayer.getMark() + " wins!");
            restart();
        }
        // determine if the end condition that there is a tie is met
        else if (moves == 9) {
        	gui.updateStatusLabel("It's a draw!");
            restart();
        }
        // no end condition is met, switch player turns and update the UI label to reflect that
        else {
        	switchPlayerTurn();
        	gui.updateCurrentPlayerLabel(currentPlayer);
        }
    }

    public static void main(String[] args) {
    	// initalize a new game
        Game game = new Game();
    }
}