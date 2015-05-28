package list;

import java.util.LinkedList;
import java.util.List;

public class IntegerList {

	private List<Integer> list;
	private int maxThreads;
    private int threadsInUse;
    private boolean sorted;
	private int sortedElements;

	public IntegerList() {
		list = new LinkedList<>();
	}

	public IntegerList(List<Integer> aList) {
		list = aList;
        sorted = false;
		sortedElements = 0;
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
		if(!isEmpty()) {
			new QuickSorter(this, 0, list.size() - 1).sort();
			while (!isSorted()) wait();
		}
	}

	@Override
	public String toString() {
		return list.toString();
	}

	public void setMaxThreads(int threadsCount) {
		maxThreads = threadsCount;
	}

    public boolean checkIsSorted() {
        boolean isSorted = true;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) isSorted = false;
        }

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

	public synchronized void finishedSorting() throws InterruptedException {
		this.sortedElements ++;

		if(this.sortedElements >= size()) {
			if (checkIsSorted()) {
				setSorted(true);
				notifyAll();
			}
		}
	}
}
