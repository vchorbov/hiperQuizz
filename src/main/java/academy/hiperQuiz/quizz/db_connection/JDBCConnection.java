package academy.hiperQuiz.quizz.db_connection;

import academy.hiperQuiz.quizz.entity.Gender;
import academy.hiperQuiz.quizz.entity.Role;
import academy.hiperQuiz.quizz.entity.User;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCConnection {
    public static void main(String[] args) throws IOException {
        // 1., load db props from file
        String propsParh = JDBCConnection.class.getClassLoader().getResource("db.properties").getPath();
        Properties props = new Properties();
        props.load(new FileInputStream(propsParh));

        // 2. Load DB driver
        try {
            Class.forName(props.getProperty("driver"));
            System.out.println("DB driver loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: DB driver not found");
            e.printStackTrace();
            return;
        }

        // 3. Connect to DB
        try (Connection connection = DriverManager.getConnection(props.getProperty("url"), props)) {
            System.out.printf("Successfully connected to: %s%n", props.getProperty("url"));
            // 4. Create and execute PreparedStatement
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = stmt.executeQuery();

            List<User> usersFromDb = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId((long) rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
               // user.setGender(rs.getInt("gender") == 0 ? Gender.values()[rs.getInt("gender")] : Gender.MALE);
                user.setGender(Gender.values()[rs.getInt("gender")]);
                user.setRole(rs.getInt("role") == 0 ? Role.PLAYER : Role.ADMIN);
                user.setPicture(rs.getString("picture"));
                user.setDescription(rs.getString("description"));
                user.setMetadata(rs.getString("metadata"));
                user.setStatus(rs.getInt("status") == 0 ? true : false);
                usersFromDb.add(user);

            }
            usersFromDb.forEach(System.out::println);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
