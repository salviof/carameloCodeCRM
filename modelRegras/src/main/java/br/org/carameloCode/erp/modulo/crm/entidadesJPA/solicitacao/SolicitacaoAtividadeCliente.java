package br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao;

/**
 *
 * @author salvio
 */
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@InfoObjetoSB(tags = "Solicitações de cliente", plural = "Solicitações de atividade")
public class SolicitacaoAtividadeCliente extends Solicitacao {

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Contato", obrigatorio = true, caminhoParaLista = "pessoa.contatosProspecto")
    @ManyToOne(targetEntity = ContatoProspecto.class)
    private ContatoProspecto contatoPessoa;

    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    @ManyToOne(targetEntity = TipoAtividadeCRM.class)
    private TipoAtividadeCRM tipoAtividade;

    public SolicitacaoAtividadeCliente() {
        setTipoSolicitacao(FabTipoSolicitacao.SOLICITACAO_ATIVIDADE_CLIENTE.getRegistro());
    }

    public ContatoProspecto getContatoPessoa() {
        return contatoPessoa;
    }

    public void setContatoPessoa(ContatoProspecto contatoPessoa) {
        this.contatoPessoa = contatoPessoa;
    }

    public TipoAtividadeCRM getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(TipoAtividadeCRM tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

}
