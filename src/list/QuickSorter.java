package list;

public class QuickSorter extends Thread {

    private Integer maxThreadsCount;
    private QuickSorter parent;
    private IntegerList listToSort;
    private int sortedLists;
    private int threadsInUse;

    public QuickSorter(IntegerList listToSort, Integer maxThreadsCount) {
        this.listToSort = listToSort;
        sortedLists = 0;
        threadsInUse = 0;
        this.maxThreadsCount = maxThreadsCount;
    }

    public QuickSorter(IntegerList listToSort, QuickSorter parent) {
        this.listToSort = listToSort;
        sortedLists = 0;
        this.parent = parent;
        maxThreadsCount = parent.maxThreadsCount;
        parent.notifyAboutNewThread();
        threadsInUse = parent.threadsInUse;
    }

    private void notifyAboutNewThread() {
        threadsInUse ++;
    }

    public synchronized void sort() throws InterruptedException {
        if(listToSort.size() <= 1) return;
        int pivot = listToSort.get(listToSort.size() / 2);
        IntegerList left = listToSort.lessThan(pivot);
        IntegerList right = listToSort.greaterThan(pivot);

        while(maxThreadsCount == threadsInUse) {
            System.out.println("Threads in use: " + threadsInUse);
            wait();
        }
        new QuickSorter(left, this).start();

        while(maxThreadsCount == threadsInUse) {
            System.out.println("Threads in use: " + threadsInUse);
            wait();
        }
        new QuickSorter(right, this).start();

        while(sortedLists < 2)
            wait();

        left.add(pivot);
        left.concat(right);
        listToSort.sortLike(left, this);
    }

    public synchronized void finishedSorting() {
        sortedLists ++;
        if(parent != null)
            parent.releaseThread();
        notifyAll();
    }

    private synchronized void releaseThread() {
        threadsInUse --;
    }

    @Override
    public void run() {
        try {
            sortAndNotify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sortAndNotify() throws InterruptedException {
        sort();
        parent.finishedSorting();
    }
}
