package list;

public class QuickSorter extends Thread {

    private Integer higherIndex;
    private Integer lowerIndex;
    private IntegerList listToSort;

    public QuickSorter(IntegerList listToSort, Integer lowerIndex, Integer higherIndex) {
        this.listToSort = listToSort;
        this.lowerIndex = lowerIndex;
        this.higherIndex = higherIndex;
    }

    public synchronized void sort() throws InterruptedException {
        if(lowerIndex >= higherIndex) {
            listToSort.finishedSorting();
            return;
        }

        int pivot = listToSort.get(higherIndex);
        int partition = partition(lowerIndex, higherIndex, pivot);

        startNewThread(listToSort, 0, partition - 1);
        startNewThread(listToSort, partition + 1, higherIndex);

        listToSort.finishedSorting();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private int partition(int left, int right, int pivot){
        int leftCursor = left - 1;
        int rightCursor = right;
        while(leftCursor < rightCursor){
            while(listToSort.get(++leftCursor) < pivot);
            while(rightCursor > 0 && listToSort.get(--rightCursor) > pivot);

            if(leftCursor < rightCursor)
                swap(leftCursor, rightCursor);
        }
        swap(leftCursor, right);
        return leftCursor;
    }

    private synchronized void startNewThread(IntegerList listToSort, Integer  lowIndex, Integer highIndex) {
        new QuickSorter(listToSort, lowIndex, highIndex).start();
    }

    private void swap(int i, int j) {
        int temp = listToSort.get(i);
        listToSort.set(i, listToSort.get(j));
        listToSort.set(j, temp);
    }

    @Override
    public void run() {
        try {
            sort();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void start() {
        if(!listToSort.isSorted()) {
            try {
                listToSort.registerThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.start();
            listToSort.releaseThread();
        }
    }
}
