package br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucaoAtividade;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.AtividadeCRM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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

    public AtividadeCRM getAtividade() {
        return atividade;
    }

    public void setAtividade(AtividadeCRM atividade) {
        this.atividade = atividade;
        if (atividade != null) {
            setEntidadeAlvoNome(AtividadeCRM.class.getSimpleName());
            setEntidadeAlvoCodigo(atividade.getId().toString());
        }
    }

}
