/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.TipoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Solicitação de chamado", plural = "Solicitações de chamados")
public class SolicitacaoChamado extends Solicitacao {

    @ManyToOne(targetEntity = ChamadoCliente.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA)
    private ChamadoCliente chamado;

    @ManyToOne(targetEntity = TipoChamado.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, label = "Tipo de chamado")
    private TipoChamado tipoChamado;

    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = {Pessoa.class})
    @Override
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        setTipoSolicitacao(FabTipoSolicitacao.SOLICITACAO_CHAMADO.getRegistro());
        if (isTemParametroDesteTipo(ChamadoCliente.class, parametros)) {
            chamado = getParametroInicialEnviado(ChamadoCliente.class, parametros);
            setPessoa(chamado.getPessoa());
            setUsuarioSolicitado(chamado.getUsuarioCliente());
            setTipoChamado(chamado.getTipoChamado());
        } else {
            setPessoa(getParametroInicialEnviado(Pessoa.class, parametros));
            setUsuarioSolicitante((UsuarioCRM) SBCore.getUsuarioLogado());
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            setTipoSolicitacao(FabTipoSolicitacao.SOLICITACAO_CHAMADO.getRegistro());
        }
    }

    public SolicitacaoChamado() {
        setTipoSolicitacao(FabTipoSolicitacao.SOLICITACAO_CHAMADO.getRegistro());
    }

    public ChamadoCliente getChamado() {
        return chamado;
    }

    public void setChamado(ChamadoCliente chamado) {
        this.chamado = chamado;
    }

    public TipoChamado getTipoChamado() {
        return tipoChamado;
    }

    public void setTipoChamado(TipoChamado tipoChamado) {
        this.tipoChamado = tipoChamado;
    }

}
