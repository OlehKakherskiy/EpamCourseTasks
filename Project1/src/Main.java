import model.entity.credit.Credit;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Main {

    public static void main(String[] args) throws IntrospectionException {
        Credit c = new Credit("creditName", "bankName", 12, 1, 10000) {
            @Override
            public String getOfferDetails() {
                return null;
            }

            @Override
            public String getRequiredDocumentsDescription() {
                return null;
            }

            @Override
            public void processBankOperation(Map<String, Object> documents) {

            }
        };

        PropertyDescriptor[] descriptors = Introspector.getBeanInfo(c.getClass()).getPropertyDescriptors();

        for (PropertyDescriptor pd : descriptors)
            System.out.println(pd.toString());
    }
}
