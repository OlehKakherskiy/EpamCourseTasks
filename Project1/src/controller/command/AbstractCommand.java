package controller.command;

import controller.ValidationException;
import controller.validator.AbstractValidator;
import view.View;

import java.util.Scanner;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public abstract class AbstractCommand<V, T, E> {

    protected View view;

    protected Scanner scanner;


    protected AbstractValidator<T, E> validator;

    protected T paramsTemplate;


    public AbstractCommand(View view, Scanner sc) {
        this.view = view;
        this.scanner = sc;
    }

    public AbstractCommand(View view, Scanner sc,
                           AbstractValidator<T, E> validator, T paramsTemplate) {
        this.view = view;
        this.scanner = sc;
        this.validator = validator;
        this.paramsTemplate = paramsTemplate;
    }

    protected abstract E inputParametersForProcessing();

    public final void processCommand() {
        boolean successfulValidation = false;
        E inputParams = null;
        while (!successfulValidation) {
            inputParams = inputParametersForProcessing();
            successfulValidation = validateInputParams(inputParams);
        }

        outputResult(processCommandHook(inputParams));
    }

    protected abstract V processCommandHook(E params);

    protected abstract void outputResult(V result);

    public void setValidator(AbstractValidator<T, E> validator) {
        this.validator = validator;
    }

    public void setParamsTemplate(T paramsTemplate) {
        this.paramsTemplate = paramsTemplate;
    }

    private boolean validateInputParams(E inputParams) {
        if (validator == null)
            return true;
        try {
            validator.validate(paramsTemplate, inputParams);
        } catch (ValidationException e) {
            view.printMessage(e.getMessage());
            return false;
        }
        return true;
    }
}