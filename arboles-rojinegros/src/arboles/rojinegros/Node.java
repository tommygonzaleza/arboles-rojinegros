package arboles.rojinegros;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Jrios
 */
public class Node {
    private Node left;
    private Node right;
    private boolean red;
    private int data;
    private  String nombre;
    private Node padre;
    private String apellido;
    
    public Node(String nombre, String apellido,int data){
        
        this.left=null;
        this.right=null;
        this.red=true;
        this.nombre=nombre;
        this.apellido=apellido;
        this.data=data;
        
        
    }

    /**
     * @return the left
     */
    public Node getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public Node getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(Node right) {
        this.right = right;
    }

    /**
     * @return the red
     */
    public boolean isRed() {
        return red;
    }

    /**
     * @param red the red to set
     */
    public void setRed(boolean red) {
        this.red = red;
    }

    /**
     * @return the data
     */
    public int getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     * @return the fe
     */
    

    /**
     * @param fe the fe to set
     */
   

    /**
     * @return the padre
     */
    public Node getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(Node padre) {
        this.padre = padre;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
}
