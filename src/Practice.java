import java.util.HashSet;
import java.util.Set;

/**
 * A utility class providing various graph traversal methods using DFS.
 */
public class Practice {

  /**
   * Prints the value of every vertex reachable from the given starting vertex,
   * including the starting vertex itself. Each value is printed on a separate line.
   * The order of printing is unimportant.
   *
   * Each vertex's value should be printed only once, even if it is reachable via multiple paths.
   * It is guaranteed that no two vertices will have the same value.
   *
   * If the given vertex is null, this method prints nothing.
   *
   * @param vertex The starting vertex for the traversal.
   */
  public <T> void printVertexVals(Vertex<T> vertex) {
    printVertexVals(vertex, new HashSet<Vertex<T>>());
  }
  
  public <T> void printVertexVals(Vertex<T> vertex, Set<Vertex<T>> visited) {
    // Base case
    if (vertex == null) return;
    if (visited.contains(vertex)) return;
    
    visited.add(vertex);
    System.out.println(vertex.data);

    if (vertex.neighbors == null) return;

    for (Vertex<T> neighbor : vertex.neighbors) {
      printVertexVals(neighbor, visited);
    }
  }

  /**
   * Returns a set of all vertices reachable from the given starting vertex,
   * including the starting vertex itself.
   *
   * If the given vertex is null, an empty set is returned.
   *
   * @param vertex The starting vertex for the traversal.
   * @return A set containing all reachable vertices, or an empty set if vertex is null.
   */
  public <T> Set<Vertex<T>> reachable(Vertex<T> vertex) {
    Set<Vertex<T>> reachableVertices = new HashSet<>();
    reachableHelper(vertex, reachableVertices);
    return reachableVertices;
  }

  public <T> void reachableHelper(Vertex<T> vertex, Set<Vertex<T>> visited) {
    // Base case
    if (vertex == null) return;
    if (visited.contains(vertex)) return;

    visited.add(vertex);
    
    for (Vertex<T> neighbor : vertex.neighbors) {
      reachableHelper(neighbor, visited);
    }
  }

  /**
   * Returns the maximum value among all vertices reachable from the given starting vertex,
   * including the starting vertex itself.
   *
   * If the given vertex is null, the method returns Integer.MIN_VALUE.
   *
   * @param vertex The starting vertex for the traversal.
   * @return The maximum value of any reachable vertex, or Integer.MIN_VALUE if vertex is null.
   */
  public int max(Vertex<Integer> vertex) {
    Set<Vertex<Integer>> visited = new HashSet<>();
    return max(vertex, visited);
  }
  
  private int max(Vertex<Integer> vertex, Set<Vertex<Integer>> visited) {
    if (vertex == null || visited.contains(vertex)) return Integer.MIN_VALUE;

    visited.add(vertex);
    int maxVal = vertex.data;

    for (Vertex<Integer> neighbor : vertex.neighbors) {
      if (neighbor.data > maxVal) maxVal = neighbor.data;
      maxVal = Math.max(maxVal, max(neighbor, visited));
    }

    return maxVal;
  }

  /**
   * Returns a set of all leaf vertices reachable from the given starting vertex.
   * A vertex is considered a leaf if it has no outgoing edges (no neighbors).
   *
   * The starting vertex itself is included in the set if it is a leaf.
   *
   * If the given vertex is null, an empty set is returned.
   *
   * @param vertex The starting vertex for the traversal.
   * @return A set containing all reachable leaf vertices, or an empty set if vertex is null.
   */
  public <T> Set<Vertex<T>> leaves(Vertex<T> vertex) {
    Set<Vertex<T>> visited = new HashSet<>();
    Set<Vertex<T>> leaves = new HashSet<>();
    return leaves(vertex, visited, leaves);
  }
  
  public <T> Set<Vertex<T>> leaves(Vertex<T> vertex, Set<Vertex<T>> visited,  Set<Vertex<T>> leaves) {
    if (vertex == null || visited.contains(vertex)) return leaves;

    visited.add(vertex);

    if (vertex.neighbors.isEmpty()) leaves.add(vertex);

    for (Vertex<T> neighbor : vertex.neighbors) {
      leaves(neighbor, visited, leaves);
    }

    return leaves;
  }


  /**
   * Determines whether there exists a strictly increasing path from the given start vertex
   * to the target vertex.
   *
   * A path is strictly increasing if each visited vertex has a value strictly greater than
   * (not equal to) the previous vertex in the path.
   *
   * If either start or end is null, a NullPointerException is thrown.
   *
   * @param start The starting vertex.
   * @param end The target vertex.
   * @return True if a strictly increasing path exists, false otherwise.
   * @throws NullPointerException if either start or end is null.
   */

   /*
    Create a visited hashset in main method
    Create a helper method and pass visited 
    Create base cases for null start, null, end visited vertices
    Add newly encountered verices to visited
    Create a foreach loop
      if a neighbor.data > vertex.data
        recurse: if path exists with neighbor = return true
    return false
    */
  public boolean hasStrictlyIncreasingPath(Vertex<Integer> start, Vertex<Integer> end) {
    Set<Vertex<Integer>> visited = new HashSet<>();
    return hasStrictlyIncreasingPath(start, end, visited);
  }
  
  public boolean hasStrictlyIncreasingPath(Vertex<Integer> start, Vertex<Integer> end, Set<Vertex<Integer>> visited) {
    if (start == null || end == null) throw new NullPointerException();
    if (visited.contains(start)) return false;
    if (start == end) return true;

    visited.add(start);

    for (Vertex<Integer> neighbor : start.neighbors) {
      if (neighbor.data > start.data) {
        if (hasStrictlyIncreasingPath(neighbor, end, visited)) return true;
      }
    }

    return false;
  }
}