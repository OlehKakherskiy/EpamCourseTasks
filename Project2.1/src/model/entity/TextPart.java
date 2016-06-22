package model.entity;

import app.GlobalContext;

import java.util.List;
import java.util.Set;

/**
 * Root of composite hierarchy of text's part. Declares main interface of hierarchy's elements (implementation of
 * transparent composite pattern).
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class TextPart {

    /**
     * Message, when unsupported method is called
     */
    private static final String UNSUPPORTED_OPERATION_MESSAGE = (String) GlobalContext.getParam(GlobalContext.UNSUPPORTED_OPERATION_TEXT_KEY);

    /**
     * Finds words in text with target length without repetitions.
     * If target length is less than 0 - returns empty set
     *
     * @param length words length that should be found
     * @return set, contained words with target length. Empty set if length <=0
     */
    public abstract Set<TextPart> findWords(int length);

    /**
     * Adds text's part to the end of current part's children list.
     * <p>
     * Should be redefined if text's part is composite structure (can have zero or more children).
     * </p>
     *
     * @param textPart text part, that should be added to the end of current part's children list
     * @throws UnsupportedOperationException as default method implementation
     */
    public void addTextPart(TextPart textPart) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }

    /**
     * Removes child at specific position. If position is <= 0 or >= children count - do nothing.
     * <p>
     * Should be redefined if text's part is composite structure (can have zero or more children).
     * </p>
     *
     * @param position specific child's position. Should be >0 AND < children count
     * @throws UnsupportedOperationException as default method implementation
     */
    public void removeTextPart(int position) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }

    /**
     * Method returns copy of data structure, containing all text part's children.
     * <p>
     * Should be redefined if text's part is composite structure (can have zero or more children).
     * </p>
     *
     * @return list of subparts
     * @throws UnsupportedOperationException as default method implementation
     */
    public List<TextPart> getChildren() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }

    /**
     * Formats string representation of this text part. If part is composite one - formats string
     * representation of all subparts.
     *
     * @return string representation of this text part.
     */
    public abstract String format();
}