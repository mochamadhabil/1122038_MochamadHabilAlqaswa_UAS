/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Games;
import model.Users;
import java.util.ArrayList;
import model.Transactions;

/**
 *
 * @author abil
 */
public class DatabaseController {

    static DatabaseHandler conn = new DatabaseHandler();

    // SELECT ALL from table users
    public ArrayList<Users> getAllUser() {
        ArrayList<Users> users = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM users";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("MASUK CATCH GET ALL USERS : ");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("MASUK CATCH GET ALL USERS NULL : ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("MASUK CATCH GET ALL USERS NULL : ");
            e.printStackTrace();
        }
        conn.disconnect();
        return (users);
    }

    // SELECT WHERE
    public static Users getUser(String email, String password) {
        conn.connect();
        String query = "SELECT * FROM users WHERE email='" + email + "' AND password='" + password + "'";
        Users loggedInUser = new Users();

        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                loggedInUser = new Users();
                loggedInUser.setId(rs.getInt("id"));
                loggedInUser.setName(rs.getString("name"));
                loggedInUser.setEmail(rs.getString("email"));
                loggedInUser.setPassword(rs.getString("password"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loggedInUser;
    }

    // SELECT ALL from table games
    public static ArrayList<Games> getAllGames(int id) {
        ArrayList<Games> games = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM games WHERE id='" + id + "'";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Games game = new Games();
                game.setId(rs.getInt("id"));
                game.setName(rs.getString("name"));
                game.setGenre(rs.getString("genre"));
                game.setPrice(rs.getInt("price"));
                games.add(game);
            }
        } catch (SQLException e) {
            System.out.println("MASUK CATCH GET ALL TODO : ");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("MASUK CATCH GET ALL TODO NULL : ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("MASUK CATCH GET ALL TODO NULL : ");
            e.printStackTrace();
        }
        conn.disconnect();
        return (games);
    }

    // INSERT
    public static boolean insertNewUser(Users user) {
        conn.connect();
        String query = "INSERT INTO users VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());

            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    // SELECT dari Tabel Games dan Transactions dan Users
//     public static ArrayList<Transaction> getUserTransactions(int userId) {
//        ArrayList<Transaction> transactions = new ArrayList<>();
//
//        try {
//            conn.connect();
//            String query = "SELECT transactions.id, transactions.user_id, users.user_name, transactions.game_id, games.game_name, transactions.total_price " +
//                    "FROM transactions " +
//                    "INNER JOIN users ON transactions.user_id = users.id " +
//                    "INNER JOIN games ON transactions.game_id = games.id " +
//                    "WHERE transactions.user_id = ?";
//            
//            PreparedStatement pstmt = conn.con.prepareStatement(query);
//            pstmt.setInt(1, userId);
//
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                int transactionId = rs.getInt("id");
//                int gameUserId = rs.getInt("user_id");
//                String userName = rs.getString("user_name");
//                int gameId = rs.getInt("game_id");
//                String gameName = rs.getString("game_name");
//                double totalPrice = rs.getDouble("total_price");
//
//                // Buat objek Transaction dan tambahkan ke dalam ArrayList
//                Transactions transaction = new Transactions(transactionId, gameUserId, userName, gameId, gameName, totalPrice);
//                transactions.add(transaction);
//            }
//
//            conn.disconnect();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return transactions;
//    }
    public static boolean buyGame(int userId, int gameId) {
        boolean success = false;

        try {
            conn.connect();
            String query = "INSERT INTO transactions (user_id, game_id) VALUES (?, ?)";
            PreparedStatement pstmt = conn.con.prepareStatement(query);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, gameId);

            int barisDimasukkan = pstmt.executeUpdate();
            if (barisDimasukkan > 0) {
                System.out.println("Pembelian berhasil. Total baris terpengaruh: " + barisDimasukkan);
                success = true;
            }

            conn.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    // DELETE
    public boolean deleteUser(Users user) {
        conn.connect();

        String query = "DELETE FROM users WHERE id='" + user.getId() + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}
