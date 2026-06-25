package game;

import javax.swing.*;
import java.awt.*;

public class StatisticsFrame extends JFrame {
    public StatisticsFrame(Player player) {
        PlayerService playerService = new PlayerService();
        Player updatedPlayer = playerService.getPlayerById(player.getId());
        if (updatedPlayer != null) {
            player = updatedPlayer;
        }

        setTitle("My Statistics - " + player.getUsername());
        setSize(300, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        panel.add(new JLabel("Username:"));
        panel.add(new JLabel(player.getUsername()));

        panel.add(new JLabel("Wins:"));
        panel.add(new JLabel(String.valueOf(player.getWins())));

        panel.add(new JLabel("Losses:"));
        panel.add(new JLabel(String.valueOf(player.getLosses())));

        panel.add(new JLabel("Draws:"));
        panel.add(new JLabel(String.valueOf(player.getDraws())));

        panel.add(new JLabel("Score:"));
        panel.add(new JLabel(String.valueOf(player.getScore())));

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> dispose());
        panel.add(new JLabel());
        panel.add(btnClose);

        add(panel);
    }
}