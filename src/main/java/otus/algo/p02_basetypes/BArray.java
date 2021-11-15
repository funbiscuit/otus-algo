package otus.algo.p02_basetypes;

class BArray<T> {
    Object[] _arr;

    final int blockSize;
    int currentSize;

    BArray(int blockSize, int initial) {
        this.blockSize = blockSize;
        currentSize = initial;
        relocate(initial, 0);
    }

    @SuppressWarnings("unchecked")
    T get(int index) {
        return (T) _arr[index];
    }

    private void relocate(int newsize, int index) {
        Object[] tmp = new Object[newsize];

        // this will increase speed 2-3 times
//        if(_arr != null)
//            System.arraycopy(_arr, 0, tmp, 0, _arr.length);

        if (_arr != null)
            for (int i = 0; i < _arr.length; i++)
                if (i < index)
                    tmp[i] = _arr[i];
                else
                    tmp[i + 1] = _arr[i];
        _arr = tmp;
    }

    void add(int index, T element) {
        if (_arr == null || _arr.length <= index)
            relocate(index + blockSize, index);
        _arr[index] = element;
        currentSize = index + 1;
    }

    void set(int index, T element) {
        _arr[index] = element;
    }

    int size() {
        return currentSize;
    }
}
