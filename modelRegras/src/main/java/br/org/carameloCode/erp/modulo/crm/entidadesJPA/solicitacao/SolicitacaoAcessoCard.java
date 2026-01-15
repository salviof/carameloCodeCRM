/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoPreparacaoObjeto;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;

/**
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = {"Solicitação de acesso"}, plural = "Solicitações de acesso")
public class SolicitacaoAcessoCard extends Solicitacao {

    public SolicitacaoAcessoCard() {
        setTipoSolicitacao(FabTipoSolicitacao.SOLICITACAO_ACESSO.getRegistro());
    }

    @Override
    @InfoPreparacaoObjeto(classesPrConstructorPrincipal = Pessoa.class)
    public void prepararNovoObjeto(Object... parametros) throws ErroPreparandoObjeto {
        if (!SBCore.getServicoSessao().getSessaoAtual().isIdentificado()) {
            throw new ErroPreparandoObjeto(this, "o usuário logado não está indentificado");
        }
        if (SBCore.getUsuarioLogado() instanceof UsuarioCrmCliente) {
            throw new ErroPreparandoObjeto(this, "Somente usuários da empresa podem realizar essa operação");
        }
        setUsuarioSolicitante((UsuarioCRM) SBCore.getUsuarioLogado());
        setPessoa(getParametroInicialEnviado(Pessoa.class, parametros));
        if (getPessoa() == null) {
            throw new ErroPreparandoObjeto(this, "A pessoa é obrigatória para solicitar acesso");
        }
        if (getPessoa().getCPinst(CPPessoa.usuarioatendimento).getValor() != null) {
            setUsuarioSolicitado(getPessoa().getUsuarioAtendimento());
        } else if (getPessoa().getCPinst(CPPessoa.usuarioresponsavel).getValor() != null) {
            setUsuarioSolicitado(getPessoa().getUsuarioResponsavel());
        } else {
            if (getPessoa().getUsuarioCriou() != null) {
                setUsuarioSolicitado(getPessoa().getUsuarioCriou());
            } else {
                if (getPessoa().getUsuariosRespDisponiveis().isEmpty()) {
                    EntityManager em = UtilSBPersistencia.getNovoEMIniciandoTransacao();
                    try {

                        Pessoa pessoa = UtilSBPersistencia.loadEntidade(getPessoa(), em);
                        pessoa.setUmPerfilPrivado(false);
                        pessoa.setUmPerfilPublico(true);
                        UtilSBPersistencia.mergeRegistro(pessoa, em);

                    } finally {
                        UtilSBPersistencia.finzalizaTransacaoEFechaEM(em);
                    }
                    throw new ErroPreparandoObjeto(this, "Nenhum responsável foi encontrado, o status de privado foi alterado para publico");
                }
            }
        }

    }

}
