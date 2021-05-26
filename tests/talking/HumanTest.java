package talking;

import Talking.Emotion;
import Talking.Human;
import Talking.Intonation;
import Talking.Statement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {
    private boolean mapEquals(Map<Emotion, Double> a, Map<Emotion, Double> b) {
        if (a.keySet().size() != b.keySet().size()) {
            return false;
        }
        for (Emotion emotion : a.keySet()) {
            if(a.get(emotion).doubleValue() != b.get(emotion).doubleValue()){
                return false;
            }
        }
        return true;
    }


    @Test
    public void basicTalking(){
        Human human1 = new Human("Kirill");
        Human human2 = new Human("Valera");
        Map<Emotion, Double> testMap1 = new HashMap<>();
        Map<Emotion, Double> testMap2 = new HashMap<>();
        Map<Emotion, Double> testMap3 = new HashMap<>();
        Map<Emotion, Double> testMap4 = new HashMap<>();
        for(Emotion emotion : Emotion.values()) {
            testMap1.put(emotion, 1.0);
            testMap2.put(emotion, 1.5);
            testMap3.put(emotion, 2.0);
            testMap4.put(emotion, 1.75);
        }

        human1.say(new Statement(new Intonation(), 1.0), human2);
        assertAll(
                () -> assertTrue(mapEquals(testMap1, human1.getEmotions())),
                () -> assertTrue(mapEquals(testMap2, human2.getEmotions()))
        );

        human2.say(new Statement(new Intonation(), 2.0), human1);
        assertAll(
                () -> assertTrue(mapEquals(testMap3, human1.getEmotions())),
                () -> assertTrue(mapEquals(testMap4, human2.getEmotions()))
        );
    }

    @Test
    public void emotionalTalking(){
        Human human1 = new Human("Kirill");
        Human human2 = new Human("Valera");

        Map<Emotion, Double> testMap1 = new HashMap<>();
        Map<Emotion, Double> testMap2 = new HashMap<>();
        for(Emotion emotion : Emotion.values()) {
            testMap1.put(emotion, 1.0);
            testMap2.put(emotion, 1.0);
        }
        testMap2.put(Emotion.ANGER, 1.5);

        List<Emotion> emotions = new ArrayList<>();
        emotions.add(Emotion.ANGER);
        human1.say(new Statement(new Intonation(emotions), 1.0), human2);

        assertAll(
                () -> assertTrue(mapEquals(testMap1, human1.getEmotions())),
                () -> assertTrue(mapEquals(testMap2, human2.getEmotions()))
        );

        emotions.clear();
        emotions.add(Emotion.FEAR);
        human2.say(new Statement(new Intonation(emotions), 0.5), human1);

        testMap1.put(Emotion.FEAR, 1.25);
        testMap2.put(Emotion.FEAR, 0.75);

        assertAll(
                () -> assertTrue(mapEquals(testMap1, human1.getEmotions())),
                () -> assertTrue(mapEquals(testMap2, human2.getEmotions()))
        );
    }

    @Test
    public void emotionlessTalking(){
        Human human1 = new Human("Kirill");
        Human human2 = new Human("Valera");

        Map<Emotion, Double> testMap1 = new HashMap<>();
        Map<Emotion, Double> testMap2 = new HashMap<>();
        for(Emotion emotion : Emotion.values()) {
            testMap1.put(emotion, 1.0);
            testMap2.put(emotion, 1.0);
        }

        List<Emotion> emotions = new ArrayList<>();
        human1.say(new Statement(new Intonation(emotions), 1.0), human2);
        assertAll(
                () -> assertTrue(mapEquals(testMap1, human1.getEmotions())),
                () -> assertTrue(mapEquals(testMap2, human2.getEmotions()))
        );

        human2.say(new Statement(new Intonation(emotions), 1.0), human2);
        assertAll(
                () -> assertTrue(mapEquals(testMap1, human1.getEmotions())),
                () -> assertTrue(mapEquals(testMap2, human2.getEmotions()))
        );
    }

    @Test
    public void talkingWithNullValues(){
        Human human1 = new Human("Kirill");
        Human human2 = new Human("Kirill");

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> human1.say(new Statement(new Intonation(), 1.0), null)),
                () -> assertThrows(IllegalArgumentException.class, () -> human1.say(null, human2)),
                () -> assertThrows(IllegalArgumentException.class, () -> human1.say(null, null))
        );
    }

    @Test
    public void hearNothing(){
        Human human1 = new Human("Kirill");

        assertThrows(IllegalArgumentException.class, () -> human1.hear(null));
    }

    @Test
    public void talkingWithWrongDelay() {
        Human human1 = new Human("Kirill");
        Human human2 = new Human("Valera");

        Statement statement = new Statement(new Intonation(), 1.0);
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> human1.say(statement, human2, -1)),
                () -> assertThrows(IllegalArgumentException.class, () -> human1.say(statement, human2, 999999)),
                () -> assertThrows(IllegalArgumentException.class, () -> human1.say(statement, human2, 123123))
        );
    }
}