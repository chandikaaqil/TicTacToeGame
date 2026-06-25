package game;

import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    private Player currentPlayer;
    private JButton btnStartGame;
    private JButton btnStatistics;
    private JButton btnTopScorers;
    private JButton btnExit;

    public MainMenuFrame(Player player) {
        this.currentPlayer = player;
        setTitle("Main Menu - " + player.getUsername());
        setSize(300, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel lblWelcome = new JLabel("Welcome, " + player.getUsername(), SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblWelcome, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        btnStartGame = new JButton("Start Game");
        btnStatistics = new JButton("My Statistics");
        btnTopScorers = new JButton("Top 5 Scorers");
        btnExit = new JButton("Exit");

        panel.add(btnStartGame);
        panel.add(btnStatistics);
        panel.add(btnTopScorers);
        panel.add(btnExit);

        add(panel, BorderLayout.CENTER);

        btnStartGame.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(currentPlayer);
            gameFrame.setVisible(true);
            dispose();
        });

        btnStatistics.addActionListener(e -> {
            StatisticsFrame statisticsFrame = new StatisticsFrame(currentPlayer);
            statisticsFrame.setVisible(true);
        });

        btnTopScorers.addActionListener(e -> {
            TopScorersFrame topFrame = new TopScorersFrame();
            topFrame.setVisible(true);
        });

        btnExit.addActionListener(e -> {
            System.exit(0);
        });
    }
}