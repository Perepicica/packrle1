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

    private Move get(Cell cell) {
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
                if (moves.containsKey(cell)) {
                    moves.remove(cell, turn);
                }

    }


    public void theLongestLine(Move move) {
        int x = 0;
        int y = 0;
        int count;
        int num = 0;
        while (x < width) {  //ищу самую длинную последовательность в строчках
            count = 0;
            for (y = 0; y < hight ; y++) {
                Cell cell = new Cell(x, y);
                if (move.equals(get(x, y))) count++;
                if (count == width) num = x + 1;
            }
            if (count == width) System.out.println("победа в "+ num +" строке");
            x++;
        }

        y = 0;
        while (y < hight) { // ищу самую длинную последовательность в столбцах
            count = 0;
            for (x = 0; x < width ; x++) {
                Cell cell = new Cell(x, y);
                if (move.equals(get(x, y))) count++;
                if (count == width) num = y + 1;
            }
            if (count == width)  System.out.println("победа в " + num + " столбце");
            y++;
        }

        count = 0; //ищу победу в главной диагонали
        for (x = 0; x < width; x++) {
            y = x;
            Cell cell = new Cell(x, y);
            if (move.equals(get(x, y))) count++;
        }
        if (count == width)
            System.out.println("победа по главной диагонали");

        count = 0; //ищу победу в побочной диагонали
        for (x = 0; x < width; x++) {
            y = width - 1 - x;
            Cell cell = new Cell(x, y);
            if (move.equals(get(x, y))) count++;
        }
        if (count == width)
            System.out.println("победа по побочной диагонали");

    }
}
