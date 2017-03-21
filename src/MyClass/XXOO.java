package MyClass;

import java.util.HashMap;
import java.util.Map;

public final class XXOO {

    public enum Move {
        X, O;

        public Move opposite() {
            return this == X ? O : X;
        }
    }

    private final int width;
    private final int height;
    private final Map<Cell, Move> moves = new HashMap<>();
    private Move turn = Move.X;

    public XXOO(int width, int height) {
        assert (width > 0);
        assert (height > 0);
        this.width = width;
        this.height = height;
    }

    private final static class Cell {
        private final int x;
        private final int y;

        private Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
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
        Cell cell = new Cell(x, y);
        if (Move.X.equals(get(x, y)) || Move.O.equals(get(x, y)))
            System.out.println("Эта ячейка уже занята, попробуйте другую!");
        if (x < 0 || x >= width)
            System.out.println("x должен быть не меньше 0 и не больше " + width);
        if (y < 0 || y >= height)
            System.out.println("у должен быть не меньше 0 и не больше " + height);
        moves.put(cell, turn);
        theLongestLine(turn);
        turn = turn.opposite();
    }

    public void clearCell(int x, int y) {
        if (x < 0 || x >= width)
            System.out.println("x должен быть не меньше 0 и не больше " + width);
        if (y < 0 || y >= height)
            System.out.println("у должен быть не меньше 0 и не больше " + height);
        Cell cell = new Cell(x, y);
        moves.put(cell, null);
    }

    public void theLongestLine(Move move) {
        int winner = 0;
        //ищу победителя в строчках
        int x = 0;
        while (x < width) {
            int count = 0;
            for (int y = 0; y < height; y++) {
                Cell cell = new Cell(x, y);
                if (move.equals(get(x, y))) count++;
            }
            if (count == width) winner++;
            x++;
        }
        // ищу победителя в столбцах
        int y = 0;
        while (y < height) {
            int count = 0;
            for (x = 0; x < width; x++) {
                Cell cell = new Cell(x, y);
                if (move.equals(get(x, y))) count++;
            }
            if (count == width) winner++;
            y++;
        }
        //ищу победителя в главной диагонали
        count = 0;
        for (x = 0; x < width; x++) {
            y = x;
            Cell cell = new Cell(x, y);
            if (move.equals(get(x, y))) count++;
        }
        if (count == width) winner++;
        //ищу победителя в побочнй диагонали
        count = 0;
        for (x = 0; x < width; x++) {
            y = width - 1 - x;
            Cell cell = new Cell(x, y);
            if (move.equals(get(x, y))) count++;
        }
        if (count == width) winner++;
        if (winner > 0 && move.equals(Move.X)) System.out.println("Победил 1 игрок ");
        if (winner > 0 && move.equals(Move.O)) System.out.println("Победил 2 игрок ");
    }

    @Override
    public String toString() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                    System.out.print(get(x,y)+ " ");
            }
            System.out.println(" ");
        }
        return "";
    }
}


//equals
//hashcode
//toString