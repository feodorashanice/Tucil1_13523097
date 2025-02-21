import java.util.List;

public class Solver {
    private Board board;
    private List<Piece> pieces;

    public Solver(int N, int M, List<Piece> pieces){
        board = new Board(N, M);
        this.pieces = pieces;
    }

    public boolean solve(int pieceIndex){
        if (pieceIndex == pieces.size()){
            return true;
        }

        Piece piece = pieces.get(pieceIndex);

        for (int i = 0; i < board.getN(); i++){
            for (int j = 0; j < board.getM(); j++){
                for (int rotate = 0; rotate < 4; rotate++){
                    for (int flip = 0; flip < 2; flip++){
                        char[][] transformed = piece.getPiece();

                        if (flip == 1){
                            transformed = piece.flipHorizontal();
                        }
                        if (rotate > 0){
                            for (int r = 0; r < rotate; r++){
                                transformed = piece.rotate90();
                            }
                        }

                        if (board.placeValid(transformed, i, j)){
                            board.placePiece(transformed, i, j);
                            if (solve(pieceIndex + 1)){
                                return true;
                            }
                            board.removePiece(transformed, i, j); // backtrack - work in progress
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean solvePuzzle(){
        return solve(0);
    }
}