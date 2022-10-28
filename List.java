/**
    The purpose of this class is to provide the general
    Tools necessary to build a list data structure that is
    Quarterly linked- twice horizontally (forward and back)
    And vertically (upward and down)
    NOTE: Horizontally the list is x nodes wide as determined by the constructor; 
          Then a new layer will be added to the list
*/
public class List{

    private Link _head;
    private int _layerWidth;
    private int _size;

    public List(int layerWidth){
        _head = null;
        _layerWidth = layerWidth;
        _size = 0;
    }

    /** 
        Method that inserts at the back of the structure
    */
    public void push(Object nextData){
        Link next = new Link(nextData);
        next.setIndex(_size - 1);

        if(_size == 0){
            next.setRight(null);
            next.setLeft(null);
            next.setUp(null);
            next.setDown(null);
            _head = next;}

        else{
            Link tail = _head;
            int factor = _size / _layerWidth;
            int modulus = _size % _layerWidth;

            for(int index = 0; index < _size - 2; ++index){
                // set 'Down' + 'Up' fields if necessary
                if(tail.getIndex() > _layerWidth && 
                   tail.getIndex() < _size - _layerWidth &&
                   tail.getDown() == (null)){
                       for(Link e = _head; e.getIndex() == (tail.getIndex() + _layerWidth); 
                           e = e.getRight()){
                            tail.setDown(e);
                            e.setUp(tail);} }

                tail = tail.getRight();}
                
            tail.setRight(next);
            next.setLeft(tail);}

        ++_size;
    }

    /**
        Method that retrieves the data at a specified 
        Location in the structure w/o deletion
    */
    public Object pop(int target){
        Link tail = _head;
        int factor = target / _layerWidth;
        int modulus = target % _layerWidth;
        int 

        if((target * (_layerWidth + 1)) - ) < 

        for(int index = 0; index < factor; ++index){
            tail = tail.getDown();}

        for(int index = 0; index < modulus; ++index){
            tail.getRight();}

        return tail.getData();
    }

    /**
    Method that returns the size of the array
    */
    public int getSize(){
        return _size;
    }

    /**
    Method that returns the position of an element in an erray
    */
    public int getIndex(Link other){
        Link link = _head;
        for(int index = 0; index < _size; ++index){
            if(other.equals(link))
                return index;
            link = link.getRight();}
        
        return 0;
    }

    /**
    Recursive method that tests to see if two nodes are equal
    A node is equal to another if they share the same data
    */
    public boolean equals(Link other){
        Link link = _head;
        for(int index = 0; index < _size; ++index){
            // equality test
            if(other.getData().equals(link.getData()))
                return true;

            link = link.getRight();}

        return false;
    }
}
