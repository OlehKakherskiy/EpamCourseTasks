package controller.command;

import app.GlobalContext;
import controller.validator.AbstractValidator;
import model.CreditList;
import model.entity.credit.Credit;
import view.View;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class encapsulates credit's searching, using inputted params, from credit list.
 * Key of this params should be the same as property names of {@link Credit} instance
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 * @see CreditList
 */
public class FindCreditCommand extends AbstractCommand<Set<Credit>> {

    /**
     * all properties of {@link Credit}
     */
    private static PropertyDescriptor[] propertyDescriptors;

    private static CreditList creditList;

    /**
     * inits {@link #propertyDescriptors} and credit list
     */
    static {
        try {
            propertyDescriptors = Introspector.getBeanInfo(Credit.class).getPropertyDescriptors();
        } catch (IntrospectionException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        creditList = (CreditList) GlobalContext.creditList;
    }

    public FindCreditCommand(View view, Scanner sc, AbstractValidator validator, Map<String, Class> paramsTemplate) {
        super(view, sc, validator, paramsTemplate);
    }

    /**
     * {@inheritDoc}
     * <p>
     * outputs searching rules and prepares input parameters as key/value pairs. If input parameters
     * number is odd, adds null value so that key/value pairs could be formed.
     *
     * @return key/value pairs of inputted parameters.
     */
    @Override
    protected Map<String, Object> inputParametersForProcessing() {
        view.printMessage((String) GlobalContext.getParam(GlobalContext.availableKeysFilters));
        String inputData = null;
        while (scanner.hasNext() && (inputData == null || inputData.isEmpty())) {
            inputData = scanner.nextLine();
        }
        List<String> splittedData = new ArrayList<>(Arrays.asList(inputData.split(" ")));
        if (splittedData.size() % 2 == 1) {
            splittedData.add(null);
        }
        Map<String, Object> result = new HashMap<>();
        Iterator<String> iterator = splittedData.iterator();
        while (iterator.hasNext()) {
            result.put(iterator.next(), iterator.next());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * <p>
     * builds predicate according to params, then filters bank list using this predicate
     *
     * @param params inputted filter params
     * @return credits that matches to built predicate
     */
    @Override
    protected Set<Credit> processCommandHook(Map<String, Object> params) {

        List<Predicate<Credit>> composedPredicate = params.entrySet().stream().map(this::addPredicate).collect(Collectors.toList());

        if (composedPredicate.isEmpty())
            return creditList.getCredits();

        Set<Credit> res = new HashSet<>();
        outer:
        for (Credit credit : creditList.getCredits()) {
            for (Predicate<Credit> predicate : composedPredicate) {
                if (!predicate.test(credit)) {
                    continue outer;
                }
            }
            res.add(credit);
        }
        return res;
    }

    /**
     * returns equality predicate of this entry parameter
     *
     * @param entry parameter, which predicate is built from
     * @return predicate that checks condition of this predicate
     */
    private Predicate<Credit> addPredicate(Map.Entry<String, Object> entry) {
        return credit -> {
            try {
                PropertyDescriptor descriptor = getPropertyDescriptorByFieldName(entry.getKey());
                return descriptor != null && descriptor.getReadMethod() != null && descriptor.getReadMethod().invoke(credit).equals(entry.getValue());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return false;
        };
    }

    /**
     * {@inheritDoc}
     * <p>
     * prints all formatted credits results or message e.g. no matches.
     *
     * @param result result of encapsulated algorithm, that should be outputted to UI.
     */
    @Override
    protected void outputResult(Set<Credit> result) {
        StringBuilder builder = new StringBuilder("result:");
        int i = 1;
        if (result.size() == 0) {
            builder.append("no results");
        } else {
            for (Credit credit : result) {
                builder.append(i).append(": ").append(credit.toString()).append("\n");
            }
        }
        view.printMessage(builder.toString());
    }


    /**
     * returns property descriptor of special name param
     *
     * @param name property name
     * @return property descriptor of this name or null
     */
    private PropertyDescriptor getPropertyDescriptorByFieldName(String name) {
        for (PropertyDescriptor pd : propertyDescriptors) {
            if (pd.getName().equals(name)) {
                return pd;
            }
        }
        return null;
    }
}