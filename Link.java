/**
Class that provides tools to build Linked List w/ 4 pointers- a quarterly-linked list
*/
public class Link{

    private Link _right;
    private Link _left;
    private Link _up;
    private Link _down;
    private Object _data;
    private Object _component;

    public Link(){
        _right = null;
        _left = null;
        _up = null;
        _down = null;
        _data = null;
        _component = null;
    }

    public Link(Object data){
        _data = data;
        _right = null;
        _left = null;
        _up = null;
        _down = null;
        _component = null;
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

    public void setComponent(Object component){
        _component = component;
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

    public Object getComponent(){
        return _component;
    }
}
