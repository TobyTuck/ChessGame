/**
    The purpose of this class is to provide the general
    Tools necessary to build a queque data structure
*/
public class Queque{

    private Link _head;
    private int _size;

    public Queque(){
        _head = null;
        _size == 0;
    }

    /** 
        Method that inserts at the back of the structure
    */
    public void push(Object nextData){
        Link next = new Link();
        next.setData(nextData);

        if(_size = 0)
            _head = next;

        else{
            Link tail = _head;
            for(int index = 0; index < _size; ++index){
               tail = tail.getNext();}
            tail.setNext(next);}

        ++_size;
    }

    /**
        Method that retrieves the data of the top of the 
        Structure w/o deletion
    */
    public Object pop(){
        Link tail = _head;
        for(int index = 0; _index < _size; ++index){
           tail = tail.getNext();}
        return tail.getData();
    }

    /**
        Method that returns the size of the array
    */
    public int getSize(){
        return _size;
    }
}
