package org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.reservalinkpublico.etapas;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.contato.ContatoAnonimoDadoTansitorio;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAgendaPublica.ModuloCRMAgendaPublica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRMLead;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.contatoanonimodadotansitorio.CPContatoAnonimoDadoTansitorio;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import org.coletivoJava.fw.projetos.carameloCodeCRM.api.cucumber.reservalinkpublico.EtapasReservaLinkPublico;
import cucumber.api.java.pt.Quando;
import org.coletivoJava.fw.projetos.carameloCodeCRM.implementacao.cucumber.reservalinkpublico.FluxoAgendaPublicaReserva;
import org.junit.Assert;

public class B_Quando_a_pessoa_preenche_nome_e_email_nao_encontrados_no_sistema {

    @Quando(EtapasReservaLinkPublico.QUANDO_A_PESSOA_PREENCHE_NOME_E_EMAIL_NAO_ENCONTRADOS_NO_SISTEMA)
    public void implementacaoEtapa() {
        UsuarioCRMLead usuarioLead = new UsuarioCRMLead("Joao da silva", "319884587");
        CarameloCode.getServicoSessao().getSessaoAtual().setUsuario(usuarioLead);
        ItfRespostaAcaoDoSistema respValidacao = ModuloCRMAgendaPublica.validadarDadosCanidatoAgendaPublica(usuarioLead);
        Assert.assertTrue("Falha na validação de dados da agenda pulica", respValidacao.isSucesso());
        FluxoAgendaPublicaReserva.dadosContato = new ContatoAnonimoDadoTansitorio();

        try {
            FluxoAgendaPublicaReserva.dadosContato.getCPinst(CPContatoAnonimoDadoTansitorio.nomeusuario).setValorSeValido(FluxoAgendaPublicaReserva.NOME_RESERVA);
            FluxoAgendaPublicaReserva.dadosContato.getCPinst(CPContatoAnonimoDadoTansitorio.celular).setValorSeValido(FluxoAgendaPublicaReserva.TELEFONE_RESERVA);
            FluxoAgendaPublicaReserva.dadosContato.getCPinst(CPContatoAnonimoDadoTansitorio.nomeempresa).setValorSeValido("Empresa");
            FluxoAgendaPublicaReserva.dadosContato.getCPinst(CPContatoAnonimoDadoTansitorio.site).setValorSeValido("google.com");
            FluxoAgendaPublicaReserva.dadosContato.getCPinst(CPContatoAnonimoDadoTansitorio.email).setValorSeValido(FluxoAgendaPublicaReserva.EMAIL_RESERVA);
        } catch (ErroValidacao ex) {
            Assert.fail("Falha definindo dados do contato" + ex.getMessage());
        }

    }
}
