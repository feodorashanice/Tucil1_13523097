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
    
    public boolean placeValid(char[][] piece, int row, int col) {
        if (row + piece.length > N || col + piece[0].length > M) {
            // printMatrix(piece);
            return false;
        }
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[0].length; j++) {
                if (piece[i][j] != '.' && board[row + i][col + j] != '.') {
                    // printMatrix(piece);
                    return false;
                }
            }
        }
        // printMatrix(piece);
        return true;
    }

    public void placePiece(char[][] piece, int row, int col) {
        if (!placeValid(piece, row, col)) {
            return;
        }
        // printMatrix(piece);
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[0].length; j++) {
                if (row + i < N && col + j < M && piece[i][j] != '.') {
                    board[row + i][col + j] = piece[i][j];
                }
            }
        }
        printBoard();
    }

    public void removePiece(char[][] piece, int row, int col) {
        // printMatrix(piece);
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[0].length; j++) {
                if (row + i < N && col + j < M && piece[i][j] != '.') {
                    board[row + i][col + j] = '.';
                }
            }
        }
        printBoard();
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
    public void printBoard() {
        for (char[] row : board) {
            StringBuilder line = new StringBuilder();
            for (char cell : row) {
                if (cell == '.') {
                    line.append(cell).append(" ");
                } else {
                    String color = getColor(cell);
                    line.append(color).append(cell).append("\u001B[0m ").append(" ");
                }
            }
            System.out.println(line.toString());
        }
        System.out.println();
    }
    
    private String getColor(char id){
        switch (id){
            case 'A': return "\u001B[31m";
            case 'B': return "\u001B[32m";
            case 'C': return "\u001B[33m";
            case 'D': return "\u001B[34m";
            case 'E': return "\u001B[35m";
            case 'F': return "\u001B[36m";
            case 'G': return "\u001B[37m";
            case 'H': return "\u001B[91m";
            case 'I': return "\u001B[92m";
            case 'J': return "\u001B[93m";
            case 'K': return "\u001B[94m";
            case 'L': return "\u001B[95m";
            case 'M': return "\u001B[96m";
            case 'N': return "\u001B[97m";
            case 'O': return "\u001B[30m";
            case 'P': return "\u001B[90m";
            case 'Q': return "\u001B[40m";
            case 'R': return "\u001B[41m";
            case 'S': return "\u001B[42m";
            case 'T': return "\u001B[43m";
            case 'U': return "\u001B[44m";
            case 'V': return "\u001B[45m";
            case 'W': return "\u001B[46m";
            case 'X': return "\u001B[47m";
            case 'Y': return "\u001B[100m";
            case 'Z': return "\u001B[101m";
            default: return "\u001B[0m";
        }
    }
}