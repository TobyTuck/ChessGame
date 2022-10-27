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

        if(_size == 0){
            next.setRight(null);
            next.setLeft(null);
            next.setUp(null);
            next.setDown(null);
            _head = next;}

        else if(_size == 1){
            _head.setRight(next);
            next.setLeft(_head);
        }

        else{
            Link tail = _head;
            Link link = _head;
            int factor = _size / _layerWidth;
            int modulus = _size % _layerWidth;

            for(int factor = 0; factor < _size; ++factor){
                    tail = tail.getDown();
                 
                for(int index = 0; index < _layerwidth; ++index){
                    if(factor > 1){
                        for(int count = 0; count < _layerWidth;
                            ++count){
                            link = tail.getLeft();}
                        tail.setUp(link);
                        link.setDown(tail);

                    tail = tail.getRight();}

                tail.setUp(link);

            Link previous = tail.getLeft(); 
            tail.setLeft(trailing);
            tail.setRight(next);}

            if(_size > _layerWidth){
                Link link = next;
                for(int index = 0; index < _layerWidth; ++index){
                    link = link.getLeft();}

                next.setUp(link);
                link.setDown(next);} }

        ++_size;
    }

    /**
        Method that retrieves the data at a specified 
        Location in the structure w/o deletion
    */
    public Object pop(int target){
        Link tail = _head;
        int factor = _size / _layerWidth;
        int modulus = _size % _layerWidth;

        for(int index = 0; index < factor; ++index){
            tail = tail.getDown();}

        for(int index = 0; index < modulus; ++index){
            tail.getRight();}

        return tail.getData();
    }

    /**
        Recursive method that retrieves the data at a specified location in the 
        Structure w/o deletion
    
    public Object pop(int target){
        if(target != 0)
            return pop(target - 1);
        return 
    */

    /**
        Method that returns the size of the array
    */
    public int getSize(){
        return _size;
    }
}
