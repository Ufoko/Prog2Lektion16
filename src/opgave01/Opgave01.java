package opgave01;

public class Opgave01 {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("Hej0");
        linkedList.add("Hej1");
        linkedList.add("Hej2");
        linkedList.add("Hej3");
        linkedList.add("Hej5");

        System.out.println("linkedList.get(1) = " + linkedList.get(1));
        System.out.println("linkedList.recursionGet(1) = " + linkedList.recursionGet(1));
        System.out.println("linkedList.getFirst() = " + linkedList.getFirst());
        System.out.println("linkedList.size() = " + linkedList.size());
        linkedList.addFirst("Hej00");
        System.out.println("linkedList.size() = " + linkedList.size());
        System.out.println("linkedList.getFirst() = " + linkedList.getFirst());
        System.out.println("linkedList.get(0) = " + linkedList.get(0));
        System.out.println("linkedList.contains(\"Hej5\") = " + linkedList.contains("Hej5"));
        System.out.println("linkedList.indexOf(\"Hej5\") = " + linkedList.indexOf("Hej5"));
        linkedList.removeFirst();
        System.out.println("linkedList.getFirst() = " + linkedList.getFirst());
        linkedList.add(4,"Hej4");
        System.out.println("linkedList.get(4) = " + linkedList.get(4));
        System.out.println("linkedList.isEmpty() = " + linkedList.isEmpty());
        linkedList.clear();
        System.out.println("linkedList.isEmpty() = " + linkedList.isEmpty());

    }
}
