package modelo;

public class Usuario {

    int IdUsuario;
    String Usuario;
    String Password;
    String Nombre;

    public Usuario() {
    }

    public Usuario(int IdUsuario, String Usuario, String Password, String Nombre) {
        this.IdUsuario = IdUsuario;
        this.Usuario = Usuario;
        this.Password = Password;
        this.Nombre = Nombre;
    }

    public Usuario(String Usuario, String Password, String Nombre) {
        this.Usuario = Usuario;
        this.Password = Password;
        this.Nombre = Nombre;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return "Usuario{" + "IdUsuario=" + IdUsuario + ", Usuario=" + Usuario + ", Password=" + Password + ", Nombre=" + Nombre + '}';
    }

}
