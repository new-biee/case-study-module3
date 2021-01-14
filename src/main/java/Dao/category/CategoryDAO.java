package Dao.category;

import helper.ConnectionHelper;
import helper.Query;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO {

    private Connection connection;
    private PreparedStatement statement;
    private Category category;

    public CategoryDAO() {
        connection = ConnectionHelper.getConnection();
        System.out.println("Ket noi thanh cong");
    }

    @Override
    public void insertCategory(Category category) throws SQLException {
        statement = connection.prepareStatement(Query.INSERT_CATEGORY_SQL);
        statement.setString(1, category.getCategoryName());
        statement.setString(2, category.getDesc());
        statement.executeUpdate();
    }

    @Override
    public List<Category> getCategoryList() throws SQLException {
        List<Category> categoryList = new ArrayList<>();
        statement = connection.prepareStatement(Query.SELECT_ALL_CATEGORIES);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String categoryName = rs.getString("category_name");
            String categoryDesc = rs.getString("category_desc");
            categoryList.add(new Category(id, categoryName, categoryDesc));
        }
        return categoryList;
    }

    @Override
    public boolean updateCategory(Category category) throws SQLException {
        boolean rowUpdated;
        statement = connection.prepareStatement(Query.UPDATE_CATEGORY_SQL);
        statement.setString(1, category.getCategoryName());
        statement.setString(2, category.getDesc());
        statement.setInt(3, category.getCategoryId());
        rowUpdated = statement.executeUpdate() > 0;
        return rowUpdated;
    }

    @Override
    public boolean removerCategory(Category category) throws SQLException {
        boolean rowRemove;
        statement = connection.prepareStatement(Query.REMOVE_CATEGORY_SQL);
        statement.setInt(1, category.getStatus());
        statement.setInt(2, category.getCategoryId());
        rowRemove = statement.executeUpdate() > 0;
        return rowRemove;
    }

    public Category getCategoryById(int id) throws SQLException {
        statement = connection.prepareStatement(Query.SElECT_CATEGORY_BY_ID);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        Category category = new Category();
        if (rs.next()) {
            category.setCategoryId(rs.getInt("id"));
            category.setCategoryName(rs.getString("category_name"));
            category.setDesc(rs.getString("category_desc"));
            category.setStatus(rs.getInt("status"));
        }
        return category;
    }

    @Override
    public List<Category> selectByCategoryName(String categoryName) throws SQLException {
        List<Category> categories = new ArrayList<>();
        statement = connection.prepareStatement(Query.SELECT_CATEGORY_BY_NAME);
        statement.setString(1, categoryName);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("category_name");
            String categoryDesc = rs.getString("category_desc");
            categories.add(new Category(id, name, categoryDesc));
        }
        return categories;
    }


}
