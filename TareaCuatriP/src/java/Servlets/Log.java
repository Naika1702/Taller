/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "Log", urlPatterns = {"/Log"})
public class Log extends HttpServlet {
        private String Identificacion=null;
        private String Cont=null;
        String a = null;
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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
       
        Identificacion=request.getParameter("txtuser");
        Cont=request.getParameter("txtpass");
        a=request.getParameter("botonEnviar");
        Connection cn = null; // variable de conexion
    Statement st = null;  // variable de instruccion SQL
    ResultSet rs = null; // variable de registros o filas
try {
 String connectionUrl = "jdbc:sqlserver://DESKTOP-B6COF45:1433;" +
            "databaseName=Encomiendas; user=Naika; password=123123;"; 
 // connectionUrl = "jdbc:sqlserver://localhost:1433;databasename=PUBS;integratedSecurity=true";
  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
       //return(DriverManager.getConnection(connectionUrl));
          
                   cn = DriverManager.getConnection(connectionUrl);
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        
            rs = st.executeQuery("Exec Loguear '"+Identificacion+"','"+Cont+"'");
  
    while (rs.next())
    { System.out.println(rs.getString(1) +  " " + rs.getString(2));
     
   if(Identificacion.equals(rs.getString(1)) && Cont.equals(rs.getString(2))){
    
      try (PrintWriter out = response.getWriter()) {
             
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Log</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Ingresado</h1>");
            out.println("</body>");
            out.println("</html>");
            response.sendRedirect("PanelPrincipal.html");
      }
    }else{
   
       try (PrintWriter out = response.getWriter()) {
             
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Log</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Error. La contraseña y/o usuario es incorrecto</h1>");
            out.println("</body>");
            out.println("</html>");
            
      }
       
   }
     
    }
        
    try (PrintWriter out = response.getWriter()) {
             
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Log</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Error. La contraseña y/o usuario es incorrecto</h1>");
            out.println("</body>");
            out.println("</html>");
            
      }
    
       
       
    String insertar = "INSERT INTO cliente(id,nombre,telefono) VALUES ('4','Marco vargas','69857477')";
   // st.executeUpdate(insertar);
 
  String update = "update cliente set nombre = 'Carlos Torres' where id = 2 ";
  //     st.executeUpdate(update);
 String del = "delete cliente where id = 3 ";
  //     st.executeUpdate(del);
      
      
    }catch (Exception e)
    {
        System.out.println("Error: " + e.getMessage());
    }
        
        /*try (PrintWriter out = response.getWriter()) {
             TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Log</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Log at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
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
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
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
