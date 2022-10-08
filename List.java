/**
    The purpose of this class is to provide the general
    Tools necessary to build a list data structure
*/
public class List{

    private Link _head;
    private int _size;

    public List(){
        _head = null;
        _size = 0;
    }

    /** 
        Method that inserts at the back of the structure
    */
    public void push(Object nextData){
        Link next = new Link();
        next.setData(nextData);
        next.setNext(null);

        if(_head == null){
            _head = new Link();
            _head = next;}

        else{
            Link tail = _head;
            for(Link index = _head; index.getNext() != null; index.getNext()){
               tail = tail.getNext();}
            tail.setNext(next);}

        ++_size;
    }

    /**
        Method that retrieves the data at a specified 
        Location in the structure w/o deletion
    */
    public Object pop(int target){
        Link tail = _head;
        for(int index = 0; index < target; ++index){
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
