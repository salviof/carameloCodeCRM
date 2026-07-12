package br.org.carameloCode.erp.modulo.crm.implemetation.model.metadadoatendente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadoatendente.ValorLogicoMetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadoatendente.ValoresLogicosMetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.estatisticas.MetadadoAtendente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import javax.persistence.EntityManager;

@ValorLogicoMetadadoAtendente(calculo = ValoresLogicosMetadadoAtendente.SOLICITACOESFEITASPORMIMPARACLIENTES)
public class ValorLogicoMetadadoAtendenteSolicitacoesFeitasPorMimParaClientes
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoMetadadoAtendenteSolicitacoesFeitasPorMimParaClientes(
            ItfCampoInstanciado pCampo) {

        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            String jpql = "SELECT COUNT(s) FROM Solicitacao s "
                    + "WHERE s.usuarioSolicitante = :usuarioLogado "
                    + "AND s.foiFinalizada = false "
                    + "AND s.tipoEntitySoliciatacao IN ('SolicitacaoArquivoCliente', 'SolicitacaoArquivoCliente')";

            Long quantidade = em.createQuery(jpql, Long.class)
                    .setParameter("usuarioLogado", CarameloCode.getUsuarioLogado())
                    .getSingleResult();
            getMetadadoAtendente().setSolicitacoesFeitasPorMimParaClientes(quantidade.intValue());
        } finally {

        }
        return getMetadadoAtendente().getSolicitacoesFeitasPorMimParaClientes();
    }

    public MetadadoAtendente getMetadadoAtendente() {
        return (MetadadoAtendente) getCampoInst().getObjetoRaizDoAtributo();
    }
}
