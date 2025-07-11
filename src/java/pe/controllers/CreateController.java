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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import pe.model.ElectronicDao;
import pe.model.ElectronicDto;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String CREATE_PAGE = "create.jsp";
    private final String SEARCH_CONTROLLER = "SearchController";

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
        String url = CREATE_PAGE;
        String regex = "E-\\d{3}";
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
                String dateTestRaw = request.getParameter("dateTest");
                String timeTestRaw = request.getParameter("timeTest");
                String dateTimeTestRaw = request.getParameter("dateTimeTest");

                boolean hasError = handleEmpty(request, id, "id");
                if (!isNullOrEmptyString(id) && !id.matches(regex)) {
                    request.setAttribute("idError", "invalid id format.");
                    hasError = true;
                }

                // check dup
                if (!hasError) {
                    ElectronicDto item = dao.getById(id);
                    if (item != null) {
                        request.setAttribute("idError", "id is existed.");
                        hasError = true;
                    }
                }

                hasError = hasError 
                        | handleEmpty(request, name, "name")
                        | handleEmpty(request, description, "description")
                        | handleEmpty(request, priceRaw, "price")
                        | handleEmpty(request, quantityRaw, "quantity")
                        | handleEmpty(request, statusRaw, "status")
                        | handleEmpty(request, dateTestRaw, "date")
                        | handleEmpty(request, timeTestRaw, "time")
                        | handleEmpty(request, dateTimeTestRaw, "dateTime");

                // handle float
                float price = 0;
                if (!isNullOrEmptyString(priceRaw)) {
                    try {
                        price = Float.parseFloat(priceRaw);
                        if (price <= 0) {
                            request.setAttribute("priceError", "price must be greater than 0.");
                            hasError = true;
                        } else if (Float.isInfinite(price) && price > 0) {
                            request.setAttribute("priceError", "price exceeds the maximum float value.");
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
                        request.setAttribute("quantityError", "quantity must be a number within the allowed range of an int.");
                        hasError = true;
                    }
                }

                // handle boolean
                boolean status = false;
                if (!isNullOrEmptyString(statusRaw)) {
                    status = statusRaw.equals("1");
                }

                // handle date
                LocalDate dateTest = null;
                if (!isNullOrEmptyString(dateTestRaw)) {
                    try {
                        dateTest = LocalDate.parse(dateTestRaw);

                        // tạo 1 ngày cụ thể để check sau ngày đó (yyyy-MM-dd)
                        // LocalDate minDate = LocalDate.of(2025, 7, 10);
                        // hoặc dùng hiện tại
                        LocalDate now = LocalDate.now();

                        // dùng cái hàm isBefore, isEqual, isAfter để check (nếu cần)
                        if (dateTest.isBefore(now)) {
                            request.setAttribute("dateError", "Date must equal or after now");
                            hasError = true;
                        }
                    } catch (DateTimeParseException e) {
                        request.setAttribute("dateError", "Invalid date format.");
                        hasError = true;
                    }
                }

                // handle time
                LocalTime timeTest = null;
                if (!isNullOrEmptyString(timeTestRaw)) {
                    try {
                        timeTest = LocalTime.parse(timeTestRaw);

                        // tạo 1 time cụ thể để check sau time đó (hh-mm-ss) or (hh-mm)
                        // có overload khác nhau
                        // LocalTime minTime = LocalTime.of(22, 0);
                        // hoặc dùng hiện tại
                        LocalTime now = LocalTime.now();

                        // dùng cái hàm isBefore, isEqual, isAfter để check (nếu cần)
                        if (timeTest.isBefore(now)) {
                            request.setAttribute("timeError", "Time must equal or after now");
                            hasError = true;
                        }
                    } catch (DateTimeParseException e) {
                        request.setAttribute("timeError", "Invalid time format.");
                        hasError = true;
                    }
                }

                // handle date time
                LocalDateTime dateTimeTest = null;
                if (!isNullOrEmptyString(dateTimeTestRaw)) {
                    try {
                        dateTimeTest = LocalDateTime.parse(dateTimeTestRaw);

                        // tự check overload
                        // LocalDateTime minDateTime = LocalDateTime.of();
                        // hoặc dùng hiện tại
                        LocalDateTime now = LocalDateTime.now();

                        // dùng cái hàm isBefore, isEqual, isAfter để check (nếu cần)
                        if (dateTimeTest.isBefore(now)) {
                            request.setAttribute("dateTimeError", "Date time must equal or after now");
                            hasError = true;
                        }
                    } catch (DateTimeParseException e) {
                        request.setAttribute("dateTimeError", "Invalid date time format.");
                        hasError = true;
                    }
                }

                if (!hasError) {
                    boolean check = dao.create(id, name, description,
                            price, quantity, status,
                            dateTest, timeTest, dateTimeTest);
                    if (!check) {
                        request.setAttribute("error", "Create failed.");
                    } else {
                        request.setAttribute("msg", "Create success.");
                        url = SEARCH_CONTROLLER;
                    }
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error at create controller.");
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
