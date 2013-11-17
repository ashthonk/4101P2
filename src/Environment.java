// Environment.java -- a data structure for Scheme environments

// An Environment is a list of frames.  Each frame represents a scope
// in the program and contains a set of name-value pairs.  The first
// frame in the environment represents the innermost scope.

// For the code below, I am assuming that a scope is implemented
// as an association list, i.e., a list of two-element lists.  E.g.,
// the association list ((x 1) (y 2)) associates the value 1 with x
// and the value 2 with y.

// To implement environments in an object-oriented style, it would
// be better to define a Frame class and make an Environment a list
// of such Frame objects.  If we simply use the parse tree structure
// for lists of association lists, we end up having to write the
// lookup functions in a more functional style.

// You need the following methods for modifying environments:
//  - constructors:
//      - create the empty environment (an environment with an empty frame)
//      - add an empty frame to the front of an existing environment
//  - lookup the value for a name (for implementing variable lookup)
//      if the name exists in the innermost scope, return the value
//      if it doesn't exist, look it up in the enclosing scope
//      if we don't find the name, it is an error
//  - define a name (for implementing define and parameter passing)
//      if the name already exists in the innermost scope, update the value
//      otherwise add a name-value pair as first element to the innermost scope
//  - assign to a name (for implementing set!)
//      if the name exists in the innermost scope, update the value
//      if it doesn't exist, perform the assignment in the enclosing scope
//      if we don't find the name, it is an error

class Environment extends Node 
{
    // An Environment is implemented like a Cons node, in which
    // every list element (every frame) is an association list.
    // Instead of Nil(), we use null to terminate the list.

    private Node scope;		// the innermost scope, an association list
    private Environment env;	// the enclosing environment

    public Environment()		{ scope = new Nil();  env = null; }
    public Environment(Environment e)	{ scope = new Nil();  env = e; }
    public boolean superEnv() { if (env == null) return true; else return false;}
    public void print(int n) 
    {
	for (int i = 0; i < n; i++)  System.out.print(' ');
	System.out.println("#{Environment");
        
	scope.print(n+3);
        if (env != null) env.print(n+3);
        
	for (int i = 0; i < n; i++)  System.out.print(' ');
	System.out.println('}');
    }

    // This is not in an object-oriented style, it's more or less a
    // translation of the Scheme assq function.
    private static Node find (Node id, Node alist) 
    {
        Node toreturn;
        System.out.println("_.-~~~~~Environment - Find~~~~~-._");
        System.out.println("We are attempting to find " + id.getName() + " in " + alist.getClass());
	if (!alist.isPair()){
            System.out.println("Alist is not a pair. Returning null (e.g. false)");
	    return null;}	// in Scheme we'd return #f
	else 
        {
	    Node bind = alist.getCar();
            System.out.println("Checking item: " +  bind.getCar().getName()+ " against our target " +  id.getName());
	    if (id.getName().equals(bind.getCar().getName())){
		// return a list containing the value as only element
                System.out.println("We've found it!");
                toreturn = bind.getCdr();
		return bind.getCdr();
            }
            else {System.out.println("We didn't find it");return find(id, alist.getCdr());}
	}
    }

    @SuppressWarnings("empty-statement")
    public Node lookup (Node id) 
    {
        System.out.println("_.-~~~~~ENVIRONMENT-LOOKUP~~~~~-._");
        //System.out.println("I made it into lookup");
	Node val = find(id, scope);
        System.out.println("val: " + val + " env: "+ env);
        //System.out.println("I made it past find, and val = " + val + " and env = " + env);
	if (val == null && env == null) 
        {
	   //System.out.println("undefined variable");
	    return null;
	}
	else if (val == null)
	    // look up the identifier in the enclosing scope
	    return env.lookup(id);
        else if (env == null){
            System.out.println("Found in the BuiltIn table!");
            return val.getCar();
        }else
	    // get the value out of the list we got from find()
	    return val.getCar();
    }

    public void define (Node id, Node val) 
    {
        
        System.out.println("Debug~~ Trying to define id: " + id.getName() + " with the value " + val.getInt());
        System.out.println("Scope is of type: " + scope.getClass());
        Node temp = find(id, scope);
        System.out.println(temp);
        if (temp != null) { System.out.println("We found the ID. Updating " + id.getName() + " to " + val.getInt()); temp.setCar(val.getCar());}
        else {System.out.println("We did not find the ID. Constructing a new frame!"); scope = new Cons(new Cons(id, val), scope);}
    }

    public void assign (Node id, Node val) 
    { //May need to make it more like lookup
        Node temp = find(id, val);
        if (temp != null) {temp.setCar(val);}
        else if (env != null) { env.assign(id, val);}
        else {System.err.println("Name not found~!"); System.exit(0);}
    }
}

// Environment.java -- a data structure for Scheme environments

// An Environment is a list of frames.  Each frame represents a scope
// in the program and contains a set of name-value pairs.  The first
// frame in the environment represents the innermost scope.

// For the code below, I am assuming that a scope is implemented
// as an association list, i.e., a list of two-element lists.  E.g.,
// the association list ((x 1) (y 2)) associates the value 1 with x
// and the value 2 with y.

// To implement environments in an object-oriented style, it would
// be better to define a Frame class and make an Environment a list
// of such Frame objects.  If we simply use the parse tree structure
// for lists of association lists, we end up having to write the
// lookup functions in a more functional style.

// You need the following methods for modifying environments:
//  - constructors:
//      - create the empty environment (an environment with an empty frame)
//      - add an empty frame to the front of an existing environment
//  - lookup the value for a name (for implementing variable lookup)
//      if the name exists in the innermost scope, return the value
//      if it doesn't exist, look it up in the enclosing scope
//      if we don't find the name, it is an error
//  - define a name (for implementing define and parameter passing)
//      if the name already exists in the innermost scope, update the value
//      otherwise add a name-value pair as first element to the innermost scope
//  - assign to a name (for implementing set!)
//      if the name exists in the innermost scope, update the value
//      if it doesn't exist, perform the assignment in the enclosing scope
//      if we don't find the name, it is an error

