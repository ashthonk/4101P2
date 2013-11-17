// BuiltIn.java -- the data structure for function closures

// Class BuiltIn is used for representing the value of built-in functions
// such as +.  Populate the initial environment with
// (name, new BuiltIn(name)) pairs.

// The object-oriented style for implementing built-in functions would be
// to include the Java methods for implementing a Scheme built-in in the
// BuiltIn object.  This could be done by writing one subclass of class
// BuiltIn for each built-in function and implementing the method apply
// appropriately.  This requires a large number of classes, though.
// Another alternative is to program BuiltIn.apply() in a functional
// style by writing a large if-then-else chain that tests the name of
// of the function symbol.

class BuiltIn extends Node 
{
    private Node symbol;
    public BuiltIn(Node s)		{ symbol = s;}
    
    public Node getSymbol()		{ return symbol; }
    public boolean isProcedure()	{ return true; }

    public void print(int n) 
    {
	for (int i = 0; i < n; i++) System.out.print(' ');
	System.out.println("#{Built-in Procedure");
	symbol.print(n+3);
	for (int i = 0; i < n; i++) System.out.print(' ');
	System.out.println('}');
    }

    //TODO: this
    public Node eval (Environment e) 
    { 
        e.apply(new Nil());
	return null;
    }
    
    //TODO: this
    public Node apply(Node args) 
    {
        if(this.getName().equals("number?")){
            
        }
        else if(this.getName().equals("symbol?")){
            
        }
                else if(this.getName().equals("b+")){
            
        }
                else if(this.getName().equals("b-")){
            
        }
                else if(this.getName().equals("b*")){
            
        }
                else if(this.getName().equals("b/")){
            
        }
                else if(this.getName().equals("b=")){
            
        }
                else if(this.getName().equals("b<")){
            
        }
                else if(this.getName().equals("car")){
            
        }
                else if(this.getName().equals("cdr")){
            
        }
                else if(this.getName().equals("cons")){
            
        }
                else if(this.getName().equals("set-car!")){
            
        }
                else if(this.getName().equals("set-cdr!")){
            
        }
                else if(this.getName().equals("symbol?")){
            
        }
                else if(this.getName().equals("null?")){
            
        }
                else if(this.getName().equals("pair?")){
            
        }
                else if(this.getName().equals("eq?")){
            
        }
                else if(this.getName().equals("procedure?")){
            
        }
                else if(this.getName().equals("read")){
            
        }
                else if(this.getName().equals("write")){
            
        }
                else if(this.getName().equals("display")){
            
        }
                else if(this.getName().equals("newline")){
            
        }
                else if(this.getName().equals("eval")){
            
        }
                else if(this.getName().equals("apply")){
            
        }
                else if(this.getName().equals("interaction-environment")){
            
        }
	return null;
    }
    
}
