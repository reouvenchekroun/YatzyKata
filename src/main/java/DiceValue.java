import java.util.Arrays;

public enum DiceValue {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6);

    private final Integer value;
    DiceValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static DiceValue from(int diceValue) {
        return Arrays.stream(DiceValue.values())
                .filter(result -> result.value.equals(diceValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The result of dice should be between 1 and 6, but is : " + diceValue));
    }

}
