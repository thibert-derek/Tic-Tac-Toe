import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    public static Scanner sc = new Scanner(System.in);
public static enum Marker {
    X, O, EMPTY;

    public char getChar() {
        switch(this) {
            case X: return 'X';
            case O: return 'O';
            case EMPTY: return ' ';
            default: return ' ';
        }
    }
}    
public static void main(String[] args){
    final int BOARD = 3;
    // TicTacToe game = new TicTacToe();
    Marker[][] board = new Marker[BOARD][BOARD];
    reset(board);

    System.out.println("Tic-Tac-Toe");
    System.out.println("Would you like to play as \"X\" or \"O\"?");
    Marker userMarker;
    if(sc.next().toLowerCase().charAt(0) == 'x'){
        userMarker = Marker.X;
    }
    else {
        userMarker = Marker.O;
    }
    Marker compMarker = (userMarker == Marker.X) ? Marker.O : Marker.X;
    Random rand = new Random();
    int turn = rand.nextInt(2);
    int remainCount = BOARD * BOARD;
    printBoard(board);
    remainCount--;
    boolean done = false;
    int winner = -1;
    while (!done && remainCount>0){
        done = findWinner(board, turn, userMarker, compMarker);
        if(done){
            winner = turn;
        } else {
            turn = (turn+1)%2;

            if(turn == 0){
                userPlay(board, userMarker);
            } else {
                compPlay(board, compMarker);
                printBoard(board);
                remainCount--;
            }
        }
    }
        if (winner == 0){
            System.out.println("You won!");
        } else if (winner == 1){
            System.out.println("Computer Wins!");
        } else {
            System.out.println("Draw");
        }
}
    
        public static void reset(Marker[][]board) {
            for (int i = 0; i < board.length; i++){
                for (int j = 0; j < board.length; j++) {
                    board[i][j] = Marker.EMPTY;
                }
            }
        }
    
        public static void printBoard(Marker[][] board) {
            int numRow = board.length;
            int numCol = board[0].length;
            System.out.println();
            System.out.print(" ");
            for (int i = 0; i < numCol; i++){
                System.out.print("  " + i + "  ");
            }
            System.out.print("\n");
            System.out.println();
            for (int i = 0; i < numRow; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < numCol; j++) {
                    if (j != 0){
                        System.out.print("|");
                    }
                    System.out.print(" " + board[i][j].getChar() + " ");
                }
                System.out.println();
                if (i != (numRow - 1)) {
                    System.out.print(" ");
                    for (int j = 0; j < numCol; j++) {
                        if (j != 0){
                            System.out.print("|");
                        } else {
                            System.out.print(" ");
                        }
                        System.out.print("___");
                    }
                    System.out.println();
                }
            }
            System.out.print("     |   |  ");
            System.out.println();
        }

        public static void userPlay(Marker[][]board, Marker usym) {
            System.out.print("\nEnter the row: ");
            int rowIndex = sc.nextInt();
            sc.nextLine();
            System.out.print("\nEnter the column: ");
            int colIndex = sc.nextInt();
            sc.nextLine();
                while (board[rowIndex][colIndex] != Marker.EMPTY) {
                   try { System.out.println("\nCell is taken.");
                    System.out.print("\nEnter the row: ");
                    rowIndex = sc.nextInt();
                    sc.nextLine();
                    System.out.print("\nEnter the column: ");
                    colIndex = sc.nextInt();
                    sc.nextLine();
                    }
                    catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Sorry, the numbers you've entered is not allowed.");
                    }
                }
            board[rowIndex][colIndex] = usym;
        }

        public static void compPlay(Marker[][] board, Marker compMarker) {
           Random rand = new Random();
            while(true) {
                int i = rand.nextInt(3);
                int j = rand.nextInt(3);
                    if (board[i][j] == Marker.EMPTY) {
                        board[i][j] = compMarker;
                        break;
                    }
           }
           return;
        }

        public static boolean findWinner(Marker[][]board, int turn, Marker userMarker, Marker compMarker) {
            Marker mrkr;
            if (turn == 0){
                mrkr = userMarker;
            }  else {
                mrkr = compMarker;
            }
            int i, j;
            boolean win = false;
 // Check row
            for (i = 0; i < board.length && !win; i++) {
                for (j = 0; j < board[0].length; j++) {
                    if (board[i][j] != mrkr){
                        break;
                    }
                }
                    if (j == board[0].length){
                        win = true;
                    }
                }
 // Check column
            for (j = 0; j < board[0].length && !win; j++) {
                for (i = 0; i < board.length; i++) {
                    if (board[i][j] != mrkr){
                        break;
                    }
                }
                    if (i == board.length){
                        win = true;
                    }
                }
            
 // Check diagonal
            if (!win) {
                for (i = 0; i < board.length; i++) {
                    if (board[i][i] != mrkr){
                        break;
                    }
                }
                    
                if (i == board.length){
                    win = true;
                }  
                if (!win) {
                    for (i = 0; i < board.length; i++) {
                        if (board[i][board.length - 1 - i] != mrkr){
                            break;
                        }
                    }
                    if (i == board.length) {
                        win = true;
                    }
                }
            }
        return win;
 
    }
}

