package controller.command;

import controller.validator.AbstractValidator;
import model.CreditList;
import model.entity.credit.Credit;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class FindCreditCommand extends AbstractOperationCommand<List<Credit>> {

    private static PropertyDescriptor[] propertyDescriptors;

    static {
        try {
            propertyDescriptors = Introspector.getBeanInfo(Credit.class).getPropertyDescriptors();
        } catch (IntrospectionException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public FindCreditCommand(Map<String, Class> paramsTemplates,
                             AbstractValidator<Map<String, Class>, Map<String, Object>> paramsTemplatesValidator) {
        super(paramsTemplates, paramsTemplatesValidator);
    }

    @Override
    protected List<Credit> processCommandHook(Map<String, Object> params) {

        CreditList list = (CreditList) params.get("creditList");

        Predicate<Credit> composedPredicate = null;

        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (composedPredicate == null) {
                composedPredicate = addPredicate(param);
            } else {
                composedPredicate.and(addPredicate(param));
            }
        }
        return list.getCredits().stream().filter(composedPredicate).collect(Collectors.toList());
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

    private PropertyDescriptor getPropertyDescriptorByFieldName(String name) {
        for (PropertyDescriptor pd : propertyDescriptors) {
            if (pd.getName().equals(name)) {
                return pd;
            }
        }
        return null;
    }
}