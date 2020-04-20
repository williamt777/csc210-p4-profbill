/**
 * This class is an edge in an undirected graph. 
 * It's my implementation of the Edge210 interface.
 */
public class MyEdge implements Edge210 {
  /** first vert on the edge */
  private int vert1;
  /** second vert on the edge */
  private int vert2;
  /** edge weight */
  private double weight;

  /**
   * ctor - There is no checking of the args; MyEdge assumes you do that.
   * @param vert1 First vert
   * @param vert2 Second vert
   * @param weight Edge weight
   */
  public MyEdge( int vert1, int vert2, double weight) {
    this.vert1 = vert1;
    this.vert2 = vert2;
    this.weight = weight;
  }

  @Override
  public int getVert1() { return this.vert1; }

  @Override
  public int getVert2() { return this.vert2; }

  @Override
  public double getWeight() { return this.weight; }

  @Override
  public int otherVert(int v) {
    int other = this.vert1;
    if( v == this.vert1) {
      other = this.vert2;
    }
    return other;
  }

  @Override
  public boolean hasVert(int v) {
    boolean found = false;
    if( v == this.vert1  ||  v == this.vert2) {
      found = true;
    }
    return found;
  }  

  @Override
  public void normalize() {
    if( this.vert1 > this.vert2) {
      int tmp = this.vert1;
      this.vert1 = this.vert2;
      this.vert2 = tmp;
    }    
  }

  /**
   * Prints a string for the edge in Sedgewick format: <v1> <v2> <weight>.
   * Example: 12 7 1.25
   * @return Returns a string for the edge
   */
  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append( this.vert1);
    sb.append( " ");
    sb.append( this.vert2);
    sb.append( " ");
    sb.append( this.weight);
    return sb.toString();
  }
}
