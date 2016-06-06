package controller.command;

import app.GlobalContext;
import controller.validator.AbstractValidator;
import controller.validator.IncompatibleStringValueValidator;
import model.CreditList;
import model.entity.credit.Credit;
import view.View;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class FindCreditCommand extends MultipleParamsCommand<Set<Credit>> {

    private static PropertyDescriptor[] propertyDescriptors;

    private static CreditList creditList;

    static {
        try {
            propertyDescriptors = Introspector.getBeanInfo(Credit.class).getPropertyDescriptors();
        } catch (IntrospectionException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        creditList = (CreditList) GlobalContext.creditList;
    }

    public FindCreditCommand(View view, Scanner sc) {
        super(view, sc);
    }

    public FindCreditCommand(View view, Scanner sc, AbstractValidator<Map<String, Class>, Map<String, Object>> validator, Map<String, Class> paramsTemplate) {
        super(view, sc, validator == null ? new IncompatibleStringValueValidator(null) : validator, paramsTemplate);
    }


    @Override
    protected Map<String, Object> inputParametersForProcessing() {
        view.printMessage((String) GlobalContext.getParam(GlobalContext.availableKeysFilters));
        String inputData = null;
        if (scanner.hasNext()) {
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

    @Override
    protected Set<Credit> processCommandHook(Map<String, Object> params) {

        List<Predicate<Credit>> composedPredicate = params.entrySet().stream().map(this::addPredicate).collect(Collectors.toList());

        if (composedPredicate == null)
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

    private Predicate<Credit> addPredicate(Map.Entry<String, Object> entry) {
        return credit -> {
            try {
                Method m = getPropertyDescriptorByFieldName(entry.getKey()).getReadMethod();
                return m != null && m.invoke(credit).equals(entry.getValue());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return false;
        };
    }

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

    private PropertyDescriptor getPropertyDescriptorByFieldName(String name) {
        for (PropertyDescriptor pd : propertyDescriptors) {
            if (pd.getName().equals(name)) {
                return pd;
            }
        }
        return null;
    }
}