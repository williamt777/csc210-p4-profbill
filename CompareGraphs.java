import java.util.Scanner;

/**
 * This main() is just a test driver to compare two graphs.
 */
public class CompareGraphs {

  public static void main( String[] args) {
    Scanner keyboard = new Scanner( System.in);

    System.out.println( "\nHello, CompareGraphs");

    // get the first Graph210
    System.out.println();
    System.out.println( "Enter graph1 file:");
    String name1 = keyboard.nextLine();
    Graph210 g1 = GraphHelper.readGraphFile( name1);
    if( g1 == null) { System.exit(1); }

    // get the second Graph210
    System.out.println();
    System.out.println( "Enter graph2 file:");
    String name2 = keyboard.nextLine();
    Graph210 g2 = GraphHelper.readGraphFile( name2);
    if( g2 == null) { System.exit(1); }

    // compare the two graphs and print the result
    System.out.println();
    int numErrors = GraphHelper.compareGraphs( g1, g2);
    if( numErrors == 0) {
      System.out.println( "compareGraphs: Graphs are EQUAL!");
    }
    else {
      System.out.println( "compareGraphs: Graphs are NOT equal, num errors=" + numErrors);
    }

    keyboard.close();
    System.out.println(); // bye
  }
}
