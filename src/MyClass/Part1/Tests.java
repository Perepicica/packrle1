package MyClass.Part1;

import MyClass.Part1.XXOO;
import org.junit.Test;

import static MyClass.Part1.XXOO.Move.O;
import static MyClass.Part1.XXOO.Move.X;
import static org.junit.Assert.*;

public class Tests {
    @Test
    public void theLongestLine() {
        XXOO field = new XXOO(3, 3,"aa","ss");
        field.makeTurn(0, 0);
        field.makeTurn(2,0);
        field.makeTurn(0,1 );
        field.makeTurn(2,1);
        field.makeTurn(1,0);
        field.makeTurn(2,2);
        field.gettWinner();

    }
}
