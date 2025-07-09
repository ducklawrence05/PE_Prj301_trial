/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package pe.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import pe.model.ElectronicDao;
import pe.model.ElectronicDto;

/**
 *
 * @author Admin
 */
@WebServlet(name="SearchController", urlPatterns={"/SearchController"})
public class SearchController extends HttpServlet {
    private final String LOGIN_PAGE = "login.jsp";
    private final String SEARCH_PAGE = "search.jsp";
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String url = SEARCH_PAGE;
        try {
            HttpSession session = request.getSession(false);
            if(session == null || session.getAttribute("loginUser") == null) {
                request.setAttribute("error", "Please login");
                url = LOGIN_PAGE;
            } else {
                String key = request.getParameter("key");
                ElectronicDao dao = new ElectronicDao();
                if(!isNullOrEmptyString(key)) {
                    List<ElectronicDto> list = dao.getByName(key);
                    if (list.isEmpty()) {
                        request.setAttribute("error", "No data matching the search criteria found !");
                    } else {
                        request.setAttribute("list", list);
                    }
                }
                request.setAttribute("key", key);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error at search controller.");
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    } 

    private boolean isNullOrEmptyString(String str) {
        return str == null || str.isEmpty();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
