import java.util.Comparator;

public class Yatzy {

    public static final int YATZY_POINTS = 50;
    public static final int NO_POINTS = 0;

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

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[0] == 1 &&
                tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1)
            return 15;
        return 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1
                && tallies[5] == 1)
            return 20;
        return 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;

        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i+1;
            }

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i+1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
            return 0;
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