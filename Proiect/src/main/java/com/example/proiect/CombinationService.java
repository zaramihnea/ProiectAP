package com.example.proiect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CombinationService {

    private final DNASolver dnaSolver;
    private final DNACombinationRepository dnaCombinationRepository;

    @Autowired
    public CombinationService(DNACombinationRepository dnaCombinationRepository) {
        this.dnaSolver = new DNASolver();
        this.dnaCombinationRepository = dnaCombinationRepository;
    }

    public List<String> generateCombinations(int maxCombinations) {
        List<DNAWord> combinations = dnaSolver.findLargestSet(maxCombinations);
        List<String> combinationStrings = combinations.stream()
                .map(DNAWord::toString)
                .collect(Collectors.toList());

        combinationStrings.forEach(combination -> {
            DNACombination dnaCombination = new DNACombination(combination);
            dnaCombinationRepository.save(dnaCombination);
        });

        return combinationStrings;
    }
}
