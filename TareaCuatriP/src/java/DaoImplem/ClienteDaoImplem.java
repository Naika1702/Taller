
import BD.Conexion_BD;
import DAO.ClienteDAO;
import Modelo.Cliente;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDaoImplem implements ClienteDAO {

    Connection cn = Conexion_BD.getDBConnection();

    @Override
    public String Guardar(Cliente cliente) {
        String mensaje = "";
        try {

            String sql = ("INSERT INTO ClienteEmisor (Identificacion, Nombre, Cel, Correo, Direccion)"+"( VALUES (?,?,?,?,?)");
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, cliente.getIdentificacion());
            pstm.setString(2, cliente.getNombre());
            pstm.setString(3, cliente.getCel());
            pstm.setString(4, cliente.getCorreo());
            pstm.setString(5, cliente.getDireccion());

            pstm.executeUpdate();

        } catch (Exception e) {

        }
        return mensaje;
    }

    @Override
    public List<Cliente> ListarClientes() {

        List<Cliente> listaClientes = new ArrayList<Cliente>();
        try {
            String sql = "SELECT * FROM ClienteEmisor";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String identificacion = rs.getString(1);
                String nombre = rs.getString(2);
                String cel = rs.getString(3);
                String correo = rs.getString(4);
                String direccion = rs.getString(5);
                
                Cliente cliente = new Cliente(identificacion,nombre,cel,correo,direccion);
                listaClientes.add(cliente);

            }

        } catch (Exception e) {

        }
        return listaClientes;
    }

}
