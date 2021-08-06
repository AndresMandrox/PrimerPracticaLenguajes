package Main;

public class Enums {
    
    public enum Tipo {      
    IDENTIFICADOR(" es un Identificador"),
    NUM_ENTERO(" es un Numero Entero"), 
    NUM_DECIMAL(" es un Numero decimal"), 
    SIMBOLO(" es un Simbolo"), 
    ERROR(" es un Error, no coincide con ningun identificador");
    
    private String descripcion;
    
    private Tipo(String descripcion){
        this.descripcion = descripcion;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }
    
    }
    
}
