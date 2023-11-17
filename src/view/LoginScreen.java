/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.DatabaseController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.Users;

/**
 *
 * @author abil
 */
public class LoginScreen {

    JFrame container;
    JTextField email;
    JPasswordField password;
    JButton btnLogin;
    JButton btnBack;

    public LoginScreen() {
        container = new JFrame("Login");
        container.setSize(400, 200);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLocationRelativeTo(null);
        container.setLayout(null);

        //Bagian Email
        JLabel emailLabel = new JLabel("Email          : ");
        email = new JTextField();
        emailLabel.setBounds(30, 30, 150, 25);
        email.setBounds(110, 33, 230, 20);
        container.add(emailLabel);
        container.add(email);

        //Bagian Password
        JLabel passwordLabel = new JLabel("Password : ");
        password = new JPasswordField();
        passwordLabel.setBounds(30, 50, 150, 25);
        password.setBounds(110, 55, 230, 20);
        container.add(passwordLabel);
        container.add(password);

        //button login
        btnLogin = new JButton("Login");
        btnLogin.setBounds(110, 100, 110, 25);
        container.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userEmail = email.getText();
                String userPassword = new String(password.getPassword());
                Users loggedInUser = DatabaseController.getUser(userEmail, userPassword);

                // Pengecekan ke database
                if (loggedInUser.getId() != 0) {
                    container.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Login Berhasil");
                    new GameListScreen(loggedInUser);
                } else {
                    JOptionPane.showMessageDialog(null, "Login Gagal. email atau password salah.");
                }
            }
        });

        //button back
        btnBack = new JButton("Back");
        btnBack.setBounds(230, 100, 110, 25);
        container.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu();

                container.setVisible(false);
            }
        });

        container.setVisible(true);
    }
}
