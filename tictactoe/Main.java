package tictactoe;
import java.util.* ;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        char[][] board = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("|");
            System.out.println("");
        }
        System.out.println("---------");

        //Create a player1 boolean that is true if it is player 1's turn and false if it is player 2's turn
        boolean player1 = true;

        //Create a gameEnded boolean and use it as the condition in the while loop
        boolean gameEnded = false;

        while (!gameEnded) {
            //Create a char variable that stores either 'x' or 'o' based on what player's turn it is
            char c = ' ';
            if (player1) {
                c = 'X';
            } else {
                c = 'O';
            }

            //Create row and col variables which represent indexes that correspond to a position on our board
            int row = 0;
            int col = 0;

            //Only break out of the while loop once the user enters a valid position
            while (true) {
                boolean verify = true;
                //Ask the user for what position they want to place their x or o
                System.out.print("Enter the coordinates:");
                try {
                    col = Integer.parseInt(s.next());
                    row = Integer.parseInt(s.next());
                } catch (NumberFormatException ex) {
                    System.out.println("You should enter numbers!");
                    verify = false;
                }

                if (row == 1) {
                    row = 3;
                } else if (row == 3) {
                    row = 1;
                }

                row = row - 1;
                col = col - 1;

                if (verify) {
                    //Check if the row and col are 0, 1, or 2
                    if (row < 0 || col < 0 || row > 2 || col > 2) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        //Check if the position on the board the user entered is empty (has a -) or not
                    } else if (board[row][col] != ' ') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        break;
                    }
                }
            }

            board[row][col] = c;

            System.out.println("---------");
            for (int i = 0; i < 3; i++) {
                System.out.print("| ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.print("|");
                System.out.println("");
            }
            System.out.println("---------");

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '_') {
                        board[i][j] = ' ';
                    }
                }
            }

            if(playerHasWon(board) == 'X') {
                System.out.println("X wins");
                gameEnded = true;
            } else if(playerHasWon(board) == 'O') {
                System.out.println("O wins");
                gameEnded = true;
            } else {

                //If neither player has won, check to see if there has been a tie (if the board is full)
                if(boardIsFull(board)) {
                    System.out.println("Draw");
                    gameEnded = true;
                } else {
                    //If player1 is true, make it false, and vice versa; this way, the players alternate each turn
                    player1 = !player1;
                }
            }
        }
    }

    //Make a function to see if someone has won and return the winning char
    public static char playerHasWon(char[][] board) {

        //Check each row
        for(int i = 0; i < 3; i++) {
            if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return board[i][0];
            }
        }

        //Check each column
        for(int j = 0; j < 3; j++) {
            if(board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != ' ') {
                return board[0][j];
            }
        }

        //Check the diagonals
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return board[0][0];
        }
        if(board[2][0] == board[1][1] && board[1][1] ==  board[0][2] && board[2][0] != '-') {
            return board[2][0];
        }

        //Otherwise nobody has not won yet
        return ' ';

    }

    //Make a function to check if all of the positions on the board have been filled
    public static boolean boardIsFull(char[][] board) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

