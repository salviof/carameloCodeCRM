package br.org.carameloCode.erp.modulo.crm.implemetation.model.metadadoatendente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadoatendente.ValorLogicoMetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadoatendente.ValoresLogicosMetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.api.model.solicitacao.CPSolicitacao;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.estatisticas.MetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.solicitacao.Solicitacao;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import javax.persistence.EntityManager;

@ValorLogicoMetadadoAtendente(calculo = ValoresLogicosMetadadoAtendente.SOLICITACOESFEITASPARAMIM)
public class ValorLogicoMetadadoAtendenteSolicitacoesFeitasParaMim
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoMetadadoAtendenteSolicitacoesFeitasParaMim(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
        ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(Solicitacao.class, em);
        consulta.addCondicaoManyToOneIgualA(CPSolicitacao.usuariosolicitado, getMetadadoAtendente().getUsuario());
        consulta.addCondicaoNegativo(CPSolicitacao.foifinalizada);
        int valor = consulta.gerarResultados().size();
        getMetadadoAtendente().setSolicitacoesFeitasParaMim(valor);
        return getMetadadoAtendente().getSolicitacoesFeitasParaMim();
    }

    public MetadadoAtendente getMetadadoAtendente() {
        return (MetadadoAtendente) getCampoInst().getObjetoRaizDoAtributo();
    }
}
