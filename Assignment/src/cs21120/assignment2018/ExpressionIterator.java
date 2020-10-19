/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs21120.assignment2018;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * An in order iterator over an IEquation
 * @author bpt
 */
public class ExpressionIterator implements Iterator<IExpression> {
    
    LinkedList<IExpression> stack = new LinkedList<>();

    /**
     * Constructor for an EquationIterator
     * @param root the root of the equation to iterate over
     */
    public ExpressionIterator(IExpression root) {
        stack.push(root);
        IExpression current = root;
        while (current.getLeft() != null) {
            current = current.getLeft();
            stack.push(current);
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public IExpression next() {
        IExpression node = stack.pop();
        IExpression current = node.getRight();
        while (current != null) {
            stack.push(current);
            current = current.getLeft();
        }
        return node;
    }
    
}
