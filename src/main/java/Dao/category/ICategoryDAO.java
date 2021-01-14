package Dao.category;

import model.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICategoryDAO {

    public void insertCategory(Category category) throws Exception;

    public List<Category> selectByCategoryName(String categoryName) throws SQLException;

    public List<Category> getCategoryList() throws SQLException;

    public boolean updateCategory(Category category)throws SQLException;

    public boolean removerCategory(Category category)throws SQLException;
}
