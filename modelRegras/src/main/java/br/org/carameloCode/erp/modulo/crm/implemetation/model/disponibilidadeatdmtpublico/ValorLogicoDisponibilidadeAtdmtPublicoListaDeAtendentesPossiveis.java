package br.org.carameloCode.erp.modulo.crm.implemetation.model.disponibilidadeatdmtpublico;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuarioCliente.UsuarioCrmCliente;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.disponibilidadeatdmtpublico.ValorLogicoDisponibilidadeAtdmtPublico;
import br.org.carameloCode.erp.modulo.crm.api.model.disponibilidadeatdmtpublico.ValoresLogicosDisponibilidadeAtdmtPublico;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.DisponibilidadeAtdmtPublico;

@ValorLogicoDisponibilidadeAtdmtPublico(calculo = ValoresLogicosDisponibilidadeAtdmtPublico.LISTADEATENDENTESPOSSIVEIS)
public class ValorLogicoDisponibilidadeAtdmtPublicoListaDeAtendentesPossiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDisponibilidadeAtdmtPublicoListaDeAtendentesPossiveis(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getDisponibilidade().getListaDeAtendentesPossiveis() == null || getDisponibilidade().getListaDeAtendentesPossiveis().isEmpty()) {
            EntityManager em = UtilSBPersistencia.getEMDoContexto();
            List<UsuarioCRM> atendentesDisponiveis = UtilSBPersistencia.getListaTodos(UsuarioCRM.class, em);
            List<UsuarioCrmCliente> usuariosClientes = UtilSBPersistencia.getListaTodos(UsuarioCrmCliente.class, em);
            atendentesDisponiveis.removeAll(usuariosClientes);
            getDisponibilidade().setListaDeAtendentesPossiveis((List) atendentesDisponiveis);
        }

        return getDisponibilidade().getListaDeAtendentesPossiveis();
    }

    public DisponibilidadeAtdmtPublico getDisponibilidade() {
        return (DisponibilidadeAtdmtPublico) getCampoInst().getObjetoDoAtributo();
    }
}
