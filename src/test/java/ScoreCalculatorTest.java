import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreCalculatorTest {

    private ScoreCalculator scoreCalculator;

    @BeforeEach
    public void init() {
        scoreCalculator = new ScoreCalculator();
    }

    @Test
    void chance() {
        assertEquals(5, scoreCalculator.chance(new DiceWrapper(1,1,1,1,1)));
        assertEquals(15, scoreCalculator.chance(new DiceWrapper(2,3,4,5,1)));
        assertEquals(16, scoreCalculator.chance(new DiceWrapper(3,3,4,5,1)));
    }

    @Test
    void yatzy() {
        assertEquals(50, scoreCalculator.yatzy(new DiceWrapper(4,4,4,4,4)));
        assertEquals(50, scoreCalculator.yatzy(new DiceWrapper(6,6,6,6,6)));
        assertEquals(0, scoreCalculator.yatzy(new DiceWrapper(6,6,6,6,3)));
    }

    @Test
    void ones() {
        assertEquals(0, scoreCalculator.ones(new DiceWrapper(6,2,2,4,5)));
        assertEquals(1, scoreCalculator.ones(new DiceWrapper(1, 2, 3, 4, 5)));
        assertEquals(2, scoreCalculator.ones(new DiceWrapper(1,2,1,4,5)));
        assertEquals(4, scoreCalculator.ones(new DiceWrapper(1,2,1,1,1)));
    }

    @Test
    void twos() {
        assertEquals(0, scoreCalculator.twos(new DiceWrapper(1,5,3,1,6)));
        assertEquals(4, scoreCalculator.twos(new DiceWrapper(1,2,3,2,6)));
        assertEquals(10, scoreCalculator.twos(new DiceWrapper(2,2,2,2,2)));
    }

    @Test
    void threes() {
        assertEquals(0, scoreCalculator.threes(new DiceWrapper(1,6,4,2,6)));
        assertEquals(6, scoreCalculator.threes(new DiceWrapper(1,2,3,2,3)));
        assertEquals(12, scoreCalculator.threes(new DiceWrapper(2,3,3,3,3)));
    }

    @Test
    void fours() {
        assertEquals(0, scoreCalculator.fours(new DiceWrapper(1,2,3,2,5)));
        assertEquals(4, scoreCalculator.fours(new DiceWrapper(4,5,5,5,5)));
        assertEquals(8, scoreCalculator.fours(new DiceWrapper(4,4,5,5,5)));
        assertEquals(12, scoreCalculator.fours(new DiceWrapper(4,4,4,5,5)));
    }

    @Test
    void fives() {
        assertEquals(0, scoreCalculator.fives(new DiceWrapper(2,4,4,1,6)));
        assertEquals(10, scoreCalculator.fives(new DiceWrapper(4,4,4,5,5)));
        assertEquals(15, scoreCalculator.fives(new DiceWrapper(4,4,5,5,5)));
        assertEquals(20, scoreCalculator.fives(new DiceWrapper(4,5,5,5,5)));
    }

    @Test
    void sixes() {
        assertEquals(0, scoreCalculator.sixes(new DiceWrapper(4,4,4,5,5)));
        assertEquals(6, scoreCalculator.sixes(new DiceWrapper(4,4,6,5,5)));
        assertEquals(18, scoreCalculator.sixes(new DiceWrapper(6,5,6,6,5)));
    }

    @Test
    void onePair() {
        assertEquals(0, scoreCalculator.onePair(new DiceWrapper(3,4,1,5,6)));
        assertEquals(6, scoreCalculator.onePair(new DiceWrapper(3,4,3,5,6)));
        assertEquals(10, scoreCalculator.onePair(new DiceWrapper(5,3,3,3,5)));
        assertEquals(12, scoreCalculator.onePair(new DiceWrapper(5,3,6,6,5)));
        assertEquals(2, scoreCalculator.onePair(new DiceWrapper(1,1,1,1,1)));
    }

    @Test
    void twoPairs() {
        assertEquals(0, scoreCalculator.twoPairs(new DiceWrapper(1,2,5,4,3)));
        assertEquals(16, scoreCalculator.twoPairs(new DiceWrapper(3,3,5,4,5)));
        assertEquals(16, scoreCalculator.twoPairs(new DiceWrapper(3,3,5,5,5)));
    }

    @Test
    void threeOfAKind() {
        assertEquals(0, scoreCalculator.threeOfAKind(new DiceWrapper(3,3,2,4,5)));
        assertEquals(9, scoreCalculator.threeOfAKind(new DiceWrapper(3,3,3,4,5)));
        assertEquals(15, scoreCalculator.threeOfAKind(new DiceWrapper(5,3,5,4,5)));
        assertEquals(9, scoreCalculator.threeOfAKind(new DiceWrapper(3,3,3,3,5)));
    }

    @Test
    void fourOfAKind() {
        assertEquals(0, scoreCalculator.fourOfAKind(new DiceWrapper(2,3,3,3,5)));
        assertEquals(12, scoreCalculator.fourOfAKind(new DiceWrapper(3,3,3,3,5)));
        assertEquals(20, scoreCalculator.fourOfAKind(new DiceWrapper(5,5,5,4,5)));
        assertEquals(12, scoreCalculator.fourOfAKind(new DiceWrapper(3,3,3,3,3)));
    }

    @Test
    void smallStraight() {
        assertEquals(0, scoreCalculator.smallStraight(new DiceWrapper(1,2,2,4,5)));
        assertEquals(15, scoreCalculator.smallStraight(new DiceWrapper(1,2,3,4,5)));
        assertEquals(15, scoreCalculator.smallStraight(new DiceWrapper(2,3,4,5,1)));
    }

    @Test
    void largeStraight() {
        assertEquals(0, scoreCalculator.largeStraight(new DiceWrapper(1,2,2,4,5)));
        assertEquals(20, scoreCalculator.largeStraight(new DiceWrapper(6,2,3,4,5)));
        assertEquals(20, scoreCalculator.largeStraight(new DiceWrapper(2,3,4,5,6)));
    }

    @Test
    void fullHouse() {
        assertEquals(0, scoreCalculator.fullHouse((new DiceWrapper(2,3,4,5,6))));
        assertEquals(18, scoreCalculator.fullHouse((new DiceWrapper(6,2,2,2,6))));
        assertEquals(0, scoreCalculator.fullHouse((new DiceWrapper(1,1,2,2,6))));
        assertEquals(0, scoreCalculator.fullHouse((new DiceWrapper(1,1,1,1,6))));
    }
}