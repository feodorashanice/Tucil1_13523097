import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    public static ParsedInput parseFile(String filename) throws IOException {
        File file = new File(filename);
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String firstLine = br.readLine();
            if (firstLine == null || firstLine.trim().isEmpty()){
                throw new IOException("File is empty or invalid");
            }
            
            // Read N, M, and P
            String[] parameters = firstLine.split(" ");
            int N = Integer.parseInt(parameters[0]);
            int M = Integer.parseInt(parameters[1]);
            int P = Integer.parseInt(parameters[2]);

            br.readLine(); // always DEFAULT

            // Read puzzle pieces
            List<Piece> pieces = new ArrayList<>();
            List<String> currentPieceLines = new ArrayList<>();
            char id = 'A';

            String line;
            while ((line = br.readLine()) != null){
                if (!line.isEmpty()){
                    currentPieceLines.add(line);
                } else {
                    if (!currentPieceLines.isEmpty()){
                        pieces.add(new Piece(convertToCharMatrix(currentPieceLines), id++));
                        currentPieceLines.clear();
                    }
                }
            }
            if (!currentPieceLines.isEmpty()){
                pieces.add(new Piece(convertToCharMatrix(currentPieceLines), id++));
            }
            return new ParsedInput(N, M, P, pieces);
        }
    }

    // Convert List<String> to char[][]
    private static char[][] convertToCharMatrix(List<String> pieceLines){
        int rows = pieceLines.size();
        int cols = pieceLines.get(0).length();
        char[][] piece = new char[rows][cols];

        for (int i = 0; i < rows; i++){
            piece[i] = pieceLines.get(i).toCharArray();
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