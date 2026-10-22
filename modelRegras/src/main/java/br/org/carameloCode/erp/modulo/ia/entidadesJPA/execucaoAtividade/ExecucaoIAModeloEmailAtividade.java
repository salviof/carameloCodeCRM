package br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.modeloEmail.ModeloEmail;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

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

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = ModeloEmail.class)
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        super.prepararNovoObjeto(parametros);

        setModeloEmail(getParametroInicialEnviado(ModeloEmail.class, parametros));
        if (getModeloEmail().getPersonaCoautoria() == null) {
            throw new ErroPreparandoObjeto(this, "Persona não foi definida");
        }
        setPersona(getModeloEmail().getPersonaCoautoria());
        //mesmo nome usado pelo ServicoIA na busca do diálogo
        setEntidadeAlvoNome(UtilCRCReflexaoObjeto.getClassExtraindoProxy(getModeloEmail().getClass().getSimpleName()).getSimpleName());
        setEntidadeAlvoCodigo(getModeloEmail().getId().toString());

    }

    public ModeloEmail getModeloEmail() {
        return modeloEmail;
    }

    public void setModeloEmail(ModeloEmail modeloEmail) {
        this.modeloEmail = modeloEmail;
        if (modeloEmail != null) {
            //mesmo nome usado pelo ServicoIA na busca do diálogo
            setEntidadeAlvoNome(UtilCRCReflexaoObjeto.getClassExtraindoProxy(modeloEmail.getClass().getSimpleName()).getSimpleName());
            setEntidadeAlvoCodigo(modeloEmail.getId().toString());
        }
    }

}
