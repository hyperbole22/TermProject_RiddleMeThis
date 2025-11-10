import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class testingStuff {

    public static void main(String[] args) {
        // 1. Create the Map with 10 key-value pairs
        Map<String, String> myMap = new HashMap<>();
        myMap.put("key1", "valueA");
        myMap.put("key2", "valueB");
        myMap.put("key3", "valueC");
        myMap.put("key4", "valueD");
        myMap.put("key5", "valueE");
        myMap.put("key6", "valueF");
        myMap.put("key7", "valueG");
        myMap.put("key8", "valueH");
        myMap.put("key9", "valueI");
        myMap.put("key10", "valueJ");

        // 2. Extract Values and 3. Convert to List and Shuffle
        ArrayList<String> shuffledValues = new ArrayList<>(myMap.values());
        Collections.shuffle(shuffledValues);

        // 4. Populate the Queue with 5 random values
        Queue<String> myQueue = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            myQueue.offer(shuffledValues.get(i));
        }

        System.out.println("Randomly assigned values in the queue: " + myQueue);
    }
}