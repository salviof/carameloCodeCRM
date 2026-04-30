package br.org.carameloCode.erp.modulo.crm.implemetation.model.pessoa;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.CPContatoProspecto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValidadorPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.ValidadoresPessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringTelefone;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import javax.persistence.EntityManager;

@ValidadorPessoa(validador = ValidadoresPessoa.TELEFONEPRINCIPAL)
public class ValidacaoPessoaTelefonePrincipal extends ValidacaoGenerica<Pessoa> {

    public ValidacaoPessoaTelefonePrincipal(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List validar(java.lang.Object o) throws ErroValidacao {

        String telefone = (String) o;

        telefone = UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(telefone);
        if (telefone != null) {
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            try {
                ContatoProspecto contato;
                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ContatoProspecto.class, em);
                consulta.addcondicaoCampoIgualA(CPContatoProspecto.celularformatointernacional, telefone);
                contato = consulta.getPrimeiroRegistro();
                if (contato != null) {
                    String url = "semURl";
                    if (getPessoa().getId() != null) {
                        if (!contato.getProspecto().getId().equals(getPessoa().getId())) {
                            url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO, contato.getProspecto());
                            throw new ErroValidacao("Este telefone já foi cadastrado para " + contato.getProspecto().getNome() + " -> Acesse: " + url);
                            //throw new ErroValidacao("Este telefone já foi cadastrado para " + contato.getProspecto().getNome() + " -> Acesse: " + url);
                        }
                    } else {
                        url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO, contato.getProspecto());
                        throw new ErroValidacao("Este telefone já foi cadastrado para " + contato.getProspecto().getNome() + " -> Acesse: " + url);
                    }

                }
            } finally {
                UtilSBPersistencia.fecharEM(em);
            }
        }
        return new ArrayList<>();

    }

    public Pessoa getPessoa() {
        return getObjetoDoAtributo();
    }
}
