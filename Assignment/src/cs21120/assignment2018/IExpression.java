/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs21120.assignment2018;

import java.util.Iterator;

/**
 * This interface defines the functions required by an Equation
 * The implementation should be a binary expression tree
 *
 * @author bpt
 */
public interface IExpression {
    /**
     * The list of available operations
     */
    public enum Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    ;


    /**
     * Evaluate the expression tree recursively, checking that the result is a
     * positive integer at every step, as required in a Countdown solution
     *
     * @return returns the result of evaluating the expression tree
     * @throws Exception throws an exception if a problem is encountered, including if the Countdown rules are violated.
     */
    int evaluateCountdown() throws Exception;

    /**
     * Return the expression as a String.  For clarity brackets should be placed around each part
     * to ensure it can be read in the right order.
     *
     * @return Returns the string representation of the expression
     */
    String toString();

    /**
     * Assigns the values of an internal node
     *
     * @param left  the expression to use for the left hand side
     * @param right the expression to use for the right hand side
     * @param op    the operation to use at the node
     */
    void set(IExpression left, IExpression right, Operation op);

    /**
     * Assign the value of a leaf node
     *
     * @param value the value to use at this leaf
     */
    void set(int value);

    /**
     * Get the value of a leaf node
     *
     * @return returns the leaf node value
     * @throws Exception should throw an Exception if this is not a leaf node
     */
    int getValue() throws Exception;

    /**
     * Checks if this is a leaf node (both children null) or not (both children not null)
     *
     * @return returns true if this is a leaf node
     */
    boolean isLeaf();

    /**
     * Return an iterator for this equation.  An iterator implementation is provided,
     * so you just need to return an instance of it.
     *
     * @return returns an iterator
     */
    Iterator<IExpression> getIterator();

    /**
     * Get the left side of the equation
     *
     * @return the left side node, null if a leaf
     */
    IExpression getLeft();

    /**
     * Get the right side of the equation
     *
     * @return return the right side, null if it is a leaf
     */
    IExpression getRight();

    /**
     * Get the operations
     *
     * @return returns the operation, null if it is a leaf
     */
    Operation getOperation();
}
