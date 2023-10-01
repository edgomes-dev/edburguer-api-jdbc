package dao;

import db.ConnectionDb;
import model.AddressModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO {
    private Connection connection;

    public AddressDAO() { connection = ConnectionDb.getConnection();
    }

    public void create(AddressModel model) {
        try {
            String sql = "INSERT INTO public.address (name, street, number, complement, district_id) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
            statement.setString(2, model.getStreet());
            statement.setString(3, model.getNumber());
            statement.setString(4, model.getComplement());
            statement.setLong(5, model.getDistrictId());
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<AddressModel> findAll() throws Exception {
        List<AddressModel> list = new ArrayList<>();

        String sql = "SELECT * FROM public.address";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            AddressModel model = new AddressModel();
            model.setId(result.getLong("address_id"));
            model.setName(result.getString("name"));
            model.setStreet(result.getString("street"));
            model.setNumber(result.getString("number"));
            model.setComplement(result.getString("complement"));

            list.add(model);
        }

        return list;
    }

    public AddressModel findById(Long id) throws Exception {
        AddressModel model = new AddressModel();

        String sql = "SELECT * FROM public.address WHERE address_id = " + id;
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        result.next();

        model.setId(result.getLong("address_id"));
        model.setName(result.getString("name"));
        model.setStreet(result.getString("street"));
        model.setNumber(result.getString("number"));
        model.setComplement(result.getString("complement"));
        model.setDistrictId(result.getLong("district_id"));

        return model;
    }

    public void update(AddressModel model) {
        try {
            String sql = "UPDATE public.address SET name = ?, street = ?, number = ?, complement = ?, district_id = ? WHERE address_id = " + model.getId();
            PreparedStatement statement;
            statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
            statement.setString(2, model.getStreet());
            statement.setString(3, model.getNumber());
            statement.setString(4, model.getComplement());
            statement.setLong(5, model.getDistrictId());

            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        try {
            String sql = "DELETE FROM public.address WHERE address_id = " + id;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
