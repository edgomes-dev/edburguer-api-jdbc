package dao;

import db.ConnectionDb;
import model.IngredientModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAO {
    private Connection connection;

    public IngredientDAO() {
        connection = ConnectionDb.getConnection();
    }

    public void create(IngredientModel model) {
        try {
            String sql = "INSERT INTO public.ingredient (name, description, price, repetitions) VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
            statement.setString(2, model.getDescription());
            statement.setDouble(3, model.getPrice());
            statement.setInt(4, model.getRepetitions());
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<IngredientModel> findAll() throws Exception {
        List<IngredientModel> list = new ArrayList<>();

        String sql = "SELECT * FROM public.ingredient";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            IngredientModel model = new IngredientModel();
            model.setId(result.getLong("ingredient_id"));
            model.setName(result.getString("name"));
            model.setDescription(result.getString("description"));
            model.setPrice(result.getDouble("price"));
            model.setRepetitions(result.getInt("repetitions"));

            list.add(model);
        }

        return list;
    }

    public IngredientModel findById(Long id) throws Exception {
        IngredientModel model = new IngredientModel();

        String sql = "SELECT * FROM public.ingredient WHERE ingredient_id = " + id;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        result.next();

        model.setId(result.getLong("ingredient_id"));
        model.setName(result.getString("name"));
        model.setDescription(result.getString("description"));
        model.setPrice(result.getDouble("price"));
        model.setRepetitions(result.getInt("repetitions"));

        return model;
    }

    public void update(IngredientModel model) {
        try {
            String sql = "UPDATE public.ingredient SET name = ?, description = ?, price = ?, repetitions = ? WHERE ingredient_id = " + model.getId();

            PreparedStatement statement;
            statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
            statement.setString(2, model.getDescription());
            statement.setDouble(3, model.getPrice());
            statement.setInt(4, model.getRepetitions());

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
            String sql = "DELETE FROM public.ingredient WHERE ingredient_id = " + id;
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
