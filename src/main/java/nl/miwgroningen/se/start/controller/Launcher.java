package nl.miwgroningen.se.start.controller;

import nl.miwgroningen.se.start.model.WordLinesMap;
import nl.miwgroningen.se.start.model.WordMap;
import nl.miwgroningen.se.start.model.WordSet;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Lees een tekst bestand in en analyseer het
 */
public class Launcher {

    public static void main(String[] args) {
//        WordMap wordMap = new WordMap();

//        wordMap.readFromFile("MaxHavelaar.txt");
//        wordMap.readFromFile("gedichten.txt");
//        wordMap.readFromFile("input.txt");

//        System.out.println(wordMap.getWordsSorted());
//
//        for (String word : wordMap.getWordsSorted()) {
//            System.out.printf("%s: %d\n", word, wordMap.getWordCount(word));
//        }
//        System.out.println(wordMap.getNrOfUniqueWords());

        WordLinesMap wordLinesMap = new WordLinesMap();

        wordLinesMap.readFromFile("MaxHavelaar.txt");

        System.out.printf("Max Havelaar bevat %d unieke woorden\n", wordLinesMap.getNrOfUniqueWords());
        System.out.println("Er wordt nu een bestand afgedrukt met alle woorden op alfabetische volgorde\n" +
                "en de regels waarin ze gebruikt worden.");

        try (PrintWriter printWriter = new PrintWriter("MaxHavelaarData.txt")){
            for (String word : wordLinesMap.getWordsSorted()) {
                List<Integer> wordLineNumbers = wordLinesMap.getWordLineNrs(word);
                String output = String.format("%s appears at Lines %s", word, wordLineNumbers);
                printWriter.println(output.replace("[", "").replace("]", ""));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
    }