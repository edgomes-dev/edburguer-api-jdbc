package dao;

import db.ConnectionDb;
import model.IngredientModel;
import model.ProductCategoryModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDAO {
    private Connection connection;

    public ProductCategoryDAO() {
        connection = ConnectionDb.getConnection();
    }

    public void create(ProductCategoryModel model) {
        try {
            String sql = "INSERT INTO public.product_category (name) values(?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ProductCategoryModel> findAll() throws Exception {
        List<ProductCategoryModel> list = new ArrayList<>();

        String sql = "SELECT * FROM public.product_category";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            ProductCategoryModel model = new ProductCategoryModel();
            model.setId(result.getLong("product_category_id"));
            model.setName(result.getString("name"));

            list.add(model);
        }

        return list;
    }

    public ProductCategoryModel findById(Long id) throws Exception {
        ProductCategoryModel model = new ProductCategoryModel();

        String sql = "SELECT * FROM public.product_category WHERE product_category_id = " + id;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        result.next();

        model.setId(result.getLong("product_category_id"));
        model.setName(result.getString("name"));

        return model;
    }

    public void update(ProductCategoryModel model) {
        try {
            String sql = "UPDATE public.product_category SET name = ? WHERE product_category_id = " + model.getId();

            PreparedStatement statement;
            statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());

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
            String sql = "DELETE FROM public.product_category WHERE product_category_id = " + id;
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
