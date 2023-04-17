package main;

public class Main {
    public static void main(String[] args) {

        int[][] pairs = {
                {1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5},
                {15, 21}, {4, 8}, {4, 9}, {9, 11}, {14, 4}, {13, 12},
                {12, 9}, {15, 13}
        };
        Solution solution = new Solution();
        solution.setPairs(pairs);
        System.out.println(solution.hasCommonAncestor(6,8)); // true
        System.out.println(solution.hasCommonAncestor( 6, 9)); // true
        System.out.println(solution.hasCommonAncestor(1, 3)); // false
        System.out.println(solution.hasCommonAncestor( 3, 1)); // false
        System.out.println(solution.hasCommonAncestor( 7, 11)); // true
        System.out.println(solution.hasCommonAncestor( 6, 5)); // true
        System.out.println(solution.hasCommonAncestor( 5, 6)); // true
        System.out.println(solution.hasCommonAncestor( 3, 6)); // true
        System.out.println(solution.hasCommonAncestor( 21, 11));

    }

}