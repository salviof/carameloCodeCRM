/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmChamado;

import br.org.coletivoJava.fw.api.erp.chat.ERPChat;
import br.org.coletivoJava.fw.api.erp.chat.ErroConexaoServicoChat;
import br.org.carameloCode.erp.modulo.crm.api.FabConfigErpCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.FabStatusChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.InfoAcaoCRMCliente;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.CPChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;

/**
 *
 * @author salvio
 */
public class ModuloCRMChamado extends ControllerAbstratoSBPersistencia {

    @InfoAcaoCRMCliente(acao = FabAcaoCRMCliente.CHAMADO_CTR_NOTIFICAR_CHAMADOS_ABERTOS_AUTO_EXEC)
    public static synchronized ItfRespostaAcaoDoSistema chamadosNotificarChamadoAberto() {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(new ChamadoCliente()), new ChamadoCliente()) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ConsultaDinamicaDeEntidade novaConsutla = new ConsultaDinamicaDeEntidade(ChamadoCliente.class, getEm());
                novaConsutla.addCondicaoManyToOneIgualA(CPChamadoCliente.status, FabStatusChamado.AGUARDANDO_ATENDIMENTO.getRegistro());

                List<ChamadoCliente> chamados = novaConsutla.gerarResultados();

                for (ChamadoCliente chamado : chamados) {
                    UsuarioCRM atendimento = (UsuarioCRM) chamado.getPessoa().getCPinst(CPPessoa.usuarioatendimento).getValor();

                    if (atendimento == null) {
                        atendimento = (UsuarioCRM) chamado.getPessoa().getCPinst(CPPessoa.usuarioresponsavel).getValor();
                    }
                    if (chamado.getPessoa().getUsuariosResponsaveis().size() == 1) {
                        atendimento = chamado.getPessoa().getUsuarioResponsavel();
                    }

                    if (atendimento == null) {
                        String emailAdmin = FabConfigErpCRM.EMAIL_USUARIO_AMDIN.getValorParametroSistema();
                        try {
                            atendimento = (UsuarioCRM) SBCore.getServicoPermissao().getUsuarioByEmail(emailAdmin);
                        } catch (Throwable t) {

                        }
                    }
                    if (atendimento != null) {
                        String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.MEUS_CHAMADOS_FRM_CHAMADOS_DEFINIR_ATENDIMENTO, chamado);
                        url = url.replace("https://atendimento.", "htpps://crm.");
                        if (atendimento != null) {
                            try {
                                ERPChat.MATRIX_ORG.getImplementacaoDoContexto().enviarDirect(atendimento.getCodigoMatrix(),
                                        chamado.getUsuarioCliente().getNome() + "O Chamado cod " + chamado.getId() + "criado para" + chamado.getPessoa().getNome() + " AGUARDA ATENDIMENTO, acesse via: " + url);
                            } catch (ErroConexaoServicoChat ex) {
                                Logger.getLogger(ModuloCRMChamado.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        }.getResposta();

    }

}
