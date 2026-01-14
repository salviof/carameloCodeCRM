package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCListasObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariosb.CPUsuarioSB;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.USUARIOSRESPDISPONIVEIS)
public class ValorLogicoPessoaUsuariosRespDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaUsuariosRespDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private boolean valorDefinido;

    @Override
    public Object getValor(Object... pEntidade) {

        if (!valorDefinido) {
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(UsuarioCRM.class, em);
            consulta.addCondicaoPositivo(CPUsuarioSB.ativo);
            consulta.addcondicaoCampoIgualA(CPUsuarioSB.tipousuario, UsuarioCRM.class.getSimpleName());
            setValorPorReflexao(consulta.resultadoRegistros());
            UtilCRCListasObjeto.ordernarPorCampo(getPessoa().getUsuariosRespDisponiveis(), CPUsuarioSB.nome);
            UtilSBPersistencia.fecharEM(em);
            valorDefinido = true;
        }
        return getPessoa().getUsuariosRespDisponiveis();
    }

    public Pessoa getPessoa() {

        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }
}
