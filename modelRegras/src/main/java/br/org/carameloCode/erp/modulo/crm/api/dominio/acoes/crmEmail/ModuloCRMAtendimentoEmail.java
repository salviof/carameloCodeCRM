/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmEmail;

import br.org.carameloCode.erp.modulo.crm.api.ERPCrm;
import br.org.coletivojava.fw.utils.agendador.UtilSBAgendadorTarefas;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.EmailCrm;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.LogEmailRecebidoLido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.caixaPostal.CaixaPostalEstatisticas;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.categoriaMailRecebido.FabCategoriaEmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.emailRecebido.EmailRecebido;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.FabStatusEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.FabTipoLogFalhaEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.LogFalhaEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.span.EnderecoEmailPrivado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.span.EnderecoEmailSpan;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.InfoAcaoCRMAtendimento;
import com.super_bits.marketing.Util.UtilCRCEmailAvancado;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

/**
 *
 * @author sfurbino
 */
public class ModuloCRMAtendimentoEmail extends ControllerAbstratoSBPersistencia {

    public static final String ASSUNTO_PADRAO = "ASSUNTO NÃO DEFINIDO !!";

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EMAILS_CTR_REMOVER_EMAIL)
    public static ItfRespostaAcaoDoSistema emailRemover(EmailCrm pEmail) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pEmail), pEmail) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pEmail.getId() == null) {
                    throw new ErroRegraDeNegocio("Este e-mail ainda não foi salvo como rascunho");
                }
                if (pEmail instanceof EnvioEmail) {
                    if (pEmail.getUsuarioCriou().equals(SBCore.getUsuarioLogado())) {
                        removerEntidade(pEmail);
                        setProximoFormulario(FabAcaoCRMAtendimento.EMAILS_FRM_NOVO.getRegistro().getComoFormulario());
                    } else {
                        throw new ErroRegraDeNegocio("Sem permissão para remover e-mail de terceiros");
                    }
                }
            }
        }.getResposta();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EMAILS_CTR_RELATAR_LEITURA)
    public static ItfRespostaAcaoDoSistema registrarLeituraEmailRecebido(EmailRecebido mail) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaResposta(EmailRecebido.class), mail) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (mail == null) {
                    throw new ErroRegraDeNegocio("Erro registrando leitura");
                }

                LogEmailRecebidoLido log = new LogEmailRecebidoLido();
                log.setDataHora(new Date());
                log.setUsuario((UsuarioCRM) loadEntidade(SBCore.getUsuarioLogado()));
                log.setEmail(mail);
                log = atualizarEntidade(log);
                mail.getLogsLeituraMailRecebido().add(log);
                atualizarEntidade(log);
                EmailRecebido emailAtualizado = atualizarEntidade(mail);
                emailAtualizado.setFoiIgnorado(false);
                CaixaPostalEstatisticas estatisticasa = (CaixaPostalEstatisticas) emailAtualizado.getCaixaPostal().getCampoInstanciadoByNomeOuAnotacao("estatisticas").getValor();
                if (estatisticasa != null) {
                    atualizarEntidade(estatisticasa);
                }

            }
        }.getResposta();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EMAILS_CTR_REMOVER_EMAIL_ENVIADO)
    public static ItfRespostaAcaoDoSistema removerEmailEnviado(EmailCrm pEmail) {

        return emailRemover(pEmail);

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EMAILS_CTR_ENVIAR_SALVANDO_RASCUNHO)
    public static ItfRespostaAcaoDoSistema emailSalvarRascunhoEnviar(EnvioEmail pEmail) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pEmail), pEmail) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                ItfRespostaAcaoDoSistema resposta = salvarrascunho(pEmail);
                if (!resposta.isSucesso()) {
                    throw new ErroRegraDeNegocio(resposta.getMensagens().get(0).getMenssagem());
                }
                EnvioEmail email = loadEntidade((EnvioEmail) resposta.getRetorno());
                if (email.getTextoMensagem() == null || email.getTextoMensagem().isEmpty()) {
                    throw new ErroRegraDeNegocio("Falta definir o conteúdo do e-mail");
                }
                if (email.getAssunto() == null || email.getAssunto().isEmpty()) {
                    throw new ErroRegraDeNegocio("Falta definir o assunto do e-mail");
                }

                ItfRespostaAcaoDoSistema respostaEnvio = email_enviar((EnvioEmail) resposta.getRetorno());
                if (!respostaEnvio.isSucesso()) {
                    throw new ErroRegraDeNegocio(respostaEnvio.getMensagens().get(0).getMenssagem());
                }
                setProximoFormulario(FabAcaoCRMAtendimento.EMAILS_FRM_LISTAR_ENVIADOS.getRegistro().getComoFormulario());

            }
        }.getResposta();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EMAILS_CTR_ENVIAR_AGORA)
    public static ItfRespostaAcaoDoSistema email_enviar(EnvioEmail pEmail) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pEmail), pEmail) {
            @Override
            public void executarAcoesFinais() throws ErroEmBancoDeDados {
                super.executarAcoesFinais(); //To change body of generated methods, choose Tools | Templates.
                if (!isSucesso()) {
                    EntityManager emmanagerStatusEmailFotmatado = UtilSBPersistencia.getNovoEMIniciandoTransacao();
                    EnvioEmail mail = UtilSBPersistencia.loadEntidade(pEmail, emmanagerStatusEmailFotmatado);
                    mail.setStatusEnvio(FabStatusEnvioEmail.FORMATADO.getRegistro());
                    mail.setIconeAlerta("fa fa-exclamation-triangle");

                    UtilSBPersistencia.finalizarTransacao(emmanagerStatusEmailFotmatado);
                }
            }

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                EnvioEmail email = loadEntidade(pEmail);

                if (!(email.getStatusEnvio().equals(FabStatusEnvioEmail.FORMATADO.getRegistro())
                        || email.getStatusEnvio().equals(FabStatusEnvioEmail.RASCUNHO.getRegistro()))) {
                    throw new ErroRegraDeNegocio("O status do e-mail não é compatível com o envio");
                }
                if (email.getAssunto().equals(ASSUNTO_PADRAO)) {
                    throw new ErroRegraDeNegocio("O assunto não foi definido");
                }

                //  UtilCRCEmailAvancado.enviarEmailComAnexo(pContatos, ASSUNTO_PADRAO, ASSUNTO_PADRAO, pAnexos);
                try {

                    boolean enviado = ERPCrm.CARAMELO_CODE_EXTENCAO.getImplementacaoDoContexto().enviarEMailAplicandoModeloAssinatura(SBCore.getUsuarioLogado(), email, (List) email.getArquivos());

                    if (!enviado) {
                        throw new ErroRegraDeNegocio("Falha enviando E-mail, será colocado na caixa de saída e reenviado a cada 1 minuto");
                    } else {
                        email.setStatusEnvio(FabStatusEnvioEmail.ENVIADO.getRegistro());
                        email.setDatadisparo(new Date());
                        email.getComoEnvioEmail().setFoiEnviado(true);
                        UtilSBPersistencia.mergeRegistro(email, getEMResposta());
                    }
                } catch (Throwable t) {
                    LogFalhaEnvioEmail falhaEnvio = new LogFalhaEnvioEmail();
                    falhaEnvio.setEmail(email);
                    falhaEnvio.setTipoLog(FabTipoLogFalhaEnvioEmail.SERVIDOR_NEGOU);
                    UtilSBPersistencia.mergeRegistro(falhaEnvio);
                    throw new ErroRegraDeNegocio("Falha Enviando email");
                }
            }
        }.getResposta();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EMAILS_CTR_AGENDAR_ENVIO)
    public static ItfRespostaAcaoDoSistema agemdarDisparo(EnvioEmail pEmail) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pEmail), pEmail) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                EnvioEmail email;
                if (pEmail.getId() == null) {
                    email = (EnvioEmail) salvarrascunho(pEmail).getRetorno();
                } else {
                    email = pEmail;
                }
                Date dataAgendamento = pEmail.getDataHoraAgendamentoDisparo();
                email = loadEntidade(email);
                if (pEmail instanceof EnvioEmail) {
                    if (pEmail.getAssunto().equals(ModuloCRMAtendimentoEmail.ASSUNTO_PADRAO)) {
                        throw new ErroRegraDeNegocio("O assunto não foi definido");
                    }
                    if (dataAgendamento == null) {
                        throw new ErroRegraDeNegocio("Defina o horário da programação");
                    }
                    if (dataAgendamento.getTime() <= new Date().getTime()) {
                        throw new ErroRegraDeNegocio("Programe para uma data futura");
                    } else {
                        email.setDataHoraAgendamentoDisparo(dataAgendamento);
                        UtilSBAgendadorTarefas.agendarTarefa(FabAcaoCRMAtendimento.EMAILS_CTR_ENVIAR_AGORA, pEmail.getDataHoraAgendamentoDisparo(), email);
                        email.setStatusEnvio(FabStatusEnvioEmail.FORMATADO.getRegistro());
                    }

                } else {
                    throw new UnsupportedOperationException("Para agendar um encaminhamento, primeiro clique em encaminhar");
                }
            }
        }.getResposta();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EMAILS_CTR_SALVAR_RASCUNHO)
    public static ItfRespostaAcaoDoSistema salvarrascunho(EnvioEmail pEmail) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pEmail), pEmail) {

            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                if (pEmail instanceof EnvioEmail) {

                    if (pEmail.getStatusEnvio() == null) {
                        pEmail.setStatusEnvio(FabStatusEnvioEmail.RASCUNHO.getRegistro());
                    }
                    if (UtilCRCStringValidador.isNuloOuEmbranco(pEmail.getAssunto())) {
                        SBCore.enviarMensagemUsuario("Defina o Assunto do e-mail", FabMensagens.ALERTA);
                        pEmail.setAssunto(ASSUNTO_PADRAO);
                    }

                    setRetorno(atualizarEntidade(pEmail, false));

                } else {
                    throw new ErroRegraDeNegocio("Este tipo de  e-mail não pode ser salvo como rascunho");
                }
            }
        }.getResposta();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EMAILS_CTR_RELATAR_SPAN)
    public static ItfRespostaAcaoDoSistema emailDeclararSpan(EmailRecebido pEmail) {

        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pEmail), pEmail) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {

                EmailRecebido emailRecebidoAtualizado = loadEntidade(pEmail);
                List<ContatoProspecto> contatoRemetente = UtilCRCEmailAvancado.getContatosFromTextoPadraoProtocoloEmail(pEmail.getRemetente());
                if (contatoRemetente.isEmpty() || contatoRemetente.size() > 1) {
                    throw new ErroRegraDeNegocio("O remetente do e-mail não foi encontrado");
                }
                if (emailRecebidoAtualizado.getRemetente().contains("MAILER-DAEMON@amazonses.com")) {
                    throw new ErroRegraDeNegocio("Este e-mail faz parte do controle de entrega, não pode ser definido como span");
                }
                ContatoProspecto contato = UtilCRCEmailAvancado.getContatosFromTextoPadraoProtocoloEmail(emailRecebidoAtualizado.getRemetente()).get(0);
                ContatoProspecto contatoEncontrado = (ContatoProspecto) UtilSBPersistencia.getRegistroByJPQL("from ContatoProspecto where email='" + contato.getEmail() + "'", getEm());
                if (contatoEncontrado != null) {
                    throw new ErroRegraDeNegocio("Este e-mail é o contato de uma pessoa, não pode ser marcado como span");
                }
                EnderecoEmailSpan emailSpan = new EnderecoEmailSpan();
                emailSpan.setEmailRemetente(contatoRemetente.get(0).getEmail());
                emailSpan.setAssuntoDefinidoSpan(pEmail.getAssunto());
                emailSpan.setCodigoEmailDefinidoSpan(pEmail.getCodigoEmailServidor());
                emailSpan.setUltimoAssunto(pEmail.getAssunto());
                emailSpan.setUsuarioDefiniuSpan((UsuarioCRM) SBCore.getUsuarioLogado());
                emailSpan.setDataHoraCadastroSpan(new Date());
                EnderecoEmailSpan emailSpanAtualizado = atualizarEntidade(emailSpan);
                String remetente = emailRecebidoAtualizado.getRemetente();
                List<EmailRecebido> lista = UtilSBPersistencia.getListaRegistrosByHQL("from EmailRecebido where  remetente='" + remetente + "' and tipoEmail='" + EmailRecebido.class.getSimpleName() + "'" + " and caixaPostal_id=" + emailRecebidoAtualizado.getCaixaPostal().getId(), -1, getEm());
                int idx = 0;
                StringBuilder condicaoEmailRecebido = new StringBuilder();
                lista.stream().forEach(mail -> removerEntidade(mail));
                for (EmailRecebido mail : lista) {
                    if (idx == 0) {
                        condicaoEmailRecebido.append("(").append(mail.getId());
                    } else {
                        condicaoEmailRecebido.append(",");
                        condicaoEmailRecebido.append(mail.getId());
                    }
                    idx++;
                }
                condicaoEmailRecebido.append(")");
                //boolean apagarLogVisualizacao = UtilSBPersistencia.executaSQL(getEMResposta(), "delete from  LogEmailRecebidoLido where email_id in '" + condicaoEmailRecebido.toString() + "'");
                //boolean apagarEmail = UtilSBPersistencia.executaSQL(getEMResposta(), "delete from  EmailCrm where remetente='" + remetente + "'");

            }
        }.getResposta();

    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EMAILS_CTR_ATRIBUIR_PESSSOA)
    public static ItfRespostaAcaoDoSistema emailAtribuirPessoa(final EmailRecebido pEmail) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pEmail), pEmail) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                System.out.println("ok");
                EmailRecebido emailRecebidoAtualizado = loadEntidade(pEmail);
                if (pEmail.getProspecto() == null) {
                    throw new ErroRegraDeNegocio("A pessoa responsável não foi selecionada");
                }
                Pessoa pessoaVinculada = loadEntidade(pEmail.getProspecto());

                List<ContatoProspecto> contatoRemetente = UtilCRCEmailAvancado.getContatosFromTextoPadraoProtocoloEmail(emailRecebidoAtualizado.getRemetente());
                if (contatoRemetente.isEmpty()) {
                    throw new ErroRegraDeNegocio("O contato do destinatário não foi encontrado");
                }
                String emailRemetente = contatoRemetente.get(0).getEmail();
                String nomeRemetente = contatoRemetente.get(0).getNome();
                Optional<ContatoProspecto> contatoEmailEncontrado = pessoaVinculada.getContatosProspecto().stream().filter(ct -> ct.getEmail() != null && ct.getEmail().equals(emailRemetente)).findFirst();
                if (!contatoEmailEncontrado.isPresent()) {
                    Optional<ContatoProspecto> contatoNomeEncontrado = pessoaVinculada.getContatosProspecto().stream()
                            .filter(ct -> ct.getEmail() == null && ct.getNome().equals(nomeRemetente)).findFirst();
                    if (contatoNomeEncontrado.isPresent()) {
                        ContatoProspecto contatoAtualizar = contatoNomeEncontrado.get();
                        contatoAtualizar.setEmail(emailRemetente);
                        atualizarEntidade(contatoAtualizar);
                    } else {
                        contatoRemetente.get(0).setProspecto(pessoaVinculada);
                        atualizarEntidade(contatoRemetente.get(0));
                    }
                }

                boolean atualizado = UtilSBPersistencia.executaSQL(getEMResposta(), "update  EmailCrm set prospecto_id = " + pessoaVinculada.getId() + ",categoriaEmailRecebido_id = " + FabCategoriaEmailRecebido.EMAIL_PROSPECTO.getRegistro().getId() + "   where remetente ='" + emailRecebidoAtualizado.getRemetente() + "'");

                if (!atualizado) {
                    throw new ErroRegraDeNegocio("Falha vinculando e-mail a pessoa");
                }

            }
        }.getResposta();
    }

    @InfoAcaoCRMAtendimento(acao = FabAcaoCRMAtendimento.EMAILS_CTR_ATRIBUIR_PRIVADO)
    public static ItfRespostaAcaoDoSistema emailatribuirPrivado(EmailRecebido pEmail) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pEmail), pEmail) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                EmailRecebido emailRecebidoAtualizado = loadEntidade(pEmail);
                if (pEmail.getProspecto() != null) {
                    throw new ErroRegraDeNegocio("O e-mail já foi vinculado a uma pessoa");
                }

                List<ContatoProspecto> contatoRemetente = UtilCRCEmailAvancado.getContatosFromTextoPadraoProtocoloEmail(pEmail.getRemetente());
                if (contatoRemetente.isEmpty()) {
                    throw new ErroRegraDeNegocio("O contato do destinatário não foi encontrado");
                }
                String emailRemetente = contatoRemetente.get(0).getEmail();
                EnderecoEmailPrivado emailPrivado = new EnderecoEmailPrivado();
                emailPrivado.setEmail(emailRemetente);
                emailPrivado.setCaixaPostal(emailRecebidoAtualizado.getCaixaPostal());
                emailPrivado.setUsuarioRelato((UsuarioCRM) SBCore.getUsuarioLogado());
                atualizarEntidade(emailPrivado);
                boolean atualizado = UtilSBPersistencia.executaSQL(getEMResposta(), "update  EmailCrm set categoriaEmailRecebido_id=" + FabCategoriaEmailRecebido.EMAIL_PRIVADO.getRegistro().getId() + "   where remetente ='" + pEmail.getRemetente() + "' and caixaPostal_id=" + emailRecebidoAtualizado.getCaixaPostal().getId());

                if (!atualizado) {
                    throw new ErroRegraDeNegocio("Falha atribuindo contato privado");
                }

            }
        }.getResposta();
    }

}
