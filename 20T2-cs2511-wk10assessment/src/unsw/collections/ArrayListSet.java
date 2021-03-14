/**
 *
 */
package unsw.collections;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An implementation of Set that uses an ArrayList to store the elements.
 *
 * @invariant All e in elements occur only once
 *
 * @author Robert Clifton-Everest
 *
 */
public class ArrayListSet<E> implements Set<E> {

    private ArrayList<E> elements;

    public ArrayListSet() {
        elements = new ArrayList<>();
    }

    @Override
    public void add(E e) {
        // TODO Implement me
        if (!elements.contains(e)) elements.add(e);
    }

    @Override
    public void remove(E e) {
        elements.remove(e);
    }

    @Override
    public boolean contains(Object e) {
        if (elements.contains(e)) return true;
        else return false;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean subsetOf(Set<?> other) {
        // TODO Implement me
        for (E e : this)
            if (!other.contains(e)) return false;
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        // TODO Implement me
        return elements.iterator();
    }

    @Override
    public Set<E> union(Set<? extends E> other) {
        // TODO Implement me
        Set<E> union = new ArrayListSet<>();
        for (E e : this)
            union.add(e);
        for (E e : other)
            if (!this.contains(e)) union.add(e);
        return union;
    }

    @Override
    public Set<E> intersection(Set<? extends E> other) {
        // TODO Implement me
        Set<E> intersection = new ArrayListSet<>();
        for (E e : this)
            if (other.contains(e))
            intersection.add(e);
        return intersection;
    }

    /**
     * For this method, it should be possible to compare all other possible sets
     * for equality with this set. For example, if an ArrayListSet<Fruit> and a
     * LinkedListSet<Fruit> both contain the same elements they are equal.
     * Similarly, if a Set<Apple> contains the same elements as a Set<Fruit>
     * they are also equal.
     */
    @Override
    public boolean equals(Object other) {
        // TODO Implement me
        if (other == null) return false;
        if (this == other) return true;
        if (other.getClass() != this.getClass()) return false;
        if (other instanceof Set<?>) {
            Set<?> array = (Set<?>) other;
            for (E e : this)
                if (!array.contains(e)) return false;
            if (size() != array.size()) return false;
            return true;
        } 
        else return false;
    }
}
