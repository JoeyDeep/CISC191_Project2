import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Lead Author(s):
 * @author Jeorge Vorce
 * @author Braulio Ochoa
 *  
 * Version/date: 05/17/2023
 * 
 * Responsibilities of class:
 * - Create the GUI that will reflect actions performed in other classes.
 * - Provide methods other classes utilize to manage the UI, such as drawing on the UI board, changing the current player, and game status.
 * - Initialize & process user input on the buttons created in-class to send to the Game class for further processing and use.
 * - Reset the GUI to facilitate a new, fresh UI game board.
 */
//is a jframe
public class TicTacToeGUI extends JFrame {
	//has buttons
    private JButton[][] buttons;
    //has a game
    private Game game;
    //has a status
    private JLabel statusLabel;
    //has a label showing current player
    private JLabel currentPlayerLabel;

    public TicTacToeGUI(Game game) {
    	// give class the Game object for use in sending UI input to be processed and used
        this.game = game;
        // create grid of buttons to act as the board
        buttons = new JButton[3][3];

        // set window properties
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // initialize labels and buttons
        initializeStatusLabel();
        initializeCurrentPlayerLabel();
        initializeButtons();

        // position labels on GUI
        add(statusLabel, BorderLayout.NORTH);
        add(currentPlayerLabel, BorderLayout.SOUTH);
        // size the GUI to be 1:1 aspect ratio for proper visualization of game board
        setSize(500, 500);
        // the GUI is now ready to be shown to the user
        setVisible(true);
    }
    // initial property setting for status label
    private void initializeStatusLabel() {
        statusLabel = new JLabel("Welcome to Tic Tac Toe!");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    // initial property setting for current player label
    private void initializeCurrentPlayerLabel() {
        currentPlayerLabel = new JLabel("Current Player: X");
        currentPlayerLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        currentPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
    

    // initial property setting & input setup for 3x3 button grid
    private void initializeButtons() {
        JPanel buttonPanel = new JPanel();
        // use a 3x3 gridlayout to position the 
        buttonPanel.setLayout(new GridLayout(3, 3));

        // create 9 buttons for 3x3 grid
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
            	// create button object
                JButton button = new JButton();
                // set font properties (used when a mark is given to the button)
                button.setFont(new Font("Arial", Font.BOLD, 40));
                // set the action listener to the private ButtonClickListener class with the row and column position
                button.addActionListener(new ButtonClickListener(row, col));
                // cache button for ease of access
                buttons[row][col] = button;
                // add button to the gridlayout
                buttonPanel.add(button);
            }
        }

        // position the grid to the center of the GUI
        add(buttonPanel, BorderLayout.CENTER);
    }

    public void resetBoard() {
    	// for each of the 9 elements, empty the text, and re-enable the button so it can be used in the next game
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setEnabled(true);
            }
        }
    }

    public void updateBoard(int row, int col, char mark) {
    	// set the text of the button to the mark given
        buttons[row][col].setText(String.valueOf(mark));
        // disable the button to disable further input on this button
        buttons[row][col].setEnabled(false);
    }

    public void updateStatusLabel(String text) {
    	// update the status label text
        statusLabel.setText(text);
    }

    public void updateCurrentPlayerLabel(Player player) {
    	// update the current player label to be the mark of the player who's turn it is
        currentPlayerLabel.setText("Current Player: " + player.getMark());
    }

    private class ButtonClickListener implements ActionListener {
        private int row; // ButtonClickListener HAS-A row
        private int col; // ButtonClickListener HAS-A col(umn)

        // upon creation, set row and col so the game class knows which corresponding button has been pressed
        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        // when clicked, tell game to playTurn with coordinates of this specific button
        public void actionPerformed(ActionEvent e) {
            game.playTurn(row, col);
        }
    }
}