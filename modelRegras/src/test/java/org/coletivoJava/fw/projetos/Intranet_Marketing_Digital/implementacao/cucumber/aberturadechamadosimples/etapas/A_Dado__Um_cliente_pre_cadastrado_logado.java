package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.etapas;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.ModuloCRMCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.porteEmpresa.FabPorteProspectoEmpresa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.demo.FAbDadosIniciais.FabDadosIniciais;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.demo.FAbDadosIniciais.FabTipoEmpresa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import cucumber.api.java.pt.Dado;
import java.util.List;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.aberturadechamadosimples.EtapasAberturaDeChamadoSimples;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.CPContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.FluxoChamadoSimples;
import org.junit.Assert;

public class A_Dado__Um_cliente_pre_cadastrado_logado {

    @Dado(EtapasAberturaDeChamadoSimples.DADO_UM_CLIENTE_PRE_CADASTRADO_LOGADO)
    public void implementacaoEtapa() {

        List<UsuarioCRM> usuarios = UtilSBPersistencia.getListaTodos(UsuarioCRM.class, FluxoChamadoSimples.getEM());
        for (UsuarioCRM usr : usuarios) {
            System.out.println(usr.getEmail());
        }
        SBCore.getServicoSessao().logarEmailESenha("atendimento@casanovadigital.com.br", "123");
        PessoaJuridica prospecto = (PessoaJuridica) FabDadosIniciais.PROSPECTO1.getRegistro();

        UtilSBPersistencia.mergeRegistro(prospecto);

        prospecto.setTipoEmpresa(FabTipoEmpresa.COMERCIO.getRegistro());
        prospecto.setPorte(FabPorteProspectoEmpresa.GRANDE.getRegistro());
        ContatoProspecto ct = (ContatoProspecto) prospecto.getCPinst(CPPessoa.contatoprincipal).getValor();
        ct.setCelular("31984178550");
        Assert.assertTrue("Falha autenticando usuário atendimento", SBCore.getServicoSessao().getSessaoAtual().isIdentificado());
        ItfRespostaAcaoDoSistema respostaCriacaoProspecto = ModuloCRMAtendimento.prospectoSalvar(prospecto);
        Assert.assertTrue("Falha salvando Lead", respostaCriacaoProspecto.isSucesso());
        FluxoChamadoSimples.renovarConexaoEntityManagerEscopoTeste();
        prospecto = UtilSBPersistencia.loadEntidade(prospecto, FluxoChamadoSimples.getEM());
        ContatoProspecto contato = prospecto.getContatoPrincipal();

        UsuarioCrmCliente usuarioDoCliente = (UsuarioCrmCliente) contato.getCPinst(CPContatoProspecto.usuariovinculado).getValorComoEntidadeSimples();
        usuarioDoCliente.setSenha("1234");
        ItfRespostaAcaoDoSistema resp = ModuloCRMCliente.cadastrarNovaSenhaUsuario(usuarioDoCliente);
        Assert.assertTrue("FAlha atualizando senha do usuário", resp.isSucesso());
        System.out.println("Logando com " + usuarioDoCliente.getEmail());

        SBCore.getServicoSessao().logarEmailESenha(usuarioDoCliente.getEmail(), "1234");
        Assert.assertTrue("Login usuário do cliente inválido", SBCore.getServicoSessao().getSessaoAtual().isIdentificado());
        UsuarioCRM usuarioCliente = (UsuarioCRM) SBCore.getUsuarioLogado();
        Assert.assertTrue("Usuário logado não é do tipo cliente", usuarioCliente instanceof UsuarioCrmCliente);

    }
}
