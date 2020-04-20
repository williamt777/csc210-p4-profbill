import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * These are Helper methods for Program 04. This is optional. It's just one way
 * to set this up. It's also a place where I can share more P4 Helper code.
 * 
 * @author Prof Bill, Cole P, Dev T, Luis G, Jason E
 * @version 0.1
 */
public class GraphHelper {

  /**
   * Sums the weights of all edges in a Graph210. 
   * @param g The graph
   * @return Returns the sum of all edge weights
   */
  public static double sumAllEdges( Graph210 g) { 
    List<Edge210> allEdges = g.allEdges();
    double sum = 0.0;
    for( Edge210 e: allEdges) {
      sum += e.getWeight();
    }
    return sum;
  }

  /**
   * Returns the smallest graph with 1 vert and no edges. It's good for testing!
   * @return Returns the smallest graph
   */
  public static Graph210 createSmallestGraph() {
    Graph210 g = new MyGraph( 0);
    return g;
  }

  /**
   * Returns a teeny-tiny graph with 2 verts and 1 edge between them.
   * @return Returns the tiny graph
   */
  public static Graph210 createTinyGraph() {
    Graph210 g = new MyGraph( 2);
    g.addEdge( 0, 1, 1.0);
    // g.addEdge( 0, 0, 2.0);
    // g.addEdge( 1, 1, 3.0);
    return g;
  }

  /**
   * Returns true if two graphs are equal.
   * @param g1 Graph one
   * @param g2 Graph two
   * @return Returns number of errors found; if graphs are equal, then return is 0. 
   */
  public static int compareGraphs( Graph210 g1, Graph210 g2) {
    if( g1.numVerts() != g2.numVerts()) { return 1; }
    if( g1.numEdges() != g2.numEdges()) { return 1; }

    int errorCount = 0;

    // normalize edges so we can compare them
    g1.normalizeEdges();
    g2.normalizeEdges();

    List<Edge210> edgeList1 = g1.allEdges();
    List<Edge210> edgeList2 = g2.allEdges();
    for( int i = 0; i < g1.numEdges(); i++) { 
      Edge210 e1 = edgeList1.get(i);
      Edge210 e2 = edgeList2.get(i);
      if( e1.getVert1() != e2.getVert1()  ||  
          e1.getVert2() != e2.getVert2()  ||
          e1.getWeight() != e2.getWeight()) {
        errorCount++;
        if( errorCount <= 10) {
          System.out.println( "compareGraphs: Unequal edges at edge num=" + i);
        }
      }
    }
    return errorCount;
  }
 
  /**
  * Read an undirected graph from a file.
  * Error checking is pretty minimal, and any error returns a null graph.
  * 
  * The format of the file is from Sedgewick, algs4.cs.princeton.edu/43mst:
  *   num-vertices
  *   num-edges
  *   v1 v2 weight   // one line per edge
  * 
  * @param fileName name of graph file
  * @return Returns a new Graph210, or null on error
  */
  public static Graph210 readGraphFile( String fileName) {
    if( fileName == null) { return null; } // guard
    Graph210 g = null;

    try {
      File file = new File(fileName); // open the file
      Scanner scanner = new Scanner(file);

      // Step 1: read the header
      int numVerts = scanner.nextInt();
      int numEdges = scanner.nextInt();

      // Step 2: create the graph
      g = new MyGraph( numVerts);

      // Step 3: read all the edges
      for( int i = 0; i < numEdges; i++) {
        Edge210 e = readEdge( scanner, g);
        if( e == null) { return null; }
      }
      scanner.close();
    } catch( Exception exc) {
      System.out.println( exc); // just print and bail on any error
      return null;
    }

    return g;
  } 

  /**
   * Read an edge from the file scanner.
   * @param scanner The file
   * @param g The graph
   * @return Returns the edge created; on error, null is returned
   */
  private static Edge210 readEdge( Scanner scanner, Graph210 g) {
    // format is: v1 v2 weight
    int v1 = scanner.nextInt();
    int v2 = scanner.nextInt();
    double weight = scanner.nextDouble();

    // addEdge does some nice checking for us
    // on error, it prints an error message and returns null
    Edge210 e = g.addEdge( v1, v2, weight);
    return e;
  }

}
