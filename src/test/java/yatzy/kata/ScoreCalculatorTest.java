package yatzy.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScoreCalculatorTest {

    private ScoreCalculator scoreCalculator;

    @BeforeEach
    public void init() {
        scoreCalculator = new ScoreCalculator();
    }

    @Test
    void chance() {
        assertEquals(5, scoreCalculator.chance(Roll.of(1,1,1,1,1)));
        assertEquals(15, scoreCalculator.chance(Roll.of(2,3,4,5,1)));
        assertEquals(16, scoreCalculator.chance(Roll.of(3,3,4,5,1)));
    }

    @Test
    void yatzy() {
        assertEquals(50, scoreCalculator.yatzy(Roll.of(4,4,4,4,4)));
        assertEquals(50, scoreCalculator.yatzy(Roll.of(6,6,6,6,6)));
        assertEquals(0, scoreCalculator.yatzy(Roll.of(6,6,6,6,3)));
    }

    @Test
    void ones() {
        assertEquals(0, scoreCalculator.ones(Roll.of(6,2,2,4,5)));
        assertEquals(1, scoreCalculator.ones(Roll.of(1, 2, 3, 4, 5)));
        assertEquals(2, scoreCalculator.ones(Roll.of(1,2,1,4,5)));
        assertEquals(4, scoreCalculator.ones(Roll.of(1,2,1,1,1)));
    }

    @Test
    void twos() {
        assertEquals(0, scoreCalculator.twos(Roll.of(1,5,3,1,6)));
        assertEquals(4, scoreCalculator.twos(Roll.of(1,2,3,2,6)));
        assertEquals(10, scoreCalculator.twos(Roll.of(2,2,2,2,2)));
    }

    @Test
    void threes() {
        assertEquals(0, scoreCalculator.threes(Roll.of(1,6,4,2,6)));
        assertEquals(6, scoreCalculator.threes(Roll.of(1,2,3,2,3)));
        assertEquals(12, scoreCalculator.threes(Roll.of(2,3,3,3,3)));
    }

    @Test
    void fours() {
        assertEquals(0, scoreCalculator.fours(Roll.of(1,2,3,2,5)));
        assertEquals(4, scoreCalculator.fours(Roll.of(4,5,5,5,5)));
        assertEquals(8, scoreCalculator.fours(Roll.of(4,4,5,5,5)));
        assertEquals(12, scoreCalculator.fours(Roll.of(4,4,4,5,5)));
    }

    @Test
    void fives() {
        assertEquals(0, scoreCalculator.fives(Roll.of(2,4,4,1,6)));
        assertEquals(10, scoreCalculator.fives(Roll.of(4,4,4,5,5)));
        assertEquals(15, scoreCalculator.fives(Roll.of(4,4,5,5,5)));
        assertEquals(20, scoreCalculator.fives(Roll.of(4,5,5,5,5)));
    }

    @Test
    void sixes() {
        assertEquals(0, scoreCalculator.sixes(Roll.of(4,4,4,5,5)));
        assertEquals(6, scoreCalculator.sixes(Roll.of(4,4,6,5,5)));
        assertEquals(18, scoreCalculator.sixes(Roll.of(6,5,6,6,5)));
    }

    @Test
    void onePair() {
        assertEquals(0, scoreCalculator.onePair(Roll.of(3,4,1,5,6)));
        assertEquals(6, scoreCalculator.onePair(Roll.of(3,4,3,5,6)));
        assertEquals(10, scoreCalculator.onePair(Roll.of(5,3,3,3,5)));
        assertEquals(12, scoreCalculator.onePair(Roll.of(5,3,6,6,5)));
        assertEquals(2, scoreCalculator.onePair(Roll.of(1,1,1,1,1)));
    }

    @Test
    void twoPairs() {
        assertEquals(0, scoreCalculator.twoPairs(Roll.of(1,2,5,4,3)));
        assertEquals(16, scoreCalculator.twoPairs(Roll.of(3,3,5,4,5)));
        assertEquals(16, scoreCalculator.twoPairs(Roll.of(3,3,5,5,5)));
    }

    @Test
    void threeOfAKind() {
        assertEquals(0, scoreCalculator.threeOfAKind(Roll.of(3,3,2,4,5)));
        assertEquals(9, scoreCalculator.threeOfAKind(Roll.of(3,3,3,4,5)));
        assertEquals(15, scoreCalculator.threeOfAKind(Roll.of(5,3,5,4,5)));
        assertEquals(9, scoreCalculator.threeOfAKind(Roll.of(3,3,3,3,5)));
    }

    @Test
    void fourOfAKind() {
        assertEquals(0, scoreCalculator.fourOfAKind(Roll.of(2,3,3,3,5)));
        assertEquals(12, scoreCalculator.fourOfAKind(Roll.of(3,3,3,3,5)));
        assertEquals(20, scoreCalculator.fourOfAKind(Roll.of(5,5,5,4,5)));
        assertEquals(12, scoreCalculator.fourOfAKind(Roll.of(3,3,3,3,3)));
    }

    @Test
    void smallStraight() {
        assertEquals(0, scoreCalculator.smallStraight(Roll.of(1,2,2,4,5)));
        assertEquals(15, scoreCalculator.smallStraight(Roll.of(1,2,3,4,5)));
        assertEquals(15, scoreCalculator.smallStraight(Roll.of(2,3,4,5,1)));
    }

    @Test
    void largeStraight() {
        assertEquals(0, scoreCalculator.largeStraight(Roll.of(1,2,2,4,5)));
        assertEquals(20, scoreCalculator.largeStraight(Roll.of(6,2,3,4,5)));
        assertEquals(20, scoreCalculator.largeStraight(Roll.of(2,3,4,5,6)));
    }

    @Test
    void fullHouse() {
        assertEquals(0, scoreCalculator.fullHouse((Roll.of(2,3,4,5,6))));
        assertEquals(18, scoreCalculator.fullHouse((Roll.of(6,2,2,2,6))));
        assertEquals(0, scoreCalculator.fullHouse((Roll.of(1,1,2,2,6))));
        assertEquals(0, scoreCalculator.fullHouse((Roll.of(1,1,1,1,6))));
    }

    @Test
    void incorrectRollCreation() {
        var exception = assertThrows(IllegalArgumentException.class, () -> Roll.of(3,4,7,3,5));
        var expectedMessage = "The result of dice should be between 1 and 6, but is : 7";
        var actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}