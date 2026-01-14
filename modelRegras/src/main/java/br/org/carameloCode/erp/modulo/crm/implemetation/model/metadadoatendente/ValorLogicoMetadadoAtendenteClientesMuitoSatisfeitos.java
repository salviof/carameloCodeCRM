package br.org.carameloCode.erp.modulo.crm.implemetation.model.metadadoatendente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.chamado.MetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.cliente.satisfacao.FabSatisfacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadoatendente.ValorLogicoMetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadoatendente.ValoresLogicosMetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;

@ValorLogicoMetadadoAtendente(calculo = ValoresLogicosMetadadoAtendente.CLIENTESMUITOSATISFEITOS)
public class ValorLogicoMetadadoAtendenteClientesMuitoSatisfeitos
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoMetadadoAtendenteClientesMuitoSatisfeitos(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getMetadado().getUsuario() != null) {
            if (!isCacheAtivado()) {
                EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(Pessoa.class, em);
                consulta.addCondicaoManyToOneIgualA(FabSatisfacaoCliente.MUITOSATISFEITO.getRegistro());
                consulta.addCondicaoManyToManyContendoObjeto(CPPessoa.usuarioresponsavel, getMetadado().getUsuario());
                long quantidade = consulta.resultadoSomarQuantidade();
                getMetadado().setClientesInsatisfeitos(quantidade);
                ativarCache(30);
            }
        }

        return getMetadado().getClientesInsatisfeitos();

    }

    public MetadadoAtendente getMetadado() {
        return (MetadadoAtendente) getCampoInst().getObjetoDoAtributo();
    }
}
