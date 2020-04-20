import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * A collection of static methods used in running P4.
 * These are just kind of clunky glue methods.
 */
public class P4Helper {

  /**
   * Run Prim's MST algo on a Graph210 and save the results to a graph file.
   * @param g The graph
   */
  public static void doPrimsMST( PrimsAlgorithm algo, String algoName, String fileName) {
    System.out.println();
    System.out.println( "Running Prim's, flavor=" + algoName + "...");

    // run the Prim's!
    algo.runPrims();

    // print MST stats and the MST graph file
    Graph210 pg = algo.getPrimsGraph();
    System.out.println( "MST graph for Prim's=" + algoName);
    System.out.println( "\tNum edges=" + pg.numEdges());
    double weightSum = GraphHelper.sumAllEdges(pg);
    String tmp = new DecimalFormat("#0.000000").format(weightSum);
    System.out.println( "\tSum of all edge weights=" + tmp);

    // write Prim's MST graph to a file
    System.out.println( "\tMST graph written to file=" + fileName);
    try {
      PrintWriter out = new PrintWriter( fileName);
      out.println( pg.toString());
      out.close();
    } catch( Exception exc) {
      System.out.println( exc);
    }

    System.out.println();
  }

  /**
   * Read a graph file, return its Graph210.
   * @param keyboard Console input for user file name
   * @return Returns a graph file; on error null is returned
   */
  public static Graph210 readGraph( String fileName) {
    Graph210 g = GraphHelper.readGraphFile( fileName);
    return g;
  }

  /**
   * Print the graph.
   * @param g The graph
   */
  public static void printGraph( Graph210 g) {
    System.out.println();
    System.out.println( g);

    System.out.println();
    System.out.println( "Normalize and print again...");
    g.normalizeEdges();
    System.out.println( g);
  }

  /**
   * Query user for a Graph210 file name.
   * @param keyboard Console input stream
   * @return Returns the file name; on error null is returned
   */
  public static String queryFileName( Scanner keyboard) {
    System.out.println( "Enter graph file name:");
    String fileName = keyboard.nextLine();
    return fileName;
  }

  /**
   * Query user to answer a yes/no question.
   * @param keyboard Console input stream
   * @param question Question posed to user
   * @return Returns true if the user says "YES!"
   */
  public static boolean queryYesNo( Scanner keyboard, String question) {
    boolean flag = false;
    System.out.println( question + " (Y/N):");
    String str = keyboard.nextLine();

    if( str != null  &&  !str.isEmpty()) {
      str = str.trim();
      if( str.charAt(0) == 'Y'  ||  str.charAt(0) == 'y') {
        flag = true;
      }
    }
    return flag;
  }
}