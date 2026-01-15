/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.estatisticas;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.TipoRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.relacionamento.etapaFunil.MetaRelacionamento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.tagAtendimento.TagAtendimento;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

/**
 *
 * @author salviofurbino
 * @since 07/09/2019
 * @version 1.0
 */
public class EstatisticasDoUsuario {

    private final UsuarioCRM usuario;
    private List<TipoRelacionamento> relacionamentos = new ArrayList<>();

    private List<MetaRelacionamento> metas = new ArrayList<>();

    private List<TagAtendimento> tags = new ArrayList<>();

    private Map<TipoRelacionamento, BigInteger> quantidadesPorRelacionamento = new HashMap();
    private Map<MetaRelacionamento, BigInteger> quantidadePorMeta = new HashMap();
    private Map<TagAtendimento, BigInteger> quantidadePorTag = new HashMap();

    public EstatisticasDoUsuario(UsuarioCRM pUsuario) {
        EntityManager em = SBCore.getServicoRepositorio().getAcessoDadosDoContexto().getEntitiManager();
        usuario = UtilSBPersistencia.loadEntidade(pUsuario, em);
        Long idUsuario = usuario.getId();
        List<MetaRelacionamento> todasMetas = UtilSBPersistencia.getListaTodos(MetaRelacionamento.class, SBCore.getServicoRepositorio().getAcessoDadosDoContexto().getEntitiManager());
        todasMetas.stream().forEach(mt -> {
            BigInteger quantidadeMeta = (BigInteger) UtilSBPersistencia.getRegistroBySQL("select count(p.id) from ProspectoEmpresa p \n"
                    + "right join prospectos_usuarios_responsaveis pr on p.id=pr.prospecto_id \n"
                    + "right join TipoRelacionamento tr on tr.id = p.relacionamento_id\n"
                    + "right join MetaRelacionamento mt on mt.id = tr.metaRelacionamento_id\n"
                    + "where pr.usuarioCRM_id=" + idUsuario + "  and mt.id=" + mt.getId(),
                    SBCore.getServicoRepositorio().getAcessoDadosDoContexto().getEntitiManager());
            if (quantidadeMeta.intValue() > 0) {
                metas.add(mt);
                quantidadePorMeta.put(mt, quantidadeMeta);
            }
        });
        List<TipoRelacionamento> todoRelacionamento = UtilSBPersistencia.getListaTodos(TipoRelacionamento.class, SBCore.getServicoRepositorio().getAcessoDadosDoContexto().getEntitiManager());

        usuario.getTagsMonitoradas().forEach(tag -> {
            BigInteger quantidadeTag = (BigInteger) UtilSBPersistencia.getRegistroBySQL("select count(p.id) from ProspectoEmpresa p \n"
                    + "right join prospectos_usuarios_responsaveis pr on p.id=pr.prospecto_id \n"
                    + "right join tagAtendimento_prospecto tg on p.id=tg.prospecto_id\n"
                    + "where pr.usuarioCRM_id=" + usuario.getId() + "  and tg.tag_id=" + tag.getId(),
                    SBCore.getServicoRepositorio().getAcessoDadosDoContexto().getEntitiManager());
            if (quantidadeTag.intValue() > 0) {
                tags.add(tag);
                quantidadePorTag.put(tag, quantidadeTag);
            }
        });

        todoRelacionamento.stream().forEach(rl -> {
            BigInteger quantidadeMeta = (BigInteger) UtilSBPersistencia.getRegistroBySQL("select count(p.id) from ProspectoEmpresa p \n"
                    + "right join prospectos_usuarios_responsaveis pr on p.id=pr.prospecto_id \n"
                    + "right join TipoRelacionamento tr on tr.id = p.relacionamento_id \n"
                    + "where pr.usuarioCRM_id=" + idUsuario + "  and tr.id=" + rl.getId(),
                    SBCore.getServicoRepositorio().getAcessoDadosDoContexto().getEntitiManager());
            if (quantidadeMeta.intValue() > 0) {
                relacionamentos.add(rl);
                quantidadesPorRelacionamento.put(rl, quantidadeMeta);
            }
        });

    }

    public long getQuantidadeRelacionamento(TipoRelacionamento pRelacionamento) {
        return quantidadesPorRelacionamento.get(pRelacionamento).longValue();
    }

    public long getQuantidadeMeta(MetaRelacionamento pMeta) {
        return quantidadePorMeta.get(pMeta).longValue();

    }

    public Long getQuantidadeTags(TagAtendimento pTag) {
        return quantidadePorTag.get(pTag).longValue();

    }

    public List<TagAtendimento> getTags() {
        return tags;
    }

    public List<PessoaJuridica> getProspectos() {
        return new ArrayList();
    }

    public List<TipoRelacionamento> getRelacionamentos() {
        return relacionamentos;
    }

    public List<MetaRelacionamento> getMetas() {
        return metas;
    }

}
