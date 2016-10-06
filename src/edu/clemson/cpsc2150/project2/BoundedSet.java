package edu.clemson.cpsc2150.project2;

public class BoundedSet {

    private Coordinate[] contents;
    private int count;

    public BoundedSet(int max) {
        count = 0;
        contents = new Coordinate[max];

    }

    /**
     * @param element Element to be inserted into the set.
     * @requires element != null and [element is not in the set.] and
     * [The set is not full.]
     * @ensures <pre>
     * [element is unchanged.] and [element is inserted into the set.]
     * </pre>
     */
    public void insert(Coordinate element) {
        contents[count] = element;
        count++;
    }

    /**
     * @param element Element to find in the set.
     * @return Indicator of element presence in the set.
     * @requires element != null
     * @ensures <pre>
     * [element is unchanged.] and
     * [The return is true if element was found in the set; otherwise
     *  the return is false.]
     * </pre>
     */
    public boolean contains(Coordinate element) {
        for(int i = 0; i < count; i++)
        {
                if (contents[i].equals(element)) {
                    return true;
                }
        }
        return false;
    }

    /**
     * @param element Element to remove from the set.
     * @requires element != null and [element is in the set.]
     * @ensures <pre>
     * [element is unchanged.] and [element is removed from the set.]
     * </pre>
     */
    public void remove(Coordinate element){
        for(int i = 0; i < count; i++)
        {
            if(contents[i].equals(element))
            {
                for(int z = i; z < count-1; z++)
                {
                    contents[z] = contents[z+1];
                }
                count--;
            }
        }
    }

    /**
     * @return The size of the set.
     * @requires true
     * @ensures <pre>
     * [The contents of the set are unchanged.] and
     * [The return equals the number of elements in the set.]
     * </pre>
     */
    public int sizeOfSet() { return count; }

    /**
     * @return The element removed from the set.
     * @requires [The set is not empty.]
     * @ensures <pre>
     * [An arbitrarily chosen element is removed from the set; this element * is returned to the caller.]
     * </pre>
     */

    public Coordinate removeAny(){
        count--;
        return contents[count+1];
    }
}
