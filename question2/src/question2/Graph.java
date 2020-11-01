/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question2;

import java.util.Scanner;

class Graph {

   private final int islands;
   private final LinkedList edges[];
   private final int stuck[];
   private int max = -1;

    public Graph(int islands) {

        this.islands = islands;

        LinkedList edges[] = new LinkedList[islands];

        this.edges = edges;
        stuck = new int[islands];

        for (int i = 0; i < islands; i++) {
            edges[i] = new LinkedList();
        }
    }

    public void addIsland(int src, int dest) {

        this.edges[src].insert(dest);

    }

    private void TopologicalSort(boolean[] visited, int[] indegreeCounter, LinkedList paths) {

        boolean flag = false;

        for (int i = 0; i < this.islands; i++) {
            
            if (!visited[i] && indegreeCounter[i] == 0) {

                visited[i] = true;
                paths.insert(i);

                for (int j = 0; j < this.edges[i].size(); j++) {
                    int index = this.edges[i].get(j);
                    indegreeCounter[index]--;
                }
                

                TopologicalSort(visited, indegreeCounter, paths);

                visited[i] = false;
                paths.deleteAt(paths.size() - 1);

                for (int j = 0; j < this.edges[i].size(); j++) {
                    int index = this.edges[i].get(j);
                    indegreeCounter[index]++;
                }

                flag = true;
            }
        }
        if (!flag) {
            
//            paths.forEach(i -> System.out.print((i + 1) + " "));
//            System.out.println();

            stuck[paths.get(islands - 1)]++;
            if (stuck[paths.get(islands - 1)] > max) {
                max = stuck[paths.get(islands - 1)];
            }
        }
    }

    public void visitTheInitialIsland(int initial) {
        boolean[] visited = new boolean[this.islands];
        int[] indegreeCounter = new int[this.islands];

        for (int i = 0; i < this.islands; i++) {

            for (int j = 0; j < this.edges[i].size(); j++) {
                int index = this.edges[i].get(j);
                indegreeCounter[index]++;
            }
        }

        LinkedList paths = new LinkedList();

        visited[initial] = true;
        paths.insert(initial);

        for (int j = 0; j < this.edges[initial].size(); j++) {
            int index = this.edges[initial].get(j);
            indegreeCounter[index]--;
        }

        TopologicalSort(visited, indegreeCounter, paths);

        System.out.println("you will stuck in islands number : ");
        for (int i = 0; i < islands; i++) {
            if (max == stuck[i]) {
                System.out.println((i + 1) + " ");
            }
        }

    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter the number of islands : ");
        int islands = s.nextInt();

        System.out.print("Enter the number of bridges : ");
        int bridges = s.nextInt();

        System.out.print("Enter the number of the initial island : ");
        int initial = s.nextInt() - 1;

        Graph graph = new Graph(islands);

        System.out.println("Enter the edges line by line");
        for (int i = 0; i < bridges; i++) {
            graph.addIsland(s.nextInt() - 1, s.nextInt() - 1);
        }

       // System.out.println("All Topological sorts");
        graph.visitTheInitialIsland(initial);
    }
   
}

// 7 9 7 1 2 2 3 1 3 3 4 2 6 6 4 6 5 7 5 7 2
// 7 9 1 1 2 2 3 1 3 3 4 2 6 6 4 6 5 7 5 7 2
// 6 8 1 1 2 1 3 2 4 2 5 3 4 3 6 4 5 4 6
// 6 6 6 6 3 6 1 5 1 5 2 3 4 4 2
// 6 6 5 6 3 6 1 5 1 5 2 3 4 4 2
// 5 7 1 1 2 1 3 1 4 1 5 2 4 2 5 3 4
 
 /*
5 2 3 4 0 1 - 5 2 3 4 0 1 
5 2 3 4 1 0 - 5 2 3 4 1 0 
5 2 4 0 3 1 - 5 2 4 0 3 1 
5 2 4 3 0 1 - 5 2 4 3 0 1 
5 2 4 3 1 0 - 5 2 4 3 1 0 
5 4 0 2 3 1 - 5 4 0 2 3 1 
5 4 2 0 3 1 - 5 4 2 0 3 1 
5 4 2 3 0 1 - 5 4 2 3 0 1 
5 4 2 3 1 0 - 5 4 2 3 1 0
 */
