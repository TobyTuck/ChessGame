/**
Class that provides tools to build Linked List w/ 4 pointers- a quarterly-linked list
*/
public class Link{

    private Link _right;
    private Link _left;
    private Link _up;
    private Link _down;
    private Object _data;

    public Link(){
        _right = null;
        _left = null;
        _up = null;
        _down = null;
        _data = null;
    }

    public Link(Link right, Object data){
        _right = right;
        _data = data;
    }

    public Link(Link right, Link left, Link up, Link down, Object data){
        _right = right;
        _left = left;
        _up = up;
        _down = down;
        _data = data;
    }

    // Mutator methods
    public void setRight(Link right){
        _right = right;
    }

    public void setLeft(Link left){
        _left = left;
    }

    public void setUp(Link up){
        _up = up;
    }

    public void setDown(Link down){
        _down = down;
    }

    public void setData(Object data){
        _data = data;
    }

    // Accessor methods
    public Link getRight(){
        return _right;
    }

    public Link getLeft(){
        return _left;
    }

    public Link getUp(){
        return _up;
    }

    public Link getDown(){
        return _down;
    }

    public Object getData(){
        return _data;
    }
}
