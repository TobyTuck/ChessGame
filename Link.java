/**
Class that provides tool to build a general-purpose Linked List
*/
public class Link{

    private Link _next;
    private Object _data;

    public Link(){
        _next = null;
        _data = null;
    }

    public Link(Link next, Object data){
        _next = next;
        _data = data;
    }

    // Mutator methods
    public void setNext(Link next){
        _next = next;
    }

    public void setData(Object data){
        _data = data;
    }

    // Accessor methods
    public Link getNext(){
        return _next;
    }

    public Object getData(){
        return _data;
    }
}
