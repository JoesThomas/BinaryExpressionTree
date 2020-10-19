package cs21120.assignment2018;

import java.util.ArrayList;
import java.util.*;


import static cs21120.assignment2018.IExpression.Operation.*;

public class Expressionjot38 implements IExpression, IExpressionFactory {
    IExpression left;
    IExpression right;
    Operation operation;
    int value;

    /**
     * This is the constructor for the internal nodes. It requires 3 parts to create an internal node. The left branch
     * the right branch and the Operation value of the node.
     *
     * @param left      is the left branch of the node.
     * @param right     is the right branch of the node.
     * @param operation is the operation that belongs to the node.
     */

    public Expressionjot38(IExpression left, IExpression right, Operation operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    /**
     * This is the constructor for the leaf node. It only requires a single value to be created.
     *
     * @param value is the amount of a leaf node.
     */

    public Expressionjot38(int value) {
        this.value = value;
    }

    /**
     * This is a default constructor that I have produced so that the other constructors are not used in the wrong case
     */

    public Expressionjot38() {
    }

    /**
     * Evaluate the expression tree recursively, checking that the result is a
     * positive integer at every step, as required in a Countdown solution
     *
     * This method that I wrote returns the value of the expression tree created and works out the answer to the
     * equation. I found this part quite difficult, I managed to get it correct after going to CS advice and getting a
     * better understanding of the recursion used in this function. The function first checks whether or not there is a
     * leaf value that can be read, otherwise the code will work out the value of the internal nodes. If a negative or
     * fractional value is created by the method then an illegal argument exception is thrown so as to follow the rules
     * of countdown. Once I got my head around how to write this function I quite enjoyed implementing it due to the
     * recursion in it.
     *
     * @return returns the result of evaluating the expression tree
     * @throws Exception throws an exception if a problem is encountered, including if the Countdown rules are violated.
     */
    @Override
    public int evaluateCountdown() throws Exception {
        if (isLeaf()) {
            return getValue();
        } else if (!isLeaf()) {
            if (getOperation() == ADD) {
                return getLeft().evaluateCountdown() + getRight().evaluateCountdown();
            } else if (getOperation() == SUBTRACT) {
                int subtract = getLeft().evaluateCountdown() - getRight().evaluateCountdown();
                if (subtract < 0) {
                    throw new IllegalArgumentException();
                } else if (subtract > 0) {
                    return subtract;
                }
            } else if (getOperation() == MULTIPLY) {
                return getLeft().evaluateCountdown() * getRight().evaluateCountdown();
            } else if (getOperation() == DIVIDE) {
                if (getLeft().evaluateCountdown() % getRight().evaluateCountdown() > 0) {
                    throw new IllegalArgumentException();
                }
                return getLeft().evaluateCountdown() / getRight().evaluateCountdown();
            }
        }
        return 0;
    }

    /**
     * Return the expression as a String.  For clarity brackets should be placed around each part
     * to ensure it can be read in the right order.
     *
     * This function I found difficult to implement as I was not sure of the best way to print out all the equation as I
     * did not know what the most efficient method was for doing this. After a few attempts I found that this was the
     * way of creating a toString method with the least lines of code. The function first of all creates two string
     * variables which represent the left and right of the node. It then checks for a leaf value and returns the value
     * of the leaf node. Then it prints out the value of the right and left using a recursive method after checking that
     * the values contain values and are not empty. It will then perform the toString method on the operations in the
     * expression tree. It also creates a default operation.
     *
     * @return Returns the string representation of the expression
     */

    public String toString() {
        String leftStr;
        String rightStr;
        if (left == null && right == null) {
            return "" + value;
        }
        if (left == null) {
            leftStr = null;
        } else leftStr = left.toString();
        if (right == null) {
            rightStr = null;
        } else rightStr = right.toString();

        switch (operation) {
            case ADD:
                return "(" + leftStr + "+" + rightStr + ")";
            case SUBTRACT:
                return "(" + leftStr + "-" + rightStr + ")";
            case MULTIPLY:
                return "(" + leftStr + "*" + rightStr + ")";
            case DIVIDE:
                return "(" + leftStr + "/" + rightStr + ")";
            default:
                return "(" + leftStr + "?" + rightStr + ")";

        }
    }

    /**
     * Assigns the values of an internal node
     *
     * This function was relatively simple to implement as I only needed the left value, the right value and the
     * operation as this function assigns the value of an internal node.
     *
     * @param left  the expression to use for the left hand side
     * @param right the expression to use for the right hand side
     * @param op    the operation to use at the node
     */
    @Override
    public void set(IExpression left, IExpression right, Operation op) {
        this.left = left;
        this.right = right;
        this.operation = op;
    }

    /**
     * Assign the value of a leaf node
     *
     * This function was relatively simple to implement as I only needed the value of the leaf and after creating the
     * other set method this was very simple to implement.
     *
     * @param value the value to use at this leaf
     */
    @Override
    public void set(int value) {
        this.value = value;
    }

    /**
     * Get the value of a leaf node
     *
     * This function was very simple to complete as it just required a change of the return to return the value of a
     * leaf.
     *
     * @return returns the leaf node value
     * @throws Exception should throw an Exception if this is not a leaf node
     */
    @Override
    public int getValue() throws Exception {
        return value;
    }

    /**
     * Checks if this is a leaf node (both children null) or not (both children not null)
     *
     * This function took me a few minutes to figure out but once I realized that leaf nodes are at the bottom of the
     * expression tree I quickly worked out the syntax.
     *
     * @return returns true if this is a leaf node
     */
    @Override
    public boolean isLeaf() {
        if ((left == null) && (right == null)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return an iterator for this equation.  An iterator implementation is provided,
     * so you just need to return an instance of it.
     * <p>
     * I did not find this part too difficult as it stated what was needed to be done in the code. It took me a few
     * attempts to work out what the root would be called. It returns the root of the expression tree.
     *
     * @return returns an iterator
     */
    @Override
    public Iterator<IExpression> getIterator() {
        return new ExpressionIterator(this);
    }

    /**
     * Get the left side of the equation
     *
     * This method returns the left branch of an expression tree. I found it quite simple to implement, it returns the
     * value of the left branch. I could put in an if statement to check whether it is actually an internal node that is
     * being checked.
     *
     * @return the left side node, null if a leaf
     */
    @Override
    public IExpression getLeft() {
        return left;
    }

    /**
     * Get the right side of the equation
     *
     * This method returns the left branch of an expression tree. I found it quite simple to implement, it returns the
     * value of the left branch. I could put in an if statement to check whether it is actually an internal node that is
     * being checked.
     *
     * @return return the right side, null if it is a leaf
     */
    @Override
    public IExpression getRight() {
        return right;
    }

    /**
     * Get the operations
     * <p>
     * This method returns the left branch of an expression tree. I found it quite simple to implement, it returns the
     * value of the left branch. I could put in an if statement to check whether it is actually an internal node that is
     * being checked.
     *
     * @return returns the operation, null if it is a leaf
     */
    @Override
    public Operation getOperation() {
        return operation;
    }

    /**
     * Returns a random equation using (some of) the values given and the available operations
     *
     * This method creates a random equation using the operators provided and rhe array "vals". I have made it as random
     * as I can possibly make it so that it fulfills the criteria. I have used the worksheet to help me with this as I
     * found it quite difficult. I went to CS advice so that I could have help with it as I was unsure how to go about
     * writing this function.
     *
     * @param vals the values that can be used in the equation
     * @return return the root of the random equation tree
     */
    @Override
    public IExpression createRandomEquation(int[] vals) {
        Random rand = new Random();
        ArrayList<IExpression> nums = new ArrayList<IExpression>(); //this puts the values into a data structure

        int amountRem = rand.nextInt(vals.length - 1); //this creates the leaves
        for (int i : vals) {
            nums.add(createLeaf(i));
        }
        for (int j = 0; j < amountRem; j++) { //this removes leaves at random
            int removed = rand.nextInt(nums.size());
            nums.remove(removed);
        }

        while (nums.size() > 1) { //this removes pairs of trees from the arraylist and puts them into an internal node
            // at random. It also repeats the necessary steps.
            int l = rand.nextInt(nums.size());
            IExpression left = nums.remove(l);
            l = rand.nextInt(nums.size());
            IExpression right = nums.remove(l);
            int counter = rand.nextInt(4);
            IExpression t = createInternalNode(left, right, IExpression.Operation.values()[counter]);
            nums.add(t); //this places the tree back into the arraylist
        }
        return nums.get(0);
    }


    /**
     * Searches (somehow) for an equation that evaluates as close as possible to the target value
     *
     * This function should work out the way to create the target created. Unfortunately, I could not get this function
     * to work. I have reused the random expression tree generator to randomly generate expression trees that could
     * output the target value. To improve it I could make an array that collects the answers given alongside the equation
     * and after I have run through every single possibility I could then sort them by subtracting the answer from the
     * target and then print out the closest to the target along with the equation.
     *
     * @param vals the values available to use in the equation
     * @param target the target value
     * @return returns the equation that the system thinks is the best
     */
    @Override
    public IExpression findBestSolution(int[] vals, int target) {
        Random rand = new Random();
        ArrayList<IExpression> nums = new ArrayList<IExpression>();
        boolean solution = false;

        while (solution == false) {

            int amountRem = rand.nextInt(vals.length - 1);
            for (int i : vals) {
                nums.add(createLeaf(i));
            }
            for (int j = 0; j < amountRem; j++) {
                int removed = rand.nextInt(nums.size());
                nums.remove(removed);
            }

            while (nums.size() > 1) {
                int l = rand.nextInt(nums.size());
                IExpression left = nums.remove(l);
                l = rand.nextInt(nums.size());
                IExpression right = nums.remove(l);
                int counter = rand.nextInt(4);
                IExpression t = createInternalNode(left, right, IExpression.Operation.values()[counter]);
                nums.add(t);
            }

            try {
               if(findBestSolution(vals, target).evaluateCountdown() - target == 0){
                    System.out.println("You've done it");
                    solution = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return nums.get(0);
}

    /**
     * Factory method to return a leaf IExpression
     *
     * This method creates a new leaf node by using the constructor which I have created at the beginning of this class.
     * It takes in a value and uses that to create a leaf node.
     *
     * @param val the value to store in the leaf
     * @return returns the IExpression containing the value
     */
    @Override
    public IExpression createLeaf(int val) {
        return new Expressionjot38(val);
    }

    /**
     * Factory method to return a new IExpression with the specified children and operation
     *
     * This method creates a new internal node using the constructor which I created earlier in this class. It takes in
     * two leaf nodes and an operation value and uses them to create the expression tree.
     *
     * @param l  the left side of the equation
     * @param r  the right side of the equation
     * @param op the operation to use
     * @return returns the IExpression "l op r"
     */
    @Override
    public IExpression createInternalNode(IExpression l, IExpression r, Operation op) {
        return new Expressionjot38(l, r, op);
    }

}
/**
 * For the first part of the assignment, the implementation, I feel as if I deserve full marks for it as I have not used
 * too many lines of code and I have not overcomplicated it in any way whilst also fulfilling the criteria. The most
 * difficult parts for it were the toString method and the evaluateCountdown method in my opinion. But due to every test
 * passing I feel as if I meet all the requirements for it.
 *
 * For the second part of the assignment, the creating of the createRandomEquation function, I feel as if I deserve
 * around 90% to 100% of the marks due to my implementation working correctly. I also feel that due to my loops I have
 * used code relatively efficiently here which I feel would help achieve higher marks in this section.
 *
 * For the third part of the assignment, creating a findBestSolution function, I feel that I am deserving of 40%-50% as
 * I feel as if I am not too far off getting it to actually work. However, I put my marks so low in this section as my
 * function does not actually work.
 *
 * Overall, I feel as if this java class is deserving of around 85% to 90% of the marks available due to the reasons I
 * stated prior.
 *
 */
