package modelo;


public class Relatives {
    
    int IdRelative;
    String Parentesco;
    String Nombre;
    String EmailR;

    public Relatives() {
    }

    public Relatives(int IdRelative, String Parentesco, String Nombre, String EmailR) {
        this.IdRelative = IdRelative;
        this.Parentesco = Parentesco;
        this.Nombre = Nombre;
        this.EmailR = EmailR;
    }

    public Relatives(String Parentesco, String Nombre, String EmailR) {
        this.Parentesco = Parentesco;
        this.Nombre = Nombre;
        this.EmailR = EmailR;
    }

    public int getIdRelative() {
        return IdRelative;
    }

    public void setIdRelative(int IdRelative) {
        this.IdRelative = IdRelative;
    }

    public String getParentesco() {
        return Parentesco;
    }

    public void setParentesco(String Parentesco) {
        this.Parentesco = Parentesco;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getEmailR() {
        return EmailR;
    }

    public void setEmailR(String EmailR) {
        this.EmailR = EmailR;
    }
    
    
    
}
