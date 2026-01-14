package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemailatividade;

import com.google.common.collect.Lists;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioDocumentoAtividade.EnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoColaborador.ContatoColaborador;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValorLogicoEnvioEmailAtividade;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemailatividade.ValoresLogicosEnvioEmailAtividade;

@ValorLogicoEnvioEmailAtividade(calculo = ValoresLogicosEnvioEmailAtividade.CONTATOSDISPONIVEIS)
public class ValorLogicoEnvioEmailAtividadeContatosDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailAtividadeContatosDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }
    private boolean listaDefinida;

    private List<ContatoColaborador> colaboradores;

    private List<ContatoProspecto> contatosDisponiveis;

    @Override
    public Object getValor(Object... pEntidade) {
        EntityManager em = UtilSBPersistencia.getEMDoContexto();
        try {
            if (!listaDefinida) {
                getEnvioMail().setContatosDisponiveis(new ArrayList<>());
                contatosDisponiveis = new ArrayList<>();
                EnvioEmailAtividade emailAtividade = UtilSBPersistencia.loadEntidade(getEnvioMail(), em);
                List<ContatoColaborador> colaboradoresAtivos = UtilSBPersistencia.getListaTodos(ContatoColaborador.class);
                colaboradoresAtivos.size();
                colaboradores = new ArrayList<>();
                colaboradoresAtivos.parallelStream().filter(cl -> cl.getCampoInstanciadoByNomeOuAnotacao("ativo").getValorComoBoolean()).forEach(contatosDisponiveis::add);
                if (emailAtividade.getProspecto() == null) {
                    emailAtividade.setProspecto(getEnvioMail().getAtividade().getProspectoEmpresa());
                }

                getEnvioMail().getProspecto().getContatosProspecto().stream().forEach(contatosDisponiveis::add);
                contatosDisponiveis.addAll(colaboradores);
                listaDefinida = true;
            }
        } finally {

        }
        getEnvioMail().setContatosDisponiveis(Lists.newArrayList(contatosDisponiveis));
        return getEnvioMail().getContatosDisponiveis();

    }

    public EnvioEmailAtividade getEnvioMail() {
        return (EnvioEmailAtividade) getCampoInst().getObjetoDoAtributo();
    }
}
