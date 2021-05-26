package Talking;

import java.util.Arrays;
import java.util.List;

public class Intonation {
    private List<Emotion> emotions;

    public Intonation(List<Emotion> emotions) {
        this.emotions = emotions;
    }

    public Intonation(){
        this.emotions = Arrays.asList(Emotion.values().clone());
    }

    public List<Emotion> getEmotions() {
        return emotions;
    }
}
