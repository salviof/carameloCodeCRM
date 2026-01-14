package br.org.carameloCode.erp.modulo.crm.implemetation.model.metadadoatendente;

import com.google.common.collect.Lists;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.FabStatusChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.MetadadoAtendente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.CPChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadoatendente.ValorLogicoMetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadoatendente.ValoresLogicosMetadadoAtendente;

@ValorLogicoMetadadoAtendente(calculo = ValoresLogicosMetadadoAtendente.CHAMADOSEMATENDIMENTO)
public class ValorLogicoMetadadoAtendenteChamadosEmAtendimento
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoMetadadoAtendenteChamadosEmAtendimento(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getMetadado().getUsuario() != null) {
            if (!isCacheAtivado()) {
                EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ChamadoCliente.class, em);
                consulta.addCondicaoManyToOneContemNoIntervalo(CPChamadoCliente.status,
                        Lists.newArrayList(FabStatusChamado.EM_ATENDIMENTO.getRegistro(),
                                FabStatusChamado.ATRAZADO.getRegistro()
                        ));
                consulta.addCondicaoManyToOneIgualA(CPChamadoCliente.atendenteresponsavel, getMetadado().getUsuario());

                getMetadado().setChamadosEmAtendimento(consulta.resultadoSomarQuantidade());
                UtilSBPersistencia.fecharEM(em);
                ativarCache(30);
            }
        }

        return getMetadado().getChamadosEmAtendimento();

    }

    public MetadadoAtendente getMetadado() {
        return (MetadadoAtendente) getCampoInst().getObjetoDoAtributo();
    }

}
