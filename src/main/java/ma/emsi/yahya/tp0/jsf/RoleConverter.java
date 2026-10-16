package ma.emsi.yahya.tp0.jsf;

import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UISelectOne;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.faces.model.SelectItem;

@FacesConverter("roleConverter")
public class RoleConverter implements Converter<String> {

    @Override
    public String getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isBlank()) {
            return null;
        }

        String normalizedSubmitted = submittedValue.replace("\r\n", "\n").replace("\r", "\n").trim();

        if (component instanceof UISelectOne selectOne) {
            for (UIComponent child : selectOne.getChildren()) {
                if (child instanceof jakarta.faces.component.UISelectItems selectItemsComp) {
                    Object val = selectItemsComp.getValue();
                    if (val instanceof Iterable<?> list) {
                        for (Object itemObj : list) {
                            if (itemObj instanceof SelectItem item) {
                                String itemVal = (String) item.getValue();
                                if (itemVal != null) {
                                    String normalizedItem = itemVal.replace("\r\n", "\n").replace("\r", "\n").trim();
                                    if (normalizedItem.equals(normalizedSubmitted)) {
                                        return itemVal;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return submittedValue.replace("\r\n", "\n").replace("\r", "\n");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, String value) {
        return value != null ? value : "";
    }
}
