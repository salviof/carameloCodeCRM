package br.org.carameloCode.erp.modulo.crm.implemetation.model.contatoprospecto;

import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.FabAcaoCRMAtendimento;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.CPContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.util.UtilCRMValidacoesEspeciais;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.FabTipoValidacaoUnitaria;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ValidacaoGenerica;
import com.super_bits.modulosSB.SBCore.modulos.view.widgetsFormulario.WidgetsFormulario;
import java.util.ArrayList;
import java.util.List;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValidadorContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.ValidadoresContatoProspecto;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import java.util.Objects;
import javax.persistence.EntityManager;

@ValidadorContatoProspecto(validador = ValidadoresContatoProspecto.EMAIL)
public class ValidacaoContatoProspectoEmail extends ValidacaoGenerica<ContatoProspecto> {

    public ValidacaoContatoProspectoEmail(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public List<WidgetsFormulario> validar(Object pValor) throws ErroValidacao {
        if (!FabTipoValidacaoUnitaria.REGEX.getValidador(getCampoInstanciado()).isValorValido(pValor)) {
            throw new ErroValidacao("O e-mail não parece válido");
        }
        if (UtilCRCStringValidador.isNuloOuEmbranco(pValor)) {
            return null;
        }
        if (UtilCRCStringValidador.isNuloOuEmbranco(getContatoProspecto().getNome())) {
            if (!UtilCRCStringValidador.isNuloOuEmbranco(pValor)) {
                throw new ErroValidacao("Defina o nome do responsável por este e-mail");
            }
        }
        UtilCRMValidacoesEspeciais.validarEmailCliente(getContatoProspecto().getProspecto(), (String) pValor);

        if (getContatoProspecto().getUsuarioVinculado() != null) {
            String emailDOUsuario = getContatoProspecto().getUsuarioVinculado().getEmail();
            getContatoProspecto().getUsuarioVinculado().setEmail((String) pValor);
        }
        if (pValor != null) {
            EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
            try {
                ConsultaDinamicaDeEntidade consultaEmail = new ConsultaDinamicaDeEntidade(ContatoProspecto.class, em);
                consultaEmail.addcondicaoCampoIgualA(CPContatoProspecto.email, pValor);
                ContatoProspecto c = consultaEmail.getPrimeiroRegistro();
                if (c != null) {
                    if (!Objects.equals(c.getId(), getContatoProspecto().getId())) {
                        if (Objects.equals(c.getProspecto(), getContatoProspecto().getProspecto())) {
                            throw new ErroValidacao(c.getProspecto().getNome() + " já tem um contato " + c.getNome() + " com email: " + pValor + " cadastrado");
                        } else {
                            String url = SBCore.getServicoVisualizacao().getEndrRemotoFormulario(FabAcaoCRMAtendimento.PROSPECTO_FRM_OPCOES_DO_PROSPECTO, c.getProspecto());
                            throw new ErroValidacao("Outra empresa: " + c.getProspecto().getNome() + ", código: " + c.getProspecto().getId() + " já tem um contato com o e-mail" + pValor + " cadastrado, Acesse: " + url);
                        }

                    }
                }
            } finally {

            }
        }
        return new ArrayList<>();
    }

    public ContatoProspecto getContatoProspecto() {
        return getObjetoDoAtributo();
    }
}
