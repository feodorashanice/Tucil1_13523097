import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser("file.txt");
        int N = fileParser.getN();
        int M = fileParser.getM();
        List<char[][]> pieces = fileParser.getPieces();

        Board board = new Board(N, M);
    }
}
