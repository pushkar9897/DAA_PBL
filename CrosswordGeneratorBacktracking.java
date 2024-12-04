import java.util.ArrayList;

public class CrosswordGeneratorBacktracking {
    static final int GRID_SIZE = 10; // Size of the grid
    static char[][] grid = new char[GRID_SIZE][GRID_SIZE];
    static ArrayList<String> words = new ArrayList<>();

    public static void main(String[] args) {
        initializeGrid();

        // List of words to place in the crossword
        words.add("JAVA");
        words.add("PROGRAM");
        words.add("CROSSWORD");
        words.add("ALGORITHM");
        words.add("LOGIC");

        // Attempt to solve the crossword puzzle using backtracking
        if (solve(0)) {
            printGrid(); // Print the solution if successful
        } else {
            System.out.println("Could not generate a valid crossword with the given words.");
        }
    }

    // Initialize the grid with empty spaces
    private static void initializeGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = '.'; // Empty space
            }
        }
    }

    // Solve the crossword puzzle by placing all words using backtracking
    private static boolean solve(int wordIndex) {
        if (wordIndex == words.size()) {
            return true; // All words placed successfully
        }

        String word = words.get(wordIndex);

        // Try placing the word in every possible position and direction
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                // Try horizontal placement
                if (canPlaceWord(word, row, col, true)) {
                    placeWord(word, row, col, true);
                    if (solve(wordIndex + 1)) {
                        return true;
                    }
                    removeWord(word, row, col, true); // Backtrack
                }

                // Try vertical placement
                if (canPlaceWord(word, row, col, false)) {
                    placeWord(word, row, col, false);
                    if (solve(wordIndex + 1)) {
                        return true;
                    }
                    removeWord(word, row, col, false); // Backtrack
                }
            }
        }

        return false; // No valid placement for this word
    }

    // Check if a word can be placed at the given position and direction
    private static boolean canPlaceWord(String word, int row, int col, boolean horizontal) {
        if (horizontal) {
            if (col + word.length() > GRID_SIZE) return false;

            for (int i = 0; i < word.length(); i++) {
                if (grid[row][col + i] != '.' && grid[row][col + i] != word.charAt(i)) {
                    return false;
                }
            }
        } else {
            if (row + word.length() > GRID_SIZE) return false;

            for (int i = 0; i < word.length(); i++) {
                if (grid[row + i][col] != '.' && grid[row + i][col] != word.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Place a word in the grid at the given position and direction
    private static void placeWord(String word, int row, int col, boolean horizontal) {
        if (horizontal) {
            for (int i = 0; i < word.length(); i++) {
                grid[row][col + i] = word.charAt(i);
            }
        } else {
            for (int i = 0; i < word.length(); i++) {
                grid[row + i][col] = word.charAt(i);
            }
        }
    }

    // Remove a word from the grid (used during backtracking)
    private static void removeWord(String word, int row, int col, boolean horizontal) {
        if (horizontal) {
            for (int i = 0; i < word.length(); i++) {
                grid[row][col + i] = '.';
            }
        } else {
            for (int i = 0; i < word.length(); i++) {
                grid[row + i][col] = '.';
            }
        }
    }

    // Print the crossword grid
    private static void printGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
