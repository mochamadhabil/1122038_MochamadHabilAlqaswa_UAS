/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.DatabaseController;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.Users;

/**
 *
 * @author abil
 */
public class TransactionsMenu {

    JFrame container;

    public TransactionsMenu(Users loggedInUser) {
        container = new JFrame("Transactions");
        container.setSize(800, 600);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLocationRelativeTo(null);
        container.setLayout(null);

        String userName = loggedInUser.getName();


//        // Fetch transactions for the logged-in user
//        ArrayList<TransactionsMenu> games = DatabaseController.getUserTransactions(loggedInUser.getId());
//
//        // Define column names
//        String[] columnNames = {"ID", "User ID", "User Name", "Game ID", "Game Name", "Total Price"};
//
//        // Create data array for the table
//        Object[][] data = new Object[games.size()][6];
//
//        // Populate data array from transactions list
//        for (int i = 0; i < games.size(); i++) {
//            Transactions transaction = games.get(i);
//            data[i][0] = transaction.getId();
//            data[i][1] = transaction.getUserId();
//            data[i][2] = transaction.getUserName();
//            data[i][3] = transaction.getGameId();
//            data[i][4] = transaction.getGameName();
//            data[i][5] = transaction.getTotalPrice();
//        }
//
//        // Create JTable and set data
//        JTable table = new JTable(data, columnNames);
//        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setBounds(10, 70, 760, 400);
//        container.add(scrollPane);
//
//        // Display the frame
        container.setVisible(true);
    }
}
