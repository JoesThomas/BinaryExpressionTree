/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs21120.assignment2018;

import cs21120.assignment2018.IExpression.Operation;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bpt
 */
public class IExpressionTest {
    IExpressionFactory factory = new Expressionjot38();
    public IExpressionTest() {
    }

    /**
     * Test of evaluateCountdown method, of class IExpression.
     */
    @Test
    public void testEvaluateCountdown() throws Exception {
        System.out.println("evaluateCountdown");
        IExpression left, right, node1, node2;
        left = factory.createLeaf(10);
        right = factory.createLeaf(2);
        node1 = factory.createInternalNode(left, right, IExpression.Operation.ADD);
        left = factory.createLeaf(50);
        right = factory.createLeaf(5);
        node2 = factory.createInternalNode(left, right, IExpression.Operation.DIVIDE);
        left=node1;
        right = factory.createLeaf(7);
        node1 = factory.createInternalNode(left, right, IExpression.Operation.MULTIPLY);
        left = node1;
        right = node2;

        IExpression instance = factory.createInternalNode(left, right, IExpression.Operation.SUBTRACT);

        int expResult = 74;
        int result = instance.evaluateCountdown();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of toString method, of class IExpression.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        IExpression left, right, node1, node2;
        left = factory.createLeaf(10);
        right = factory.createLeaf(2);
        node1 = factory.createInternalNode(left, right, IExpression.Operation.ADD);
        left = factory.createLeaf(50);
        right = factory.createLeaf(5);
        node2 = factory.createInternalNode(left, right, IExpression.Operation.DIVIDE);
        left=node1;
        right = factory.createLeaf(7);
        node1 = factory.createInternalNode(left, right, IExpression.Operation.MULTIPLY);
        left = node1;
        right = node2;

        IExpression instance = factory.createInternalNode(left, right, IExpression.Operation.SUBTRACT);

        String expResult = "(((10+2)*7)-(50/5))";
        String result = instance.toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of set method, of class IExpression.
     */
    @Test
    public void testSet_3args() throws Exception {
        System.out.println("set");
        IExpression left = factory.createLeaf(8);
        IExpression right = factory.createLeaf(7);
        IExpression.Operation op = Operation.ADD;
        IExpression instance = factory.createLeaf(0);
        instance.set(left, right, op);
        assertEquals(instance.getLeft(), left);
        assertEquals(instance.getRight(), right);
        assertEquals(instance.getOperation(), op);

        assertEquals(instance.getLeft().getValue(), 8, 0);
        assertEquals(instance.getRight().getValue(), 7, 0);
        assertEquals(instance.evaluateCountdown(), 15, 0);

    }

    /**
     * Test of set method, of class IExpression.
     */
    @Test
    public void testSet_int() throws Exception {
        System.out.println("set");
        int value = 10;
        IExpression instance = factory.createLeaf(0);
        instance.set(value);
        assertEquals(instance.getValue(), 10, 0);

    }

    /**
     * Test of getValue method, of class IExpression.
     */
    @Test
    public void testGetValue() throws Exception {
        System.out.println("getValue");
        int value = 27;
        IExpression instance = factory.createLeaf(0);
        instance.set(value);
        assertEquals(instance.getValue(), 27);
    }

    /**
     * Test of isLeaf method, of class IExpression.
     */
    @Test
    public void testIsLeaf() {
        System.out.println("isLeaf");
        IExpression instance = factory.createLeaf(10);
        boolean result = instance.isLeaf();
        assertTrue(result);
        instance = factory.createInternalNode(instance, factory.createLeaf(7), Operation.MULTIPLY);
        assertFalse(instance.isLeaf());
    }

    /**
     * Test of getIterator method, of class IExpression.
     */
    @Test
    public void testGetIterator() throws Exception {
        System.out.println("getIterator");
        IExpression instance = factory.createInternalNode(factory.createLeaf(10), factory.createLeaf(7), Operation.MULTIPLY);

        Iterator<IExpression> iterator = instance.getIterator();
        assertTrue(iterator.hasNext());
        IExpression node = iterator.next();
        assertTrue(node.isLeaf());
        assertEquals(node.getValue(), 10);
        assertTrue(iterator.hasNext());
        node = iterator.next();
        assertFalse(node.isLeaf());
        assertTrue(iterator.hasNext());
        node = iterator.next();
        assertTrue(node.isLeaf());
        assertEquals(node.getValue(), 7);
        assertFalse(iterator.hasNext());
    }

    /**
     * Test of getLeft method, of class IExpression.
     */
    @Test
    public void testGetLeft() {
        System.out.println("getLeft");
        IExpression left = factory.createLeaf(10);
        IExpression right = factory.createLeaf(7);
        IExpression instance = factory.createInternalNode(left, right, Operation.SUBTRACT);

        IExpression expResult = left;
        IExpression result = instance.getLeft();
        assertEquals(expResult, result);

    }

    /**
     * Test of getRight method, of class IExpression.
     */
    @Test
    public void testGetRight() {
        System.out.println("getRight");
        IExpression left = factory.createLeaf(10);
        IExpression right = factory.createLeaf(7);
        IExpression instance = factory.createInternalNode(left, right, Operation.SUBTRACT);

        IExpression expResult = right;
        IExpression result = instance.getRight();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOperation method, of class IExpression.
     */
    @Test
    public void testGetOperation() {
        System.out.println("getOperation");
        IExpression left = factory.createLeaf(100);
        IExpression right = factory.createLeaf(5);
        IExpression instance = factory.createInternalNode(left, right, Operation.DIVIDE);

        Operation expResult = Operation.DIVIDE;
        Operation result = instance.getOperation();
        assertEquals(expResult, result);
    }


}
