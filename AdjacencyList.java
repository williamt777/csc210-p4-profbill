import java.util.ArrayList;
import java.util.List;

/**
 * This class is a list of edges adjacent to a vertex.
 * I used this class to avoid the goofy Java list of lists syntax in class.
 */
public class AdjacencyList {
  /** the vert that owns this list */
  private int vert;
  /** list of edges holding adjacent verts */
  private ArrayList<Edge210> edgeList;

  /**
   * ctor
   * @param vert The vert that owns the adjacency list
   */
  public AdjacencyList( int vert) {
    this.vert = vert;
    edgeList = new ArrayList<>();
  }

  /**
   * Getter for the vert field; returns the vert that owns this list.
   * @return Returns the vertex
   */
  public int getVert() { return this.vert; }

  /** Getter for the edges in this adj list.
   * @return Returns a list of adjacent edges; if none, then an empty list is returned, never null
  */
  public List<Edge210> getEdges() { return this.edgeList; }

  /**
   * Adds the edges to the adjacency list for the vert.
   * Note: Edges are never added more than once.
   * @param e The edge
   */
  public void addAdjacentEdge( Edge210 e) {
    if( ! this.edgeList.contains( e)) {
      this.edgeList.add( e);
    }
  }
}
