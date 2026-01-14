package br.org.carameloCode.erp.modulo.crm.implemetation.model.tipoatividadecrm;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.Atividade.tipoAtividade.TipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.FabModulosCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.modulo.InfoModulosCRM;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;
import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCReflexao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrm.ValorLogicoTipoAtividadeCRM;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoatividadecrm.ValoresLogicosTipoAtividadeCRM;

@ValorLogicoTipoAtividadeCRM(calculo = ValoresLogicosTipoAtividadeCRM.ACOESDEPLUGINSDISPONIVEIS)
public class ValorLogicoTipoAtividadeCRMAcoesDePluginsDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoTipoAtividadeCRMAcoesDePluginsDisponiveis(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getTipoAtividade().getAcoesDePluginsDisponiveis() == null || getTipoAtividade().getAcoesDePluginsDisponiveis().isEmpty()) {
            List<Class> classes = UtilCRCReflexao.getClassesComEstaAnotacao(InfoModulosCRM.class, "org.coletivoJava.fw.projetos.crm.plugin");
            List<AcaoDoSistema> acoes = new ArrayList<>();
            classes.stream().filter(classe -> (classe.getAnnotation(InfoModulosCRM.class) != null))
                    .forEach(fabrica -> {
                        InfoModulosCRM modulo = (InfoModulosCRM) fabrica.getAnnotation(InfoModulosCRM.class);
                        if (modulo != null && modulo.modulo().equals(FabModulosCRM.PLUGIN)) {
                            try {

                                for (Object fab : fabrica.getEnumConstants()) {

                                    ComoFabricaDeAcoesPersistencia fabAcao = (ComoFabricaDeAcoesPersistencia) fab;
                                    if (fabAcao.getRegistro().isUmaAcaoFormulario()) {
                                        acoes.add(fabAcao.getRegistro());
                                    }

                                }
                            } catch (Throwable t) {
                                System.out.println("Erro analizando " + fabrica.getName());
                            }

                        }
                    });
            getTipoAtividade().setAcoesDePluginsDisponiveis(acoes);

        }
        return getTipoAtividade().getAcoesDePluginsDisponiveis();
    }

    public TipoAtividadeCRM getTipoAtividade() {
        TipoAtividadeCRM tipo = (TipoAtividadeCRM) getCampoInst().getObjetoDoAtributo();
        if (tipo.getId() == null) {
            return tipo;
        }
        return UtilSBPersistencia.loadEntidade((TipoAtividadeCRM) getCampoInst().getObjetoDoAtributo(), UtilSBPersistencia.getEMDoContexto());
    }
}
