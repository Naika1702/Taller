package Modelo;

public class Cliente {


    private String identificacion, nombre, cel, correo, direccion;

    public Cliente() {
    }

    public Cliente(String identificacion, String nombre, String cel, String correo, String direccion) {
       
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.cel = cel;
        this.correo = correo;
        this.direccion = direccion;
    }

  

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Cliente{" + ", identificacion=" + identificacion + ", nombre=" + nombre + ", cel=" + cel + ", correo=" + correo + ", direccion=" + direccion + '}';
    }

   
    
  


}

