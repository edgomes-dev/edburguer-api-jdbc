package dao;

import model.UsersModel;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserDAOTest {
    @Test
    public void create() {
        UsersDAO dao = new UsersDAO();
        UsersModel model = new UsersModel();
        
        model.setName("teste");
        model.setEmail("teste@gmail.com");
        model.setPhone("81988884444");
        model.setPassword("teste123");

        dao.create(model);
    }

    @Test
    public void findAll() {
        UsersDAO dao = new UsersDAO();
        try {
            List<UsersModel> list = dao.findAll();

            for (UsersModel model : list) {
                System.out.println(model);
                System.out.println("----------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void findById() {
        UsersDAO dao = new UsersDAO();

        try {
            UsersModel model = dao.findById(1L);
            System.out.println(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        try {
            UsersDAO dao = new UsersDAO();
            UsersModel model = dao.findById(4L);

            model.setName("New name");
            dao.update(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        try {
            UsersDAO dao = new UsersDAO();
            dao.delete(4L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
