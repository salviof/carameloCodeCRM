/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento;

import br.org.carameloCode.erp.crm.paginas.moduloWeb.ModuloCRMAcoesWeb;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.sms.MensagemSMS;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.wtzpModeloMKT.MensagemMktWhatsapp;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgenda.FabAcaoCrmAtendimentoAgenda;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;

import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.ExecucaoComGestaoERegraDeNegocioPadrao;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabMensagens;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.webSite.InfoWebApp;
import com.super_bits.modulosSB.webPaginas.util.UtilSBWP_JSFTools;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringTelefone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.acoes.ComoAcaoDoSistema;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;

/**
 *
 * @author SalvioF
 */
@Named
@ViewScoped
public class ServicoCRMContato implements Serializable {

    private ContatoProspecto novoContato;

    private ComoAcaoDoSistema acaoSelcionada;

    @Inject
    private InfoWebApp infoWeb;
    @Inject
    private ItfPaginaAtual paginaAtual;

    private ContatoProspecto contatoSelecionado;
    private List<ComoAcaoDoSistema> acoesDisponiveis;

    public void irParaAcaoSelecionada() {
        if (acaoSelcionada == null) {
            return;
        }
        if (FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_ABRIR_CHAT_WHATSAPP.equals(acaoSelcionada.getEnumAcaoDoSistema())) {
            ModuloCRMAcoesWeb.contatoAbrirWhatsapp(contatoSelecionado);
        } else if (FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_LIGAR_PELO_CELULAR.equals(acaoSelcionada.getEnumAcaoDoSistema())) {
            ModuloCRMAcoesWeb.contatoLigarCelular(contatoSelecionado);
        } else if (FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_LIGAR_PABX_ONLINE.equals(acaoSelcionada.getEnumAcaoDoSistema())) {
            ModuloCRMAcoesWeb.contatoLigarPABX(contatoSelecionado);
        } else if (FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_CONVIDAR_PARA_CHAT.equals(acaoSelcionada.getEnumAcaoDoSistema())) {
            ModuloCRMAcoesWeb.contatoConvidarParaCHat(contatoSelecionado).dispararMensagens();

        } else {
            String url = infoWeb.getUrlDaAcao(acaoSelcionada, contatoSelecionado);
            UtilSBWP_JSFTools.vaParaPagina(url);
        }
        acaoSelcionada = null;
    }

    @PostConstruct
    public void inicio() {
        novoContato = new ContatoProspecto();
        if (paginaAtual.getInfoPagina().isUmaPaginaGestaoEntidade() && paginaAtual.getInfoPagina().getComoPaginaEntidade().getEntidadeSelecionada() instanceof Pessoa) {
            Pessoa pessoa = (Pessoa) paginaAtual.getInfoPagina().getComoPaginaEntidade().getEntidadeSelecionada();
            contatoSelecionado = (ContatoProspecto) pessoa.getCPinst(CPPessoa.contatoprincipal).getValor();
        }
    }

    public void cadastroEmailRapido(PessoaJuridica p) {

        new ExecucaoComGestaoERegraDeNegocioPadrao() {
            @Override
            public void regraDeNegocio() {
                PessoaJuridica prospAtualizado = UtilSBPersistencia.loadEntidade(p, getEm());
                if (prospAtualizado.adicionarContato(novoContato)) {
                    try {
                        atualizarEntidade(novoContato);
                    } catch (ErroEmBancoDeDados ex) {
                        SBCore.enviarMensagemUsuario(ex.getMensagemUsuario(), FabMensagens.ERRO);
                        SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro adicionando novo contato", ex);
                    }
                }
            }

        };
        novoContato = new ContatoProspecto();
    }

    public ContatoProspecto getNovoContato() {
        return novoContato;
    }

    public List<ComoAcaoDoSistema> getAcoesDisponiveis() {
        acoesDisponiveis = new ArrayList();
        if (contatoSelecionado != null) {
            acoesDisponiveis.add(FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_CONVIDAR_PARA_CHAT.getRegistro());
            if (contatoSelecionado.isPossuiEmail()) {
                //acoesDisponiveis.add(FabAcaoCRMAtendimento.EMAILS_FRM_NOVO.getRegistro());
                acoesDisponiveis.add(FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_FRM_CONVITE_RESERVA.getRegistro());

                acoesDisponiveis.add(FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_FRM_CONVITE_PRIMEIRO_ACESSO.getRegistro());

            }
            if (contatoSelecionado.isPossuiTelefone()) {
                acoesDisponiveis.add(FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_FRM_ENVIAR_MKT_WTZAP.getRegistro());
                acoesDisponiveis.add(FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_ABRIR_CHAT_WHATSAPP.getRegistro());
                acoesDisponiveis.add(FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_FRM_SMS_FORMATAR.getRegistro());
                acoesDisponiveis.add(FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_LIGAR_PELO_CELULAR.getRegistro());
                acoesDisponiveis.add(FabAcaoCrmAtendimentoAgenda.MEUS_CONTATOS_CTR_LIGAR_PABX_ONLINE.getRegistro());
            }
        }
        return acoesDisponiveis;
    }

    public ComoAcaoDoSistema getAcaoSelcionada() {
        return acaoSelcionada;
    }

    public void setAcaoSelcionada(ComoAcaoDoSistema acaoSelcionada) {
        this.acaoSelcionada = acaoSelcionada;
    }

    public ContatoProspecto getContatoSelecionado() {
        return contatoSelecionado;
    }

    public void setContatoSelecionado(ContatoProspecto contatoSelecionado) {
        this.contatoSelecionado = contatoSelecionado;
        getAcoesDisponiveis();
    }

    public void selecionarNovoUsuario(ValueChangeEvent event) {
        System.out.println("New value: " + event.getNewValue());
    }

    public void enviarMensagemSMS(MensagemSMS pMensagem) {
        ModuloCRMAtendimento.contatoProspectoEnviarSMS(pMensagem).dispararMensagens();
    }

    public void enviarMensagemWtzap(MensagemMktWhatsapp pMensagem) {
        ModuloCRMAtendimento.contatoProspectoEnviarWhatzapMtk(pMensagem).dispararMensagens();
    }

    public void enviarMensagemWtzapParaTodos(MensagemMktWhatsapp pMensagem) {
        for (ContatoProspecto contato : pMensagem.getPessoa().getContatosProspecto()) {
            String telefone = UtilCRCStringTelefone.gerarCeluarWhatasapp(contato.getCelular());
            if (telefone != null) {
                MensagemMktWhatsapp mensagemWhatsapp = new MensagemMktWhatsapp();
                try {
                    mensagemWhatsapp.prepararNovoObjeto(contato);
                    ModuloCRMAtendimento.contatoProspectoEnviarWhatzapMtk(mensagemWhatsapp).dispararMensagens();
                } catch (ErroPreparandoObjeto ex) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Falha criando nova mensagem de Whatsapp", ex);
                    mensagemWhatsapp = null;
                }

            }

        }

    }

    public void definirUsuarioPrincipal(Pessoa pPessoa, ContatoProspecto pContato) {
        if (!pPessoa.getContatosProspecto().contains(pContato)) {
            SBCore.enviarAvisoAoUsuario("Contato não encontrado em " + pPessoa.getNome());
            return;
        }
        //pPessoa.setContatoPrincipal(pContato);
        UtilSBPersistencia.executaSQL("update ProspectoEmpresa set contatoPrincipal_id=" + pContato.getId() + " where id =" + pPessoa.getId());
    }

}
