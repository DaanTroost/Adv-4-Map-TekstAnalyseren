package nl.miwgroningen.se.start.model;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

/**
 * @author Daan Troost <dq.troost@st.hanze.nl>
 * Purpose of the program
 */
public class WordLinesMap {
    private Map<String, List<Integer>> wordLines = new HashMap<>();

    public void readFromFile(String filenaam){
        Path path = Path.of(String.format("src/main/resources/%s", filenaam));
        int lineCount = 1;
        try (Scanner scanFile = new Scanner(path)){
            while (scanFile.hasNextLine()){
                String line = scanFile.nextLine();
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = word.replaceAll("[^A-Za-z0-9]", "");
                    if (!word.isEmpty() && !word.equals(" ")) {
                        wordLines.computeIfAbsent(word, lines -> new ArrayList<>()).add(lineCount);
                    }
                }
                lineCount++;
            }
        } catch (IOException e){
            System.err.println("File does not appear to exist at this location.");
        }
    }

    public int getNrOfUniqueWords(){
        List<String> wordList = new ArrayList<>(wordLines.keySet());
        return wordList.size();
    }

    public Iterable<String> getWordsSorted(){
        List<String> wordList = new ArrayList<>(wordLines.keySet());
        Collections.sort(wordList);
        return wordList;
    }

    public List<Integer> getWordLineNrs(String word){
        return wordLines.get(word);
    }
}