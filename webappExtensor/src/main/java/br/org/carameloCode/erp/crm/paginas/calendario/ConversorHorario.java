/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package br.org.carameloCode.erp.crm.paginas.calendario;

import com.super_bits.modulosSB.webPaginas.JSFBeans.util.ConversorSB;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import org.primefaces.util.CalendarUtils;

/**
 *
 * @author SalvioF
 */
@FacesConverter(value = "conversorTimesql")
public class ConversorHorario extends ConversorSB {

    private static final SimpleDateFormat FORMATADOR_DATA = new SimpleDateFormat("HH:mm");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        try {
            if (value == null) {
                return null;
            }
            if (value.contains("_")) {
                // Bug primefaces
                return new Date();
            }

            Date data = FORMATADOR_DATA.parse(value);
            Time horario = new Time(data.getTime());
            return data;

        } catch (Throwable t) {
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Map<String, Object> atributos = component.getAttributes();
        return FORMATADOR_DATA.format(value);

    }

}
