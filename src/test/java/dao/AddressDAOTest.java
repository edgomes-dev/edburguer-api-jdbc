package dao;

import model.AddressModel;
import model.UsersModel;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddressDAOTest {
    @Test
    public void create() {
        AddressDAO dao = new AddressDAO();
        AddressModel model = new AddressModel();

        model.setName("trabalho");
        model.setStreet("Rua Teste");
        model.setNumber("314");
        model.setComplement("Bloco A");
        model.setDistrictId(1L);

        dao.create(model);
    }

    @Test
    public void findAll() {
        AddressDAO dao = new AddressDAO();
        try {
            List<AddressModel> list = dao.findAll();

            for (AddressModel model : list) {
                System.out.println(model);
                System.out.println("----------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void findById() {
        AddressDAO dao = new AddressDAO();

        try {
            AddressModel model = dao.findById(1L);
            System.out.println(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        try {
            AddressDAO dao = new AddressDAO();
            AddressModel model = dao.findById(6L);

            model.setName("New name");
            dao.update(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        try {
            AddressDAO dao = new AddressDAO();
            dao.delete(6L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
