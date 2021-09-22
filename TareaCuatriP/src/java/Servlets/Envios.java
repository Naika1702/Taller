/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.BodyPart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 *
 * @author Usuario
 */
@WebServlet(name = "Envios", urlPatterns = {"/Envios"})
public class Envios extends HttpServlet {

String IdentificacionE = null;
String NombreE = null;
String CelE = null;
String CorreoE = null;
String DireccionE = null;
String IdentificacionR = null;
String NombreR = null;
String CelR = null;
String CorreoR = null;
String DireccionR = null;
Integer IdP = null;
String NombreP = null;
Float PesoP = null;
Float DistanciaP = null;
Integer CantidadP = null;
String Des = null;
String TipoDeEnvioE = null;
String EstadoE = null;

String Modo = null;
    
    
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
            throws ServletException, IOException, SQLException, MessagingException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
       
        IdentificacionE = request.getParameter("Identificacion Emisor");
        NombreE =  request.getParameter("Nombre Emisor");
        CelE =  request.getParameter("Telefono Emisor");
        CorreoE = request.getParameter("Correo Emisor");
        DireccionE = request.getParameter("Direccion Emisor");
        IdentificacionR = request.getParameter("Identificacion Receptor");
        NombreR = request.getParameter("Nombre Receptor");
        CelR = request.getParameter("Telefono Receptor");
        CorreoR = request.getParameter("Correo Receptor");
        DireccionR = request.getParameter("Direccion Receptor");
        IdP = Integer.valueOf(request.getParameter("IdP"));
        NombreP = request.getParameter("Nombre Paquete");
        PesoP = Float.valueOf(request.getParameter("Peso"));
        DistanciaP = Float.valueOf(request.getParameter("Distancia"));
        CantidadP = Integer.valueOf(request.getParameter("Cantidad"));
        Des = request.getParameter("Des");
        TipoDeEnvioE = request.getParameter("TipoEnvio");
        EstadoE = request.getParameter("EstadoEnvio");
        Modo = request.getParameter("Boton");
        
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
      
           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
           System.out.println("yyyy/MM/dd-> "+dtf.format(LocalDateTime.now()));
        
            rs = st.executeQuery("Exec Enviar '"+IdentificacionE+"','"+NombreE+"','"+CelE+"','"+CorreoE+"','"+DireccionE+"','"+IdentificacionR+"','"+NombreR+"','"+CelR+"','"+CorreoR+"','"+DireccionR+"','"+IdP+"','"+NombreP+"','"+PesoP+"','"+DistanciaP+"','"+CantidadP+"','"+Des+"','"+TipoDeEnvioE+"','"+EstadoE+"','"+dtf.format(LocalDateTime.now())+"','"+Modo+"'");
  
    /*while (rs.next())
    { System.out.println(rs.getString(1) +  " " + rs.getString(2));
    }*/
       
      
      
    }catch (Exception e)
    {
        System.out.println("Error: " + e.getMessage());
    }
        

            String connectionUrl = "jdbc:sqlserver://DESKTOP-B6COF45:1433;" +
            "databaseName=Encomiendas; user=Naika; password=123123;";  
            // connectionUrl = "jdbc:sqlserver://localhost:1433;databasename=PUBS;integratedSecurity=true";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             //return(DriverManager.getConnection(connectionUrl));
          
                   cn = DriverManager.getConnection(connectionUrl);
                    st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
      
            rs = st.executeQuery("Select PrecioEnvioD from Envios where Id="+IdP+"");
  
            while (rs.next())
    { System.out.println(rs.getString(1));
     
   try{
        
        PDDocument documento = new PDDocument();
        PDPage pagina = new PDPage(PDRectangle.A4);
        documento.addPage(pagina);
        PDPageContentStream contenido = new PDPageContentStream(documento,pagina);
        
        contenido.beginText();
        contenido.setFont(PDType1Font.HELVETICA,14);
        contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight()-52);
        contenido.showText("Factura Electronica: ");
        contenido.endText();
        contenido.beginText();
        contenido.setFont(PDType1Font.HELVETICA,12);
        contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight()-52*2);
        contenido.showText("Datos Emisor: "+IdentificacionE+" | "+NombreE+" | "+CelE+" | "+CorreoE+" | "+DireccionE);
        contenido.endText();
        contenido.beginText();
        contenido.setFont(PDType1Font.HELVETICA,12);
        contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight()-52*3);
        contenido.showText("Datos Receptor: "+IdentificacionR+" | "+NombreR+" | "+CelR+" | "+CorreoR+" | "+DireccionR);
        contenido.endText();
        contenido.beginText();
        contenido.setFont(PDType1Font.HELVETICA,12);
        contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight()-52*4);
        contenido.showText("Datos Paquete: "+IdP+" | "+NombreP+" | "+CantidadP+" | "+PesoP+" | "+DistanciaP);
        contenido.endText();
        contenido.close();
        
        
        documento.save("C:\\Users\\Usuario\\Desktop\\R8_ControlEncomiendas\\Prueba.pdf");
        
        }catch(Exception x){
        System.out.println("Error:"+x.getMessage().toString());
        }
        
        try
        {
            // Propiedades de la conexi�n
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "emeyene.srl");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
           

            
            Session session = Session.getDefaultInstance(props,null);

            
           // Se compone la parte del texto
          //  BodyPart texto = new MimeBodyPart();
            // Se compone el adjunto con la imagen
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(
                new DataHandler(new FileDataSource("C:\\Users\\Usuario\\Desktop\\R8_ControlEncomiendas\\Prueba.pdf")));
            adjunto.setFileName("C:\\Users\\Usuario\\Desktop\\R8_ControlEncomiendas\\Prueba.pdf");
              // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            //multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);       
            
           
            
            
            
            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(CorreoE));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(CorreoE));
            message.setSubject("MultiEntregasCR Factura Electronica");
            message.setText(
               "Factura:");


            BodyPart texto = new MimeBodyPart();
            texto.setText("Adjunto encontrará los detalles de su envio.:");
            
            multiParte.addBodyPart(texto); 
            
            message.setContent(multiParte);
                  
            
            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("emeyene.srl@gmail.com", "mana@mina");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
            response.sendRedirect("Envios.html");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    }
    
            
    /*while (rs.next())

           PrintWriter out = response.getWriter();
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Envios</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Envios at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        

// Propiedades de la conexión

        

/* Lo enviamos.
Transport t = session.getTransport("smtp");
t.connect("emeyene.srl@gmail.com", "mana@mina");
t.sendMessage(message, message.getAllRecipients());
*/


// Cierre.
//t.close();


        
        /*try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Envios</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Envios at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
   
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
        Logger.getLogger(Envios.class.getName()).log(Level.SEVERE, null, ex);
    } catch (MessagingException ex) {
        Logger.getLogger(Envios.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Envios.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(Envios.class.getName()).log(Level.SEVERE, null, ex);
    } catch (MessagingException ex) {
        Logger.getLogger(Envios.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Envios.class.getName()).log(Level.SEVERE, null, ex);
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
