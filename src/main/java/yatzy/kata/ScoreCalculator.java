package yatzy.kata;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static yatzy.kata.DiceValue.*;

public class ScoreCalculator {

    public static final int YATZY_POINTS = 50;
    public static final int SMALL_STRAIGHT_POINTS = 15;
    private static final int LARGE_STRAIGHT_POINTS = 20;

    public static final int NO_POINTS = 0;
    private static final List<DiceValue> SMALL_STRAIGHT_VALUES = List.of(
            ONE, TWO, THREE, FOUR, FIVE);
    private static final List<DiceValue> LARGE_STRAIGHT_VALUES = List.of(
           TWO, THREE, FOUR, FIVE, SIX);

    public int chance(Roll roll) {
        return roll.sum();
    }

    public int yatzy(Roll roll) {
        return roll.hasAllDicesWithSameValue() ? YATZY_POINTS : NO_POINTS;
    }

    public int ones(Roll roll) {
        return calculateScoreForDiceResult(roll, ONE);
    }

    public int twos(Roll roll) {
        return calculateScoreForDiceResult(roll, TWO);
    }

    public int threes(Roll roll) {
        return calculateScoreForDiceResult(roll, THREE);
    }

    public int fours(Roll roll) {
        return calculateScoreForDiceResult(roll, FOUR);
    }

    public int fives(Roll roll) {
        return calculateScoreForDiceResult(roll, FIVE);
    }

    public int sixes(Roll roll) {
        return calculateScoreForDiceResult(roll, SIX);
    }

    public int onePair(Roll roll) {
        var highestPair = roll.getValuesWithFrequency(2).stream()
                .map(DiceValue::getValue)
                .max(Comparator.comparing(Integer::valueOf));

        return highestPair.map(value -> value * 2)
                .orElse(NO_POINTS);
    }

    public int twoPairs(Roll roll) {
        return calculateScoreForXOfAKind(roll, 2);
    }

    public int threeOfAKind(Roll roll) {
        return calculateScoreForXOfAKind(roll, 3);
    }

    public int fourOfAKind(Roll roll) {
        return calculateScoreForXOfAKind(roll, 4);
    }

    public int smallStraight(Roll roll) {
        return roll.containsResults(SMALL_STRAIGHT_VALUES) ? SMALL_STRAIGHT_POINTS : NO_POINTS;
    }

    public int largeStraight(Roll roll) {
        return roll.containsResults(LARGE_STRAIGHT_VALUES) ? LARGE_STRAIGHT_POINTS : NO_POINTS;
    }

    public int fullHouse(Roll roll) {
        return isFullHouse(roll) ? roll.sum() : NO_POINTS;
    }

    private int calculateScoreForDiceResult(Roll roll, DiceValue diceValue) {
        return roll.getOccurrencesOfValue(diceValue) * diceValue.getValue();
    }

    private int calculateScoreForXOfAKind(Roll roll, Integer frequency) {
        return roll.getValuesWithFrequency(frequency).stream()
                .map(dice -> dice.getValue() * frequency)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    public boolean isFullHouse(Roll roll) {
        var pairs = roll.getValuesWithFrequency(2);
        var threeOfAKind = roll.getValuesWithFrequency(3);
        return containsPairAndThreeOfAKind(pairs, threeOfAKind);
    }

    private boolean containsPairAndThreeOfAKind(Set<DiceValue> pairs, Set<DiceValue> threeOfAKind) {
        return !threeOfAKind.isEmpty() && !threeOfAKind.containsAll(pairs);
    }
}