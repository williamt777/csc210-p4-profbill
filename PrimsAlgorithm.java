
/**
 * This interface defines a Prim's algorithm.
 * It's pretty basic: 1) run your algo, 2) get the resulting MST graph.
 */
public interface PrimsAlgorithm {
  /**
   * Getter for the original graph, presumably set via the ctor
   * @return Returns the original graph
   */
  public Graph210 getGraph();

  /**
   * Run the Prim's!
   */
  public void runPrims();

  /**
   * Get the MST graph created by runPrims().
   * @return Returns the Prims MST graph; returns null if you haven't run Prim's yet.
   */
  public Graph210 getPrimsGraph();
}
