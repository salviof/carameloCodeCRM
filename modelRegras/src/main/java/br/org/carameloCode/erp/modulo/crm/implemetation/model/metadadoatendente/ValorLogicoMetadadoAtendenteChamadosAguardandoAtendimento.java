package br.org.carameloCode.erp.modulo.crm.implemetation.model.metadadoatendente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.FabStatusChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.MetadadoAtendente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.CPChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadoatendente.ValorLogicoMetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadoatendente.ValoresLogicosMetadadoAtendente;

@ValorLogicoMetadadoAtendente(calculo = ValoresLogicosMetadadoAtendente.CHAMADOSAGUARDANDOATENDIMENTO)
public class ValorLogicoMetadadoAtendenteChamadosAguardandoAtendimento
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoMetadadoAtendenteChamadosAguardandoAtendimento(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getMetadado().getUsuario() != null) {
            if (!isCacheAtivado()) {
                EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ChamadoCliente.class, em);
                consulta.addCondicaoManyToOneIgualA(FabStatusChamado.AGUARDANDO_ATENDIMENTO.getRegistro());

                List<ChamadoCliente> todosChamadosAbertos = consulta.resultadoRegistros();
                List<ChamadoCliente> chamadosDoUsuario = new ArrayList<>();

                todosChamadosAbertos.stream()
                        .filter(ch -> ((List) ch.getCampoInstanciadoByNomeOuAnotacao(CPChamadoCliente.usuariodisponiveis).getValor()).contains(getMetadado().getUsuario()))
                        .forEach(chamadosDoUsuario::add);
                getMetadado().setChamadosAbertos(chamadosDoUsuario.size());
                UtilSBPersistencia.fecharEM(em);
                ativarCache(30);
            }
        }

        return getMetadado().getChamadosAbertos();

    }

    public MetadadoAtendente getMetadado() {
        return (MetadadoAtendente) getCampoInst().getObjetoDoAtributo();
    }
}
