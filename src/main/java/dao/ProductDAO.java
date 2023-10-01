package dao;

import db.ConnectionDb;
import model.IngredientModel;
import model.ProductCategoryModel;
import model.ProductModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public ProductDAO() {
        connection = ConnectionDb.getConnection();
    }

    public void create(ProductModel model) {
        try {
            String sql = "INSERT INTO public.product (name, description, price, product_category_id) VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
            statement.setString(2, model.getDescription());
            statement.setDouble(3, model.getPrice());
            statement.setLong(4, model.getProductCategoryId());
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ProductModel> findAll() throws Exception {
        List<ProductModel> list = new ArrayList<>();

        String sql = "SELECT * FROM public.product";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            ProductModel model = new ProductModel();
            model.setName(result.getString("name"));
            model.setDescription(result.getString("description"));
            model.setPrice(result.getDouble("price"));
            model.setProductCategoryId(result.getLong("product_category_id"));

            list.add(model);
        }

        return list;
    }

    public ProductModel findById(Long id) throws Exception {
        ProductModel model = new ProductModel();

        String sql = "SELECT * FROM public.product WHERE product_id = " + id;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        result.next();

        model.setId(result.getLong("product_id"));
        model.setName(result.getString("name"));
        model.setDescription(result.getString("description"));
        model.setPrice(result.getDouble("price"));
        model.setProductCategoryId(result.getLong("product_category_id"));

        return model;
    }

    public void update(ProductModel model) {
        try {
            String sql = "UPDATE public.product SET name = ?, description = ?, price = ?, product_category_id = ? WHERE product_id = " + model.getId();

            PreparedStatement statement;
            statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
            statement.setString(2, model.getDescription());
            statement.setDouble(3, model.getPrice());
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
            String sql = "DELETE FROM public.product WHERE product_id = " + id;
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
