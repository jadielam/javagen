package com.jadieldearmas.codegenerator;

class Pair<F, S> {
    
	/**
	 * First member of pair
	 */
	private F first;
	
	/**
	 * Second member of pair
	 */
    private S second; 

    /**
     * Creates a pair given its two elements.
     * @param first the first member of the pair
     * @param second the second member of the pair
     */
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Sets the first member
     * @param first the first member of the pair
     */
    public void setFirst(F first) {
        this.first = first;
    }

    /**
     * Sets the second member
     * @param second the second member of the pair
     */
    public void setSecond(S second) {
        this.second = second;
    }

    /**
     * Returns the first member of the pair
     * @return the first member
     */
    public F getFirst() {
        return this.first;
    }

    /**
     * Returns the second member of the pair
     * @return the second member
     */
    public S getSecond() {
        return this.second;
    }

	/** 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.first == null) ? 0 : this.first.hashCode());
		result = prime * result
				+ ((this.second == null) ? 0 : this.second.hashCode());
		return result;
	}

	/**
	 * Equality based on the equals method of the first and of the second member. 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Pair)) {
			return false;
		}
		Pair other = (Pair) obj;
		if (this.first == null) {
			if (other.first != null) {
				return false;
			}
		} else if (!this.first.equals(other.first)) {
			return false;
		}
		if (this.second == null) {
			if (other.second != null) {
				return false;
			}
		} else if (!this.second.equals(other.second)) {
			return false;
		}
		return true;
	}
    
    
}