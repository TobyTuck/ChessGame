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
    public void push(Object nextData, Object component){
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
                
            // set 'left', 'right', 'component' pointers 
            tail.setRight(next);
            next.setLeft(tail);
            next.setComponent(component);
        
            // if necessary, traverse up to set 'up' field of inserted node
            if(_size > _layerWidth){
                for(int index = 0; index < (_layerWidth - 1); ++index){
                    tail = tail.getLeft();}
                next.setUp(tail);
                tail.setDown(next);} }

        ++_size;
    }

    /**
        Method that retrieves the data at a specified location in the structure w/o deletion
    */
    public Object pop(int target){

        Link tail = _head;

        for(int index = 0; index < target; ++index){
            tail = tail.getRight();}

        return tail.getData();

        /*
        Link tail = _head;
        int limit = target / _layerWidth;
        int remainder = target % _layerWidth;

        for(int index = 0; index < limit; ++index){
            tail = tail.getDown();}

        for(int index = 0; index < remainder; ++index){
            tail = tail.getRight();}

        // delete
        System.out.println(limit);

        return tail.getData();
        */
        
        /*
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
        */
    }

    /**
    Method that adds a component to a link
    */
    public void addComponent(Object data, Object component){
        
        // special case
        if(_head.getData().equals(data))
            _head.setComponent(component);

        Link myLink = _head;
        for(Link index = _head; !index.getData().equals(data); 
            index = index.getRight()){
            myLink = index;}
        myLink = myLink.getRight();

        myLink.setComponent(component);
    }

    /**
    Method that swaps the components of two links
    */
    public void swapComponent(Object data1, Object data2){
        
        Link myLink1 = _head;
        Link myLink2 = null;

        for(Link index = _head; !index.getData().equals(data1); 
            index = index.getRight()){
            myLink1 = index;

            if(index.getData().equals(data2))
                myLink2 = index;}
        myLink1 = myLink1.getRight();

        if(myLink2 == null){
            for(Link index = myLink1; !index.getData().equals(data2); 
                index = index.getRight()){
                myLink2 = index;} }
            myLink2 = myLink2.getRight();

        Object component = myLink1.getComponent();

        myLink1.setComponent(myLink2.getComponent());
        myLink2.setComponent(component);
    }

    /**
    Method that replaces the components of one link with another
    The component of the latter will be set to null
    */
    // error: if select first link twice, it seems that the component is set to null
    public void replaceComponent(Object giver, Object taker){

        Link myGiver = _head,
             myTaker = _head;

        // special case
        if(_head.getData().equals(giver)){
            for(Link index = _head; !index.getData().equals(taker); 
                index = index.getRight()){
                myTaker = index;}
            myTaker = myTaker.getRight();

            myTaker.setComponent(_head.getComponent());
            myGiver.setComponent(null);}

        else if(_head.getData().equals(taker)){
            for(Link index = _head; !index.getData().equals(giver); 
                index = index.getRight()){
                myGiver = index;}
            myGiver = myGiver.getRight();

            _head.setComponent(myGiver.getComponent());
            myGiver.setComponent(null);}

        else{
            myGiver = _head;
            myTaker = null;

            for(Link index = _head; !index.getData().equals(giver); 
                index = index.getRight()){
                myGiver = index;

                if(index.getData().equals(taker))
                    myTaker = index;}
            myGiver = myGiver.getRight();

            // special case
            if(taker == giver)
                myTaker = myGiver;

            else{
                if(myTaker == null){
                    for(Link index = myGiver; !index.getData().equals(taker); 
                        index = index.getRight()){
                        myTaker = index;}
                    myTaker = myTaker.getRight();} }

            if(!(myTaker.getComponent() == null && myGiver.getComponent() == null) &&
               myTaker != myGiver){
                myTaker.setComponent(myGiver.getComponent());
                myGiver.setComponent(null);} }
    }

    /**
    Method that returns the component field of a link
    */
    public Object getComponent(int target){
        
        Link myLink = _head;

        for(int index = 0; index < target; ++index){
            myLink = myLink.getRight();}

        return myLink.getComponent();
    }

    /** 
    Method that returns the component field using the data
    */
    public Object getComponent(Object data){
        
        if(_head.getData().equals(data))
            return _head.getComponent();

        Link myLink = _head;

        for(Link index = _head; !index.getData().equals(data); 
            index = index.getRight()){
            myLink = index;}
        myLink = myLink.getRight();

        return myLink.getComponent();
    }

    /**
    Method that returns the size of the array
    */
    public int getSize(){
        return _size;
    }
}
