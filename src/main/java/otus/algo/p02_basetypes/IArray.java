package otus.algo.p02_basetypes;

class IArray<T> {
    BArray<BArray<T>> _arr;
    int _delta;

    IArray(int size) {
        _delta = size;
        _arr = new BArray<>(_delta, 0);
        _arr.add(0, new BArray<>(_delta, 0));
    }

    T get(int index) {
        int index1 = index / _delta;
        int index2 = index % _delta;
        return (T) _arr.get(index1).get(index2);
    }

    private void relocate() {
        _arr.add(_arr.size(), new BArray<>(_delta, 0));
    }

    void add(int index, T element) {
        while (index >= _arr.size() * _delta)
            relocate();
        int index1 = index / _delta;
        int index2 = index % _delta;
        _arr.get(index1).add(index2, element);
    }

}
