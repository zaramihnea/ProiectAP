package com.example.proiect;

import java.util.ArrayList;
import java.util.List;

class PermutationSolver {

    private int v, q, lambda, d;

    public PermutationSolver(int v, int q, int lambda, int d) {
        this.v = v;
        this.q = q;
        this.lambda = lambda;
        this.d = d;
    }

    public List<String> findEquidistantPermutations() {
        List<String> codewords = new ArrayList<>();
        generatePermutations(new ArrayList<>(), codewords);
        return codewords;
    }

    private void generatePermutations(List<Integer> current, List<String> codewords) {
        if (current.size() == q * lambda) {
            if (isValid(current, codewords)) {
                StringBuilder sb = new StringBuilder();
                for (int num : current) {
                    sb.append(num).append(' ');
                }
                codewords.add(sb.toString().trim());
            }
            return;
        }

        for (int i = 0; i < q; i++) {
            int count = 0;
            for (int num : current) {
                if (num == i) {
                    count++;
                }
            }
            if (count < lambda) {
                current.add(i);
                generatePermutations(current, codewords);
                current.remove(current.size() - 1);
            }
        }
    }

    private boolean isValid(List<Integer> current, List<String> codewords) {
        String newWord = current.toString();
        for (String word : codewords) {
            if (hammingDistance(newWord, word) != d) {
                return false;
            }
        }
        return true;
    }

    private int hammingDistance(String word1, String word2) {
        String[] seq1 = word1.split(", ");
        String[] seq2 = word2.split(", ");
        int distance = 0;
        for (int i = 0; i < seq1.length; i++) {
            if (!seq1[i].equals(seq2[i])) {
                distance++;
            }
        }
        return distance;
    }
}