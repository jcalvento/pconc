package list;

import java.util.LinkedList;
import java.util.List;

public class IntegerList {

	private List<Integer> list;
	private int maxThreads;
    private int threadsInUse;
    private boolean sorted;

    public IntegerList() {
		list = new LinkedList<>();
	}

	public IntegerList(List<Integer> aList) {
		list = aList;
        sorted = false;
	}

	public synchronized int size() {
		return list.size();
	}
	
	public synchronized boolean isEmpty() {
		return list.isEmpty();
	}
	
	public synchronized boolean contains(Integer anInteger) {
		return list.contains(anInteger);
	}
	
	public synchronized void add(Integer anInteger) {
		list.add(anInteger);
	}
	
	public synchronized Integer get(Integer anIndex) {
		return list.get(anIndex);
	}
	
	public synchronized void set(Integer anIndex, Integer anElementToAdd) {
		list.set(anIndex, anElementToAdd);
	}
	
	public synchronized void sort() throws InterruptedException {
		new QuickSorter(this).sort(0, list.size() - 1);
        while(!isSorted()) wait();
	}

	@Override
	public String toString() {
		return list.toString();
	}

	public void setMaxThreads(int threadsCount) {
		maxThreads = threadsCount;
	}

    public synchronized void finishedSorting() {
        if(checkIsSorted())
            notifyAll();
    }

    private boolean checkIsSorted() {
        boolean isSorted = true;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) isSorted = false;
        }

        if (isSorted) setSorted(true);
        return isSorted;
    }

	public synchronized boolean isSorted() {
		return sorted;
	}

    public synchronized void registerThread() throws InterruptedException {
        while(maxThreads == threadsInUse) {
            wait();
        }
        threadsInUse ++;
    }

    public synchronized void releaseThread() {
        threadsInUse --;
        notifyAll();
    }

    public synchronized void setSorted(boolean sorted) {
        this.sorted = sorted;
    }
}
