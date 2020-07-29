package model;

public class Par<K, V> {

    private final K element0;
    private final V element1;

    public static <K, V> Par<K, V> createPair(K element0, V element1) {
        return new Par<K, V>(element0, element1);
    }

    public Par(K element0, V element1) {
        this.element0 = element0;
        this.element1 = element1;
    }

    public K getElement0() {
        return element0;
    }

    public V getElement1() {
        return element1;
    }

}