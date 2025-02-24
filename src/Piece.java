public class Piece {
    private char[][] piece;
    private char id;

    // Constructor
    public Piece(char[][] piece, char id) {
        this.piece = piece;
        this.id = id;
    }

    // Getter of piece
    public char[][] getPiece() {
        int rows = piece.length, cols = piece[0].length;
        char[][] copy = new char[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                copy[i][j] = piece[i][j];
        System.out.println("Getting piece " + id + ":");
        printMatrix(copy);
        return copy;
    }

    // Getter of piece ID
    public char getID() {
        return id;
    }

    // Rotate 90 degrees
    public char[][] rotate90(char[][] inputPiece) {
        int rows = inputPiece.length, cols = inputPiece[0].length;
        char[][] rotated = new char[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - 1 - i] = inputPiece[i][j];
            }
        }
        System.out.println("Rotated 90° piece " + id + ":");
        printMatrix(rotated);
        return rotated;
    }

    // Flip horizontally
    public char[][] flipHorizontal() {
        int rows = piece.length, cols = piece[0].length;
        char[][] flipped = new char[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                flipped[i][cols - 1 - j] = piece[i][j];
        System.out.println("Flipped horizontally piece " + id + ":");
        printMatrix(flipped);
        return flipped;
    }

    // Rotate after flip
    public char[][] rotateAfterFlip() {
        char[][] flipped = flipHorizontal();
        char[][] rotated = rotate90(flipped);
        System.out.println("Rotated after flip piece " + id + ":");
        printMatrix(rotated);
        return rotated;
    }

    // Helper method to print a char matrix
    private static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            System.out.println(new String(row));
        }
        System.out.println();
    }
}