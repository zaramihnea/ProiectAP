package com.example.proiect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "DNA Combination API", description = "Operations related to DNA combinations")
public class CombinationController {

    @Autowired
    private CombinationService combinationService;

    @GetMapping("/generate-combinations/{number}")
    @Operation(summary = "Generate DNA combinations", description = "Provide the number of combinations to generate")
    public ResponseEntity<List<String>> generateCombinations(
            @Parameter(description = "Number of combinations to generate", required = true)
            @PathVariable int number) {
        List<String> combinations = combinationService.generateCombinations(number);
        return ResponseEntity.ok(combinations);
    }
}
