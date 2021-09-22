
package DAO;

import Modelo.Cliente;
import java.util.List;


public interface ClienteDAO {
    
    public String Guardar(Cliente cliente);
    
    //Para Listar los clientes 
    public List<Cliente> ListarClientes();
    
}
