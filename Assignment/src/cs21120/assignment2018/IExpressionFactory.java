/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs21120.assignment2018;

import cs21120.assignment2018.IExpression.Operation;


/**
 * This interface is for creating instances of IExpressions
 *
 * @author bpt
 */
public interface IExpressionFactory {
    /**
     * Returns a random equation using (some of) the values given and the available operations
     *
     * @param vals the values that can be used in the equation
     * @return return the root of the random equation tree
     */
    public IExpression createRandomEquation(int[] vals);

    /**
     * Searches (somehow) for an equation that evaluates as close as possible to the target value
     *
     * @param vals   the values available to use in the equation
     * @param target the target value
     * @return returns the equation that the system thinks is the best
     */
    public IExpression findBestSolution(int[] vals, int target);

    /**
     * Factory method to return a leaf IExpression
     *
     * @param val the value to store in the leaf
     * @return returns the IExpression containing the value
     */
    public IExpression createLeaf(int val);

    /**
     * Factory method to return a new IExpression with the specified children and operation
     *
     * @param l  the left side of the equation
     * @param r  the right side of the equation
     * @param op the operation to use
     * @return returns the IExpression "l op r"
     */
    public IExpression createInternalNode(IExpression l, IExpression r, Operation op);
}