import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    public static ParsedInput parseFile(String filename) throws IOException {
        File file = new File(filename);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String firstLine = br.readLine();
            if (firstLine == null || firstLine.trim().isEmpty()) {
                throw new IOException("File is empty or invalid");
            }
            
            // Read N, M, and P
            String[] parameters = firstLine.split(" ");
            int N = Integer.parseInt(parameters[0]);
            int M = Integer.parseInt(parameters[1]);
            int P = Integer.parseInt(parameters[2]);

            br.readLine(); // always DEFAULT

            List<String> allLines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    allLines.add(line);
                }
            }

            if (allLines.size() % P != 0) {
                throw new IOException("Invalid format: Number of lines (" + allLines.size() + ") must be divisible by number of pieces (" + P + "). Expected " + P + " pieces, each with the same number of lines.");
            }

            List<Piece> pieces = new ArrayList<>();
            char id = 'A';
            int linesPerPiece = allLines.size() / P;

            for (int i = 0; i < P; i++) {
                int start = i * linesPerPiece;
                int end = start + linesPerPiece;
                List<String> currentPieceLines = allLines.subList(start, end);
                char[][] piece = convertToCharMatrix(currentPieceLines);
                // printMatrix(piece);
                pieces.add(new Piece(piece, id++));
            }

            if (pieces.size() != P) {
                throw new IOException("Expected " + P + " pieces, but found " + pieces.size() + ". Check file format: N M P on first line, DEFAULT on second, followed by P pieces (each piece on separate lines, no empty lines between pieces).");
            }
            return new ParsedInput(N, M, P, pieces);
        }
    }

    // Convert List<String> to char[][] with padding
    private static char[][] convertToCharMatrix(List<String> pieceLines) {
        int rows = pieceLines.size();
        int cols = 0;
        for (String line : pieceLines) {
            cols = Math.max(cols, line.length());
        }
        char[][] piece = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            String line = pieceLines.get(i);
            for (int j = 0; j < cols; j++) {
                if (j < line.length()) {
                    piece[i][j] = line.charAt(j);
                } else {
                    piece[i][j] = '.';
                }
            }
        }
        return piece;
    }
}

class ParsedInput {
    public int N, M, P;
    public List<Piece> pieces;

    public ParsedInput(int N, int M, int P, List<Piece> pieces) {
        this.N = N;
        this.M = M;
        this.P = P;
        this.pieces = pieces;
    }
}