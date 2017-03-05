package MyClass;

import java.util.HashMap;
import java.util.Map;

public class XXOO {
    public enum Move {
        X, O;


        public Move  opposite() {
            if (this == X) return O;
            else return X;
        }

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
    Board field = new Board(3,3);


    public static class Board {
        private final int width;
        private final int hight;
        private final Map<Cell, Move> moves = new HashMap<>();
        private Move turn = Move.X;

        public Board(int width, int hight) {
            this.width = width;
            this.hight = hight;
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
            while (x < width) {
                int count = 0;
                for (int y = 0; y < hight - 1; y++) {
                    Cell cell = new Cell(x,y);
                    Cell next = new Cell(x,y+1);
                    if (move.equals(get(cell)) && move.equals(get(next)))
                        count++;

                }
                Cell last = new Cell(x,hight-1);
                if(move.equals(get(last)))
                    count++;
                if (count > result) result = count;
                x++;
            }
            int y = 0;
            while (y < hight) {
                int count = 0;
                for (x = 0; x < width - 1; x++) {
                    Cell cell = new Cell(x,y);
                    Cell next = new Cell(x+1,y);
                    if (move.equals(get(cell)) && move.equals(get(next)))
                        count++;

                }
                Cell last = new Cell(width-1,y);
                if(move.equals(get(last)))
                    count++;
                if (count > result) result = count;
                y++;
            }
            return result;
        }
    }
}