import java.util.*;

public class PieceTransformation{
    public static List<char[][]> transformPieces(List<char[][]> piece){
        List<char[][]> transformedPieces = new ArrayList<>();
        for (char[][] p : piece) {
            transformedPieces.add(p);
            transformedPieces.add(rotate90(p));
            transformedPieces.add(rotate180(p));
            transformedPieces.add(rotate270(p));
            transformedPieces.add(flipHorizontal(p));
            transformedPieces.add(rotate90(flipHorizontal(p)));
            transformedPieces.add(rotate180(flipHorizontal(p)));
            transformedPieces.add(rotate270(flipHorizontal(p)));
        }
        return transformedPieces;
    }
    private static char[][] rotate90(char[][] piece) {
        int rows = piece.length, cols = piece[0].length;
        char[][] rotated = new char[cols][rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                rotated[j][rows - 1 - i] = piece[i][j];
        return rotated;
    }
    private static char[][] rotate180(char[][] piece) {
        return rotate90(rotate90(piece));
    }
    private static char[][] rotate270(char[][] piece) {
        return rotate90(rotate180(piece));
    }
    private static char[][] flipHorizontal(char[][] piece) {
        int rows = piece.length, cols = piece[0].length;
        char[][] flipped = new char[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                flipped[i][cols - 1 - j] = piece[i][j];
        return flipped;
    }
}