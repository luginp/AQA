package kaspersky.tests.utils;

public class StepCounter {
    private int step = 0;

    public int nextStep(){
        return ++step;
    }
}
