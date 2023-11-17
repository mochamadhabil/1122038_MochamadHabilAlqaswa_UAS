/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.DatabaseController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import model.Games;
import model.Users;

public class GameListScreen {

    JFrame container;

    public GameListScreen(Users loggedInUser) {
        container = new JFrame("Game List");
        container.setSize(800, 600);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLocationRelativeTo(null);
        container.setLayout(null);

        // Tombol Transactions
        JButton btnTransactions = new JButton("Transactions");
        btnTransactions.setBounds(300, 60, 150, 25);
        container.add(btnTransactions);

        btnTransactions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TransactionsMenu(loggedInUser);
                container.setVisible(false);
            }
        });

        displayGames(loggedInUser);

        container.setVisible(true);
    }

    private void displayGames(Users loggedInUser) {
        ArrayList<Games> games = DatabaseController.getAllGames(loggedInUser.getId());

        String[] columnNames = {"Id", "Name", "Genre", "Price", "Action"};
        Object[][] data = new Object[games.size()][5];

        for (int i = 0; i < games.size(); i++) {
            Games game = games.get(i);
            data[i][0] = game.getId();
            data[i][1] = game.getName();
            data[i][2] = game.getGenre();
            data[i][3] = game.getPrice();

            JButton buyButton = new JButton("Buy Game");
            container.add(buyButton);
            int gameId = game.getId(); 

            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean success = DatabaseController.buyGame(loggedInUser.getId(), gameId);

                    if (success) {
                        JOptionPane.showMessageDialog(container, "Pembelian berhasil!");
                    } else {
                        JOptionPane.showMessageDialog(container, "Pembelian gagal!");
                    }
                }
            });

            data[i][4] = buyButton;
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 100, 760, 400);
        container.add(scrollPane);
    }
}
