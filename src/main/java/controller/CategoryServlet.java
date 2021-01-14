package controller;

import Dao.category.CategoryDAO;
import model.Category;

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

@WebServlet(name = "CategoryServlet", urlPatterns = "/category")
public class CategoryServlet extends HttpServlet {
    private CategoryDAO categoryDAO;

    public void init() {
        categoryDAO = new CategoryDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String action = request.getParameter("action");
        String search = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showFormCreateCategory(request, response);
                    break;
                case "edit":
                    showFormUpdateCategory(request, response);
                    break;
                default:
                    displayCategoryList(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
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
                    insertCategory(request, response);
                    break;
                case "edit":
                    updateCategory(request, response);
                    break;
                case "remove":
                    removeCategory(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
   }

    private void removeCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int status = 0;
        Category block = new Category(id, status);
        categoryDAO.removerCategory(block);
        HttpSession session = request.getSession();
        session.setAttribute("message_remove", "Removed successfully");
        response.sendRedirect("category");
    }

    private void insertCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");

        Category newCategory = new Category(name, desc);
        categoryDAO.insertCategory(newCategory);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/category/home.jsp");
//        dispatcher.forward(request, response);
        HttpSession session = request.getSession();
        session.setAttribute("message_add", "Added successfully");
        response.sendRedirect("category");

    }

    private void showFormCreateCategory(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/category/create.jsp");
        dispatcher.forward(request, response);
    }

    private void displayCategoryList(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String categoryName = request.getParameter("search");
        categoryName = "%" + categoryName + "%";
        List<Category> categoryList = categoryDAO.getCategoryList();
        List<Category> categories = categoryDAO.selectByCategoryName(categoryName);

        if (categoryName.equals("%null%")) {
            request.setAttribute("categoryList", categoryList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/category/home.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("categoryList", categories);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/category/home.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void showFormUpdateCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int idCategory = Integer.parseInt(request.getParameter("id"));
        Category existingCategory = categoryDAO.getCategoryById(idCategory);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/category/update.jsp");
        request.setAttribute("category", existingCategory);
        dispatcher.forward(request, response);
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        Category block = new Category(id, name, desc);
        categoryDAO.updateCategory(block);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/category/update.jsp");
//        dispatcher.forward(request, response);
        HttpSession session = request.getSession();
        session.setAttribute("message_edit", "Edited successfully");
        response.sendRedirect("category");
    }
}