package com.company;

import com.company.AbstractTree;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by Manash on 6/28/2016.
 */
public class BST <E extends  Comparable<E>> extends AbstractTree<E> {

    protected TreeNode<E> root;
    protected int size = 0;

    public BST() {}

    public BST(E[] objects){
        for (int i = 0; i < objects.length; i++)
            insert(objects[i]);
    }

    @Override
    public boolean search(E e){
        TreeNode<E> current = root;
        while(current != null){
            if (e.compareTo(current.element) < 0){
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0){
                current = current.right;
            }
            else
                return true;
        }
        return false;
    }

    @Override
    public boolean insert(E e){
        if (root == null){
            root = createNewNode(e);
        } else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while(current != null){
                if (e.compareTo(current.element) < 0){
                    parent = current;
                    current = current.left;
                }
                else if (e.compareTo(current.element) > 0){
                    parent = current;
                    current = current.right;
                }
                else
                    return false;
            }
            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }
        size++;
        return true;
    }

    protected TreeNode<E> createNewNode(E e){
        return new TreeNode<>(e);
    }

    @Override
    public void inorder(){
        inorder(root);
    }

    protected void inorder(TreeNode<E> root){
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    @Override
    public void postorder(){
        preorder(root);
    }

    protected void postorder(TreeNode<E> root){
        if (root == null) return;
        System.out.println(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    @Override
    public void preorder(){
        preorder(root);
    }

    protected void preorder(TreeNode<E> root){
        if (root == null) return;
        preorder(root.left);
        preorder(root.right);
        System.out.println(root.element + " ");
    }

    public static class TreeNode<E extends Comparable<E>> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e){
            element = e;
        }
    }

    public int getSize(){
        return size;
    }

    public TreeNode<E> getRoot(){
        return root;
    }

    public java.util.ArrayList<TreeNode <E>> path(E e){
        ArrayList<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current = root;
        while(current != null){
            list.add(current);
            if (e.compareTo(current.element) < 0) current = current.left;
            else if (e.compareTo(current.element) > 0) current = current.right;
            else break;
        }
        return list;
    }

    @Override
    public Iterator<E> iterator(){
        return new InorderIterator();
    }

    @Override
    public boolean delete(E e){
        return true;
    }

    private class InorderIterator implements Iterator<E> {
        private ArrayList<E> list = new ArrayList<>();
        private int current = 0;
        public InorderIterator(){
            inorder();
        }

        private void inorder() {
            inorder(root);
        }

        private void inorder(TreeNode<E> root){
            if (root == null) return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        public boolean hasNext(){
            if (current < list.size())
                return true;
            return false;
        }

        @Override
        public void remove(){

        }

        @Override
        public E next(){
            return list.get(current++);
        }

        public void clear(){
            root = null;
            size = 0;
        }
    }
}
