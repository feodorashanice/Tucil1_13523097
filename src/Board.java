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
    public char[][] getBoard(){
        return board;
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
    public void printBoard(){
        for (char[] row : board){
            StringBuilder line = new StringBuilder();
            for (char cell : row){
                if (cell == '.'){
                    line.append(cell).append(" ");
                } else{
                    String color = getColor(cell);
                    line.append(color).append(cell).append("\u001B[0m ").append(" ");
                }
            }
            System.out.println(line.toString());
        }
    }
    
    private String getColor(char id){
        switch (id){
            case 'A': return "\u001B[31m"; // Red
            case 'B': return "\u001B[32m"; // Green
            case 'C': return "\u001B[33m"; // Yellow
            case 'D': return "\u001B[34m"; // Blue
            case 'E': return "\u001B[35m"; // Magenta
            case 'F': return "\u001B[36m"; // Cyan
            case 'G': return "\u001B[37m"; // White
            case 'H': return "\u001B[91m"; // Bright Red
            case 'I': return "\u001B[92m"; // Bright Green
            case 'J': return "\u001B[93m"; // Bright Yellow
            case 'K': return "\u001B[94m"; // Bright Blue
            case 'L': return "\u001B[95m"; // Bright Magenta
            case 'M': return "\u001B[96m"; // Bright Cyan
            case 'N': return "\u001B[97m"; // Bright White
            case 'O': return "\u001B[30m"; // Black
            case 'P': return "\u001B[90m"; // Dark Gray
            case 'Q': return "\u001B[40m"; // Background Black
            case 'R': return "\u001B[41m"; // Background Red
            case 'S': return "\u001B[42m"; // Background Green
            case 'T': return "\u001B[43m"; // Background Yellow
            case 'U': return "\u001B[44m"; // Background Blue
            case 'V': return "\u001B[45m"; // Background Magenta
            case 'W': return "\u001B[46m"; // Background Cyan
            case 'X': return "\u001B[47m"; // Background White
            case 'Y': return "\u001B[100m"; // Background Dark Gray
            case 'Z': return "\u001B[101m"; // Background Bright Red
            default: return "\u001B[0m"; // Default (no color)
        }
    }
}