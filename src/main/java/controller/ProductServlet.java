package controller;

import Dao.category.CategoryDAO;
import Dao.product.ProductDAO;
import helper.Query;
import model.Category;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    public static final int RECORD_PER_PAGE_DISPLAY = 10;
    public static final int START_PAGE_DISPLAY = 1;

    private ProductDAO productDAO = new ProductDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        } else if (action.equals("filter")) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showFormCreateProduct(request, response);
                    break;
                case "edit":
                    showFormUpdateProduct(request, response);
                    break;
                default:
                    displayProductList(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertProduct(request, response);
                    break;
                case "edit":
                    updateProduct(request, response);
                    break;
                case "remove":
                    removeProduct(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayProductList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String productName = request.getParameter("search");
        String categoryId = request.getParameter("categoryId");
        List<Category> categoryList = categoryDAO.getCategoryList();
        productName = "%" + productName + "%";
        List<Product> productList = productDAO.getProductList();
        String addQuery = categoryId == null ? "" : " and category_id = " + categoryId;
        List<Product> products = productDAO.selectByProductName(productName, Query.SELECT_PRODUCT_BY_NAME + addQuery);

        if (categoryId != null) {
            int category_id = Integer.parseInt(categoryId);
            List<Product> listProduct = productDAO.getProductByCategory(category_id);
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("productList", listProduct);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/admin/product/home.jsp");
            requestDispatcher.forward(request, response);
        } else {
            if (productName.equals("%null%")) {
                request.setAttribute("categoryList", categoryList);
                request.setAttribute("productList", productList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/product/home.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("categoryList", categoryList);
                request.setAttribute("productList", products);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/product/home.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    //region CREATE
    private void showFormCreateProduct(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException, SQLException {
        List<Category> categoryList = categoryDAO.getCategoryList();
        request.setAttribute("categoryList", categoryList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/admin/product/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Category> categoryList = categoryDAO.getCategoryList();
        String productCode = request.getParameter("product_code");
        String productName = request.getParameter("product_name");
        String productDesc = request.getParameter("product_desc");
        double price = Double.parseDouble(request.getParameter("product_price"));
        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        if (productDAO.getProductByProductCode(productCode) != null) {
            request.setAttribute("ID_EXIST", "Please input new Product Code");
            request.setAttribute("categoryList", categoryList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/admin/product/create.jsp");
            requestDispatcher.forward(request, response);
        } else{
            Product newProduct = new Product(productCode, productName, productDesc, categoryId, price);
            productDAO.insertProduct(newProduct);
            request.setAttribute("categoryList", categoryList);
            HttpSession session = request.getSession();
            session.setAttribute("message_add", "New item is successfully added");
            response.sendRedirect("/products");
        }
    }
    //endregion

    //region UPDATE
    private void showFormUpdateProduct(HttpServletRequest request, HttpServletResponse response) throws
            SQLException, ServletException, IOException {
        String productCode = request.getParameter("product_code");
        Product existingProduct = productDAO.getProductByProductCode(productCode);
        List<Category> categoryList = categoryDAO.getCategoryList();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/admin/product/update.jsp");
        request.setAttribute("product", existingProduct);
        request.setAttribute("categoryList", categoryList);
        requestDispatcher.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws
            SQLException, IOException {
        String productName = request.getParameter("product_name");
        String productDesc = request.getParameter("product_desc");
        double productPrice = Double.parseDouble(request.getParameter("product_price"));
        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        Category category = categoryDAO.getCategoryById(categoryId);
        String productCode = request.getParameter("product_code");
        Product product = new Product(productCode, productName, productDesc, category, productPrice);
        productDAO.updateProduct(product);
        HttpSession session = request.getSession();
        session.setAttribute("message_edit", "Edited successfully");
        response.sendRedirect("products");
    }
    //endregion

    //region REMOVE
    private void removeProduct(HttpServletRequest request, HttpServletResponse response) throws
            SQLException, IOException {
        String productCode = request.getParameter("product_code");
        int status = 0;
        Product product = new Product(productCode, status);
        productDAO.removeProduct(product);
        HttpSession session = request.getSession();
        session.setAttribute("message_remove", "Removed successfully");
        response.sendRedirect("products");
    }
    //endregion
}