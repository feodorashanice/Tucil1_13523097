import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter test case file (.txt): ");
        String filename = scanner.nextLine();

        try {
            ParsedInput input = FileParser.parseFile(filename);
            Solver solver = new Solver(input.N, input.M, input.pieces);

            if (solver.solveDetails()) {
                solver.getBoard().printBoard();
                System.out.println("Apakah Anda ingin menyimpan hasil ke file? (y/n)");
                if (scanner.nextLine().equalsIgnoreCase("Y")){
                    saveSolution(solver.getBoard());
                }
            } else {
                System.out.println("Tidak ada solusi yang ditemukan");
            }
        } catch (IOException e) {
            System.out.println("File tidak ditemukan atau format tidak valid");
        } finally {
            scanner.close();
        }
    }
    private static void saveSolution(Board board){
        try {
            java.io.PrintWriter writer = new java.io.PrintWriter("solution.txt");
            for (char[] row : board.getBoard()) {
                writer.println(new String(row));
            }
            writer.close();
            System.out.println("Solusi disimpan ke 'solution.txt'");
        } catch (IOException e) {
            System.err.println("Error saving solution: " + e.getMessage());
        }
    }
}