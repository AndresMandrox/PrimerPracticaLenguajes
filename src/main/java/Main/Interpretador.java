
package Main;

import Main.Enums.Tipo;
import java.awt.TextArea;
import java.awt.TextField;
import javax.swing.*;


public class Interpretador {
    
      private final String[] letras = new String[]{"a","b","c","d","e","f","g","h","i","j",
                                            "k","l","m","n","ñ","o","p","q","r","s","t",
                                            "u","v","w","x","y","z","A","B","C","D","E",
                                            "F","G","H","I","J","K","L","M","N","Ñ","O",
                                            "P","Q","R","S","T","U","V","W","X","Y","Z"};
    
    private final String[] numeros = new String[]{"0","1","2","3","4","5","6","7","8","9"};
    private final String[] simbolos = new String[]{"(",")","¿","?",";",":","{","}","[","]",
                                                "!","¡","#","%","$","/","=","*","+","-"};
    
    private final String punto = "."; 
    
    private Tipo tipo = Tipo.ERROR;
    private final JTextArea areaT;  
    private final String[] letrasTexto;
    
    public Interpretador(JTextField palabras, JTextArea texto){
        this.letrasTexto = this.SeparacionLetras(palabras.getText());
        this.areaT = texto;
        this.Lectura(letrasTexto);
         
    }

    Interpretador(TextField textField1, TextArea textArea1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private String[] SeparacionLetras(String palabra){
        int tamaño = palabra.length();
        String[] cadenaTexto = new String[tamaño];
                
        char[] letra = palabra.toCharArray();
        for(int i = 0; i<tamaño; i++){
            cadenaTexto[i]=Character.toString(letra[i]);
        }
        return cadenaTexto;
       
    }
    
    private void Lectura(String[] letrasTexto){
        boolean letra = false; 
        boolean numero= false; 
        boolean numeros = false; 
        boolean numeroDespunto = false;
        boolean signo= false; 
        boolean error = false; 
        int primer = 0;
       
        int auxIni = 0;
        int auxFin = 0;
        int auxTam = letrasTexto.length;
       
        for(int i = 0; i<auxTam; i++){
            for(String x:this.letras){
                if(letrasTexto[i].equals(x)){
                    
                    if(primer==0){
                       primer = 1;
                    }
                    letra = true;
                }
            }
            for(String x:this.numeros){
                if(letrasTexto[i].equals(x)){
                    if(numeros == true && numero == true){
                        numeroDespunto = true;
                    }else{ 
                        if(primer==0){
                            primer = 2;                     
                        }
                        numero = true;
                    }
                }
            }
             
            for(String x:this.simbolos){
                if(letrasTexto[i].equals(x)){
                    
                    if(primer==0){
                       primer = 3;
                    }
                    signo = true;
                }
            }
            if(letrasTexto[i].equals(this.punto)&& numeros==false){
                numeros = true;
                if(primer == 0 ){
                    primer = 4;
                }
            }
            
            else if(letrasTexto[i].equals(" ")){
                auxFin = i;
                this.Token(letra, numero, numeros, numeroDespunto, signo, primer);
                this.Show(auxIni, auxFin);
                letra = false; 
                numero= false; 
                numeros = false; 
                numeroDespunto = false;
                signo= false; 
                error = false; 
                primer = 0;
                this.tipo = Tipo.ERROR;
                auxIni = auxFin++;
                
            }
        auxFin = i;
            
        }
        System.out.println(primer);
        Token(letra, numero, numeros, numeroDespunto, signo, primer);
        this.Show(auxIni, auxFin);
                
        auxIni = 0;
        auxFin = 0;
       
       
        
    }
    
    private void Token(boolean l,boolean n,boolean p,boolean np,boolean s, int first){
        
        if(first==1&& l && n && !p && !np && !s){
            this.tipo = Tipo.IDENTIFICADOR;
        }else if(first==2){
            if(!l&& n && !p && !np && !s){
                this.tipo = Tipo.NUM_ENTERO;
            }else if(!l&& n && p && np && !s){
                this.tipo = Tipo.NUM_DECIMAL;
            }
        }else if(first == 3 && !l&& !n && !p && !np && s){
            this.tipo = Tipo.SIMBOLO;
        }else{
            this.tipo = Tipo.ERROR;
        }
    }
    
    
    //Proceso para mostrar las letras en el area de texto de abajo en la ventana
    
    private void Show (int inicio, int fin){
        int start = inicio;
        int end = fin;        
        for(int i = start; i<=end; i++){
            areaT.append(this.letrasTexto[i]);
        }      
        areaT.append(this.tipo.getDescripcion()+"\n");
    }
    
    private void Reset(boolean l,boolean n,boolean p,boolean np,boolean s, int first){
        
        first = 0;
        l = false;
        n = false;
        p = false;
        np = false;
        s = false;
        this.tipo = Tipo.ERROR;
    }
    
}
