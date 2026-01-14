package br.org.carameloCode.erp.modulo.crm.implemetation.model.chamadocliente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.FabStatusChamado;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmCliente.FabAcaoCRMCliente;
import com.super_bits.modulos.SBAcessosModel.view.FabAcaoPaginasDoSistema;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.Date;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValorLogicoChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValoresLogicosChamadoCliente;

@ValorLogicoChamadoCliente(calculo = ValoresLogicosChamadoCliente.LINKURLACESSOCLIENTE)
public class ValorLogicoChamadoClienteLinkUrlAcessoCliente
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoChamadoClienteLinkUrlAcessoCliente(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public Long getDiasToken() {
        if (getChamadoCliente().getDataHoraUltimoLinkAcesso() == null) {
            return null;
        }
        return UtilCRCDataHora.intervaloTempoDias(getChamadoCliente().getDataHoraUltimoLinkAcesso(), new Date());
    }

    @Override
    public Object getValor(Object... pEntidade) {

        Long dias = getDiasToken();
        if (dias == null || dias > 1) {
            if (getChamadoCliente().getStatus() != null) {
                if (getChamadoCliente().getStatus().equals(FabStatusChamado.RASCUNHO.getRegistro())) {
                    return getChamadoCliente().getLinkUrlAcessoCliente();
                }
            }
            ItfTokenAcessoDinamico token = SBCore.getServicoPermissao().gerarTokenDinamico(FabAcaoCRMCliente.CHAMADO_FRM_VISUALIZAR, getChamadoCliente(), getChamadoCliente().getUsuarioCliente().getEmail());
            String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoPaginasDoSistema.PAGINA_NATIVA_TOKEN_DINAMICO_MB, token);
            url = url.replace("https://crm.", "https://atendimento.");
            getChamadoCliente().setLinkUrlAcessoCliente(url);
            getChamadoCliente().setDataHoraUltimoLinkAcesso(new Date());
        }

        return getChamadoCliente().getLinkUrlAcessoCliente();
    }

    public ChamadoCliente getChamadoCliente() {
        return (ChamadoCliente) getCampoInst().getObjetoRaizDoAtributo();
    }

}
