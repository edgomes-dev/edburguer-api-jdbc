package dao;

import db.ConnectionDb;
import model.DistrictModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DistrictDAO {
    private Connection connection;

    public DistrictDAO() {
        connection = ConnectionDb.getConnection();
    }

    public void create(DistrictModel model) {
        try {
            String sql = "INSERT INTO public.district ( name, delivery_price) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
            statement.setDouble(2, model.getDeliveryPrice());
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<DistrictModel> findAll() throws Exception {
        List<DistrictModel> list = new ArrayList<>();

        String sql = "SELECT * FROM public.district";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            DistrictModel model = new DistrictModel();
            model.setId(result.getLong("district_id"));
            model.setName(result.getString("name"));
            model.setDeliveryPrice(result.getDouble("delivery_price"));

            list.add(model);
        }

        return list;
    }

    public DistrictModel findById(Long id) throws Exception {
        DistrictModel model = new DistrictModel();

        String sql = "SELECT * FROM public.district WHERE district_id = " + id;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        result.next();

        model.setId(result.getLong("district_id"));
        model.setName(result.getString("name"));
        model.setDeliveryPrice(result.getDouble("delivery_price"));

        return model;
    }

    public void update(DistrictModel model) {
        try {
            String sql = "UPDATE public.district SET name = ?, delivery_price = ? WHERE district_id = " +  model.getId();

            PreparedStatement statement;
            statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
            statement.setDouble(2, model.getDeliveryPrice());

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
            String sql = "DELETE FROM public.district WHERE district_id = " + id;
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
