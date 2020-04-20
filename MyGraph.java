import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is an undirected graph. 
 * It's my implementation of the P4 Graph210 interface.
 */
public class MyGraph implements Graph210 {
  /** num verts in the graph */
  private int numVerts;
  /** list of all edges in the graph */
  private ArrayList<Edge210> edgeList;
  /** adjacency list, one entry per vertex */
  private AdjacencyList[] adjList;

  /**
   * ctor
   * @param numVerts Number of verts in the graph; if this is <= 0, then 1 is used instead
   */
  public MyGraph( int numVerts) {
    if( numVerts <= 0) { numVerts = 1; }
    this.numVerts = numVerts;
    this.edgeList = new ArrayList<>();

    // create adjacency list: 1) array, 2) create list object for each array slot
    this.adjList = new AdjacencyList[numVerts];
    for( int i = 0; i < numVerts; i++) {
      this.adjList[i] = new AdjacencyList(i);
    }
  }

  @Override
  public int numVerts() { return this.numVerts; }

  @Override
  public int numEdges() { return this.edgeList.size(); }

  /**
   * Adds an edge to the graph.
   * On bad data, the graph is not changed and null is returned.
   */
  @Override
  public Edge210 addEdge(int v1, int v2, double weight) {
    // error checking - return null on any problem
    // BILL: add error messages later?
    if( v1 < 0  ||  v1 >= this.numVerts) {
      System.out.println("Error: addEdge() has bad v1=" + v1);
      return null;
    }
    if( v2 < 0  ||  v2 >= this.numVerts) {
      System.out.println("Error: addEdge() has bad v2=" + v2);
      return null;
    }
    if( weight <= 0.0) { 
      System.out.println( "Error: addEdge has bad weight=" + weight);
      return null;
    }

    // ok, happy case: create the edge, add it to the arraylist, and return
    Edge210 e = new MyEdge( v1, v2, weight);
    this.edgeList.add( e);  // update edge list
    this.adjList[v1].addAdjacentEdge(e);  // update adj list too
    this.adjList[v2].addAdjacentEdge(e);
    return e;
  }

  @Override
  public List<Edge210> edges(int v) {
    if( v < 0  ||  v >= this.numVerts) { return new ArrayList<>(); }

    AdjacencyList al = this.adjList[v];
    return al.getEdges();
  }

  @Override
  public List<Edge210> allEdges() {
    return this.edgeList;
  }

  @Override
  public void normalizeEdges() {
    // step 1: normalize all the edges, individually
    for( Edge210 curEdge: allEdges()) {
      curEdge.normalize();
    }

    // step 2: sort the normalized edges
    Collections.sort(this.edgeList, new NormalEdgeComp());
  }

  /**
   * Print the MyGraph in Sedgewick format.
   * Source: algs4.cs.princeton.edu/43mst
   */
  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();

    // print the header: num verts, num edges
    sb.append( this.numVerts());
    sb.append( "\n");
    sb.append( this.numEdges());
    sb.append( "\n");

    // print all edges
    for( Edge210 e: this.edgeList) {
      sb.append( e.toString());
      sb.append( "\n");
    }
    return sb.toString();
  }

  /**
   * Comparator used to sort edges in a normalized graph.
   * We do this so it's easy to compare two normalized graph
   * becuase normalized edges should be identical.
   */
  public class NormalEdgeComp implements Comparator<Edge210> {

    @Override
    public int compare(Edge210 o1, Edge210 o2) {
      int diff = o1.getVert1() - o2.getVert1();
      if( diff == 0) {
        diff = o1.getVert2() - o2.getVert2();
      }
      if( diff == 0) {
        if( o1.getWeight() > o2.getWeight()) { diff = 1; }
        else if( o1.getWeight() < o2.getWeight()) { diff = -1; }
      }
      return diff;
    }
  }
}
