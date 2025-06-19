import java.util.Arrays;

public class UnionFind {
    private int[] vertices;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        vertices = new int[n];
        Arrays.fill(vertices, -1);
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex >= vertices.length){
            throw new IndexOutOfBoundsException("vertex " + vertex + " is not between 0 and " + (vertices.length - 1));
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        return -parent(find(v1));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int vertex) {
        validate(vertex);
        return vertices[vertex];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        if (!connected(v1, v2)) {
            if (sizeOf(v1) >= sizeOf(v2)) {// if the set that v1 is in is heavier...
                vertices[find(v1)] -= sizeOf(v2);
                vertices[find(v2)] = find(v1);
            } else {
                vertices[find(v2)] -= sizeOf(v1);
                vertices[find(v1)] = find(v2);
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        if(vertices[vertex] < 0){
            return vertex;
        }
        return find(vertices[vertex]);
    }

    public static void main(String[] args) {
        // Manual Test 1
        UnionFind disSet1 = new UnionFind(7);
        disSet1.union(1, 2);
        disSet1.union(0, 4);
        disSet1.union(0, 1);

        disSet1.union(3, 5);
        System.out.println(disSet1.find(5));// 3
        System.out.println(disSet1.find(2));// 0
        disSet1.union(5, 2);
    }
}
