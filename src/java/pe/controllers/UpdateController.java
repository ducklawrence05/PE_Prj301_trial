/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.controllers;

import java.io.IOException;
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
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String UPDATE_PAGE = "update.jsp";
    private final String SEARCH_PAGE = "search.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = UPDATE_PAGE;
        String regex = "E-[a-zA-Z0-9]{3}";
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("loginUser") == null) {
                request.setAttribute("error", "Please login");
                url = LOGIN_PAGE;
            } else {
                ElectronicDao dao = new ElectronicDao();

                String id = request.getParameter("id");
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String priceRaw = request.getParameter("price");
                String quantityRaw = request.getParameter("quantity");
                String statusRaw = request.getParameter("status");

                boolean hasError = handleEmpty(request, id, "id");
                if (!isNullOrEmptyString(id) && !id.matches(regex)) {
                    request.setAttribute("idError", "invalid id format.");
                    hasError = true;
                }

                // check exist
                if (!hasError) {
                    ElectronicDto item = dao.getById(id);
                    if (item == null) {
                        request.setAttribute("idError", "id not found.");
                        hasError = true;
                    }
                }

                hasError = hasError 
                        || handleEmpty(request, name, "name")
                        || handleEmpty(request, description, "description")
                        || handleEmpty(request, priceRaw, "price")
                        || handleEmpty(request, quantityRaw, "quantity")
                        || handleEmpty(request, statusRaw, "status");
                
                // handle float
                float price = 0;
                if (!isNullOrEmptyString(priceRaw)) {
                    try {
                        price = Float.parseFloat(priceRaw);
                        if (price <= 0) {
                            request.setAttribute("priceError", "price must be greater than 0.");
                            hasError = true;
                        }
                    } catch (NumberFormatException ex) {
                        request.setAttribute("priceError", "price must be a number.");
                        hasError = true;
                    }
                }

                //handle int
                int quantity = 0;
                if (!isNullOrEmptyString(quantityRaw)) {
                    try {
                        quantity = Integer.parseInt(quantityRaw);
                        if (quantity <= 0) {
                            request.setAttribute("quantityError", "quantity must be greater than 0.");
                            hasError = true;
                        }
                    } catch (NumberFormatException ex) {
                        request.setAttribute("quantityError", "quantity must be a number.");
                        hasError = true;
                    }
                }

                // handle boolean
                boolean status = false;
                if (!isNullOrEmptyString(statusRaw)) {
                    status = statusRaw.equals("1");
                }

                if (!hasError) {
                    boolean check = dao.update(id, name, description, price, quantity, status);
                    if (!check) {
                        request.setAttribute("error", "Update failed.");
                    } else {
                        request.setAttribute("msg", "Update success.");
                        request.setAttribute("list", dao.getAll());
                        url = SEARCH_PAGE;
                    }
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error at update controller.");
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private boolean isNullOrEmptyString(String str) {
        return str == null || str.isEmpty();
    }

    private boolean handleEmpty(HttpServletRequest req, String str,
            String nameVal) {
        if (isNullOrEmptyString(str)) {
            req.setAttribute(nameVal + "Error", nameVal + " cannot be empty.");
            return true;
        }
        return false;
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
