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
import java.util.ArrayList;
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
public class Control extends HttpServlet {

    private Management mng;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        try ( PrintWriter out = response.getWriter()) {
            //En la siguiente línea se obtiene la ruta absoluta del proyecto, 
            //Se concatena con la carpeta de "resources/files/" para establecer la ubicación de los archivos.
            String path = getServletContext().getRealPath("/")+"resources/files/";
            
            try {
                String option = request.getParameter("option");
                mng = new Management();
                mng.loadDepartments(path, "departments.json");
                switch( option ){
                    case "0" : out.println( new Gson().toJson( mng.getDepartments()));
                    break;
                    
                    case "1" : 
                        mng.loadTowns( path, "towns.json" );
                        String code = request.getParameter("code");
                        out.println( new Gson().toJson( mng.getTownsDpto( code )));
                    break;
                    
                    case "2" :
                        String codeTown = request.getParameter("code");
                        mng.loadTowns( path, "towns.json" );
                        
                        //Se obtiene el municipio con base en el código.
                        //En el atributo *department* contiene es el código del departamento al que pertencece
                        Town town = mng.findTown( codeTown );
                        
                        //Se usa un objeto de Town para asignar los datos del Municipio que se encontró
                        //pero con el nombre del departamento, en vez del código.
                        Town aux = new Town( town.getCode( ), mng.getDepartment(codeTown).getName(), town.getName() );
                        
                        out.println( new Gson().toJson( aux ) );
                        
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
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
