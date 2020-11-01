/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.Scanner;

/**
 *
 * @author abdelrhman shoukry
 */
 class MST {

    // to point to edges
    class Edge {

        private int node1, node2, weight;

    };

    private void quickSort(Edge arr[], int low, int high) {
        // if the array is empty return
        if (arr == null || arr.length == 0) {
            return;
        }
        // if the pointer for the left side is bigger
        // than or equal to the pointer to the right side 
        if (low >= high) {
            return;
        }

        int middle = low + (high - low) / 2;
        int pivot = arr[middle].weight;
        // counter for left side and right 
        int i = low, j = high;

        while (i <= j) {
            // to make the left array of the pivot bigger than the privot
            //we pick the elements which value is smaller than the pivot
            while (arr[i].weight > pivot) {
                i++;
            }
            // to make the right array of the pivot smaller than the pivot
            //we pick the elements which value is bigger than the pivot
            while (arr[j].weight < pivot) {
                j--;
            }

            if (i <= j) {
                // swaping the edges which weights are compared to the pivot
                Edge temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                // to take the unsorted elements after swapping
                i++;
                j--;
            }
        }
        // recursion to sort the left side of the array
        // if j is smaller than low then the left side of the array is sorted
        if (low < j) {
            quickSort(arr, low, j);
        }
        // recursion to sort the right side of the array
        // if i is bigger than high then the right side of the array is sorted
        if (high > i) {
            quickSort(arr, i, high);
        }
    }

    // to point to sets
    class set {

        int parent;
    };

    private final int V, E;
    private final Edge edges[];

    MST(int v, int e) {

        V = v;
        E = e;

        // making the edges
        edges = new Edge[E];

        for (int i = 0; i < e; ++i) {
            edges[i] = new Edge();
        }
    }

    private int getroot(set sets[], int i) {
        // if the parent of the node isn't the node itself
        if (sets[i].parent != i) {
            // make the parent the node it self 
            //(this will let us know if this edges will make a cycle or not)
            sets[i].parent = getroot(sets, sets[i].parent);
        }
        return sets[i].parent;
    }

    //to store the cost in it
    private static int cost = 0;

    public void Kruskal() {

        // step 1 : create set for each neode
        set sets[] = new set[V];

        // step 2 : initialize edges of the safeEdge
        // to get the MST and Subtract the weigth from the cost
        Edge result[] = new Edge[V];

        for (int i = 0; i < V; ++i) {
            sets[i] = new set();
            // the initial parent of all nodes is itself
            sets[i].parent = i;
            // making the edges
            result[i] = new Edge();
        }

        // step 3 : sort edges
        // sorting the edges in decreasing order (by weigth)
        // note that we want to get the minimum cost of removing edges
        // thats why we won't sort it in increasing order
        quickSort(edges, 0, E - 1);

        // counter for the MST edges
        int e = 0;

        // counter for the original edges
        int i = 0;

        // step 4 : while more than one set remains in the forest
        while (e < V - 1) {

            //to get the parent (set) of the nodes
            int x = getroot(sets, edges[i].node1);
            int y = getroot(sets, edges[i].node2);

            //step 5 : if x and y belongs to different sets :
            // if the parent (set) of both nodes is the same 
            // it will make a cycle
            if (x != y) {
                //step 5.1 : add the edge to the safeEdge set
                result[e] = edges[i];

                // step 5.2 : calculate the cost
                // substracting the cost of the result edges
                // to get the cost of the removed edges
                cost -= result[e++].weight;

                // step 5.3 :merge the sets x and y
                //changing the parent (set) of x to be y
                sets[x].parent = y;
            }
            i++;
        }

        System.out.println("the minimum cost is : ");

        System.out.println(cost);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int V, E;

        do {

            System.out.println("enter the number of nodes and edges : ");
            V = s.nextInt();
            E = s.nextInt();

            if (V <= 1) {
                System.out.println("number of nodes must be bigger than 1\ntry again\n");
            } else if (E < V - 1) {
                System.out.println("number of edges can't be smaller than (number of nodes - 1)\ntry again\n");
            } // maximum number of nodes = (V * (V - 1))/2
            else if (E > (V * (V - 1)) / 2) {
                System.out.println("number of edges must be smaller than (V * (V - 1))/2\n"
                        + "note : V is the number of nodes\ntry again\n");
            }
        } while (V <= 0 || E < V - 1 || E > (V * (V - 1)) / 2);

        MST graph = new MST(V, E);

        for (int i = 0; i < E; i++) {
            System.out.println("enter the edge number " + (i + 1) + " : ");

            // substacting 1 because the arrays is 0
            // based and the first node is 1
            graph.edges[i].node1 = s.nextInt() - 1;
            graph.edges[i].node2 = s.nextInt() - 1;

            //the user may enter invalid number 
            if ((graph.edges[i].node1 < 0 || graph.edges[i].node1 >= V)
                    || (graph.edges[i].node2 < 0 || graph.edges[i].node2 >= V)) {
                System.out.println("Invalid number enter a number bigger than 0\n"
                        + "and  smaller than or equal to the number of nodes\n");
                i--;
                continue;
            }

            //node1 may be smaller than node2 so the value will be negative
            if (graph.edges[i].node1 > graph.edges[i].node2) {
                graph.edges[i].weight = graph.edges[i].node1 - graph.edges[i].node2;
            } else {
                graph.edges[i].weight = graph.edges[i].node2 - graph.edges[i].node1;
            }

            // to get the total cost of all edges
            cost += graph.edges[i].weight;
        }
        graph.Kruskal();
    }
}
