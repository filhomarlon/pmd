/* Generated By:JJTree: Do not edit this line. SimpleNode.java */
package net.sourceforge.pmd.ast;

import net.sourceforge.pmd.symboltable.Scope;

public class SimpleNode implements Node {
  protected Node parent;
  protected Node[] children;
  protected int id;

  protected JavaParser parser;

  public SimpleNode(int i) {
    id = i;
  }

  public SimpleNode(JavaParser p, int i) {
    this(i);
    parser = p;
  }

    public void jjtOpen() {
        if (parser.token.next != null) {
            beginLine = parser.token.next.beginLine;
            beginColumn = parser.token.next.beginColumn;
        }
    }

    // NEW STUFF
    private String image;
    private int beginLine = -1;
    private int endLine;
    private int beginColumn = -1;
    private int endColumn;
    private Scope scope;

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public Scope getScope() {
        if (scope == null) {
            throw new RuntimeException("No scope set on " + this);
        }
        return scope;
    }

    public void jjtClose() {
      if ((children == null) || (children.length == 0)){
        beginLine = parser.token.beginLine;
        beginColumn = parser.token.beginColumn;
      }
      endLine = parser.token.endLine;
      endColumn = parser.token.endColumn;
    }

    public int getBeginLine() {
        if (beginLine != -1) {
            return beginLine;
        } else {
            if ((children != null) && (children.length > 0)) {
                return ((SimpleNode) children[0]).getBeginLine();
            } else {
              throw new RuntimeException("Unable to determine begining line of Node.");
            }
        }
    }

    public void testingOnly__setBeginLine(int i) {
        this.beginLine = i;
    }

    public int getBeginColumn() {
        if (beginColumn != -1) {
            return beginColumn;
        } else {
            if ((children != null) && (children.length > 0)) {
                return ((SimpleNode) children[0]).getBeginColumn();
            } else {
              throw new RuntimeException("Unable to determine begining line of Node.");
            }
        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getEndLine() {
        return endLine;
    }

    public int getEndColumn() {
        return endColumn;
    }
    // NEW STUFF

  public void jjtSetParent(Node n) { parent = n; }
  public Node jjtGetParent() { return parent; }

  public void jjtAddChild(Node n, int i) {
    if (children == null) {
      children = new Node[i + 1];
    } else if (i >= children.length) {
      Node c[] = new Node[i + 1];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
    }
    children[i] = n;
  }

  public Node jjtGetChild(int i) {
    return children[i];
  }

  public int jjtGetNumChildren() {
    return (children == null) ? 0 : children.length;
  }

  /** Accept the visitor. **/
  public Object jjtAccept(JavaParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  /** Accept the visitor. **/
  public Object childrenAccept(JavaParserVisitor visitor, Object data) {
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        children[i].jjtAccept(visitor, data);
      }
    }
    return data;
  }

  /* You can override these two methods in subclasses of SimpleNode to
     customize the way the node appears when the tree is dumped.  If
     your output uses more than one line you should override
     toString(String), otherwise overriding toString() is probably all
     you need to do. */

  public String toString() { return JavaParserTreeConstants.jjtNodeName[id]; }
  public String toString(String prefix) { return prefix + toString(); }

  /* Override this method if you want to customize how the node dumps
     out its children. */

  public void dump(String prefix) {
    System.out.println(toString(prefix));
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
	SimpleNode n = (SimpleNode)children[i];
	if (n != null) {
	  n.dump(prefix + " ");
	}
      }
    }
  }
}

