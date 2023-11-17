/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.DatabaseController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
public class RegisterScreen {

    JFrame container;
    JTextField email;
    JTextField name;
    JPasswordField password;
    JButton btnRegistrasi;
    JButton btnBack;

    ArrayList<Users> users = new ArrayList<>();

    public RegisterScreen() {
        container = new JFrame("Main Menu");
        container.setSize(400, 300);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLocationRelativeTo(null);
        container.setLayout(null);

        //Bagian Email
        JLabel emailLabel = new JLabel("Email          : ");
        email = new JTextField();
        emailLabel.setBounds(30, 30, 150, 25);
        email.setBounds(130, 33, 230, 20);
        container.add(emailLabel);
        container.add(email);

        //Bagian Nama
        JLabel namaLabel = new JLabel("Nama         : ");
        name = new JTextField();
        namaLabel.setBounds(30, 50, 150, 25);
        name.setBounds(130, 55, 230, 20);
        container.add(namaLabel);
        container.add(name);

        //Bagian Password
        JLabel passwordLabel = new JLabel("Password : ");
        password = new JPasswordField();
        passwordLabel.setBounds(30, 70, 150, 25);
        password.setBounds(130, 75, 230, 20);
        container.add(passwordLabel);
        container.add(password);

        //button Registrasi
        btnRegistrasi = new JButton("Registrasi");
        btnRegistrasi.setBounds(100, 200, 110, 25);
        container.add(btnRegistrasi);

        btnRegistrasi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idValue = 0;
                String namaValue = name.getText();
                String emailValue = email.getText();
                String passValue = new String(password.getPassword());

                Users user = new Users(idValue, namaValue, emailValue, passValue);
                users.add(user);

                DatabaseController controller = new DatabaseController();
                controller.insertNewUser(user);

                container.setVisible(false);

                JOptionPane.showMessageDialog(null, "Data berhasil disimpan.");

                new MainMenu();
            }
        });

        //button back
        btnBack = new JButton("Back");
        btnBack.setBounds(230, 200, 110, 25);
        container.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.setVisible(false);
                new MainMenu();

            }
        });
        container.setVisible(true);
    }

}
