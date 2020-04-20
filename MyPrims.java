import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
/**
 * This class implements the Prim's algorithm for the min spanning tree (MST) of a graph.
 * This flavor of Prim's runs a PQ of PrimsRecord structures.
 */
public class MyPrims implements PrimsAlgorithm {
  /** the graph */
  private Graph210 theGraph;
  /** array with graph status while Prim's is running  */
  private PrimsRecord runArray[];
  /** Graph210 of the Prim's MST result */
  private Graph210 primsGraph;

  /**
   * ctor
   * @param g The graph
   */
  public MyPrims( Graph210 g) {
    theGraph = g;
    runArray = null;
    primsGraph = null;
  }

  @Override
  public Graph210 getGraph() { 
    return this.theGraph;
  }

  /**
   * Run Prim's min spanning tree algorithm, starting at vert=0.
   */
  @Override
  public void runPrims() {
    int startVert = 0;

    // init array of PrimRecord objects, one per vert
    initRunArray(startVert);

    // create PQ with PrimRecord comparator, sort by distance
    PrimsRecordComparator comp = new PrimsRecordComparator();
    PriorityQueue<PrimsRecord> pq = new PriorityQueue<>(comp);

    pq.add(runArray[startVert]);
    while( ! pq.isEmpty()) {
      PrimsRecord rec = pq.poll();
      int v = rec.vert;
      runArray[v].known = true;

      List<Edge210> adjEdges = theGraph.edges(v);
      for( Edge210 curEdge: adjEdges) {
        int otherV = curEdge.otherVert(v);
        if( ! runArray[otherV].known  &&  curEdge.getWeight() < runArray[otherV].distance) {
          // update the vertex with this better edge!
          PrimsRecord otherRec = runArray[otherV];
          pq.remove( otherRec);
          otherRec.distance = curEdge.getWeight();
          otherRec.parent = v;
          pq.add( otherRec);
        }
      }
    }
    this.primsGraph = createPrimsGraph();
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
   * Create the Prim's MST graph from the runArray information.
   * @return Returns the Graph210 for Prim's MST
   */
  private Graph210 createPrimsGraph() {
    Graph210 g = new MyGraph( this.theGraph.numVerts());

    for( PrimsRecord pr: this.runArray) {
      // if parent is -1, then no edge; otw, add an edge to Prim's graph
      if( pr.parent != -1) {
        g.addEdge(pr.vert, pr.parent, pr.distance);
      }
    }
    return g;
  }

  /**
   * Init an array of PrimRecord objects, one per vert.
   * @param startVert The start vert for Prim's; his distance is set to zero
   */
  private void initRunArray(int startVert) {
    runArray = new PrimsRecord[theGraph.numVerts()];
    for( int i = 0; i < runArray.length; i++) {
      runArray[i] = new PrimsRecord( i);
    }
    runArray[startVert].distance = 0;
  }

  /**
   * This class does the Prims accounting while the algorithm is running.
   * Each vert holds a distance, parent vert, and known flag.
   * Fields are access directly because this is an inner class.
   */
  public class PrimsRecord {
    public int vert;
    public double distance;
    public int parent;
    public boolean known;

    /**
     * ctor
     * @param v The vert
     */
    public PrimsRecord(int v) {
      vert = v;
      distance = Integer.MAX_VALUE;
      parent = -1;
      known = false;
    }
  }

  /**
   * This class sorts PrimsRecord objects for the priority queue in Prim's.
   * The objects are sorted by distance.
   */
  public class PrimsRecordComparator implements Comparator<PrimsRecord> {

    @Override
    public int compare(PrimsRecord o1, PrimsRecord o2) {
      double cmp = o1.distance - o2.distance;
      if( cmp > 0.0) { return 1; }
      else if( cmp < 0.0) { return -1; }
      else { return 0; }
    }
  }
}
