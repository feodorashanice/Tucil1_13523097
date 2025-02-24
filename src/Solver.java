import java.util.ArrayList;
import java.util.List;

public class Solver {
    private Board board;
    private List<Piece> pieces;
    private long startTime;
    private int iterationCount = 0;

    public Solver(int N, int M, List<Piece> pieces) {
        board = new Board(N, M);
        this.pieces = pieces;
    }

    public boolean solveDetails() {
        startTime = System.currentTimeMillis();
        boolean result = solvePuzzle();
        long endTime = System.currentTimeMillis();
        System.out.println("Waktu pencarian: " + (endTime - startTime) + " ms");
        System.out.println("Banyak kasus yang ditinjau: " + iterationCount);
        if (result) {
            System.out.println("Apakah Anda ingin menyimpan hasil ke file? (y/n)");
        }
        return result;
    }

    public boolean solve(int pieceIndex) {
        iterationCount++;

        if (pieceIndex >= pieces.size()) {
            board.printBoard();
            return board.isFull();
        }

        Piece piece = pieces.get(pieceIndex);

        for (int i = 0; i < board.getN(); i++) {
            for (int j = 0; j < board.getM(); j++) {
                List<char[][]> transformations = new ArrayList<>();

                transformations.add(piece.getPiece());
                // printMatrix(transformations.get(0));

                transformations.add(piece.flipHorizontal());
                // printMatrix(transformations.get(1));

                char[][] rotated90 = piece.getPiece();
                for (int k = 0; k < 3; k++) {
                    rotated90 = piece.rotate90(rotated90);
                    transformations.add(rotated90);
                    // printMatrix(rotated90);
                }

                char[][] flipped = piece.flipHorizontal();
                char[][] flippedRotated90 = flipped;
                for (int k = 0; k < 3; k++) {
                    flippedRotated90 = piece.rotate90(flippedRotated90);
                    transformations.add(flippedRotated90);
                    // printMatrix(flippedRotated90);
                }

                for (char[][] transformation : transformations) {
                    // printMatrix(transformation);
                    if (board.placeValid(transformation, i, j)) {
                        board.placePiece(transformation, i, j);
                        if (solve(pieceIndex + 1)) {
                            return true;
                        }
                        board.removePiece(transformation, i, j);
                    }
                }
            }
        }
        return false;
    }

    public boolean solvePuzzle() {
        return solve(0);
    }

    public Board getBoard() {
        return board;
    }

    // Helper method to print a char matrix
    private static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            System.out.println(new String(row));
        }
        System.out.println();
    }
}