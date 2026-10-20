package br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail.ModeloEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Execução de IA que gera ou revisa um modelo de e-mail de atividade.
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(plural = "Execuções de IA de modelo de e-mail", tags = "Execução de IA de modelo de e-mail", icone = "fa fa-envelope")
public class ExecucaoIAModeloEmailAtividade extends ExecucaoIA {

    @ManyToOne(targetEntity = ModeloEmail.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, descricao = "Modelo de e-mail gerado ou revisado pela IA nesta execução.", label = "Modelo de e-mail")
    private ModeloEmail modeloEmail;

    public ModeloEmail getModeloEmail() {
        return modeloEmail;
    }

    public void setModeloEmail(ModeloEmail modeloEmail) {
        this.modeloEmail = modeloEmail;
        if (modeloEmail != null) {
            setEntidadeAlvoNome(ModeloEmail.class.getSimpleName());
            setEntidadeAlvoCodigo(modeloEmail.getId().toString());
        }
    }

}
