package br.org.carameloCode.erp.modulo.crm.implemetation.model.chamadocliente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValorLogicoChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValoresLogicosChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.tipochamado.CPTipoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.TipoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.grupo.FabGruposCRMCaramelo;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCListas;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;

@ValorLogicoChamadoCliente(calculo = ValoresLogicosChamadoCliente.TIPOSDISPONIVEIS)
public class ValorLogicoChamadoClienteTiposDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoChamadoClienteTiposDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (UtilCRCListas.isNuloOuVazio(getChamadoCliente().getTiposDisponiveis())) {

            ConsultaDinamicaDeEntidade novaconsulta = new ConsultaDinamicaDeEntidade(TipoChamado.class, UtilSBPersistencia.getEMDoContexto());
            if (SBCore.getUsuarioLogado().getGrupo().getId().equals(FabGruposCRMCaramelo.CRM_CLIENTE.getRegistro().getId())) {
                novaconsulta.addCondicaoPositivo(CPTipoChamado.podeclientecriar);
            }
            List<TipoChamado> tiposDisponiveis = novaconsulta.resultadoRegistros();
            getChamadoCliente().setTiposDisponiveis(tiposDisponiveis);
        }

        return getChamadoCliente().getTiposDisponiveis();
    }

    public ChamadoCliente getChamadoCliente() {
        return (ChamadoCliente) getCampoInst().getObjetoRaizDoAtributo();
    }
}
