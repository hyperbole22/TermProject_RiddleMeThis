import java.util.*;
import java.io.*;

public class Riddle {

    public static final String rules = "welcome to Riddle Me This! \n " +
                    "you will be given five random riddles to solve \n" +
                    "all answers will be one word answers, other kinds of answers will not be accepted \n" +
                    "after three guesses you will be offered a hint\n" +
                    "there are three hints for every riddle\n" + 
                    "solve all five riddles to win the game\n" +
                    "if you exceed ten guesses on one riddle, you will automatically lose the game";

    public static final String loss = "wow you suck at this... \n" +
                  "better luck next time I guess";
    
    public static final String win = "congrats you actually managed to solve all the riddles! \n" +
                 "too bad even a 3rd grader could do that...";

    public static final String RESET = "\033[0m";
    public static final String YELLOW = "\033[33m";
    
    //Put all riddles in at start of program
    public static void main(String[] args) {
        System.out.println("debug");
        File riddleFile = new File("riddles.txt");
        System.out.println("debug1");
        File hintFile = new File("hints.txt");
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> riddleMap = new HashMap<>();
        HashMap<String, List<String>> hintMap = new HashMap<>();
        
        //read riddles from file and store in hashmap
        try {
            System.out.println("debug2");
            while (scanner.hasNextLine()) {
                System.out.println("debug3"); // didn't print
                String line = scanner.nextLine();
                String[] parts = line.split(":");
                if (parts.length == 2){
                    String answer = parts[0].trim();
                    String riddle = parts[1];
                    riddleMap.put(answer, riddle);
                }
            }
        } 
        catch (Exception e) {
            System.out.println("error reading file");
            e.printStackTrace();
        }

        //read hints from file and store in hashmap
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String answer = parts[0].trim();
                    String[] hints = parts[1].split(",");
                    if (hints.length == 3) {
                        //idk if i need this part
                        String hintOne = hints[0];
                        String hintTwo = hints[1];
                        String hintThree = hints[2];
                    }
                    hintMap.put(answer, Arrays.asList(hints));
                }
            }
        }
        catch (Exception e) {
            System.out.println("error reading file");
            e.printStackTrace();
        }
        
        
        //Create and shuffle a complete list of riddles
        ArrayList<String>shuffledRiddles = new ArrayList();
        for (String riddle : riddleMap.values()) {
            shuffledRiddles.add(riddle);
        }
        Collections.shuffle(shuffledRiddles);

        //assign riddles to be solved
        Queue<String> riddlesToDo = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            riddlesToDo.offer(shuffledRiddles.get(i));
        }


    

        while (!riddlesToDo.isEmpty()) {
            String currentRiddle = riddlesToDo.poll();
            boolean solved = false;
            int attempts = 0;
            while(!solved) {
                System.out.println("riddle");
                System.out.println("enter your guess: ");
                String userGuesses = scanner.nextLine();
                if (userGuesses.equalsIgnoreCase("riddle")) {
                    System.out.println("good job! you actually got one!");
                    solved = true;
                }
                else {
                    //add option for hints after three wrong guesses
                    attempts++;
                    if (attempts >= 3) {
                        System.out.println("would you like a hint? (y/n): ");
                        String wantHint = scanner.nextLine();
                        if (wantHint.equalsIgnoreCase("y")){
                            System.out.println("here is your hint: ");
                        }
                        else if (wantHint.equalsIgnoreCase("n")){
                            System.out.println("really? okay... try again I guess");
                        }
                        else {
                                System.out.println("that wasn't either option... try again");
                        }                       
                    }
                    System.out.println("wrong! how can you not get it?");
                }
            }
        }

        System.out.println("congratulations! you have solved all the riddles!");
        System.out.println("here is your prize:");
        System.out.println(YELLOW + " .  .  .  .");
        System.out.println(YELLOW + "/\\_/\\_/\\_/\\");
        System.out.println(YELLOW + "|          |");
        System.out.println(YELLOW + "|          |");
        System.out.println(YELLOW + "------------" + RESET);
    }
}

/* HINTS
 * - hints will be stored in chained hash table
 * - each hint will hash to index based on which riddle it relates to
 *   - if linked list is empty, call person dumb for using all their hints
 * - gives option for a hint after three failed guesses
 * 
 * RIDDLES
 * - Write in text file with hints and riddles
 * - Overall loop pops off riddle
 *   - Internal loop loops through riddle and hints until the riddle is solved
 * - Riddles and their answers will be stored in a map as key - value pairs
 * 
 * ?? creat hash map w/ arraylist as value??
 * ?? if key in hint hashmap matches key in riddle hashmap
 *      for each value in arraylist, map to hash table index based on index of riddle
 * 
 * 
*/
