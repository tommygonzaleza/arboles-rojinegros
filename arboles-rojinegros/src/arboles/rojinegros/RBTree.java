package arboles.rojinegros;

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

    public boolean isEmpty() {
        return root == null;
    }

    public int Max(int x, int d) {
        if (x > d) {
            return x;
        } else if (x < d) {
            return d;
        } else {
            return x;
        }
    }

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

    public void printPreorder(Node root) {
        System.out.println(root.getData());
        if (root.getLeft() != null) {
            printPreorder(root.getLeft());
        }
        if (root.getRight() != null) {
            printPreorder(root.getRight());

        }
    }
    public String printNode(Node node){
        String aux="Nombre: " +node.getNombre()+"\n" + 
                "Apellido: " + node.getApellido()+"\n"+
                "Cedula: " + Integer.toString(node.getData());
        
        return aux;
    }

    public Node AuxPadre(Node mynode, Node root) {
        if (root == null || mynode == null) {
            return null;
        } else if ((root.getRight() != null && root.getRight() == mynode) || (root.getLeft() != null && root.getLeft() == mynode)) {
            return root;
        } else {
            Node encontrado = AuxPadre(mynode, root.getLeft());
            if (encontrado == null) {
                encontrado = AuxPadre(mynode, root.getRight());
            }
            return encontrado;
        }
    }

    public Node Padre(int data) {
        Node aux = SearchNode(this.root, data);
        Node padre = AuxPadre(aux, this.root);
        return padre;
    }
    public boolean Search( int i, Node root){
        boolean aux=false;
        if (root==null) {
            return false;
        }else{
            if (i==root.getData()) {
                return aux=true;
            }else if (root!=null){
                 if (i<root.getData()) {
                    aux=Search(i,root.getLeft());
                }else{
                     aux=Search(i,root.getRight());
                 }
            
            }
            
        }
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

    public void printInorder(Node root) {

        if (root.getLeft() != null) {
            printInorder(root.getLeft());

        }
        System.out.println(root.getNombre()+","+root.getApellido()+","+root.getData());
        if (root.getRight() != null) {
            printInorder(root.getRight());

        }
    }

    // Rotacion doble derecha
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

    public void insert2(String nombre,String apellido,int data) {
        Node newNode = new Node(nombre,apellido,data);
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

    public void insert(String nombre, String apellido, int key) {
        Node node = new Node(nombre, apellido, key);
        Node y = null;
        Node x = this.root;
        if (isEmpty()) {
            root = node;
            x = node;

        } else {
            while (x != null) {
                y = x;
                if (node.getData() < x.getData()) {
                    x = x.getLeft();
                } else {
                    x = x.getRight();
                }

            }
            //while(x!=null){
            //    y=x;
            //    if (node.getData()<x.getData()) {
            //        x=x.getLeft();
            //    }else{
            //        x=x.getRight();
            //    }
            //}
            node.setPadre(y);
            if (y == null) {
                root = node;
            } else if (node.getData() < y.getData()) {
                y.setLeft(node);
            } else {
                y.setRight(node);
            }
            if (node.getPadre() == null) {
                node.setRed(false);
                return;
            }
            if (node.getPadre().getPadre() == null) {
                return;
            }
            Auxinsert(node);
        }
    }
    
    public Node minimum(Node raiz){
        while(raiz.getLeft()!=null){
            raiz = raiz.getLeft();
        }
        return raiz;
    }
    
    public void transplanteRB(Node n, Node v){
        if(n.getPadre()==null){
            root = v;
        }else if(n==n.getPadre().getLeft()){
            n.getPadre().setLeft(v);
        }else{
            n.getPadre().setRight(v);
        }
        v.setPadre(n.getPadre());
    }
    
    public void eliminarNodo(Node raiz, int ci){
        // Buscar el nodo que contiene la CI (Cédula)
        Node z = null;
        Node x, y;
        while(raiz != null){
            if(raiz.getData() == ci){
                z = raiz;
            }
            if(raiz.getData()<=ci){
                raiz = raiz.getRight();
            }else{
                raiz = raiz.getLeft();
            }
        }
        if(z==null){
            JOptionPane.showMessageDialog(null, "Couldn't find key in the tree");
        }
        y = z;
        if(z.getLeft()==null){
            x = z.getRight();
            transplanteRB(z, z.getRight());
        }else if(z.getRight()==null){
            x = z.getLeft();
            transplanteRB(z, z.getLeft());
        }else{
            y = minimum(z.getRight());
            x = y.getRight();
            if(y.getPadre()==z){
                x.setPadre(y);
            }else{
                transplanteRB(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setPadre(y);
                y.setRed(z.isRed());
            }
        }
        if(!y.isRed()){
            arreglarDelete(x);
        }
    }
    
    public void arreglarDelete(Node x){
        Node s;
        while(x != root && x.isRed()==false){
            if(x==x.getPadre().getLeft()){
                s=x.getPadre().getRight();
                if(s.isRed()){
                    s.setRed(false);
                    x.getPadre().setRed(true);
                    leftRotate(x.getPadre());
                    s = x.getPadre().getRight();
                }
                if(s.getLeft().isRed() == true && s.getRight().isRed() == false){
                    s.setRed(true);
                    x = x.getPadre();
                }else{
                    if(s.getRight().isRed()){
                        s.getLeft().setRed(false);
                        s.setRed(true);
                        rightRotate(s);
                        s = x.getPadre().getRight();
                    }
                    s.setRed(x.getPadre().isRed());
                    x.getPadre().setRed(false);
                    s.getRight().setRed(false);
                    leftRotate(x.getPadre());
                    x = root;
                }
            } else {
                s = x.getPadre().getLeft();
                if(s.isRed()){
                    s.setRed(false);
                    x.getPadre().setRed(true);
                    rightRotate(x.getPadre());
                    s = x.getPadre().getLeft();
                }
                if(s.getRight().isRed()==false){
                    s.setRed(true);
                    x = x.getPadre();
                } else {
                    if(!s.getLeft().isRed()){
                        s.getRight().setRed(false);
                        s.setRed(true);
                        leftRotate(s);
                        s = x.getPadre().getLeft();
                    }
                    s.setRed(x.getPadre().isRed());
                    x.getPadre().setRed(false);
                    s.getLeft().setRed(false);
                    rightRotate(x.getPadre());
                    x = root;
                }
            }
        }
        x.setRed(false);
    }
}
