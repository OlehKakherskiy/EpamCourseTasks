package tasks;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class ArrayElementWrapper {

    private int arrayElement;

    private int elementIndex;

    public ArrayElementWrapper(int arrayElement, int elementIndex) {
        this.arrayElement = arrayElement;
        this.elementIndex = elementIndex;
    }

    public int getArrayElement() {
        return arrayElement;
    }

    public int getElementIndex() {
        return elementIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayElementWrapper)) return false;

        ArrayElementWrapper that = (ArrayElementWrapper) o;

        if (arrayElement != that.arrayElement) return false;
        return elementIndex == that.elementIndex;

    }
}
