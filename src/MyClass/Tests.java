package MyClass;

import org.junit.Test;

import static MyClass.XXOO.Move.O;
import static MyClass.XXOO.Move.X;
import static org.
        junit.Assert.*;

public class Tests {
    @Test
    public void theLongestLine() {
        XXOO field = new XXOO(3, 3);
        field.makeTurn(1, 1);
        assertEquals(X, field.get(1, 1));
        field.makeTurn(1, 0);
        assertEquals(O, field.get(1, 0));
        field.makeTurn(2, 2);
        assertEquals(X, field.get(2, 2));
        field.makeTurn(0, 0);
        assertEquals(O, field.get(0, 0));
        field.makeTurn(2, 0);
        field.makeTurn(0, 2);
        field.makeTurn(2, 1);
        field.toString();

    }
}
