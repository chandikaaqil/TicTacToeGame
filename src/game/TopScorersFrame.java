package game;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TopScorersFrame extends JFrame {
    public TopScorersFrame() {
        setTitle("Top 5 Scorers");
        setSize(450, 220);
        setLocationRelativeTo(null);
        setResizable(false);

        String[] columns = {"Username", "Wins", "Losses", "Draws", "Score"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        PlayerService playerService = new PlayerService();
        ArrayList<Player> topPlayers = playerService.getTopFiveScorers();

        for (Player p : topPlayers) {
            model.addRow(new Object[]{
                p.getUsername(),
                p.getWins(),
                p.getLosses(),
                p.getDraws(),
                p.getScore()
            });
        }

        JTable table = new JTable(model);
        table.setEnabled(false);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> dispose());
        add(btnClose, BorderLayout.SOUTH);
    }
}