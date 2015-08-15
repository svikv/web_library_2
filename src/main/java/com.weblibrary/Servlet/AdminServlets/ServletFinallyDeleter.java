package com.weblibrary.Servlet.AdminServlets;

@WebServlet("/delete")
public class ServletFinallyDeleter extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");

        DBManager dbManager = (DBManager) getServletContext().getAttribute("DBManager");
        BookManager bookManager = new BookManager(dbManager);
        try{
            bookManager.deleteBook(isbn);
        } catch (SQLException e){
            e.printStackTrace();
            String error = "Error deleting book!";
            request.setAttribute("error", error);
            request.setAttribute("forwardTo", "admin/admin.html");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin.html");
        requestDispatcher.forward(request, response);
    }
}
