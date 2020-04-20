import java.io.File;
import java.util.Scanner;

/**
 * The main() class for Program04, CSC 210 Spring 2020.
 * @author Prof Bill
 */
public class Program04 {
  /** output file names for Prim's and Prims2 MST results */
  public final static String PRIMS_FILE_NAME = "prims_mst.txt";
  public final static String PRIMS2_FILE_NAME = "prims2_mst.txt";

  /**
   * The main() method for Program 04.
   * @param args unused
   */
  public static void main( String[] args) {

    printFancyTitle(); // creative element (smile)

    Scanner keyboard = new Scanner( System.in); // used for all console input

    // read graph file specified by user
    String fileName = P4Helper.queryFileName( keyboard);
    Graph210 g = P4Helper.readGraph( fileName);
    if( g == null) { System.exit(1); }

    // print the graph
    if( P4Helper.queryYesNo(keyboard, "Print graph?")) {
      P4Helper.printGraph( g);
    }

    // run Prim's on the graph to get MST
    Graph210 pg1 = null;
    if( P4Helper.queryYesNo(keyboard, "Run Prim's on graph?")) {
      PrimsAlgorithm algo1 = new MyPrims( g);
      P4Helper.doPrimsMST( algo1, "Original", PRIMS_FILE_NAME);
      pg1 = algo1.getGraph();
    }

    // run Prims2 on the graph to get MST
    Graph210 pg2 = null;
    if( P4Helper.queryYesNo(keyboard, "Run Prims2 on graph?")) {
      PrimsAlgorithm algo2 = new MyPrims2( g);
      P4Helper.doPrimsMST( algo2, "Prims2", PRIMS2_FILE_NAME);
      pg2 = algo2.getGraph();
    }

    // compare two Prim's results to make sure they are equal
    if( pg1 != null  &&  pg2 != null) {
      System.out.println( "Compare two Prim results...");
      int errorCount;
      errorCount = GraphHelper.compareGraphs( pg1, pg2);
      if( errorCount == 0) {
        System.out.println( "\tOK: The two Prim results are EQUAL!");
      }
      else {
        System.out.println( "\tERROR: Compare found errors=" + errorCount);
      }
    }

    keyboard.close();
    System.out.println(); // bye
  }

  /**
   * Prints a fancy title for P4.
   * Source: patorjk.com/software/taag; font is Standard.
   */
  public static void printFancyTitle() {
    System.out.println();
    System.out.println( "Hello, Program 04");
    Scanner scanner;
    try {
      File f = new File( "fancy_title.txt");
      scanner = new Scanner( f);
    } catch( Exception exc) {
      return;
    }

    System.out.println( "by");
    String s;
    while( scanner.hasNextLine()) {
      s = scanner.nextLine();
      System.out.println( s);
    }
    scanner.close();
    System.out.println();
  }
}
