package br.org.carameloCode.erp.modulo.crm.implemetation.model.metadadoatendente;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.MetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.cliente.satisfacao.FabSatisfacaoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadoatendente.ValorLogicoMetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadoatendente.ValoresLogicosMetadadoAtendente;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;

@ValorLogicoMetadadoAtendente(calculo = ValoresLogicosMetadadoAtendente.CLIENTESSATISFEITOS)
public class ValorLogicoMetadadoAtendenteClientesSatisfeitos
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoMetadadoAtendenteClientesSatisfeitos(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getMetadado().getUsuario() != null) {
            if (!isCacheAtivado()) {
                EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(Pessoa.class, em);
                consulta.addCondicaoManyToOneIgualA(FabSatisfacaoCliente.SATISFEITO.getRegistro());
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
