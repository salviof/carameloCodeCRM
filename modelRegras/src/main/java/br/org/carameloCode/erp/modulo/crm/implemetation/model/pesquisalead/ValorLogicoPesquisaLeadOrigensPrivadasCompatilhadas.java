package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisalead;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.origemPrivada.OrigemProspectoPrivado;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.pesquisaLead.PesquisaLead;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.origemprospecto.CPOrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValorLogicoPesquisaLead;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisalead.ValoresLogicosPesquisaLead;

@ValorLogicoPesquisaLead(calculo = ValoresLogicosPesquisaLead.ORIGENSPRIVADASCOMPATILHADAS)
public class ValorLogicoPesquisaLeadOrigensPrivadasCompatilhadas
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaLeadOrigensPrivadasCompatilhadas(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    private UsuarioCRM ultimoHorarioDefinido = null;

    @Override
    public Object getValor(Object... pEntidade) {

        if (ultimoHorarioDefinido == null || !ultimoHorarioDefinido.equals(getPesquisaLead().getUsuario())) {
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            UsuarioCRM usuario = (UsuarioCRM) getPesquisaLead().getUsuario();
            List<OrigemProspectoPrivado> todasOrigens = new ConsultaDinamicaDeEntidade(OrigemProspectoPrivado.class, em)
                    .addcondicaoCampoIgualA(CPOrigemProspecto.tipoorigem, OrigemProspectoPrivado.class.getSimpleName())
                    .resultadoRegistros();

            List<OrigemProspectoPrivado> origens = new ArrayList<>();

            todasOrigens.stream().filter(or
                    -> !or.getUsuarioDono().equals(usuario)
                    && or.getUsuariosComAcesso().contains(usuario))
                    .forEach(origens::add);
            getPesquisaLead().setOrigensPrivadasCompatilhadas(origens);
            ultimoHorarioDefinido = getPesquisaLead().getUsuario();
        }
        return getPesquisaLead().getOrigensPrivadasCompatilhadas();
    }

    public PesquisaLead getPesquisaLead() {
        return (PesquisaLead) getCampoInst().getObjetoDoAtributo();
    }
}
