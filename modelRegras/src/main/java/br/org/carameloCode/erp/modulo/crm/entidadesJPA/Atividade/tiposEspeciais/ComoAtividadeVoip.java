package br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tiposEspeciais;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.pabx.AudioVoip;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.wtzpModeloMKT.telefone.Telefone;

public interface ComoAtividadeVoip {
    Telefone getTelefoneVoip();
    void setTelefoneVoip(Telefone telefone);
    AudioVoip getAudioVoip();
    void setAudioVoip(AudioVoip audioVoip);

}
