/**
    The purpose of this class is to provide the general
    Tools necessary to build a list data structure that is
    Quarterly linked- twice horizontally (forward and back)
    And vertically (upward and down)
    NOTE: Horizontally the list is x nodes wide as determined by the constructor; 
          Then a new layer will be added to the list
*/

import java.lang.Math; // used for absolute value function

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

        // special case- inserting the first node into the list
        if(_size == 0){
            _head = next;}

        // case 2- general case for inserting any node at a position greater than 1 
        else{
            Link tail = _head;

            // traverse down the structure as far as possible
            for(Link index = _head; index.getDown() != null; index = index.getDown()){
                tail = tail.getDown();}
            
            // traverse right until the end of the link is reached
            for(Link count = tail; count.getRight() != null; count = count.getRight()){
                tail = tail.getRight();}
                
            // set 'left', 'right' fields
            tail.setRight(next);
            next.setLeft(tail);
        
            // if necessary, traverse up to set 'up' field of inserted node
            if(_size > _layerWidth){
                for(int index = 0; index < _layerWidth; ++index){
                    tail = tail.getLeft();}
                next.setUp(tail);
                tail.setDown(next);} }

        ++_size;
    }

    /**
        Method that retrieves the data at a specified location in the structure w/o deletion
    */
    public Object pop(int target){
        // special case- target is larger than the size of the list
        if(_size < target)
            return null;

        Link tail = _head;
        int lowerLimit = target / _layerWidth;
        int upperLimit = lowerLimit + 1;
        int a = _size / _layerWidth;

        // instance where the shortest route to the target is from the 1st node of the same row 
        if(((target - (lowerLimit * _layerWidth)) < ((upperLimit * _layerWidth) - target)) || 
            upperLimit == a){
            for(int index = 0; index < lowerLimit - 1; ++index){
                tail = tail.getDown();}

            for(int index = 0; index < (target % _layerWidth); ++index){
                tail = tail.getRight();} 

            // delete
            System.out.println("Lower route used!");

            return tail.getData();}

        // instance where the shortest route is from the 1st node of the row below the target
        else{
            for(int index = 0; index < upperLimit; ++index){
                tail = tail.getDown();}

            for(int index = 0; index < (_layerWidth - (target % _layerWidth)); ++index){
                tail = tail.getLeft();} 

            return tail.getData();}
    }

    /**
    Method that returns the size of the array
    */
    public int getSize(){
        return _size;
    }
}
