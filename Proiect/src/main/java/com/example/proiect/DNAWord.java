package com.example.proiect;

class DNAWord {
    private String word;

    public DNAWord(String word) {
        this.word = word;
    }

    public int hammingDistance(DNAWord other) {
        int distance = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != other.word.charAt(i)) {
                distance += 1;
            }
        }
        return distance;
    }

    public DNAWord reverse() {
        return new DNAWord(new StringBuilder(word).reverse().toString());
    }

    public DNAWord complement() {
        StringBuilder complement = new StringBuilder();
        for (char c : word.toCharArray()) {
            switch (c) {
                case 'A': complement.append('T'); break;
                case 'T': complement.append('A'); break;
                case 'C': complement.append('G'); break;
                case 'G': complement.append('C'); break;
            }
        }
        return new DNAWord(complement.toString());
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return word;
    }
}