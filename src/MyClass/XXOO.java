package MyClass;

import java.util.HashMap;
import java.util.Map;

public class XXOO {
    public enum Move {
        X, O;


        public Move opposite() {
            if (this == X) return O;
            else return X;
        }
    }

    private final int width;
    private final int hight;
    private final Map<Cell, Move> moves = new HashMap<>();
    private Move turn = Move.X;

    public XXOO(int width, int hight) {
        this.width = width;
        this.hight = hight;
    }

    private static class Cell {
        private final int x;
        private final int y;

        private Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj instanceof Cell) {
                Cell cell = (Cell) obj;
                return x == cell.x && y == cell.y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int result1 = x;
            result1 = 31 * result1 + y;
            return result1;
        }
    }

    public Move get(int x, int y) {
        return get(new Cell(x, y));
    }

    public Move get(Cell cell) {
        return moves.get(cell);
    }

    public Move getMove() {
        return turn;
    }

    public void makeTurn(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= hight) throw new IllegalArgumentException();
        Cell cell = new Cell(x, y);
        moves.put(cell, turn);
        turn = turn.opposite();
    }


    public void clearCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= hight) throw new IllegalArgumentException();
        Cell cell = new Cell(x, y);
        moves.put(cell, null);
    }

    private void VictoryInRow(Move move) {
        int x = 0;
        int num = 0;
        while (x < width) {
            int count = 0;
            for (int y = 0; y < hight; y++) {
                Cell cell = new Cell(x, y);
                if (move.equals(get(x, y))) count++;
                if (count == width) num = x + 1;
            }
            if (count == width) System.out.println("победа в " + num + " строке");
            x++;
        }
    }

    private void VictoryInColumn(Move move) {
        int y = 0;
        int num = 0;
        while (y < hight) {
            int count = 0;
            for (int x = 0; x < width; x++) {
                Cell cell = new Cell(x, y);
                if (move.equals(get(x, y))) count++;
                if (count == width) num = y + 1;
            }
            if (count == width) System.out.println("победа в " + num + " столбце");
            y++;
        }
    }

    private void VictoryInMainDiagonal(Move move) {
        int count = 0;
        for (int x = 0; x < width; x++) {
            int y = x;
            Cell cell = new Cell(x, y);
            if (move.equals(get(x, y))) count++;
        }
        if (count == width)
            System.out.println("победа по главной диагонали");
    }

    private void VictotyInSecondaryDiagonal(Move move) {
        int count = 0;
        for (int x = 0; x < width; x++) {
            int y = width - 1 - x;
            Cell cell = new Cell(x, y);
            if (move.equals(get(x, y))) count++;
        }
        if (count == width)
            System.out.println("победа по побочной диагонали");
    }

    public void theLongestLine(Move move) {
        VictoryInRow(move);
        VictoryInColumn(move);
        VictoryInMainDiagonal(move);
        VictotyInSecondaryDiagonal(move);
    }
}
