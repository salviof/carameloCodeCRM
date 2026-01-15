package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipochamado;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.TipoChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCListas;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.tipochamado.ValorLogicoTipoChamado;
import br.org.carameloCode.erp.modulo.crm.api.model.tipochamado.ValoresLogicosTipoChamado;

@ValorLogicoTipoChamado(calculo = ValoresLogicosTipoChamado.RESPONSAVEISDISPONIVEIS)
public class ValorLogicoTipoChamadoResponsaveisDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoTipoChamadoResponsaveisDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (UtilCRCListas.isNuloOuVazio(getTipoChamado().getResponsaveis())) {
            ConsultaDinamicaDeEntidade novaconsulta = new ConsultaDinamicaDeEntidade(UsuarioCRM.class, UtilSBPersistencia.getEMDoContexto());
            novaconsulta.addcondicaoCampoIgualA("tipoUsuario", UsuarioCRM.class.getSimpleName());
            List<UsuarioCRM> usuarios = novaconsulta.resultadoRegistros();
            getTipoChamado().setResponsaveis(usuarios);
        }

        return getTipoChamado().getResponsaveis();
    }

    public TipoChamado getTipoChamado() {
        return (TipoChamado) getCampoInst().getObjetoDoAtributo();
    }
}
