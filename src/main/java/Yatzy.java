import java.util.Comparator;
import java.util.List;

public class Yatzy {

    public static final int YATZY_POINTS = 50;
    public static final int SMALL_STRAIGHT_POINTS = 15;
    private static final int LARGE_STRAIGHT_POINTS = 20;

    public static final int NO_POINTS = 0;
    private static final List<DiceResult> SMALL_STRAIGHT_VALUES = List.of(
            DiceResult.ONE, DiceResult.TWO, DiceResult.THREE, DiceResult.FOUR, DiceResult.FIVE);
    private static final List<DiceResult> LARGE_STRAIGHT_VALUES = List.of(
           DiceResult.TWO, DiceResult.THREE, DiceResult.FOUR, DiceResult.FIVE, DiceResult.SIX);

    public static int chance(DiceWrapper diceWrapper)
    {
        return diceWrapper.sum();
    }

    public static int yatzy(DiceWrapper diceWrapper)
    {
        return diceWrapper.hasAllDicesWithSameValue() ? YATZY_POINTS : NO_POINTS;
    }

    public static int ones(DiceWrapper diceWrapper) {
        return countPointsForDicesWithValue(diceWrapper, DiceResult.ONE);
    }

    public static int twos(DiceWrapper diceWrapper) {
        return countPointsForDicesWithValue(diceWrapper, DiceResult.TWO);
    }

    public static int threes(DiceWrapper diceWrapper) {
        return countPointsForDicesWithValue(diceWrapper, DiceResult.THREE);
    }

    public static int fours(DiceWrapper diceWrapper) {
        return countPointsForDicesWithValue(diceWrapper, DiceResult.FOUR);
    }

    public static int fives(DiceWrapper diceWrapper) {
        return countPointsForDicesWithValue(diceWrapper, DiceResult.FIVE);
    }

    public static int sixes(DiceWrapper diceWrapper) {
        return countPointsForDicesWithValue(diceWrapper, DiceResult.SIX);
    }

    public static int onePair(DiceWrapper diceWrapper)
    {
        var highestPair = diceWrapper.getValuesWithFrequency(2).stream()
                .max(Comparator.comparing(Integer::valueOf));

        return highestPair.map(value -> value * 2)
                .orElse(NO_POINTS);
    }

    public static int twoPairs(DiceWrapper diceWrapper)
    {
        return calculateXOfAKindScore(diceWrapper, 2);
    }

    public static int threeOfAKind(DiceWrapper diceWrapper)
    {
        return calculateXOfAKindScore(diceWrapper, 3);
    }

    public static int fourOfAKind(DiceWrapper diceWrapper)
    {
        return calculateXOfAKindScore(diceWrapper, 4);
    }

    public static int smallStraight(DiceWrapper diceWrapper)
    {
        return diceWrapper.containsResults(SMALL_STRAIGHT_VALUES) ? SMALL_STRAIGHT_POINTS : NO_POINTS;
    }

    public static int largeStraight(DiceWrapper diceWrapper)
    {
        return diceWrapper.containsResults(LARGE_STRAIGHT_VALUES) ? LARGE_STRAIGHT_POINTS : NO_POINTS;
    }

    public static int fullHouse(DiceWrapper diceWrapper) {
        return diceWrapper.isFullHouse() ? diceWrapper.sum() : NO_POINTS;
    }

    private static int countPointsForDicesWithValue(DiceWrapper diceWrapper, DiceResult diceResult) {
        return diceWrapper.getOccurencesOfValue(diceResult) * diceResult.getValue();
    }

    private static int calculateXOfAKindScore(DiceWrapper diceWrapper, Integer frequency) {
        return diceWrapper.getValuesWithFrequency(frequency).stream()
                .map(value -> value * frequency)
                .mapToInt(Integer::valueOf)
                .sum();
    }
}