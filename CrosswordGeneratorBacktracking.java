import java.util.ArrayList;

public class CrosswordGeneratorBacktracking {
    static final int GRID_SIZE = 10; 
    static char[][] grid = new char[GRID_SIZE][GRID_SIZE];
    static ArrayList<String> words = new ArrayList<>();

    public static void main(String[] args) {
        initializeGrid();

       
        words.add("JAVA");
        words.add("PROGRAM");
        words.add("CROSSWORD");
        words.add("ALGORITHM");
        words.add("LOGIC");

        
        if (solve(0)) {
            printGrid(); 
        } else {
            System.out.println("Could not generate a valid crossword with the given words.");
        }
    }

   
    private static void initializeGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = '.';
            }
        }
    }

   
    private static boolean solve(int wordIndex) {
        if (wordIndex == words.size()) {
            return true;
        }

        String word = words.get(wordIndex);

      
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                
                if (canPlaceWord(word, row, col, true)) {
                    placeWord(word, row, col, true);
                    if (solve(wordIndex + 1)) {
                        return true;
                    }
                    removeWord(word, row, col, true); 
                }

             
                if (canPlaceWord(word, row, col, false)) {
                    placeWord(word, row, col, false);
                    if (solve(wordIndex + 1)) {
                        return true;
                    }
                    removeWord(word, row, col, false); 
                }
            }
        }

        return false; 
    }

  
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

  
    private static void printGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
