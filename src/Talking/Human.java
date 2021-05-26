package Talking;

import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;

public class Human {
    private String name;
    private Map<Emotion, Double> emotions = new TreeMap<>();

    public Human(String name) {
        this.name = name;
        for(Emotion emotion : Emotion.values()) {
            emotions.put(emotion, 1.0);
        }
    }

    public void say(Statement statement, Human target, int delay) throws IllegalArgumentException {
        if(statement == null || target == null || delay < 0 || delay > 100000) {
            throw new IllegalArgumentException();
        }
    }

    public void say(Statement statement, Human target) throws IllegalArgumentException{
        if(statement == null || target == null) {
            throw new IllegalArgumentException();
        }
        target.hear(statement);
        for(Emotion emotion : statement.getIntonation().getEmotions()) {
            this.emotions.put(emotion, this.emotions.get(emotion) / 2);
            this.addEmotion(emotion, statement.getTimbre() / 2);
        }
    }

    public void hear(Statement statement) throws IllegalArgumentException{
        if(statement == null) {
            throw new IllegalArgumentException();
        }
        for(Emotion emotion : statement.getIntonation().getEmotions()) {
            this.addEmotion(emotion, statement.getTimbre() / 2);
        }
    }

    public void addEmotion(Emotion emotion, double power) {
        this.emotions.put(emotion, this.emotions.get(emotion) + power);
    }


    public Map<Emotion, Double> getEmotions() {
        return emotions;
    }
}
