/**
 * Lead Author(s):
 * @author Jeorge Vorce
 * @author Braulio Ochoa
 *  
 * Version/date: 05/17/2023
 * 
 * Responsibilities of class:
 * - Create a board matrix that contains all of the player's inputs
 * - Manage player's inputs by marking the spaces they input
 * - Analyze the board matrix to determine if the player's input does not overwrite an existing move
 * - Analyze the board matrix to determine a win condition
 */

public class Board {
    private char[][] matrix; // Board HAS-A matrix

    public Board() {
    	// create a new 3x3 matrix to represent the board
        matrix = new char[3][3];
        // initialize the matrix to contain all values of '-' (empty value)
        clear();
    }

    public void clear() {
    	// loop through each element of the matrix and clear any marks placed during gameplay
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	markSpace(i, j, '-');
            }
        }
    }
    public boolean isValidMove(int row, int column) {
    	// determines if the value at the given row and column of the board matrix contains an empty value
        return matrix[row][column] == '-';
    }

    public void markSpace(int row, int column, char mark) {
    	// sets the matrix value at the given row and column indexes to the given mark
        matrix[row][column] = mark;
    }
   
    public boolean checkWin(char mark) {
        // check rows
        for (int i = 0; i < 3; i++) {
            if (matrix[i][0] == mark && matrix[i][1] == mark && matrix[i][2] == mark) {
                return true;
            }
        }

        // check columns
        for (int j = 0; j < 3; j++) {
            if (matrix[0][j] == mark && matrix[1][j] == mark && matrix[2][j] == mark) {
                return true;
            }
        }

        // check left-to-right diagonal
        if (matrix[0][0] == mark && matrix[1][1] == mark && matrix[2][2] == mark) {
            return true;
        }
        // check right-to-left diagonal
        else if (matrix[0][2] == mark && matrix[1][1] == mark && matrix[2][0] == mark) {
            return true;
        }

        // if no conditions above have been met, a win has not been determined
        return false;
    }
}