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

    public Link(Object data){
        _data = data;
        _right = null;
        _left = null;
        _up = null;
        _down = null;
        _data = null;
    }

    // method that compares other Link fields
    public boolean equals(Link other){
        // compare only the 'data' field
        if(other == null){
            if(_data == null)
                return true;
            else
                return false;}

        // compare every field
        else{
            if(other.getData().equals(_data) && /* other.getUp().equals(_up) &&
               other.getDown().equals(_down) && */other.getRight().equals(_right) /*&&
               other.getLeft().equals(_left) */ )
                return true;
            else{
                return false;} }
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
