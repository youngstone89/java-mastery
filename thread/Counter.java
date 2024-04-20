package thread;

public class Counter {
    protected long count = 0;

    public void add(long value) {
        synchronized (this) {
            this.count = this.count + value;
        }
    }
}
