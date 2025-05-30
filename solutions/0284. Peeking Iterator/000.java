// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> iterator;
    Integer tmp;
    boolean valid = false;
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    this.iterator = iterator;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
          if(valid) return tmp;
          tmp = iterator.next();
          valid = true;
          return tmp;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
        if(valid)
        {
            valid = false;
            return tmp;
        }
	    return iterator.next();
	}

	@Override
	public boolean hasNext() {
	    return valid || iterator.hasNext();
	}
}