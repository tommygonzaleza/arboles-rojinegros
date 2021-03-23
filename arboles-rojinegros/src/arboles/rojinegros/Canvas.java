/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles.rojinegros;
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
    public static final int ANCHO = 30;
    
    public void setArbol(RBTree arbol){
        this.arbol = arbol;
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        pintar(g, getWidth() / 2, 20, arbol.getRoot());
    }
    
    private void pintar(Graphics g, int x, int y, Node n) {
        if (n == null)
        {}
        else {
            int EXTRA = n.nodosCompletos(n) * (ANCHO/2);
            g.drawOval(x, y, DIAMETRO, DIAMETRO);
            g.drawString(Integer.toString(n.getData()), x + 10, y + 15);
            if (n.getLeft() != null){
                g.drawLine(x+RADIO, y+RADIO, x-ANCHO-EXTRA+RADIO, y+ANCHO+RADIO);
            }
            if(n.getRight()!=null){
                g.drawLine(x+RADIO,y+RADIO,x+ANCHO+EXTRA+RADIO,y+ANCHO+RADIO);
            }
            pintar(g, x-ANCHO-EXTRA, y+ANCHO, n.getLeft());
            pintar(g, x+ANCHO+EXTRA, y+ANCHO,n.getRight());
        }
    }
}
