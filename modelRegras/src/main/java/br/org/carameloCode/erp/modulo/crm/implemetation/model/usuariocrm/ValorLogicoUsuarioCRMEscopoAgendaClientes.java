package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuariocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.ValorLogicoUsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.ValoresLogicosUsuarioCRM;
import org.coletivoJava.fw.projetos.agendamentoPublico.model.escopoPesquisa.EscopoPesquisaMelhorHorario;

@ValorLogicoUsuarioCRM(calculo = ValoresLogicosUsuarioCRM.ESCOPOAGENDACLIENTES)
public class ValorLogicoUsuarioCRMEscopoAgendaClientes
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoUsuarioCRMEscopoAgendaClientes(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getUsuario().getEscopoAgendaClientes() == null) {
            getUsuario().setEscopoAgendaClientes(new EscopoPesquisaMelhorHorario());
            getUsuario().getEscopoAgendaClientes().setAtendentes(new ArrayList<>());
            getUsuario().getEscopoAgendaClientes().getAtendentes().add((UsuarioSB) getUsuario());
            getUsuario().getEscopoAgendaClientes().setHorarioinicio(new Date(new Time(05, 00, 00).getTime()));
            getUsuario().getEscopoAgendaClientes().setHorarioFinal(new Date(new Time(18, 00, 00).getTime()));

        }
        return getUsuario().getEscopoAgendaClientes();
    }

    public UsuarioCRM getUsuario() {
        return (UsuarioCRM) getCampoInst().getObjetoDoAtributo();
    }
}
