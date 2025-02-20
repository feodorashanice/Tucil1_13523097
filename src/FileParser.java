import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List; 

public class FileParser{
    public static void main(String[] args){
        File file = new File("file.txt"); // nanti nama filenya diganti

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String firstline = br.readLine();
            if (firstline == null || firstline.trim().isEmpty()) {
                System.out.println("File is empty or invalid");
                return;
            }
            
            // Read N, M, and P
            String[] parameters = firstline.split(" ");
            int N = Integer.parseInt(parameters[0]);
            int M = Integer.parseInt(parameters[1]);
            int P = Integer.parseInt(parameters[2]);

            br.readLine(); // selalu default

            // Read puzzle pieces
            List<char[][]> pieces = new ArrayList<>();
            List<String> currentPieceLines = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    currentPieceLines.add(line);
                } else {
                    if (!currentPieceLines.isEmpty()) {
                        pieces.add(convertToCharMatrix(currentPieceLines));
                        currentPieceLines.clear();
                    }
                }
            }

            if (!currentPieceLines.isEmpty()) {
                pieces.add(convertToCharMatrix(currentPieceLines));
            }

            br.close();

            // for (int i = 0; i < pieces.size(); i++) {
            //     System.out.println("Piece " + (i + 1) + ":");
            //     printMatrix(pieces.get(i));
            //     System.out.println();
            // }
            // ini kalo mau tes diprint
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Convert List<String> to char[][]
    private static char[][] convertToCharMatrix(List<String> pieceLines) {
        int rows = pieceLines.size();
        int cols = pieceLines.get(0).length();
        char[][] piece = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            piece[i] = pieceLines.get(i).toCharArray();
        }

        return piece;
    }
    // private static void printMatrix(char[][] matrix) {
    //     for (char[] row : matrix) {
    //         System.out.println(new String(row));
    //     }
    // }
}