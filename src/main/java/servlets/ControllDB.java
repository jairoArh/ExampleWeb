/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import edu.uptc.classroom.model.Management;
import edu.uptc.classroom.model.Town;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jairoarmando
 */
public class ControllDB extends HttpServlet {

    private Management mng;

    public ControllDB() {
        try {
            mng = new Management();
            mng.loadDepartmentsDB();
            mng.loadTownsDB(); 
        } catch (SQLException ex) {
            Logger.getLogger(ControllDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String option = request.getParameter("option");
            switch (option) {
                case "0":
                    out.println(new Gson().toJson(mng.getDepartments()));
                    break;

                case "1":
                    String code = request.getParameter("code");
                    out.println(new Gson().toJson(mng.getTownsDpto(code)));
                    break;

                case "2":
                    String codeTown = request.getParameter("code");
                    Town town = mng.findTown(codeTown);
                    Town aux = new Town(town.getCode(), mng.findDepartment(town.getDepartment()).getName(), town.getName());
                    out.println(new Gson().toJson(aux));
                    break;

                case "3":
                    String codeFind = request.getParameter("code");
                    Town townFind = mng.findTown(codeFind);
                    if (townFind != null) {
                        out.println(new Gson().toJson(townFind));
                    } else {
                        out.println(new Gson().toJson(false));
                    }
                    break;
                    
                case "4":
                    String codeDel = request.getParameter("code");
                    if( mng.deleteTownDB( codeDel ) ){
                        out.println(new Gson().toJson(true));
                    }else{
                        out.println(new Gson().toJson(false));
                    }
                    break;    
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
        //processRequest(request, response);
        response.setContentType("application/json;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String codeAdd = request.getParameter("code");
            String dpto = request.getParameter("dpto");
            String name = request.getParameter("name");

            if (mng.addTown(codeAdd, dpto, name)) {
                out.println(new Gson().toJson(mng.getTownsDpto(dpto)));
            } else {
                out.println(new Gson().toJson(false));
            }
        }
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
