package Firefight;

public class IpBuffer<T> {

    T data = null;

    public boolean isFull() {
        return data != null;
    }

    public boolean isEmpty() {
        return data == null;
    }

    public T get() {
        T d = data;
        data = null;
        return d;
    }

    public void put(T data) {
        this.data = data;
    }
}
