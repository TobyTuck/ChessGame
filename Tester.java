public class Tester{
  
    public static void main(String[] args){
        Link other = new Link();
        String otherMessage = "Wassup";
        Object x = (Object) otherMessage;
        other.setData(x);

        Link maybe = new Link();
        String v = "niger";
        Object j = (Object) v;
        maybe.setData(v);

        Link link1 = new Link();
        String message1 = "Hello";
        Object obj1 = (Object) message1;
        link1.setData(obj1);
        link1.setRight(other);

        Link link2 = new Link();
        String message2 = "Hello";
        Object obj2 = (Object) message2;
        link2.setData(obj2);
        link2.setRight(other);

        if(link1.equals(link2))
            System.out.println("The links are equal");
        else{
            System.out.println("Nope");}
    }
}
