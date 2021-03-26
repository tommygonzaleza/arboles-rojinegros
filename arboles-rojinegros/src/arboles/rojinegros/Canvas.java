/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles.rojinegros;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Tomas
 */
public class Canvas extends JPanel {
    private RBTree arbol;
    public static final int DIAMETRO = 30;
    public static final int RADIO = DIAMETRO / 2;
    public static final int ANCHO = 50;
    
    /**
     * Este método recibe el valor del arbol que se quiere pintar y lo guarda en la variable arbol.
     * @param arbol RBTree Es el arbol que se quiere pintar.
     */
    public void setArbol(RBTree arbol){
        this.arbol = arbol;
        repaint();
    }
    
    /**
     * Este método pinta el arbol ejecutnado el método pintar().
     * @param g Graphics que nos permiten pintar el arbol.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        pintar(g, getWidth() / 2, 20, arbol.getRoot());
    }
    
    /**
     * Este método nos permite pintar el arbol, crea las circunferencias, las líneas que las une y agrega los números.
     * @param g Graphics que nos permiten pintar el arbol.
     * @param x int Número que nos ayuda a determinar el tamaño de las líneas para el vector x.
     * @param y int Número que nos ayuda a determinar el tamaño de las líneas para el vector y.
     * @param n Node nodo que se va a pintar.
     */
    private void pintar(Graphics g, int x, int y, Node n) {
        g.setFont(new Font("Arial",Font.ITALIC,9));
        if (n == null)
        {}
        else {
            int EXTRA = n.nodosCompletos(n) * (ANCHO/2);
            if(n.isRed()){
                g.setColor(java.awt.Color.red);
                
            }
            g.drawOval(x, y, DIAMETRO, DIAMETRO);
            g.setColor(java.awt.Color.black);
            
            if (n.getLeft()==null &&n.getRight()==null) {
                g.drawString(Integer.toString(n.getData()), x + 5, y +10);
                
            }
            
            if (n.getLeft() != null){
                g.drawString(Integer.toString(n.getData()), x + 5, y + 10);
                g.drawLine(x+RADIO, y+RADIO, x-ANCHO-EXTRA+RADIO, y+ANCHO+RADIO);
            }
            if(n.getRight()!=null){
                g.drawLine(x+RADIO,y+RADIO,x+ANCHO+EXTRA+RADIO,y+ANCHO+RADIO);
                g.drawString(Integer.toString(n.getData()), x + 5, y + 10);
            }
            
            pintar(g, x-ANCHO-EXTRA, y+ANCHO, n.getLeft());
            pintar(g, x+ANCHO+EXTRA, y+ANCHO,n.getRight());
        }
    }
}
