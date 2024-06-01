package com.example.proiect;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            DNAGUI gui = new DNAGUI();
            gui.setVisible(true);
        });
    }
}
