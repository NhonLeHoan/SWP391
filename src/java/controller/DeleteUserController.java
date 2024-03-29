/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import dto.Users;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kyrov
 */
public class DeleteUserController extends HttpServlet {

    private static final String SUCCESS = "SearchUserController";
    private static final String ERROR = "SearchUserController";
    private static final String LOGOUT = "LogoutController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean check = false;
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            DAO dao = new DAO();

            HttpSession session = request.getSession();
            Users loggedInUser = (Users) session.getAttribute("LOGIN_USER");

            if (!loggedInUser.getUserID().equals(userID)) {
                dao.deleteUser(userID);
                url = SUCCESS;
                check = true;
            } else {
                dao.deleteUser(userID);
                url = LOGOUT;
                check = false;
            }
        } catch (Exception e) {
            log("Error at DeleteController" + e.toString());
        } finally {
            if (check) {
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
