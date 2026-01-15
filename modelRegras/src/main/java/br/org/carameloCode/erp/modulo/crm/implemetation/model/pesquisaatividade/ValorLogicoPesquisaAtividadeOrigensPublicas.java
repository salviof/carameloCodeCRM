package br.org.carameloCode.erp.modulo.crm.implemetation.model.pesquisaatividade;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.pesquisaAtividade.PesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.origemprospecto.CPOrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValorLogicoPesquisaAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.pesquisaatividade.ValoresLogicosPesquisaAtividade;

@ValorLogicoPesquisaAtividade(calculo = ValoresLogicosPesquisaAtividade.ORIGENSPUBLICAS)
public class ValorLogicoPesquisaAtividadeOrigensPublicas
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPesquisaAtividadeOrigensPublicas(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    boolean origensDefinidas = false;

    @Override
    public Object getValor(Object... pEntidade) {

        if (!origensDefinidas) {
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            UsuarioCRM usuario = (UsuarioCRM) SBCore.getUsuarioLogado();
            List<OrigemProspecto> todasOrigens = new ConsultaDinamicaDeEntidade(OrigemProspecto.class, em)
                    .addcondicaoCampoIgualA(CPOrigemProspecto.tipoorigem, OrigemProspecto.class.getSimpleName())
                    .resultadoRegistros();

            List<OrigemProspecto> origens = new ArrayList<>();

            todasOrigens.stream().filter(or -> or.isUmaOrigempublica()
                    || or.getUsuariosComAcesso().contains(usuario))
                    .forEach(origens::add);
            getPesquisaLead().setOrigensPublicas(origens);
            origensDefinidas = true;
        }

        return getPesquisaLead().getOrigensPublicas();
    }

    public PesquisaAtividade getPesquisaLead() {
        return (PesquisaAtividade) getCampoInst().getObjetoDoAtributo();
    }
}
