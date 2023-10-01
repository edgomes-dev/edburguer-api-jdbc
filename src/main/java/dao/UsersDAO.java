package dao;

import db.ConnectionDb;
import model.UsersModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    private Connection connection;

    public UsersDAO() {
        connection = ConnectionDb.getConnection();
    }

    public void create(UsersModel model) {
        try {
            String sql = "INSERT INTO public.users (name, email, phone, password) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
            statement.setString(2, model.getEmail());
            statement.setString(3, model.getPhone());
            statement.setString(4, model.getPassword());
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UsersModel> findAll() throws Exception {
        List<UsersModel> list = new ArrayList<>();

        String sql = "SELECT * FROM public.users";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            UsersModel model = new UsersModel();
            model.setId(result.getLong("user_id"));
            model.setName(result.getString("name"));
            model.setEmail(result.getString("email"));
            model.setPhone(result.getString("phone"));
            model.setPassword(result.getString("password"));

            list.add(model);
        }

        return list;
    }

    public UsersModel findById(Long id) throws Exception {
        UsersModel user = new UsersModel();

        String sql = "SELECT * FROM public.users WHERE user_id = " + id;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        result.next();

        user.setId(result.getLong("user_id"));
        user.setName(result.getString("name"));
        user.setEmail(result.getString("email"));
        user.setPhone(result.getString("phone"));
        user.setPassword(result.getString("password"));

        return user;
    }

    public void update(UsersModel model) {
        try {
            String sql = "UPDATE public.users SET name = ?, email = ?, phone = ?, password = ? WHERE user_id = " + model.getId();

            PreparedStatement statement;
            statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
            statement.setString(2, model.getEmail());
            statement.setString(3, model.getPhone());
            statement.setString(4, model.getPassword());

            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        try {
            String sql = "DELETE FROM public.users WHERE user_id = " + id;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
