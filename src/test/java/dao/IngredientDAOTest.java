package dao;

import model.IngredientModel;
import model.UsersModel;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IngredientDAOTest {
    @Test
    public void create() {
        IngredientDAO dao = new IngredientDAO();
        IngredientModel model = new IngredientModel();

        model.setName("teste");
        model.setDescription("teste delicioso");
        model.setPrice(14.0);
        model.setRepetitions(1);

        dao.create(model);
    }

    @Test
    public void findAll() {
        IngredientDAO dao = new IngredientDAO();
        try {
            List<IngredientModel> list = dao.findAll();

            for (IngredientModel model : list) {
                System.out.println(model);
                System.out.println("----------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void findById() {
        IngredientDAO dao = new IngredientDAO();

        try {
            IngredientModel model = dao.findById(1L);
            System.out.println(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        try {
            IngredientDAO dao = new IngredientDAO();
            IngredientModel model = dao.findById(4L);

            model.setName("New name");
            dao.update(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        try {
            IngredientDAO dao = new IngredientDAO();
            dao.delete(4L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
