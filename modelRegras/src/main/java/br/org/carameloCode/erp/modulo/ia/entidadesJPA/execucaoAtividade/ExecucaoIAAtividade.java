package br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 * Execução de IA vinculada a uma atividade do CRM.
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(plural = "Execuções de IA da atividade", tags = "Execução de IA da atividade", icone = "fa fa-robot")
public class ExecucaoIAAtividade extends ExecucaoIA {

    @ManyToOne(targetEntity = AtividadeCRM.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, descricao = "Atividade do CRM para a qual o conteúdo foi gerado.", label = "Atividade")
    private AtividadeCRM atividade;

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = AtividadeCRM.class)
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        super.prepararNovoObjeto(parametros);

        setAtividade(getParametroInicialEnviado(AtividadeCRM.class, parametros));
        if (getAtividade().getTipoAtividade() == null || getAtividade().getTipoAtividade().getPersona() == null) {
            throw new ErroPreparandoObjeto(this, "Persona não foi definida no tipo de atividade");
        }
        setPersona(getAtividade().getTipoAtividade().getPersona());
        //mesmo nome usado pelo ServicoIA na busca do diálogo (as subclasses de AtividadeCRM usam o próprio nome)
        setEntidadeAlvoNome(UtilCRCReflexaoObjeto.getClassExtraindoProxy(getAtividade().getClass().getSimpleName()).getSimpleName());
        setEntidadeAlvoCodigo(getAtividade().getId().toString());

    }

    public AtividadeCRM getAtividade() {
        return atividade;
    }

    public void setAtividade(AtividadeCRM atividade) {
        this.atividade = atividade;
        if (atividade != null) {
            //mesmo nome usado pelo ServicoIA na busca do diálogo (as subclasses de AtividadeCRM usam o próprio nome)
            setEntidadeAlvoNome(UtilCRCReflexaoObjeto.getClassExtraindoProxy(atividade.getClass().getSimpleName()).getSimpleName());
            setEntidadeAlvoCodigo(atividade.getId().toString());
        }
    }

}
