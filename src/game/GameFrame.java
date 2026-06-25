package game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;
    private JButton[] buttons;
    private JLabel lblStatus;

    public GameFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();
        this.gameLogic = new GameLogic();

        setTitle("Tic Tac Toe - " + player.getUsername());
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        lblStatus = new JLabel("Your turn! (X)", SwingConstants.CENTER);
        lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblStatus, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttons = new JButton[9];

        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            buttons[i].addActionListener(e -> handlePlayerMove(index));
            boardPanel.add(buttons[i]);
        }

        add(boardPanel, BorderLayout.CENTER);

        JButton btnBack = new JButton("Back to Menu");
        btnBack.addActionListener(e -> {
            MainMenuFrame menuFrame = new MainMenuFrame(currentPlayer);
            menuFrame.setVisible(true);
            dispose();
        });
        add(btnBack, BorderLayout.SOUTH);
    }

    private void handlePlayerMove(int index) {
        if (!gameLogic.makeMove(index, 'X')) return;
        buttons[index].setText("X");
        buttons[index].setForeground(Color.BLUE);
        buttons[index].setEnabled(false);

        if (gameLogic.checkWinner('X')) {
            finishGame("WIN");
            return;
        }

        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        lblStatus.setText("Computer is moving...");
        int compIndex = gameLogic.computerMove();
        gameLogic.makeMove(compIndex, 'O');
        buttons[compIndex].setText("O");
        buttons[compIndex].setForeground(Color.RED);
        buttons[compIndex].setEnabled(false);

        if (gameLogic.checkWinner('O')) {
            finishGame("LOSE");
            return;
        }

        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        lblStatus.setText("Your turn! (X)");
    }

    private void finishGame(String result) {
        playerService.updateStatistics(currentPlayer, result);

        for (JButton btn : buttons) {
            btn.setEnabled(false);
        }

        String message;
        if (result.equals("WIN")) {
            message = "Congratulations! You win! +10 points";
            lblStatus.setText("You Win!");
        } else if (result.equals("LOSE")) {
            message = "You lose! Better luck next time.";
            lblStatus.setText("You Lose!");
        } else {
            message = "Draw! +3 points";
            lblStatus.setText("Draw!");
        }

        JOptionPane.showMessageDialog(this, "Game result: " + result + "\n" + message);
        MainMenuFrame menuFrame = new MainMenuFrame(currentPlayer);
        menuFrame.setVisible(true);
        dispose();
    }
}