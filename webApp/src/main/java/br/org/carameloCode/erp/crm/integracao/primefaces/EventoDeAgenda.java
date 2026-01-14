/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.integracao.primefaces;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.siteMap.MapaDeFormularios;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.primefaces.model.ScheduleDisplayMode;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author desenvolvedor
 */
public class EventoDeAgenda implements ScheduleEvent<AtividadeCRM> {

    private final AtividadeCRM atividade;

    public EventoDeAgenda(AtividadeCRM pAtividade) {
        atividade = pAtividade;
    }

    @Override
    public String getId() {
        return String.valueOf(atividade.getId());
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public AtividadeCRM getData() {
        return atividade;
    }

    @Override
    public String getTitle() {

        return atividade.getTipoAtividade().getNomeAtividade() + " - " + atividade.getProspectoEmpresa().getNome();
    }

    public LocalDateTime convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public Date convertToLocalDateViaInstant(LocalDateTime localDateToConvert) {
        Date date1 = Date.from(localDateToConvert.atZone(ZoneId.systemDefault()).toInstant());
        return date1;
    }

    @Override
    public LocalDateTime getStartDate() {
        return convertToLocalDateViaInstant(atividade.getDataHoraPrevisaoExecucao());
    }

    @Override
    public LocalDateTime getEndDate() {
        return convertToLocalDateViaInstant(UtilCRCDataHora.incrementaMinutos(atividade.getDataHoraPrevisaoExecucao(), 15));
    }

    @Override
    public boolean isAllDay() {
        return false;
    }

    @Override
    public String getStyleClass() {
        return "Evento";
    }

    @Override
    public boolean isEditable() {
        return false;
    }

    @Override
    public String getDescription() {
        return atividade.getAnotacoes();
    }

    @Override
    public String getUrl() {
        return MapaDeFormularios.getUrlFormulario(FabAcaoCRMAtendimento.EXECUCAO_ATIVIDADE_MB.getRegistro(),
                atividade.getTipoAtividade(), atividade.getProspectoEmpresa(), atividade);
    }

    @Override
    public String getGroupId() {
        return "atividadesAgendadas";
    }

    @Override
    public void setStartDate(LocalDateTime start) {
        atividade.setDataHoraPrevisaoExecucao(convertToLocalDateViaInstant(start));
    }

    @Override
    public void setEndDate(LocalDateTime end) {
        atividade.setDataHoraPrevisaoExecucao(convertToLocalDateViaInstant(end));
    }

    @Override
    public boolean isOverlapAllowed() {
        return false;
    }

    private Map<String, Object> dynamicProperties;

    @Override
    public Map<String, Object> getDynamicProperties() {
        return dynamicProperties;
    }

    public Object setDynamicProperty(String key, Object value) {
        if (dynamicProperties == null) {
            dynamicProperties = new HashMap<>();
        }
        return dynamicProperties.put(key, value);
    }

    @Override
    public void setAllDay(boolean bln) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ScheduleDisplayMode getDisplay() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getBackgroundColor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getBorderColor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTextColor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean isDraggable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean isResizable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
