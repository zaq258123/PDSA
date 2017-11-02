import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Created by Cavitation on 2016/3/26.
 */
public class Deque<T> implements Iterable<T>{

    private Object[] dequeArray;
    public int arraySize;

    public Deque() {
        this.dequeArray = new Object[100000];
        arraySize = 0;
    }

    public boolean isEmpty() {
        return arraySize == 0;
    }

    public int size() {
        return arraySize;
    }

    public void addFirst(T item) {
        if (item == null) throw new NullPointerException();
        if (arraySize != 0) {
            for (int i = arraySize; i > 0; i--) {
                dequeArray[i] = dequeArray[i-1];
            }
        }
        dequeArray[0] = item;
        arraySize++;
    }

    public void addLast(T item) {
        if (item == null) throw new NullPointerException();

        if (dequeArray.length > arraySize) {
            dequeArray[arraySize] = item;
        }
        arraySize++;
    }

    public T removeFirst() {
        if(isEmpty()) throw new NoSuchElementException();
        T returnItem = (T) dequeArray[0];
        dequeArray[0] = null;
        for (int i = 0; i < arraySize-1; i++) {
            dequeArray[i] = dequeArray[i+1];
        }
        arraySize--;
        return returnItem;
    }
//
    public T removeLast() {
        if(isEmpty()) throw new NoSuchElementException();
        T returnItem = (T) dequeArray[arraySize];
        dequeArray[arraySize-1] = null;
        arraySize--;
        return returnItem;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iter = new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < arraySize;
            }
            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();

                index++;
                return (T) dequeArray[index-1];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return iter;
    }
}
