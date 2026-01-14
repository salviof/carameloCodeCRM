package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValorLogicoPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValoresLogicosPessoa;

@ValorLogicoPessoa(calculo = ValoresLogicosPessoa.USUARIORESPONSAVEL)
public class ValorLogicoPessoaUsuarioResponsavel
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoPessoaUsuarioResponsavel(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (!isCacheAtivado()) {
            EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
            try {
                if (getPessoa().getUsuarioResponsavel() == null) {

                    UsuarioCRM usuarioCriou = (UsuarioCRM) getPessoa().getUsuarioInsersao();
                    if (usuarioCriou != null) {
                        if (!getPessoa().getUsuariosResponsaveis().isEmpty()) {
                            if (getPessoa().getUsuariosResponsaveis().contains(usuarioCriou)) {
                                setValorPorReflexao(usuarioCriou);
                            }
                        } else {
                            setValorPorReflexao(usuarioCriou);
                        }

                    }
                    if (getPessoa().getUsuarioResponsavel() == null) {
                        if (!getPessoa().getUsuariosResponsaveis().isEmpty()) {
                            setValorPorReflexao(getPessoa().getUsuariosResponsaveis().get(0));
                        }
                    }
                    if (getPessoa().getUsuarioResponsavel() == null) {
                        if (getPessoa().getUsuarioAtendimento() != null) {
                            setValorPorReflexao(getPessoa().getUsuarioAtendimento());
                        }
                    }
                }

            } catch (Throwable t) {

            } finally {
                setCacheSegundosPadrao(10);
            }

        }

        return getPessoa()
                .getUsuarioResponsavel();
    }

    public Pessoa getPessoa() {
        return (Pessoa) getCampoInst().getObjetoDoAtributo();
    }
}
