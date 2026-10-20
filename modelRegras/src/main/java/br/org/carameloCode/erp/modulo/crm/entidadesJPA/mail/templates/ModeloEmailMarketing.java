/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.templates;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail.ModeloEmail;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(plural = "Modelo de email", tags = "Modelo de Email", icone = "fa-solid fa-tower-broadcast")
@Entity
public class ModeloEmailMarketing extends ModeloEmail {

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String codigoMautic;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "modeloEmailMarketing_instrucaoIA",
            joinColumns = @JoinColumn(name = "modeloEmailMarketing_id"),
            inverseJoinColumns = @JoinColumn(name = "instrucaoIA_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, descricao = "Instruções de IA usadas ao gerar ou revisar este e-mail marketing.", label = "Instruções de IA")
    private List<InstrucaoIA> instrucoesIA = new ArrayList<>();

    public List<InstrucaoIA> getInstrucoesIA() {
        return instrucoesIA;
    }

    public void setInstrucoesIA(List<InstrucaoIA> instrucoesIA) {
        this.instrucoesIA = instrucoesIA;
    }
}
