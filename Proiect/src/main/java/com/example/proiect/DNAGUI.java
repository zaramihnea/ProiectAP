package com.example.proiect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DNAGUI extends JFrame {
    private JTextField maxCombinationsField;
    private JTextArea resultArea;
    private JButton solveButton;
    private JComboBox<String> problemSelector;
    private JTextField vField, qField, lambdaField, dField;
    private DNASolver dnaSolver;
    private PermutationSolver permutationSolver;

    public DNAGUI() {
        dnaSolver = new DNASolver();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Combinatorial Problem Solver");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        problemSelector = new JComboBox<>(new String[]{"DNA Solver", "Permutation Solver"});
        problemSelector.addActionListener(e -> toggleInputFields());

        maxCombinationsField = new JTextField();
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveButton.setText("Working...");
                solveButton.setEnabled(false);

                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        String selectedProblem = (String) problemSelector.getSelectedItem();

                        if ("DNA Solver".equals(selectedProblem)) {
                            String maxCombinationsText = maxCombinationsField.getText();
                            int maxCombinations = maxCombinationsText.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(maxCombinationsText);
                            List<DNAWord> largestSet = dnaSolver.findLargestSet(maxCombinations);
                            displayResults(largestSet);
                        } else if ("Permutation Solver".equals(selectedProblem)) {
                            int v = Integer.parseInt(vField.getText());
                            int q = Integer.parseInt(qField.getText());
                            int lambda = Integer.parseInt(lambdaField.getText());
                            int d = Integer.parseInt(dField.getText());
                            permutationSolver = new PermutationSolver(v, q, lambda, d);
                            List<String> largestSet = permutationSolver.findEquidistantPermutations();
                            displayResults(largestSet);
                        }

                        return null;
                    }

                    @Override
                    protected void done() {
                        solveButton.setText("Solve");
                        solveButton.setEnabled(true);
                    }
                };

                worker.execute();
            }
        });

        vField = new JTextField();
        qField = new JTextField();
        lambdaField = new JTextField();
        dField = new JTextField();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 1));
        inputPanel.add(new JLabel("Select Problem:"));
        inputPanel.add(problemSelector);
        inputPanel.add(new JLabel("Max Combinations (for DNA Solver):"));
        inputPanel.add(maxCombinationsField);
        inputPanel.add(new JLabel("v (for Permutation Solver):"));
        inputPanel.add(vField);
        inputPanel.add(new JLabel("q (for Permutation Solver):"));
        inputPanel.add(qField);
        inputPanel.add(new JLabel("λ (for Permutation Solver):"));
        inputPanel.add(lambdaField);
        inputPanel.add(new JLabel("d (for Permutation Solver):"));
        inputPanel.add(dField);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultArea), BorderLayout.CENTER);
        panel.add(solveButton, BorderLayout.SOUTH);

        add(panel);

        toggleInputFields();
    }

    private void toggleInputFields() {
        boolean isDnaSolver = "DNA Solver".equals(problemSelector.getSelectedItem());
        maxCombinationsField.setEnabled(isDnaSolver);
        vField.setEnabled(!isDnaSolver);
        qField.setEnabled(!isDnaSolver);
        lambdaField.setEnabled(!isDnaSolver);
        dField.setEnabled(!isDnaSolver);
    }

    private void displayResults(List<?> results) {
        resultArea.setText("Results Size: " + results.size() + "\n");
        for (Object result : results) {
            resultArea.append(result.toString() + "\n");
        }
    }
}