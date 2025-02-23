public class Board {
    private char[][] board;
    private int N, M;

    // Constructor
    public Board(int N, int M){
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

    // Getter
    public int getN(){
        return N;
    }
    public int getM(){
        return M;
    }
    
    // Boolean to check if a piece can be placed on the board (position is not out of bounds and empty)
    public boolean placeValid(char[][] piece, int row, int col){
        if (row + piece.length > N || col + piece[0].length > M){
            return false;
        } // False due to out of bounds
        for (int i = 0; i < piece.length; i++){
            for (int j = 0; j < piece[0].length; j++){
                if (piece[i][j] != '.' && board[row + i][col + j] != '.'){
                    return false;   
                } // False due to overlapping pieces
            }
        }
        return true;
    }
    // Place a piece on the board
    public void placePiece(char[][] piece, int row, int col){
        if (!placeValid(piece, row, col)) return;
        for (int i = 0; i < piece.length; i++){
            for (int j = 0; j < piece[0].length; j++){
                if (row + i < N && col + j < M && piece[i][j] != '.'){
                    board[row + i][col + j] = piece[i][j];
                }
            }
        }
    }
    // Remove a piece from the board
    public void removePiece(char[][] piece, int row, int col){
        for (int i = 0; i < piece.length; i++){
            for (int j = 0; j < piece[0].length; j++){
                if (row + i < N && col + j < M && piece[i][j] != '.'){
                    board[row + i][col + j] = '.';
                }
            }
        }
    }
    // Check if the board is full
    public boolean isFull(){
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (board[i][j] == '.'){
                    return false;
                }
            }
        }
        return true;
    }
    // Print the board
    public void printBoard(){
        for (char[] row : board){
            System.out.println(new String(row));
        }
    }
}
