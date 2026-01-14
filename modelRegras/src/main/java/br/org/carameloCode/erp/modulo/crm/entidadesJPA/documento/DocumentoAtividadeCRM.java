/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.arquivos.arquivoAnexado.ArquivoAnexado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo.ModeloDocumentoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.documento.modelo.ModeloDocumentoTipoAtividade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author desenvolvedor
 */
@Entity
@InfoObjetoSB(tags = {"Documento CRM"}, plural = "Documentos CRM", modulo = ERPCrm.NOME_MODULO_ERP)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoAnexo")
public class DocumentoAtividadeCRM extends ArquivoAnexado {

    @ManyToOne(targetEntity = ModeloDocumentoCRM.class)
    private ModeloDocumentoCRM modeloDocumento;

    @ManyToOne(targetEntity = AtividadeCRM.class)
    private AtividadeCRM atividadeGeradora;

    @ManyToMany(mappedBy = "arquivosAnexados", targetEntity = EnvioEmail.class)
    private List<EnvioEmail> envios;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @CalculoDocumentoCrm(calculo = CalculosDocumentoCRM.documentoFoiEnviado)
    boolean foiEnviado;

    @Deprecated
    public DocumentoAtividadeCRM() {
        super();
    }

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = {AtividadeCRM.class, ModeloDocumentoTipoAtividade.class})
    public void prepararNovoObjeto(Object... parametros) {
        try {
            super.prepararNovoObjeto(parametros); //To change body of generated methods, choose Tools | Templates.

            atividadeGeradora = getParametroInicialEnviado(AtividadeCRM.class, parametros);
            setProspecto(atividadeGeradora.getProspectoEmpresa());
            setModeloDocumento(getParametroInicialEnviado(ModeloDocumentoTipoAtividade.class, parametros));

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro preparando " + this.getClass().getSimpleName(), t);
        }

    }

    public AtividadeCRM getAtividadeGeradora() {
        return atividadeGeradora;
    }

    public void setAtividadeGeradora(AtividadeCRM atividadeGeradora) {
        this.atividadeGeradora = atividadeGeradora;
    }

    public List<EnvioEmail> getEnvios() {
        return envios;
    }

    public void setEnvios(List<EnvioEmail> envios) {
        this.envios = envios;
    }

    public boolean isFoiEnviado() {
        foiEnviado = (Boolean) CalculosDocumentoCRM.documentoFoiEnviado.getValor(this);
        return foiEnviado;
    }

    public ModeloDocumentoCRM getModeloDocumento() {
        return modeloDocumento;
    }

    public void setModeloDocumento(ModeloDocumentoCRM modeloDocumento) {
        this.modeloDocumento = modeloDocumento;
    }

}
