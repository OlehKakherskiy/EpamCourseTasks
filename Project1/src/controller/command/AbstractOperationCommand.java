package controller.command;

import controller.validator.AbstractValidator;
import controller.validator.ParamsTemplateValidator;
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
public abstract class AbstractOperationCommand<T> implements IOperationCommand<T, Map<String, Object>> {

    protected Map<String, Class> paramsTemplates;

    protected View view;

    protected Scanner scanner;

    protected AbstractValidator<Map<String, Class>, Map<String, Object>> paramsTemplatesValidator;

    public AbstractOperationCommand(Map<String, Class> paramsTemplates,
                                    AbstractValidator<Map<String, Class>, Map<String, Object>> paramsTemplatesValidator) {
        if (!(paramsTemplatesValidator instanceof ParamsTemplateValidator)) {
            this.paramsTemplatesValidator = new ParamsTemplateValidator(paramsTemplatesValidator);
        } else {
            this.paramsTemplatesValidator = paramsTemplatesValidator;
        }
        this.paramsTemplates = paramsTemplates;
    }

    @Override
    public T processCommand(Map<String, Object> params) {
        if (!paramsTemplatesValidator.validate(paramsTemplates, params))
            return null;
        return processCommandHook(params);
    }

    protected abstract T processCommandHook(Map<String, Object> params);
}