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

    public XXOO() {
        this(3, 3);
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

    public Move getMove(Move x) {
        return turn;
    }

    public void makeTurn(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= hight) throw new IllegalArgumentException();
        for (x = 0; x < width; x++) {
            for (y = 0; y < hight; y++) {
                Cell cell = new Cell(x, y);
                moves.put(cell, turn);
                turn = turn.opposite();
            }
        }
    }


    public void clearCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= hight) throw new IllegalArgumentException();
        for (x = 0; x < width; x++) {
            for (y = 0; y < hight; y++) {
                Cell cell = new Cell(x, y);
                if (moves.containsKey(cell)) {
                    moves.remove(cell, turn);
                }
            }
        }
    }


    public int theLongestLine(Move move) {
        int result = 0;
        int x = 0;
        while (x < width) {  //ищу самую длинную последовательность в строчках
            int count = 0;
            for (int y = 0; y < hight - 1; y++) {
                Cell cell = new Cell(x, y);
                Cell next = new Cell(x, y + 1);
                if (move.equals(get(cell)) && move.equals(get(next)))
                    count++;
                if (move.equals(get(cell)) && !move.equals(get(cell))) {
                    if (count + 1 > result) result = count + 1;
                    count = 0;
                }
                if (!move.equals(get(cell)) && move.equals(get(cell))) {
                    count = 1;
                }
            }
            Cell last = new Cell(x, hight - 1);
            if (move.equals(get(last)))
                count++;
            if (count > result) result = count;
            x++;
        }
        int y = 0;
        while (y < hight) { // ищу самую длинную последовательность в столбцах
            int count = 0;
            for (x = 0; x < width - 1; x++) {
                Cell cell = new Cell(x, y);
                Cell next = new Cell(x + 1, y);
                if (move.equals(get(cell)) && move.equals(get(next)))
                    count++;
                if (move.equals(get(cell)) && !move.equals(get(cell))) {
                    if (count + 1 > result) result = count + 1;
                    count = 0;
                }
                if (!move.equals(get(cell)) && move.equals(get(cell))) {
                    count = 0;
                }
            }
            Cell last = new Cell(width - 1, y);
            if (move.equals(get(last)))
                count++;
            if (count > result) result = count;
            y++;
        }

        for (int l = 0; l < width; l++) { //поиска по побочным диагоналям выше основной побочной диагонали
            for (int i = 0; i <= l; i++) {
                int[] arrayResult1;
                arrayResult1 = new int[width - 1];
                Cell cell = new Cell(x, y);
                if (move.equals(get(i, l - 1)) && move.equals(get(i + 1, l))) arrayResult1[l] += 1;
                if (move.equals(get(i, l - 1)) && !move.equals(get(i + 1, l))) {
                    if (result < arrayResult1[l] + 1) result = arrayResult1[l] + 1;
                    arrayResult1[l] = 0;
                }
                if (!move.equals(get(i, l - 1)) && move.equals(get(i + 1, l))) arrayResult1[l] = 0;
            }
        }
        for (int l = width; l < 2*width - 1; l++) { //поиска по побочным диагоналям ниже основной побочной диагонали
            for (int j = width - 1; j > l - width; j--) {
                int[] arrayResult2;
                arrayResult2 = new int[width - 1];
                Cell cell = new Cell(x, y);
                if (move.equals(get(l - j, j)) && move.equals(get(l - j + 1, j - 1))) arrayResult2[l] += 1;
                if (move.equals(get(l - j, j)) && !move.equals(get(l - j + 1, j - 1))) {
                    if (result < arrayResult2[l] + 1) result = arrayResult2[l] + 1;
                    arrayResult2[l] = 0;
                }
                if (!move.equals(get(l - j, j)) && move.equals(get(l - j + 1, j - 1))) arrayResult2[l] = 0;
            }
        }

        return result;
    }
}
