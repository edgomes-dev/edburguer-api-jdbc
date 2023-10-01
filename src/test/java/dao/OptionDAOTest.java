package dao;

import model.OptionModel;
import model.UsersModel;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class OptionDAOTest {
    @Test
    public void create() {
        OptionDAO dao = new OptionDAO();
        OptionModel model = new OptionModel();

        model.setName("teste");
        model.setMaximumAmount(1);
        model.setRequired(true);
        model.setProductCategoryId(1L);
        model.setIngredients(Arrays.asList(1L, 2L));

        dao.create(model);
    }

    @Test
    public void findAll() {
        OptionDAO dao = new OptionDAO();
        try {
            List<OptionModel> list = dao.findAll();

            for (OptionModel model : list) {
                System.out.println(model);
                System.out.println("----------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void findById() {
        OptionDAO dao = new OptionDAO();

        try {
            OptionModel model = dao.findById(1L);
            System.out.println(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        try {
            OptionDAO dao = new OptionDAO();
            OptionModel model = dao.findById(4L);

            model.setName("New name");
            dao.update(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        try {
            OptionDAO dao = new OptionDAO();
            dao.delete(4L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
