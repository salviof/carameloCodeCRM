package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoColaborador.ContatoColaborador;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValorLogicoEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValoresLogicosEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.usuariosb.CPUsuarioSB;

@ValorLogicoEnvioEmail(calculo = ValoresLogicosEnvioEmail.CONTATOSDISPONIVEIS)
public class ValorLogicoEnvioEmailContatosDisponiveis
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailContatosDisponiveis(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private boolean listaDefinida;
    private Long pessoaDaLista;

    private Map<String, List<ContatoProspecto>> lista = new HashMap();

    private enum tipoLista {
        CONTATOS_DO_PROSPECTO, TODOS_CONTATOS
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (getEnvioMail().getProspecto() == null) {
            if (pessoaDaLista != 0) {
                listaDefinida = false;
            }
        } else {
            if (listaDefinida) {
                listaDefinida = pessoaDaLista == getEnvioMail().getProspecto().getId();
            }
        }
        if (!listaDefinida) {
            Pessoa pessoa = getEnvioMail().getProspecto();
            if (pessoa == null) {
                pessoaDaLista = 0l;
            } else {
                pessoaDaLista = pessoa.getId();
            }
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            try {

                List<ContatoColaborador> colaboradores;
                List<ContatoProspecto> listaCompleta = new ArrayList<>();
                colaboradores
                        = new ConsultaDinamicaDeEntidade(ContatoColaborador.class, em).addCondicaoPositivo(CPUsuarioSB.ativo)
                                .resultadoRegistros();
                EnvioEmail emailAenviar = UtilSBPersistencia.loadEntidade(getEnvioMail(), em);
                getEnvioMail().setContatosDisponiveis(new ArrayList<>());
                if (emailAenviar.getProspecto() != null) {
                    pessoa = UtilSBPersistencia.loadEntidade(pessoa, em);
                    pessoa.getContatosComEmail().stream().forEach(listaCompleta::add);
                    listaCompleta.addAll(colaboradores);
                } else {
                    listaCompleta = UtilSBPersistencia.getListaTodos(ContatoProspecto.class, em);
                }
                getEnvioMail().setContatosDisponiveis(listaCompleta);
                listaDefinida = true;
            } finally {
                UtilSBPersistencia.fecharEM(em);
            }
        }
        return getEnvioMail().getContatosDisponiveis();

    }

    public EnvioEmail getEnvioMail() {
        //getCampoInst().getValidacaoLogicaEstrategia()

        return (EnvioEmail) getCampoInst().getObjetoDoAtributo();
    }

}
