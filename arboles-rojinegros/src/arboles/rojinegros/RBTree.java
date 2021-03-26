package arboles.rojinegros;

import com.csvreader.CsvWriter;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jrios
 */
public class RBTree {

    private Node root;

    public RBTree() {

        this.root = null;
    }

    /**
     * @return the root
     */
    public Node getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * Esta funcion busca un nodo en el arbol y lo devuelve
     *
     * @param d elemento del nodo
     * @param root raiz del arbol y subarboles
     * @return el nodo del elemento d
     */

    public Node search(int d, Node root) {
        if (this.root == null) {
            return null;
        } else if (root.getData() == d) {
            return root;
        } else if (root.getData() < d) {
            return search(d, root.getRight());
        } else {
            return search(d, root.getLeft());
        }
    }


 

    /**
     * Este metodo devueleve el padre de un nodo, si no tiene los hijos
     * completos
     *
     * @param root raiz del arbol
     * @param data dato del nodo que se quiere buscar
     * @return Padre del nodo
     */
    public Node SearchFather(Node root, int data) {
        Node encontrado = null;
        if (data < root.getData()) {
            if (encontrado != null) {
                return encontrado;

            } else if (root.getLeft() != null) {
                encontrado = SearchFather(root.getLeft(), data);

            } else {
                return root;
            }
        } else if (data > root.getData()) {
            if (encontrado != null) {
                return encontrado;

            } else if (root.getRight() != null) {
                encontrado = SearchFather(root.getRight(), data);

            } else {
                return root;
            }
        }

        return encontrado;
    }

    /*
    public void printPreorder(Node root) {
        System.out.println(root.getData());
        if (root.getLeft() != null) {
            printPreorder(root.getLeft());
        }
        if (root.getRight() != null) {
            printPreorder(root.getRight());

        }
    }
    */
    

    /**
     * Esta funcion imprime los elementos de un nodo
     *
     * @param node Nodo a imprimir
     * @return String de elementos del nodo
     */
    public String printNode(Node node) {
        String aux = "Nombre: " + node.getNombre() + "\n"
                + "Apellido: " + node.getApellido() + "\n"
                + "Cedula: " + Integer.toString(node.getData());

        return aux;
    }
    

    public Node SearchNode(Node root, int i) {
        Node aux = root;
        while (aux.getData() != i) {
            if (i < aux.getData()) {
                aux = aux.getLeft();
            } else {
                aux = aux.getRight();
            }
            if (aux == null) {
                return null;
            }
        }
        return aux;

    }
    /*
    public void printInorder(Node root) {

        if (root.getLeft() != null) {
            printInorder(root.getLeft());

        }
        System.out.println(root.getNombre() + "," + root.getApellido() + "," + root.getData());
        if (root.getRight() != null) {
            printInorder(root.getRight());

        }
    }
    */
    

    /**
     * Este metodo rota a la izquierda
     *
     * @param x El nodo que se va a rotar
     */
    public void leftRotate(Node x) {
        Node y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != null) {
            y.getLeft().setPadre(x);
        }
        y.setPadre(x.getPadre());
        if (x.getPadre() == null) {
            this.root = y;
        } else if (x == x.getPadre().getLeft()) {
            x.getPadre().setLeft(y);
        } else {
            x.getPadre().setRight(y);
        }
        y.setLeft(x);
        x.setPadre(y);
    }

    /**
     * Este metodo rota a la derecha
     *
     * @param x El nodo que se va a rotar
     */
    public void rightRotate(Node x) {
        Node y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight() != null) {
            y.getRight().setPadre(x);
        }
        y.setPadre(x.getPadre());
        if (x.getPadre() == null) {
            this.root = y;
        } else if (x == x.getPadre().getRight()) {
            x.getPadre().setRight(y);
        } else {
            x.getPadre().setLeft(y);
        }
        y.setRight(x);
        x.setPadre(y);
    }

    /**
     * Auxiliar para insertar y balancear el arbol
     *
     * @param k Nodo que se va a insertar
     */
    public void Auxinsert(Node k) {
        Node u;
        if (k.getPadre() == null) {
            k.setRed(false);
        } else {
            while (k.getPadre().isRed()) {
                if (k.getPadre() == k.getPadre().getPadre().getRight()) {
                    u = k.getPadre().getPadre().getLeft();//tio

                    if (u != null && u.isRed()) {
                        u.setRed(false);
                        k.getPadre().setRed(false);
                        k.getPadre().getPadre().setRed(true);
                        k = k.getPadre().getPadre();
                    } else {
                        if (k == k.getPadre().getLeft()) {
                            k = k.getPadre();
                            rightRotate(k);
                        }
                        k.getPadre().setRed(false);
                        k.getPadre().getPadre().setRed(true);
                        leftRotate(k.getPadre().getPadre());
                    }

                } else {
                    u = k.getPadre().getPadre().getRight();//tio
                    if (u != null && u.isRed()) {
                        u.setRed(false);
                        k.getPadre().setRed(false);
                        k.getPadre().getPadre().setRed(true);
                        k = k.getPadre().getPadre();
                    } else {
                        if (k == k.getPadre().getRight()) {
                            k = k.getPadre();
                            leftRotate(k);
                        }
                        k.getPadre().setRed(false);
                        k.getPadre().getPadre().setRed(true);
                        rightRotate(k.getPadre().getPadre());
                    }
                }
                if (k == root) {
                    break;
                }
            }
            root.setRed(false);

        }

    }

    /**
     * Este metodo inserta un nuevo nodo al arbol
     *
     * @param nombre Nombre del vecino
     * @param apellido Apellido del vecino
     * @param data Cedula del vecino
     */
    public void insert(String nombre, String apellido, int data) {
        Node newNode = new Node(nombre, apellido, data);
        if (this.root == null) {
            this.root = newNode;

        } else {
            Node nodeFather = this.SearchFather(this.root, data);
            if (nodeFather == null) {
                System.out.println("El elemento ya existe");
            } else if (nodeFather.getData() > newNode.getData()) {
                nodeFather.setLeft(newNode);
                newNode.setPadre(nodeFather);
            } else if (nodeFather.getData() < newNode.getData()) {
                nodeFather.setRight(newNode);
                newNode.setPadre(nodeFather);

            }
        }
        Auxinsert(newNode);
    }

    
    /**
     * Metodo auxiliar que elimina y balancea el arbol
     * @param x Nodo que se va a eliminar
     */
    public void AuxDelete(Node x) {
        Node s;
        while (x != null && x != root && x.isRed() == false) {
            if (x == x.getPadre().getLeft()) {
                s = x.getPadre().getRight();
                if (s.isRed() == true) {
                    // case 3.1
                    s.setRed(false);
                    x.getPadre().setRed(true);
                    leftRotate(x.getPadre());
                    s = x.getPadre().getRight();
                }

                if (s.getLeft().isRed() == false && s.getRight().isRed() == false) {
                    // case 3.2
                    s.setRed(true);
                    x = x.getPadre();
                } else {
                    if (s.getRight().isRed() == false) {
                        // case 3.3
                        s.getLeft().setRed(false);
                        s.setRed(true);
                        rightRotate(s);
                        s = x.getPadre().getRight();
                    }

                    // case 3.4
                    s.setRed(x.getPadre().isRed());
                    x.getPadre().setRed(false);
                    s.getRight().setRed(false);
                    leftRotate(x.getPadre());
                    x = root;//caso aparte
                }
            } else {
                s = x.getPadre().getLeft();
                if (s.isRed() == false) {
                    // case 3.1
                    s.setRed(false);
                    x.getPadre().setRed(true);
                    rightRotate(x.getPadre());
                    s = x.getPadre().getLeft();
                }

                if (s.getRight().isRed() == false && s.getRight().isRed() == false) { //s.right.color == 0 && s.right.color == 0
                    // case 3.2
                    s.setRed(true);
                    x = x.getPadre();
                } else {
                    if (s.getLeft().isRed() == false) {
                        // case 3.3
                        s.getRight().setRed(false);
                        s.setRed(true);
                        leftRotate(s);
                        s = x.getPadre().getLeft();
                    }

                    // case 3.4
                    s.setRed(x.getPadre().isRed());
                    x.getPadre().setRed(false);
                    s.getLeft().setRed(false);
                    rightRotate(x.getPadre());
                    x = root;
                }
            }
        }
        //x.setRed(false);
    }
    /**
     * Metodo que cambia apuntadores
     * @param u Nodo al que se le va a cambiar el apuntador
     * @param v Nodo que recibe el apuntador de u
     */
    public void transplanteRB(Node u, Node v) {
        if (u.getPadre() == null) {
            root = v;
        } else if (u == u.getPadre().getLeft()) {
            u.getPadre().setLeft(v);
        } else {
            u.getPadre().setRight(v);
        }
        if (u.getPadre() != null && v != null) {
            v.setPadre(u.getPadre());
        }
    }
    /**
     * Metodo que elimina un nodo
     * @param root Nodo de la raiz
     * @param key valor del nodo a eliminar
     */
    public void deleteNodo(Node root, int key) {
        
        Node z = null;
        Node x, y;
        while (root != null) {
            if (root.getData() == key) {
                z = root;
            }
            if (root.getData() < key) {
                root = root.getRight();
            } else {
                root = root.getLeft();
            }
        }
        if (z == null) {
            JOptionPane.showMessageDialog(null, "No se pudo encontrar la cédula");
            return;
        }
        y = z;
        boolean yOriginalColor = y.isRed();
        if (z.getLeft() == null) {
            x = z.getRight();
            transplanteRB(z, z.getRight());
        } else if (z.getRight() == null) {
            x = z.getLeft();
            transplanteRB(z, z.getLeft());
        } else {
            y = minimum(z.getRight());
            yOriginalColor = y.isRed();
            x = z.getRight();
            if (y.getPadre() == z) {
                //System.out.println(x.getData());
                x.setPadre(y);
            } else {
                transplanteRB(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setPadre(y);
            }
            transplanteRB(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setPadre(y);
            y.setRed(z.isRed());
        }
        if (yOriginalColor == false) {
            AuxDelete(x);
        }
    }
    /**
     * Metodo que busca el minimo elemento
     * @param node Nodo raiz del arbol o un nodo cualquiera que se pueda
     * encontrar el minimo
     * @return El nodo minimo 
     */
    public Node minimum(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public void writePreorder(Node root, CsvWriter csvwriter) {
        try {

            String[] habitantes_csv = {root.getNombre(), root.getApellido(), Integer.toString(root.getData())};
            csvwriter.writeRecord(habitantes_csv);

            if (root.getLeft() != null) {
                writePreorder(root.getLeft(), csvwriter);
            }
            if (root.getRight() != null) {
                writePreorder(root.getRight(), csvwriter);

            }

        } catch (Exception err) {

        }
    }
}
