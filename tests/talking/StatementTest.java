package talking;

import Talking.Intonation;
import Talking.Statement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatementTest {
    private Intonation testIntonation = new Intonation();

    @Test
    public void constructorTest(){
        Statement statement = new Statement (testIntonation, 1.0);
        assertEquals(1.0, statement.getTimbre());
    }
}
