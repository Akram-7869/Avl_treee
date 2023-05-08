// import java.util.*;

package Avl_trees;

public class avl {
    static class Node{
        int data,height;
        Node left,right;
        Node(int data){
            this.data=data;
            height=1;
        }
    }
    static Node root;
public static int height(Node root){
    if(root==null)return 0;
    return root.height;
}
public static int max(int a,int b){
    return (a>b)?a:b;
}

public static  Node insert(Node root,int key){
      if(root==null){
        return new Node(key);
      }
      //checking for the left child
      if(key<root.data){
        root.left=insert(root.left,key);
      }

      //checking for the right child
     else if(key > root.data){
        root.right=insert(root.right,key);
      }else{
        return root;// bcz duplicates are not allowed 
      }

      //update height
      root.height=1+Math.max(height(root.left),height(root.right));

      //getting the balanced factor
      int bf=getbf(root);

      //cases
      //left case
      if(bf>1 && key < root.left.data){
        return rr(root);
      }
      //right case
      if(bf< (-1) && key>root.right.data){
        return ll(root);
      } 
      //lr case
      if(bf>1 && key > root.left.data){
        root.left=ll(root.left);
        return rr(root);
      }
      //rl case
      if(bf<(-1) && key < root.right.data){
        root.right=rr(root.right);
        return ll(root);
      }

      return root;//if my tree is already a balanced avl tree
    

}
public static int getbf(Node root){
    if(root==null)return 0;
    return height(root.left)-height(root.right);
}
    
public static Node ll(Node x){
    Node y=x.right;
    Node t2=y.left; 
    //perform rotation
    y.left=x;
    x.right=t2;
    //update heights
    x.height=max(height(x.left),height(x.right))+1;
    y.height=max(height(y.left),height(y.right))+1;
    
    //return the new root
    return y;
}
public static Node rr(Node y){
    Node x=y.right;
    Node t2=x.left; 
    //perform rotation
    x.left=y;
    y.right=t2;
    //update heights
    y.height=max(height(y.left),height(y.right))+1;
    x.height=max(height(x.left),height(x.right))+1;
    
    //return the new root
    return x;
}

public static void preorder(Node root){
  if(root==null)return;
  System.out.print(root.data +" -> ");
  preorder(root.left);
  preorder(root.right);
}


    
   public static void main(String[] args) {
      root=insert(root,10);
      root=insert(root,20);
      root=insert(root,30);
      root=insert(root,40);
      root=insert(root,50);
      root=insert(root,25);

      preorder(root);
   }
}
