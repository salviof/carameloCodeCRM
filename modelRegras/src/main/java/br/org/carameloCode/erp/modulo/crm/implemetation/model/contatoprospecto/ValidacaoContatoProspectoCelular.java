package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.CPContatoProspecto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValidadorContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValidadoresContatoProspecto;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.ArrayList;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringTelefone;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import javax.persistence.EntityManager;

@ValidadorContatoProspecto(validador = ValidadoresContatoProspecto.CELULAR)
public class ValidacaoContatoProspectoCelular
        extends
        ValidacaoGenerica<ContatoProspecto> {

    public ValidacaoContatoProspectoCelular(ItfCampoInstanciado pCampo) {
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
                    if (getContatoProspecto().getProspecto() != null) {
                        url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO, contato.getProspecto());
                    }
                    if (getContatoProspecto().getId() != null) {
                        if (!contato.getId().equals(getContatoProspecto().getId())) {

                            throw new ErroValidacao("Este telefone já foi cadastrado para " + contato.getProspecto().getNome() + " -> Acesse: " + url);
                        }
                    } else {

                        throw new ErroValidacao("Este telefone já foi cadastrado para " + contato.getProspecto().getNome() + " -> Acesse: " + url);
                    }
                }
            } finally {
                UtilSBPersistencia.fecharEM(em);
            }
        }
        return new ArrayList<>();
    }

    public ContatoProspecto getContatoProspecto() {
        return getObjetoDoAtributo();
    }
}
