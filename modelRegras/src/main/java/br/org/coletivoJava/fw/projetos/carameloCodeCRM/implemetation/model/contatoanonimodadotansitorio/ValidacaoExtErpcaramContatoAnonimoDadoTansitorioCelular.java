package br.org.coletivoJava.fw.projetos.carameloCodeCRM.implemetation.model.contatoanonimodadotansitorio;

import br.org.carameloCode.erp.modulo.agenda.implemetation.model.contatoanonimodadotansitorio.ValidacaoContatoAnonimoDadoTansitorioCelular;
import br.org.carameloCode.erp.modulo.agenda.implemetation.model.contato.ContatoAnonimoDadoTansitorio;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.CPContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.contatoanonimodadotansitorio.ValidadorContatoAnonimoDadoTansitorio;
import br.org.coletivoJava.fw.projetos.carameloCodeCRM.api.model.contatoanonimodadotansitorio.ValidadoresContatoAnonimoDadoTansitorio;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringTelefone;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import java.util.ArrayList;
import javax.persistence.EntityManager;

@ValidadorContatoAnonimoDadoTansitorio(validador = ValidadoresContatoAnonimoDadoTansitorio.CELULAR)
public class ValidacaoExtErpcaramContatoAnonimoDadoTansitorioCelular
        extends
        ValidacaoContatoAnonimoDadoTansitorioCelular {

    public ValidacaoExtErpcaramContatoAnonimoDadoTansitorioCelular(
            ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List validar(java.lang.Object o) throws ErroValidacao {

        String telefone = (String) o;
        if (telefone == null) {
            return new ArrayList();
        }

        telefone = UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(telefone);
        if (telefone == null) {
            throw new ErroValidacao("Número inválido");
        }
        if (telefone != null) {
            EntityManager em = UtilSBPersistencia.getEntyManagerPadraoNovo();
            try {
                ContatoProspecto contato;
                ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ContatoProspecto.class, em);
                consulta.addcondicaoCampoIgualA(CPContatoProspecto.celularformatointernacional, telefone);
                contato = consulta.getPrimeiroRegistro();
                if (contato != null) {

                    throw new ErroValidacao("Este telefone já foi cadastrado para " + contato.getProspecto().getNome());
                }

            } finally {
                UtilSBPersistencia.fecharEM(em);
            }
        }

        return new ArrayList();
    }

    public ContatoAnonimoDadoTansitorio getContatoAnonimoDadoTansitorio() {
        return getObjetoDoAtributo();
    }
}
