import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import java.util.ArrayList;
import java.util.Scanner;

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis {
    public abstract int getResult();

    public abstract void visitNode(TreeNode node);

    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    private int leafSum = 0;

    public int getResult() {
        return this.leafSum;
    }

    public void visitNode(TreeNode node) {
        // implement this
    }

    public void visitLeaf(TreeLeaf leaf) {
        this.leafSum += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private long redNodesProduct = 1;

    public int getResult() {
        return (int) this.redNodesProduct;
    }

    public void visitNode(TreeNode node) {

        if (node.getColor().equals(Color.RED)) {
            this.redNodesProduct *= node.getValue();
            this.redNodesProduct %= 1000000007;
        }

    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor().equals(Color.RED)) {
            this.redNodesProduct *= leaf.getValue();
            this.redNodesProduct %= 1000000007;
        }
    }
}

class FancyVisitor extends TreeVis {
    private int evenDepthNodeSum = 0;
    private int greenLeafSum = 0;

    public int getResult() {
        return Math.abs(evenDepthNodeSum - greenLeafSum);
    }

    public void visitNode(TreeNode node) {
        if (node.getDepth() % 2 == 0) {
            this.evenDepthNodeSum += node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor().equals(Color.GREEN)) {
            this.greenLeafSum += leaf.getValue();
        }
    }
}

public class Solution {

    private static HashMap<Integer, ArrayList<Integer>> edges = new HashMap<Integer, ArrayList<Integer>>();
    private static int values[];
    private static Color colors[];

    public static Tree solve() {
        // read the tree from STDIN and return its root as a return value of this
        // function
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = (scanner.nextInt());
        }

        colors = new Color[n];
        for (int i = 0; i < n; i++) {
            colors[i] = (scanner.nextInt() == 0 ? Color.RED : Color.GREEN);

        }

        ArrayList<Integer> neighbors;
        for (int i = 0; i < n - 1; i++) {
            Integer v = scanner.nextInt();
            Integer u = scanner.nextInt();

            if (edges.containsKey(v)) {
                neighbors = edges.get(v);
            } else {
                neighbors = new ArrayList<Integer>();
            }
            neighbors.add(u);
            edges.put(v, neighbors);

            if (edges.containsKey(u)) {
                neighbors = edges.get(u);
            } else {
                neighbors = new ArrayList<Integer>();
            }
            neighbors.add(v);
            edges.put(u, neighbors);
        }
        scanner.close();

        TreeNode tree = new TreeNode(values[0], colors[0], 0);
        buildTree(1, tree);

        return tree;
    }

    private static void buildTree(Integer parentNode, TreeNode treeNode) {
        int depth = treeNode.getDepth() + 1;

        for (Integer u : edges.get(parentNode)) {
            edges.get(u).remove(parentNode);

            if (!edges.get(u).isEmpty()) {
                TreeNode childNode = new TreeNode(values[u - 1], colors[u - 1], depth);
                treeNode.addChild(childNode);
                buildTree(u, childNode);
            } else {
                TreeLeaf childNode = new TreeLeaf(values[u - 1], colors[u - 1], depth);
                treeNode.addChild(childNode);
            }
        }
    }

    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}