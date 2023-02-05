import java.util.List;

public class DiceWrapper {
    private final List<Integer> dices;

    public DiceWrapper(int d1, int d2, int d3, int d4, int d5) {
        this.dices = List.of(d1, d2, d3, d4, d5);
    }

    public Integer sum() {
        return dices.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public boolean hasAllDicesWithSameValue() {
        return dices.stream()
                .allMatch(dice -> dices.get(0).equals(dice));
    }

    public int getOccurencesOfValue(DiceResult diceResult) {
        return (int) dices.stream()
                .filter(dice -> dice.equals(diceResult.getValue()))
                .count();
    }
}
