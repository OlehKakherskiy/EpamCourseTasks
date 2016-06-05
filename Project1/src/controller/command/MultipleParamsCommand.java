package controller.command;

import controller.validator.AbstractValidator;
import view.View;

import java.util.Map;
import java.util.Scanner;

/**
 * Class represents command, that processes request with different count of input parameters.
 * Has a main strategy of processing this type of requests: firstly, validates all input parameters,
 * delegating validation to {@link AbstractValidator} chain and, if validation was successful, do
 * processing request operation, that should be implemented in {@link #processCommandHook(Map)} method.
 * Values of this map may be cast manually to expected types without any problems because of successful
 * validation.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @see AbstractValidator
 */
public abstract class MultipleParamsCommand<V> extends AbstractCommand<V, Map<String, Class>, Map<String, Object>> {


    public MultipleParamsCommand(View view, Scanner sc) {
        super(view, sc);
    }

    public MultipleParamsCommand(View view, Scanner sc, AbstractValidator<Map<String, Class>, Map<String, Object>> validator, Map<String, Class> paramsTemplate) {
        super(view, sc, validator, paramsTemplate);
    }
}