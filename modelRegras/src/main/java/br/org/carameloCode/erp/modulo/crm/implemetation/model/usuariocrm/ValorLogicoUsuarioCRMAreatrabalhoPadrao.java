package br.org.carameloCode.erp.modulo.crm.implemetation.model.usuariocrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.areaTrabalho.AreaTrabalho;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.ValorLogicoUsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariocrm.ValoresLogicosUsuarioCRM;

@ValorLogicoUsuarioCRM(calculo = ValoresLogicosUsuarioCRM.AREATRABALHOPADRAO)
public class ValorLogicoUsuarioCRMAreatrabalhoPadrao
        extends
        ValorLogicoCalculoGenerico {

    private static Map<UsuarioCRM, AreaTrabalho> areastrabalho = new HashMap<>();

    public ValorLogicoUsuarioCRMAreatrabalhoPadrao(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private static synchronized Map<UsuarioCRM, AreaTrabalho> getAreasDeTrabalho() {
        return areastrabalho;
    }

    @Override
    public synchronized Object getValor(Object... pEntidade) {
        try {
            if (getAreasDeTrabalho().containsKey(getUsuario())) {
                return getAreasDeTrabalho().get(getUsuario());
            } else {
                if (getUsuario().getAreatrabalhoPadrao() == null) {
                    getUsuario().setAreatrabalhoPadrao(new AreaTrabalho());
                    getUsuario().getAreatrabalhoPadrao().setUsuario(getUsuario());

                    return getUsuario().getAreatrabalhoPadrao();
                }
                new AtualizaAreaDeTrabalho(getUsuario()).start();

            }

            return getUsuario().getAreatrabalhoPadrao();
        } finally {
            new AtualizaAreaDeTrabalho(getUsuario()).start();
        }
    }

    public UsuarioCRM getUsuario() {

        return (UsuarioCRM) getCampoInst().getObjetoDoAtributo();
    }

    private static class AtualizaAreaDeTrabalho extends Thread {

        private final UsuarioCRM usuariotreadAtuzalizacao;

        public AtualizaAreaDeTrabalho(UsuarioCRM pUsuario) {
            usuariotreadAtuzalizacao = pUsuario;
        }

        public UsuarioCRM getUsuariotreadAtuzalizacao() {
            return usuariotreadAtuzalizacao;
        }

        private static synchronized void atualizar(UsuarioCRM pUsuario) {
            if (getAreasDeTrabalho().containsKey(pUsuario)) {
                Date dataultimaAtualizacao = getAreasDeTrabalho().get(pUsuario).getDataHoraAtualizacao();
                if (dataultimaAtualizacao == null || UtilCRCDataHora.intervaloTempoSegundos(dataultimaAtualizacao, new Date()) < 10) {
                    return;
                }

            }

            AreaTrabalho areaAtualizada = new AreaTrabalho();
            areaAtualizada.setDataHoraAtualizacao(new Date());
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            try {
                UsuarioCRM usuario = UtilSBPersistencia.loadEntidade(pUsuario, em);

                usuario.getAreatrabalhoPadrao().getCamposInstanciados().forEach(cp -> cp.getValor());
                areaAtualizada.copiaDados(usuario.getAreatrabalhoPadrao());
            } catch (Throwable t) {

            }
            UtilSBPersistencia.fecharEM(em);
            getAreasDeTrabalho().put(pUsuario, areaAtualizada);
        }

        @Override
        public void run() {
            atualizar(usuariotreadAtuzalizacao);
        }

    }

}
