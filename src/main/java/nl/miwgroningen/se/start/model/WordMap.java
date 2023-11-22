package nl.miwgroningen.se.start.model;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

/**
 * @author Daan Troost <dq.troost@st.hanze.nl>
 * Purpose of the program
 */
public class WordMap {
    private Map<String, Integer> wordmap = new HashMap<>();

    public void readFromFile(String filenaam){
        Path path = Path.of(String.format("src/main/resources/%s", filenaam));
        try (Scanner scanFile = new Scanner(path)){
            while (scanFile.hasNext()){
                String word = scanFile.next();
                word = word.replaceAll("[^A-Za-z0-9]", "");
                if (!word.equals("")) {
                    wordmap.put(word, wordmap.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e){
            System.err.println("File does not appear to exist at this location.");
        }
    }

    public Iterable<String> getWordsSorted(){
        List<String> wordList = new ArrayList<>(wordmap.keySet());
        Collections.sort(wordList);
        return wordList;
    }

    public int getNrOfUniqueWords(){
        List<String> wordList = new ArrayList<>(wordmap.keySet());
        return wordList.size();
    }

    public int getWordCount(String word){
        return wordmap.get(word);
    }
}