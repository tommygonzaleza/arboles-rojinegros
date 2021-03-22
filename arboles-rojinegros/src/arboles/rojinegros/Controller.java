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
    
    public Controller(Canvas canvas, RBTree arbol){
        this.canvas = canvas;
        this.arbol = arbol;
    }
    
    public void iniciar(){
        canvas.setArbol(arbol);
    }
}