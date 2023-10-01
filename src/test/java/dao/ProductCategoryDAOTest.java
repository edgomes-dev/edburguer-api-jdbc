package dao;

import model.ProductCategoryModel;
import model.UsersModel;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductCategoryDAOTest {
    @Test
    public void create() {
        ProductCategoryDAO dao = new ProductCategoryDAO();
        ProductCategoryModel model = new ProductCategoryModel();

        model.setName("teste");

        dao.create(model);
    }

    @Test
    public void findAll() {
        ProductCategoryDAO dao = new ProductCategoryDAO();
        try {
            List<ProductCategoryModel> list = dao.findAll();

            for (ProductCategoryModel model : list) {
                System.out.println(model);
                System.out.println("----------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void findById() {
        ProductCategoryDAO dao = new ProductCategoryDAO();

        try {
            ProductCategoryModel model = dao.findById(1L);
            System.out.println(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        try {
            ProductCategoryDAO dao = new ProductCategoryDAO();
            ProductCategoryModel model = dao.findById(4L);

            model.setName("New name");
            dao.update(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        try {
            ProductCategoryDAO dao = new ProductCategoryDAO();
            dao.delete(4L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
