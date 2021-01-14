package Dao.product;

import Dao.category.CategoryDAO;
import com.sun.org.apache.xerces.internal.xs.XSTerm;
import helper.ConnectionHelper;
import helper.Query;
import model.Category;
import model.Product;
import org.apache.taglibs.standard.tag.common.xml.ParseSupport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {

    private Connection connection;
    private PreparedStatement statement;
    private CategoryDAO categoryDAO;

    public ProductDAO() {
        connection = ConnectionHelper.getConnection();
        System.out.println("Ket noi thanh cong");
    }

    @Override
    public void insertProduct(Product product) throws Exception {
        statement = connection.prepareStatement(Query.INSERT_PRODUCT_SQL);
        statement.setString(1, product.getProductCode());
        statement.setInt(2, product.getCategoryId());
        statement.setString(3, product.getProductName());
        statement.setString(4, product.getDesc());
        statement.setDouble(5, product.getPrice());
        statement.executeUpdate();
    }

    @Override
    public List<Product> getProductList() throws SQLException {
        List<Product> productList = new ArrayList<>();
        categoryDAO = new CategoryDAO();
        Category category;
        statement = connection.prepareStatement(Query.SELECT_ALL_PRODUCT);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String productCode = resultSet.getString("product_code");
            int categoryId = resultSet.getInt("category_id");
            category = categoryDAO.getCategoryById(categoryId);
            String productName = resultSet.getString("product_name");
            String desc = resultSet.getString("product_desc");
            int status = resultSet.getInt("status");
            double price = resultSet.getDouble("price");
            productList.add(new Product(productCode, productName, desc, status, category, price));
        }
        return productList;
    }


    @Override
    public List<Product> selectByProductName(String productName, String query) throws SQLException {
        List<Product> products = new ArrayList<>();
        categoryDAO = new CategoryDAO();
        Category category;
        statement = connection.prepareStatement(query);
        statement.setString(1, productName);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String productCode = resultSet.getString("product_code");
            int categoryId = resultSet.getInt("category_id");
            category = categoryDAO.getCategoryById(categoryId);
            String product_name = resultSet.getString("product_name");
            String desc = resultSet.getString("product_desc");
            int status = resultSet.getInt("status");
            double price = resultSet.getDouble("price");
            products.add(new Product(productCode, product_name, desc, status, category, price));
        }
        return products;
    }

    public List<Product> getProductByCategory(int categoryId) throws SQLException {
        List<Product> productList = new ArrayList<>();
        categoryDAO = new CategoryDAO();
        Category category;
        statement = connection.prepareStatement(Query.SELECT_PRODUCT_BY_CATEGORY);
        statement.setInt(1, categoryId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String productCode = resultSet.getString("product_code");
            categoryId = resultSet.getInt("category_id");
            category = categoryDAO.getCategoryById(categoryId);
            String product_name = resultSet.getString("product_name");
            String desc = resultSet.getString("product_desc");
            int status = resultSet.getInt("status");
            double price = resultSet.getDouble("price");
            productList.add(new Product(productCode, product_name, desc, status, category, price));
        }
        return productList;
    }

    @Override
    public Product getProductByProductCode(String productCode) throws SQLException {
        categoryDAO = new CategoryDAO();
        statement = connection.prepareStatement(Query.SELECT_PRODUCT_BY_CODE);
        statement.setString(1, productCode);
        ResultSet rs = statement.executeQuery();
        Product product = null;
        if (rs.next()) {
            product = new Product();
            product.setProductCode(rs.getString("product_code"));
            product.setProductName(rs.getString("product_name"));
            product.setDesc(rs.getString("product_desc"));
            product.setPrice(rs.getDouble("price"));
            product.setCategory(categoryDAO.getCategoryById(rs.getInt("category_id")));
        }
        return product;
    }


    @Override
    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdate;
        statement = connection.prepareStatement(Query.UPDATE_PRODUCT_SQL);
        statement.setString(1, product.getProductName());
        statement.setString(2, product.getDesc());
        statement.setDouble(3, product.getPrice());
        statement.setInt(4, product.getCategory().getCategoryId());
        statement.setString(5, product.getProductCode());
        rowUpdate = statement.executeUpdate() > 0;
        return rowUpdate;
    }

    @Override
    public boolean removeProduct(Product product) throws SQLException {
        boolean rowRemove;
        statement = connection.prepareStatement(Query.REMOVE_PRODUCT_SQL);
        statement.setInt(1, product.getStatus());
        statement.setString(2, product.getProductCode());
        rowRemove = statement.executeUpdate() > 0;
        return rowRemove;
    }
}
