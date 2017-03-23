package MyClass;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
    private String player1;
    private String player2;

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
            throw new IllegalArgumentException("Эта ячейка уже занята, попробуйте другую!");
        if (x < 0 || x >= width) throw new IllegalArgumentException("x должен быть не меньше 0 и не больше " + width);
        if (y < 0 || y >= height) throw new IllegalArgumentException("у должен быть не меньше 0 и не больше " + height);
        moves.put(cell, turn);
        theLongestLine(turn);
        turn = turn.opposite();
    }

    public void clearCell(int x, int y) {
        if (x < 0 || x >= width) throw new IllegalArgumentException("x должен быть не меньше 0 и не больше " + width);
        if (y < 0 || y >= height) throw new IllegalArgumentException("у должен быть не меньше 0 и не больше " + height);
        Cell cell = new Cell(x, y);
        moves.put(cell, null);
    }

    public String theLongestLine(Move move) {
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
        int count = 0;
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
        if (winner > 0 && move.equals(Move.X)) return player1;
        if (winner > 0 && move.equals(Move.O)) return player2;
        return "";
    }

    @Override
    public String toString() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                return get(x, y) + " ";
            }
            return "/n";
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof XXOO) {
            XXOO xxoo = (XXOO) obj;
            if (width == xxoo.width && height == xxoo.height) {
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        return get(x, y) == xxoo.get(x, y);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 17 * result + height;
        return result;
    }

    // ввод имён игроков, не знаю, если надо
    private void name(int player) {
        Scanner in = new Scanner(System.in);
        String Name = in.nextLine();
        if (Name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        } else {
            if (player == 1) player1 = Name;
            if (player == 2) player2 = Name;
        }
    }
//получаю номер ячейки, которую потом в основной программе преобразую в координаты клетки
    private void Turn() {
        int flag;
        Scanner in = new Scanner(System.in);
        String Flag = in.nextLine();
        flag = Integer.parseInt(Flag);
    }
}
