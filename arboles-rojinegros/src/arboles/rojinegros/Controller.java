/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles.rojinegros;

/**
 *
 * @author Tomas
 */
public class Controller {
    private Canvas canvas;
    private RBTree arbol;
    
    /**
     * Este es el constructor de la clase, lo que hace es recibir los valores del canvas y del arbol para asignarlos.
     * @param canvas Canvas Es el valor del canvas que nos permite crear el arbol.
     * @param arbol RBTree Es el arbol que queremos pintar.
     */
    public Controller(Canvas canvas, RBTree arbol){
        this.canvas = canvas;
        this.arbol = arbol;
    }
    
    /**
     * Este método nos permite iniciar el dibujo del arbol.
     */
    public void iniciar(){
        canvas.setArbol(arbol);
    }
}