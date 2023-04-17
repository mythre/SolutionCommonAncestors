package main;

import java.util.*;

/*
Assumption from the inputs
1) The person itself is not considered as his ancestor because( 1,3) gives false  though 1 is the parent of 3
2) There is no cyclic relations(Acyclic graph)
3) There is only unidirectional relation(directed graph)
 */
public class Solution {
     int[][] pairs;

    public void setPairs(int[][] pairs){
        this.pairs=pairs;
    }
    public boolean hasCommonAncestor(int node1, int node2) {
        Map<Integer, List<Integer>> inputGraph;
        inputGraph = buildGraph();
        if (inputGraph != null) {
            Set<Integer> ancestorsA = findAncestors(node1, inputGraph);
            Set<Integer> ancestorsB = findAncestors(node2, inputGraph);
            if (ancestorsA == null || ancestorsB == null) {
                return false;
            }
            for (int ancestor : ancestorsA) {
                if (ancestorsB.contains(ancestor)) {
                    return true;
                }
            }

            return false;
        }
        return false;
    }

    public  Map<Integer, List<Integer>> buildGraph() {
        //Building grpah with child as index and parents as list to it
        Map<Integer, List<Integer>> graph = new HashMap<>();
        try {
            for (int[] pair : this.pairs) {
                int parent = pair[0];
                int child = pair[1];
                List<Integer> parents = graph.getOrDefault(child, new ArrayList<>());
                parents.add(parent);
                graph.put(child, parents);
            }

        }
        catch(ArrayIndexOutOfBoundsException exception) {
            System.out.println("there are no two nodes in the pair "+exception);

        }
        return  graph;


    }


    public  Set<Integer> findAncestors(int person,Map<Integer, List<Integer>> inputGraph)
    {  //Starting from the child,going depth first with parents and collecting ancestors
        Set<Integer> visited = new HashSet<>();
        dfs(person, visited, inputGraph);
        return visited;
    }

     void dfs(int node, Set<Integer> visited, Map<Integer, List<Integer>> inputGraph) {
        for (int parent : inputGraph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(parent)) {
                visited.add(parent);
                dfs(parent, visited, inputGraph);
            }
        }}

}
