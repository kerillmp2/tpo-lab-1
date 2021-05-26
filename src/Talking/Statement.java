package Talking;

public class Statement {
    private Intonation intonation = new Intonation();
    private double timbre = 1;

    public Statement(Intonation intonation, double timbre) {
        this.intonation = intonation;
        this.timbre = timbre;
    }

    public Intonation getIntonation() {
        return intonation;
    }

    public double getTimbre() {
        return timbre;
    }
}
