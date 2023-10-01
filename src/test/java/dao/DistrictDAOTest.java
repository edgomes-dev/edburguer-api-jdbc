package dao;

import model.DistrictModel;
import model.UsersModel;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DistrictDAOTest {
    @Test
    public void create() {
        DistrictDAO dao = new DistrictDAO();
        DistrictModel model = new DistrictModel();

        model.setName("teste");
        model.setDeliveryPrice(3.0);

        dao.create(model);
    }

    @Test
    public void findAll() {
        DistrictDAO dao = new DistrictDAO();
        try {
            List<DistrictModel> list = dao.findAll();

            for (DistrictModel model : list) {
                System.out.println(model);
                System.out.println("----------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void findById() {
        DistrictDAO dao = new DistrictDAO();

        try {
            DistrictModel model = dao.findById(1L);
            System.out.println(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        try {
            DistrictDAO dao = new DistrictDAO();
            DistrictModel model = dao.findById(4L);

            model.setName("New name");
            dao.update(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        try {
            DistrictDAO dao = new DistrictDAO();
            dao.delete(4L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
