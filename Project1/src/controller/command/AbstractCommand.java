package controller.command;

import controller.ValidationException;
import controller.validator.AbstractValidator;
import view.View;

import java.util.Scanner;

/**
 * Root of commands hierarchy. Each command encapsulates special interaction between user and program.
 * Each command interact with user for getting input params, then validates these params (optionally),
 * processes special encapsulated action, that user wants to process with program, and outputs formatted
 * result of action to UI.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @see AbstractValidator
 */
public abstract class AbstractCommand<V, T, E> {

    /**
     * output results
     */
    protected View view;

    /**
     * read command params
     */
    protected Scanner scanner;

    /**
     * validates command parameters
     */
    protected AbstractValidator<T, E> validator;

    /**
     * parameters templates for validation
     */
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

    /**
     * processes scenarios of interaction with client. returns values, that client has
     * inputted via interface (console or UI). Should describe inputting rules to user
     * and implements getting this params from UI.
     *
     * @return inputted values by client
     */
    protected abstract E inputParametersForProcessing();

    /**
     * Main algorithm of interaction between client and program. Firstly, each command
     * should interact with user via {@link #inputParametersForProcessing()}. Secondly,
     * it validates each params using {@link #validator} chains. Then each command processes
     * special encapsulated algorithm , which result is inputted then via {@link #outputResult(Object)}
     * to UI.
     */
    public final void processCommand() {
        boolean successfulValidation = false;
        E inputParams = null;
        while (!successfulValidation) {
            inputParams = inputParametersForProcessing();
            successfulValidation = validateInputParams(inputParams);
        }

        outputResult(processCommandHook(inputParams));
    }

    /**
     * Encapsulates algorithm of command. This algorithm processes special
     * actions with inputted params via UI.
     *
     * @param params inputted params via UI.
     * @return the result of encapsulated algorithm
     */
    protected abstract V processCommandHook(E params);

    /**
     * Outputs formatted result to UI.
     *
     * @param result result of encapsulated algorithm, that should be outputted to UI.
     */
    protected abstract void outputResult(V result);

    /**
     * Method validates input params, delegating the process of validation
     * to special {@link AbstractValidator} object with validation patterns
     * {@link #paramsTemplate}. If validation fails, input the exception message
     * to UI.
     *
     * @param inputParams params that should be validated
     * @return whether validation was successful
     */
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

    public void setValidator(AbstractValidator<T, E> validator) {
        this.validator = validator;
    }

    public void setParamsTemplate(T paramsTemplate) {
        this.paramsTemplate = paramsTemplate;
    }
}