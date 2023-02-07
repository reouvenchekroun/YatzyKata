Refactoring strategy :

 * Improving the code coverage to avoid any regression during the refactoring
 * Use of the same nomenclature rules for the different methods of the application
 * Use of the same signature for the methods instead 
 * Separation of dice logic from score logic (Roll and ScoreCalculator)
 * Stream and lambda use to deal more efficiently with collections treatments
 * Code factorization when it was possible (for example ones, twos, threes methods which are very similar)
 * Check when input dice values are not between 1 and 6