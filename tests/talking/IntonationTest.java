package talking;

import Talking.Emotion;
import Talking.Intonation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntonationTest {

    @Test
    public void defaultConstructorTest(){
        Intonation intonation = new Intonation();
        assertArrayEquals(Emotion.values(), intonation.getEmotions().toArray());
    }

    @Test
    public void argsConstructorTest(){
        List<Emotion> emotions = new ArrayList<>();
        emotions.add(Emotion.ANGER);
        emotions.add(Emotion.CONTEMPT);
        Intonation intonation = new Intonation(emotions);
        assertAll(
                () -> assertEquals(Emotion.ANGER, intonation.getEmotions().get(0)),
                () -> assertEquals(Emotion.CONTEMPT, intonation.getEmotions().get(1))
        );
    }
}
