package com.example.proiect;

import java.util.ArrayList;
import java.util.List;

class DNASolver {
    private static final char[] ALPHABET = {'A', 'C', 'G', 'T'};

    public List<DNAWord> findLargestSet(int maxCombinations) {
        List<DNAWord> validWords = generateValidWords(maxCombinations);
        System.out.println("Number of tries: " + validWords.size());
        List<DNAWord> largestSet = new ArrayList<>();
        backtrack(new ArrayList<>(), validWords, largestSet);
        return largestSet;
    }

    private List<DNAWord> generateValidWords(int maxCombinations) {
        List<DNAWord> validWords = new ArrayList<>();
        generateWords("", 8, 0, 0, validWords, maxCombinations);
        return validWords;
    }

    private void generateWords(String prefix, int length, int countC, int countG, List<DNAWord> validWords, int maxCombinations) {
        if (validWords.size() >= maxCombinations) {
            return;
        }

        if (length == 0) {
            if (countC + countG == 4) {
                DNAWord word = new DNAWord(prefix);
                validWords.add(word);
            }
            return;
        }

        if (countC < 4) generateWords(prefix + 'C', length - 1, countC + 1, countG, validWords, maxCombinations);
        if (countG < 4) generateWords(prefix + 'G', length - 1, countC, countG + 1, validWords, maxCombinations);
        generateWords(prefix + 'A', length - 1, countC, countG, validWords, maxCombinations);
        generateWords(prefix + 'T', length - 1, countC, countG, validWords, maxCombinations);
    }

    private void backtrack(List<DNAWord> currentSet, List<DNAWord> validWords, List<DNAWord> largestSet) {
        if (currentSet.size() > largestSet.size()) {
            largestSet.clear();
            largestSet.addAll(currentSet);
        }

        for (int i = 0; i < validWords.size(); i++) {
            DNAWord word = validWords.get(i);
            if (isValidAddition(currentSet, word)) {
                currentSet.add(word);
                List<DNAWord> remainingWords = new ArrayList<>(validWords.subList(i + 1, validWords.size()));
                backtrack(currentSet, remainingWords, largestSet);
                currentSet.remove(currentSet.size() - 1);
            }
        }
    }

    private boolean isValidAddition(List<DNAWord> currentSet, DNAWord newWord) {
        for (DNAWord word : currentSet) {
            if (word.hammingDistance(newWord) < 4 ||
                    word.reverse().hammingDistance(newWord.complement()) < 4) {
                return false;
            }
        }
        return true;
    }
}