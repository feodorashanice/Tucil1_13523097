public class Piece{
    private char[][] piece;
    private char id;

    // Constructor
    public Piece(char[][] piece, char id){
        this.piece = piece;
        this.id = id;
    }

    // Getter of piece shape copy
    public char[][] getPiece(){
        int rows = piece.length, cols = piece[0].length;
        char[][] copy = new char[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                copy[i][j] = piece[i][j];
        return copy;
    }

    // Getter of piece ID
    public char getID(){
        return id;
    }

    public char[][] rotate90(char[][] piece) {
        int rows = piece.length, cols = piece[0].length;
        char[][] rotated = new char[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - 1 - i] = piece[i][j];
            }
        }
        return rotated;
    }

    public char[][] flipHorizontal() {
        int rows = piece.length, cols = piece[0].length;
        char[][] flipped = new char[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                flipped[i][cols - 1 - j] = piece[i][j];
        return flipped;
    }

    public char[][] rotateAfterFlip() {
        return rotate90(flipHorizontal());
    }
}