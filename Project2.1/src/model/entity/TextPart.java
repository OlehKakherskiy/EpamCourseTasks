package model.entity;

import app.GlobalContext;

import java.util.List;
import java.util.Set;

/**
 * Root of composite hierarchy of text's part. Declares main interface of hierarchy's elements (implementation of
 * transparent composite pattern).This elements are encapsulated, so that user can't create them using constructor.
 * It's because this hierarchy has no logical protection of data inconsistency (e.g. potentially text part - word, can
 * consist a paragraph part, sentence part and symbols). To avoid this situation all constructors are with package
 * modifier, text parts could be created through the factory {@link TextPartFactory} or can be built using
 * {@link SplitChain} hierarchy through. SplitChain hierarchy configures before program starts, so that user has only
 * one strategy for text building.
 * <p>
 * For creating object prefer to use {@link TextPartFactory}, but if system is scaling, {@link SplitChain} hierarchy could
 * be used as well.
 * </p>
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @see TextPartFactory
 * @see SplitChain
 */
public abstract class TextPart {

    /**
     * constructor with package modifier
     */
    TextPart() {
    }

    /**
     * Message, when unsupported method is called
     */
    private static final String UNSUPPORTED_OPERATION_MESSAGE = (String) GlobalContext.getParam(GlobalContext.UNSUPPORTED_OPERATION_TEXT_KEY);

    /**
     * Finds words in all questioning sentences with target length without repetitions.
     * If target length is less than 0 - returns empty set.
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