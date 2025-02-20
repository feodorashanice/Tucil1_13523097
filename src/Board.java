public class Board {
    private char[][] board;
    private int N, M;

    // Constructor
    public Board(int N, int M) {
        this.N = N;
        this.M = M;
        board = new char[N][M];

        // Fill up empty spaces with '.'
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                board[i][j] = '.';
            }
        }
    }
    // Boolean to check if a piece can be placed on the board (position is empty)
    public boolean placeValid(char[][] piece, int row, int col) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[0].length; j++) {
                if (piece[i][j] != '.' && board[row + i][col + j] != '.') {
                    return false;   
                }
            }
        }
        return true;
    }
    // Place a piece on the board
    public void placePiece(char[][] piece, int row, int col) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[0].length; j++) {
                if (piece[i][j] != '.') {
                    board[row + i][col + j] = piece[i][j];
                }
            }
        }
    }
    // Remove a piece from the board
    public void removePiece(char[][] piece, int row, int col) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[0].length; j++) {
                if (piece[i][j] != '.') {
                    board[row + i][col + j] = '.';
                }
            }
        }
    }
    // Print the board
    public void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][j] == '\u0000' ? '.' : board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    // Get board
    public char[][] getBoard() {
        return board;
    }
}
