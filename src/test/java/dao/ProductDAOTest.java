package dao;

import model.ProductModel;
import model.UsersModel;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductDAOTest {
    @Test
    public void create() {
        ProductDAO dao = new ProductDAO();
        ProductModel model = new ProductModel();

        model.setName("teste");
        model.setDescription(null);
        model.setPrice(14.5);
        model.setProductCategoryId(1L);

        dao.create(model);
    }

    @Test
    public void findAll() {
        ProductDAO dao = new ProductDAO();
        try {
            List<ProductModel> list = dao.findAll();

            for (ProductModel model : list) {
                System.out.println(model);
                System.out.println("----------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void findById() {
        ProductDAO dao = new ProductDAO();

        try {
            ProductModel model = dao.findById(1L);
            System.out.println(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        try {
            ProductDAO dao = new ProductDAO();
            ProductModel model = dao.findById(4L);

            model.setName("New name");
            dao.update(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        try {
            ProductDAO dao = new ProductDAO();
            dao.delete(4L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
