import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * MyPrims2 uses a PQ of edges and fancy marking of verts. It's from Sedgewick mostly.
 */
public class MyPrims2 implements PrimsAlgorithm {
  /** the graph */
  private Graph210 theGraph;
  /** Graph210 of the Prim's MST result */
  private Graph210 primsGraph;

  /**
   * ctor
   * @param g The graph
   */
  public MyPrims2( Graph210 g) {
    theGraph = g;
    primsGraph = null;
  }

  @Override
  public Graph210 getGraph() { 
    return this.theGraph;
  }

  /**
   * Run the Prims2 algorithm, starting at vert=0.
   * Prims2 uses a PQ of edges and fancy vert marking.
   */
  @Override
  public void runPrims() {
    int startVert = 0;

    // init marked array, edge lists, and priority queue
    boolean marked[] = new boolean[theGraph.numVerts()];
    ArrayList<Edge210> mstEdges = new ArrayList<>();
    EdgeWeightComparator comp = new EdgeWeightComparator();
    PriorityQueue<Edge210> pq = new PriorityQueue<>(comp);

    // visit startVert to seed the PQ
    visit( startVert, pq, marked);

    while( ! pq.isEmpty()) {
      Edge210 curEdge = pq.poll();
      int v1 = curEdge.getVert1();
      int v2 = curEdge.getVert2();

      // only add edge to MST if a vert is not yet marked
      if( !marked[v1]  ||  ! marked[v2]) { mstEdges.add( curEdge); }

      visit(v1, pq, marked);
      visit(v2, pq, marked);
    }

    this.primsGraph = createPrimsGraph( mstEdges);
  }

  /**
   * Return the Graph210 for Prim's MST result
   * @return Returns the Prim's Graph210
   */
  @Override
  public Graph210 getPrimsGraph() { 
    return this.primsGraph;
  }

  /**
   * Visit a vert during Prims2. Add its edges to the PQ and then mark is visited.
   * @param vert The vert
   * @param pq Priority Queue of edges
   * @param marked boolean array of marked status for each vert 
   */
  private void visit( int vert, PriorityQueue<Edge210> pq, boolean[] marked) {
    if( marked[vert]) { return; } // already visited!

    List<Edge210> startEdges = theGraph.edges( vert);
    for( Edge210 e: startEdges) {
      pq.add(e);
    }
    marked[vert] = true;
  }

  /**
   * Create the Prim's MST graph from the list of MST edges.
   * @return Returns the Graph210 for Prim2 MST
   */
  private Graph210 createPrimsGraph( ArrayList<Edge210> mstEdges) {
    Graph210 g = new MyGraph( this.theGraph.numVerts());

    for( Edge210 e: mstEdges) {
      g.addEdge(e.getVert1(), e.getVert2(), e.getWeight());
    }
    return g;
  }

  /**
   * Compare two edges by weight; this sort is used by the Prims2 PQ.
   */
  public class EdgeWeightComparator implements Comparator<Edge210> {

    @Override
    public int compare(Edge210 e1, Edge210 e2) {
      double cmp = e1.getWeight() - e2.getWeight();
      if( cmp > 0.0) { return 1; }
      else if( cmp < 0.0) { return -1; }
      else { return 0; }
    }
  }
}
