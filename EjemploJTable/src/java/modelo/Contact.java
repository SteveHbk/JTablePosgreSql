package modelo;


public class Contact {
    
    int IdData;
    String Direccion;
    String Telefono;
    String Email;
    
    public Contact() {
    }
    
    public Contact(int IdData, String Direccion, String Telefono, String Email) {
        this.IdData = IdData;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Email = Email;
    }
    
    public Contact(String Direccion, String Telefono, String Email) {
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Email = Email;
    }

    public int getIdData() {
        return IdData;
    }

    public void setIdData(int IdData) {
        this.IdData = IdData;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    
}
