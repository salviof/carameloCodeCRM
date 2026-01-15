package br.org.carameloCode.erp.modulo.crm.implemetation.model.metadadousuariocliente;

import com.google.common.collect.Lists;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.ChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.chamado.FabStatusChamado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.MetadadoUsuarioCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.chamadocliente.CPChamadoCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadousuariocliente.ValorLogicoMetadadoUsuarioCliente;
import br.org.carameloCode.erp.modulo.crm.api.model.metadadousuariocliente.ValoresLogicosMetadadoUsuarioCliente;

@ValorLogicoMetadadoUsuarioCliente(calculo = ValoresLogicosMetadadoUsuarioCliente.CHAMADOSABERTOS)
public class ValorLogicoMetadadoUsuarioClienteChamadosAbertos
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoMetadadoUsuarioClienteChamadosAbertos(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getMetadadosClienteUsuario().getUsuario() != null) {
            if (!isCacheAtivado()) {

                EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ChamadoCliente.class, em);
                consulta.addCondicaoManyToOneIgualA(CPChamadoCliente.usuariocliente, getMetadadosClienteUsuario().getUsuario());
                consulta.addCondicaoManyToOneContemNoIntervalo(CPChamadoCliente.status,
                        Lists.newArrayList(FabStatusChamado.EM_ATENDIMENTO.getRegistro(),
                                FabStatusChamado.ATRAZADO.getRegistro(),
                                FabStatusChamado.AGUARDANDO_ATENDIMENTO.getRegistro()
                        ));
                Long chamadosAbertos = consulta.resultadoSomarQuantidade();
                getMetadadosClienteUsuario().setChamadosAbertos(chamadosAbertos.intValue());
                ativarCache(30);
            }

        }
        return getMetadadosClienteUsuario().getChamadosAbertos();
    }

    public MetadadoUsuarioCliente getMetadadosClienteUsuario() {

        return (MetadadoUsuarioCliente) getCampoInst().getObjetoDoAtributo();

    }
}
