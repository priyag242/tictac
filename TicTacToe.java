import java.util.Scanner;

public class TicTacToe {
    private static final char EMPTY = '-';
    private static final int SIZE = 3;
    private static char[][] board = new char[SIZE][SIZE];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        // create one Scanner and reuse it
        Scanner scanner = new Scanner(System.in);
        try {
            initializeBoard();
            printBoard();

            boolean gameEnded = false;
            while (!gameEnded) {
                playerMove(scanner);
                printBoard();
                if (hasWon(currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameEnded = true;
                } else if (isBoardFull()) {
                    System.out.println("The match has been drawn !!!");
                    gameEnded = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
            System.out.println("Game over.");
        } finally {
            // close the scanner once at the end
            scanner.close();
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void printBoard() {
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j]);
                if (j < SIZE - 1) System.out.print(" | ");
            }
            System.out.println();
            if (i < SIZE - 1) {
                System.out.println("---------");
            }
        }
        System.out.println();
    }

    private static void playerMove(Scanner scanner) {
        int row = -1, col = -1;
        while (true) {
            System.out.println("Player " + currentPlayer + ", enter your move (row[1-3] and column[1-3]): ");
            System.out.print("Row: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter numbers.");
                scanner.next(); // clear invalid input
                continue;
            }
            row = scanner.nextInt() - 1;

            System.out.print("Column: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter numbers.");
                scanner.next(); // clear invalid input
                continue;
            }
            col = scanner.nextInt() - 1;

            if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
                System.out.println("This position is outside the board. Try again.");
            } else if (board[row][col] != EMPTY) {
                System.out.println("This position is already taken. Try again.");
            } else {
                board[row][col] = currentPlayer;
                break;
            }
        }
    }

    private static boolean hasWon(char player) {
        // rows
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == player &&
                board[i][1] == player &&
                board[i][2] == player) {
                return true;
            }
        }
        // columns
        for (int j = 0; j < SIZE; j++) {
            if (board[0][j] == player &&
                board[1][j] == player &&
                board[2][j] == player) {
                return true;
            }
        }
        // diagonals
        if (board[0][0] == player &&
            board[1][1] == player &&
            board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player &&
            board[1][1] == player &&
            board[2][0] == player) {
            return true;
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
