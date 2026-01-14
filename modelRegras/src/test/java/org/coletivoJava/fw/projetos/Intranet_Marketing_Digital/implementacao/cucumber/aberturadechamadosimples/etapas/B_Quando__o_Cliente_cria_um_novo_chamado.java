package org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.etapas;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.ModuloCRMCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.TipoChamado;
import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.model.dados.demo.FAbDadosIniciais.FabTipoChamado;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import cucumber.api.java.pt.Quando;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.api.cucumber.aberturadechamadosimples.EtapasAberturaDeChamadoSimples;
import org.coletivoJava.fw.projetos.Intranet_Marketing_Digital.implementacao.cucumber.aberturadechamadosimples.FluxoChamadoSimples;
import org.coletivojava.fw.api.tratamentoErros.ErroPreparandoObjeto;
import org.junit.Assert;

public class B_Quando__o_Cliente_cria_um_novo_chamado {

    @Quando(EtapasAberturaDeChamadoSimples.QUANDO_O_CLIENTE_CRIA_UM_NOVO_CHAMADO)
    public void implementacaoEtapa() {
        ChamadoCliente novoChamado = new ChamadoCliente();
        try {
            novoChamado.prepararNovoObjeto(SBCore.getUsuarioLogado());
        } catch (ErroPreparandoObjeto ex) {
            Logger.getLogger(B_Quando__o_Cliente_cria_um_novo_chamado.class.getName()).log(Level.SEVERE, null, ex);
        }
        novoChamado.setDescricao("EStou com problemas para exportação de site");
        novoChamado.setTipoChamado((TipoChamado) FabTipoChamado.SUPORTE_TECNICO.getRegistro());

        ItfResposta resp = ModuloCRMCliente.chamadoAbrirChamado(novoChamado);

        Assert.assertTrue("Falha criando chamado", resp.isSucesso());
        FluxoChamadoSimples.chamado = (ChamadoCliente) resp.getRetorno();
        Assert.assertNotNull("Chamado simples não foi definido", FluxoChamadoSimples.chamado);
    }
}
