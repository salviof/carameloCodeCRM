package br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.templates.ModeloEmailMarketing;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 * Execução de IA que gera ou revisa um modelo de e-mail marketing.
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(plural = "Execuções de IA de e-mail marketing", tags = "Execução de IA de e-mail marketing", icone = "fa fa-envelope-open-text")
public class ExecucaoIAModeloEmailMkt extends ExecucaoIA {

    @ManyToOne(targetEntity = ModeloEmailMarketing.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, descricao = "Modelo de e-mail marketing gerado ou revisado pela IA nesta execução.", label = "Modelo de e-mail marketing")
    private ModeloEmailMarketing modeloEmailMarketing;

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = ModeloEmailMarketing.class)
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        super.prepararNovoObjeto(parametros);

        setModeloEmailMarketing(getParametroInicialEnviado(ModeloEmailMarketing.class, parametros));
        if (getModeloEmailMarketing().getPersonaCoautoria() == null) {
            throw new ErroPreparandoObjeto(this, "Persona não foi definida");
        }
        setPersona(getModeloEmailMarketing().getPersonaCoautoria());
        setEntidadeAlvoNome(ModeloEmailMarketing.class.getSimpleName());
        setEntidadeAlvoCodigo(getModeloEmailMarketing().getId().toString());

    }

    public ModeloEmailMarketing getModeloEmailMarketing() {
        return modeloEmailMarketing;
    }

    public void setModeloEmailMarketing(ModeloEmailMarketing modeloEmailMarketing) {
        this.modeloEmailMarketing = modeloEmailMarketing;
        if (modeloEmailMarketing != null) {
            setEntidadeAlvoNome(ModeloEmailMarketing.class.getSimpleName());
            setEntidadeAlvoCodigo(modeloEmailMarketing.getId().toString());
        }
    }

}
