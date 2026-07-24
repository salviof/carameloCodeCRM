package br.org.carameloCode.erp.modulo.crm.implemetation.model.chamadocliente;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValorLogicoChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.ValoresLogicosChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.FabDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

@ValorLogicoChamadoCliente(calculo = ValoresLogicosChamadoCliente.DADOSDOCHAMADO)
public class ValorLogicoChamadoClienteDadosDoChamado
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoChamadoClienteDadosDoChamado(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            if (getChamadoCliente().getPessoa() == null || getChamadoCliente().getUsuarioCliente() == null || getChamadoCliente().getTipoChamado() == null) {
                return getChamadoCliente().getDadosDoChamado();
            }
            if (getChamadoCliente().getTipoChamado().getTipoDados().isEmpty()) {
                getChamadoCliente().getDadosDoChamado().clear();
            } else {
                if (getChamadoCliente().getDadosDoChamado().isEmpty()) {
                    final List<TipoDadoCRM> dadosDotipo = getChamadoCliente().getTipoChamado().getTipoDados();
                    final List<DadoCRM> dadosEncontrados = getChamadoCliente().getDadosDoChamado();
                    List<DadoCRM> dadoRemover = new ArrayList<>();
                    List<TipoDadoCRM> tipoDadoAdicionar = new ArrayList<>();
                    getChamadoCliente().getDadosDoChamado().stream().filter(dado -> !dadosDotipo.contains(dado.getTipoDadoCRM())).forEach(dadoRemover::add);
                    dadosDotipo.stream()
                            .filter(tp -> !dadosEncontrados.stream().map(dado -> dado.getTipoDadoCRM()).filter(tpdadoEncontrado -> !tpdadoEncontrado.equals(tp)).findFirst().isPresent())
                            .forEach(tipoDadoAdicionar::add);
                    for (TipoDadoCRM tp : tipoDadoAdicionar) {
                        Pessoa p = UtilSBPersistencia.loadEntidade(getChamadoCliente().getPessoa(), em);
                        DadoCRM dado = FabDadoCRM.getDadoNovoSeNaoExistir(p, tp);
                        getChamadoCliente().getDadosDoChamado().add(dado);
                    }
                }
            }
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }

        return getChamadoCliente().getDadosDoChamado();
    }

    public ChamadoCliente getChamadoCliente() {
        return (ChamadoCliente) getCampoInst().getObjetoRaizDoAtributo();
    }
}
