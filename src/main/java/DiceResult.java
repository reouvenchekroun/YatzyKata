public enum DiceResult {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6);

    private final Integer value;
    DiceResult(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
