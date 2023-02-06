import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Roll {
    private final List<DiceValue> dices;

    public Roll(int d1, int d2, int d3, int d4, int d5) {
        this.dices = List.of(
                DiceValue.from(d1),
                DiceValue.from(d2),
                DiceValue.from(d3),
                DiceValue.from(d4),
                DiceValue.from(d5));
    }

    public static Roll of(int d1, int d2, int d3, int d4, int d5) {
        return new Roll(d1, d2, d3, d4, d5);
    }

    public Integer sum() {
        return dices.stream()
                .map(DiceValue::getValue)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public boolean hasAllDicesWithSameValue() {
        return dices.stream()
                .allMatch(dice -> dices.get(0).equals(dice));
    }

    public Integer getOccurencesOfValue(DiceValue diceValue) {
        return (int) dices.stream()
                .filter(dice -> dice.equals(diceValue))
                .count();
    }

    public Set<DiceValue> getValuesWithFrequency(Integer frequency) {
        return dices.stream()
                .filter(dice -> Collections.frequency(dices, dice) >= frequency)
                .collect(Collectors.toSet());
    }

    public boolean containsResults(List<DiceValue> diceValues) {
        return dices.containsAll(diceValues);
    }

}
