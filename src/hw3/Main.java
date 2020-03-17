package hw3;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void getWordsInvolvement(String[] wordArray){
        Set<String> uniqueWords = new HashSet<>();
        Map<String, String> occurrencesMap = new HashMap<>();
        Collections.addAll(uniqueWords, wordArray);

        for (String uniqueWord: uniqueWords){
            int wordCounter = 0;
            for (String word: wordArray){
                if(uniqueWord.equalsIgnoreCase(word)){
                    wordCounter++;
                }

            }
            occurrencesMap.put(uniqueWord, String.valueOf(wordCounter));
        }
        System.out.println("Список уникальных слов:");
        for(String key: occurrencesMap.keySet()){
            System.out.println(String.format("%s - входит %s раз", key, occurrencesMap.get(key)));
        }
    }

    public static void main(String[] args) throws IOException {

        /////////First task///////
        String[] wordArray = {"Word1", "Word2", "Word1", "Word6", "Word33", "Word17","Word1", "Word2", "Word17",
                "Word1", "Word2", "Word3", "Word1", "Word2", "Word1","Word77", "Word2", "Word1"};
        getWordsInvolvement(wordArray);

        /////////Second task///////
        PhoneBook phoneBook = new PhoneBook();
        try {
            phoneBook.add("Petrov", "228");
            phoneBook.add("Petrov", "222");
            phoneBook.add("Petrov", "117");
            phoneBook.add("Petrov", "999");
//            phoneBook.add("Dubow", "228");
            phoneBook.add("Dubow", "000");
        } catch (NumberAlreadyExistsException ex){
            System.out.println(ex.getMessage());
        }

        phoneBook.printSurnameNumbers("Petrov");
        phoneBook.printSurnameNumbers("Dubow");
        phoneBook.printSurnameNumbers("Lee");


    }



}
