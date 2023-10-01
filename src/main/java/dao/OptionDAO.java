package dao;

import db.ConnectionDb;
import model.IngredientModel;
import model.OptionModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OptionDAO {
    private Connection connection;

    public OptionDAO() {
        connection = ConnectionDb.getConnection();
    }

    public void create(OptionModel model) {
        try {
            String sql = "INSERT INTO public.option (name, maximum_amount, required, product_category_id) VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, model.getName());
            statement.setInt(2, model.getMaximumAmount());
            statement.setBoolean(3, model.getRequired());
            statement.setLong(4, model.getProductCategoryId());
            statement.execute();
            Long id = null;
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getLong(1);
                }
            }
            this.createOptionIngredient(id, model.getIngredients());
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOptionIngredient(Long idOption, List<Long> list) throws SQLException {
        for (Long id : list) {
            try {
                IngredientDAO ingredientDAO = new IngredientDAO();
                ingredientDAO.findById(id);

                String sql = "INSERT INTO public.option_ingredient (option_option_id, ingredient_ingredient_id) OVERRIDING SYSTEM VALUE VALUES(?,?);";
                PreparedStatement statementMany = connection.prepareStatement(sql);
                statementMany.setLong(1, idOption);
                statementMany.setLong(2, id);
                statementMany.execute();
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            }
        }
    }

    public List<OptionModel> findAll() throws Exception {
        List<OptionModel> list = new ArrayList<>();

        String sql = "SELECT * FROM public.option";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            OptionModel model = new OptionModel();
            model.setId(result.getLong("id"));
            model.setName(result.getString("name"));
            model.setMaximumAmount(result.getInt("maximum_amount"));
            model.setRequired(result.getBoolean("required"));
            model.setProductCategoryId(result.getLong("product_category_id"));

            list.add(model);
        }

        return list;
    }

    public OptionModel findById(Long id) throws Exception {
        OptionModel model = new OptionModel();

        String sql = "SELECT * FROM public.option WHERE id = " + id;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        model.setId(result.getLong("id"));
        model.setName(result.getString("name"));
        model.setMaximumAmount(result.getInt("maximum_amount"));
        model.setRequired(result.getBoolean("required"));
        model.setProductCategoryId(result.getLong("product_category_id"));

        return model;
    }

    public void update(OptionModel model) {
        try {
            String sql = "SELECT * FROM public.option WHERE id = " + model.getId();

            PreparedStatement statement;
            statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
            statement.setInt(2, model.getMaximumAmount());
            statement.setBoolean(3, model.getRequired());
            statement.setLong(4, model.getProductCategoryId());

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
            String sql = "DELETE FROM public.option WHERE id = " + id;
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
