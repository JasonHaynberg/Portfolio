# Sudoku C++ Game

## How to Use the Program

To get started with the game, compile it using the provided makefile and run the `sudoku` executable.

1. **Starting the Game:** By default, the game will launch in an interactive 9x9 mode.
2. **Entering Numbers:** To fill a cell, input the column number, row number, and the number you wish to place in that cell (using a 1-based index). These values should be separated by spaces, commas, or any other delimiter except for integers.
3. **Solving the Puzzle:** You can type “Solve” at any point to use the backtracking algorithm to solve the puzzle based on your current progress.
4. **Impossible Configuration:** If you end up in an unsolvable state, you will be asked to clear the board before continuing.
5. **Replaying:** Once the puzzle is solved, you’ll be given the option to start a new game.

6. **Using Command-Line Options:** You can also run the game with different configurations and perform additional tests by using the following command-line flags:

### Available Command-Line Flags

- `-s` or `--seed`: Set a custom random seed for the game, useful to generate repeatable results.
- `-u` or `--Unittest`: Run a speed test (unit test) with both backtracking solvers. Specify how many times you want the test to run.
- `-g` or `--gamesize`: Choose a different grid size for the puzzle (e.g., 9 for 9x9, 16 for 16x16).
- `-n` or `--nobs`: Define how many cells should be pre-filled (immutable cells) in the puzzle.
- `-v` or `--verbose`: Enable verbose output after each unit test run. This flag has no effect if you're playing interactively.

## Building the Project

To build the project, you’ll need the `armadillo` library. You can download it from [here](http://arma.sourceforge.net/).

## References

1. **Backtracking Algorithm Pseudo-code**: http://moritz.faui2k3.org/en/yasss
2. **Douglas-Rachford Splitting for Sudoku**: "Recent Results on Douglas–Rachford Methods for Combinatorial Optimization Problems"
3. **Function Timing in C++**: http://stackoverflow.com/questions/29719999/testing-function-for-speed-performance-in-cpp


