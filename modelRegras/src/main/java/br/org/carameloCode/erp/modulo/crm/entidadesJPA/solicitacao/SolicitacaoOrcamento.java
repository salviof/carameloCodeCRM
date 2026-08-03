/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao;

import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.orcamento.Orcamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(tags = "Solicitação de orçamento", plural = "Solicitações de orçamento")
@Entity
public class SolicitacaoOrcamento extends Solicitacao {

    @ManyToOne(targetEntity = Orcamento.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, caminhoParaLista = "pessoa.orcamentos")
    private Orcamento orcamento;

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = {Pessoa.class, UsuarioCRM.class})
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {

        try {
            setPessoa(getParametroInicialEnviado(Pessoa.class, parametros));
        } catch (Throwable t) {

        }
        try {
            UsuarioCRM userPr = getParametroInicialEnviado(UsuarioCRM.class, parametros);
            setUsuarioSolicitado(userPr);
        } catch (Throwable t) {

        }
        setUsuarioSolicitante((UsuarioCRM) SBCore.getUsuarioLogado());
        try {

            Orcamento orcamento = getParametroInicialEnviado(Orcamento.class, parametros);
            setOrcamento(orcamento);
            if (getPessoa() == null) {
                if (((Orcamento) (getPessoa().getCPinst(CPPessoa.ultimoorcamento).getValor())).getId() != null) {
                    setOrcamento(getPessoa().getUltimoOrcamento());
                }
            }
        } catch (Throwable t) {
            if (getPessoa() != null) {
                if (((Orcamento) (getPessoa().getCPinst(CPPessoa.ultimoorcamento).getValor())).getId() != null) {
                    setOrcamento(getPessoa().getUltimoOrcamento());
                }
            }
        }

    }

    public SolicitacaoOrcamento() {
        setTipoSolicitacao(FabTipoSolicitacao.SOLICITACAO_ORCAMENTO.getRegistro());
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

}
