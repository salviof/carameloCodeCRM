package br.org.carameloCode.erp.modulo.crm.implemetation.model.tiporelacionamento;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.tiporelacionamento.ValorLogicoTipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.api.model.tiporelacionamento.ValoresLogicosTipoRelacionamento;

@ValorLogicoTipoRelacionamento(calculo = ValoresLogicosTipoRelacionamento.PROXIMOSRESPONSAVEIS)
public class ValorLogicoTipoRelacionamentoProximosResponsaveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoTipoRelacionamentoProximosResponsaveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        List<UsuarioCRM> usuarios = new ArrayList();
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            TipoRelacionamento relacionamento = UtilSBPersistencia.loadEntidade(getTipoRElacionamento(), em);
            if (relacionamento.getUsuariosResponsaveis().isEmpty()) {
                return null;
            }
            Long idRelacionamento = relacionamento.getId();
            final Map<BigInteger, UsuarioCRM> usuario_ultimosAtendimentos = new HashMap();
            if (!relacionamento.isAdicionarTodosResponsaveis()) {
                relacionamento.getUsuariosResponsaveis().forEach(usr -> {
                    long idUsuario = usr.getId();
                    BigInteger quantida = (BigInteger) UtilSBPersistencia.getRegistroBySQL("select count(id) from ProspectoEmpresa p\n"
                            + "inner join prospectos_usuarios_responsaveis pr on p.id=pr.prospecto_id\n"
                            + "where relacionamento_id=" + idRelacionamento + " and pr.usuarioCRM_id=" + idUsuario,
                            em);
                    usuario_ultimosAtendimentos.put(quantida, usr);

                });

                BigInteger minimo = usuario_ultimosAtendimentos.keySet().stream().min(Comparator.comparing(BigInteger::intValue)).get();
                usuarios.add(usuario_ultimosAtendimentos.get(minimo));
                getTipoRElacionamento().setProximosResponsaveis(usuarios);

            }
        } catch (Throwable t) {
            UtilSBPersistencia.fecharEM(em);
        }
        return getTipoRElacionamento().getProximosResponsaveis();

    }

    public TipoRelacionamento getTipoRElacionamento() {
        return (TipoRelacionamento) getCampoInst().getObjetoDoAtributo();
    }

}
