package tests;

import main.Solution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;



class SolutionTest {


    @ParameterizedTest
    @MethodSource("buildGraphTestData")
    public void buildGraph(int[][] pairs, Map<Integer, List<Integer>> expected) {
        Solution solution = new Solution();
        solution.setPairs(pairs);
        Map<Integer, List<Integer>> actual = solution.buildGraph();
        assertEquals(expected, actual);
    }



    private static Stream<Arguments> buildGraphTestData() {

        Map<Integer, List<Integer>> graph1 = new HashMap<>();
        graph1.put(3, Arrays.asList(1));
        graph1.put(4, Arrays.asList(2));
        graph1.put(5, Arrays.asList(2));
        graph1.put(6, Arrays.asList(3, 4, 5));
        Arguments test1 = Arguments.of(new int[][]{{1, 3}, {2, 4}, {2, 5}, {3, 6}, {4, 6}, {5, 6}}, graph1);

        Map<Integer, List<Integer>> graph2 = new HashMap<>();
        graph2.put(1, Arrays.asList(2, 3));
        graph2.put(2, Arrays.asList(4));
        graph2.put(3, Arrays.asList(4));
        graph2.put(4, Arrays.asList(6));
        graph2.put(5, Arrays.asList(6));
        Arguments test2 = Arguments.of(new int[][]{{2, 1}, {3, 1}, {4, 2}, {4, 3}, {6, 4}, {6, 5}}, graph2);

        Map<Integer, List<Integer>> graph3 = new HashMap<>();
        Arguments test3 = Arguments.of(new int[0][0], graph3);

        return Stream.of(test1,test2,test3);
    }

    @ParameterizedTest
    @MethodSource("provideInputData")
    void findAncestors_returnsCorrectAncestors(int person, Map<Integer, List<Integer>> inputGraph, Set<Integer> expectedAncestors) {
        Solution solution= new Solution();
        Set<Integer> actualAncestors = solution.findAncestors(person, inputGraph);

        assertEquals(expectedAncestors, actualAncestors);
    }

    private static Stream<Arguments> provideInputData() {
        // Define test data
        Map<Integer, List<Integer>> inputGraph1 = new HashMap<>();
        inputGraph1.put(1, Arrays.asList(2, 3));
        inputGraph1.put(2, Arrays.asList(4, 5));
        inputGraph1.put(3, Arrays.asList(6));
        inputGraph1.put(4, Arrays.asList(7));
        inputGraph1.put(5, Arrays.asList(7));
        inputGraph1.put(6, Arrays.asList(7));


        Map<Integer, List<Integer>> inputGraph2 = new HashMap<>();
        inputGraph2.put(1, Arrays.asList(2));
        inputGraph2.put(2, Arrays.asList(3));
        inputGraph2.put(3, Arrays.asList(4));


        // Return test data as stream of arguments
        return Stream.of(
                Arguments.of(7, inputGraph1, Collections.emptySet()), // no ancestors for node 7
                Arguments.of(1, inputGraph1,new HashSet<>(Arrays.asList(2, 3, 4, 5, 6,7)) ),
                Arguments.of(4, inputGraph1,new HashSet<>(Arrays.asList(7)) ),
                Arguments.of(1, inputGraph2, new HashSet<>(Arrays.asList(2, 3, 4)))

        );
    }
    @ParameterizedTest
    @MethodSource("provideTestData")
    public void testHasCommonAncestor(int node1, int node2, int[][] pairs, boolean expectedHasCommonAncestor) {
        Solution solution= new Solution();
        solution.setPairs(pairs);



        // invoke the hasCommonAncestor method and assert the result
        boolean actualHasCommonAncestor = solution.hasCommonAncestor(node1, node2);


        // verify the mock findAncestors and buildGraph calls were made with the expected arguments

        assertEquals(expectedHasCommonAncestor, actualHasCommonAncestor);

    }

    private static Stream<Object[]> provideTestData() {
        return Stream.of(
                new Object[] {1, 2, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}}, false},
                new Object[] {2, 3, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}},  true},
                new Object[] {1, 2, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}}, false},
                new Object[] {1, 1, new int[][]{{1, 2}, {2, 3}}, false},
                new Object[] {4, 5, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}},true},
                new Object[] {1,2, new int[][]{{1, 2}},false}

        );
    }



}