package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoColaborador.ContatoColaborador;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValorLogicoEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValoresLogicosEnvioEmail;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@ValorLogicoEnvioEmail(calculo = ValoresLogicosEnvioEmail.HTMLMAILCAMPOEMCOPIAJSON)
public class ValorLogicoEnvioEmailHtmlMailCampoEmCopiaJson
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailHtmlMailCampoEmCopiaJson(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private List<ContatoProspecto> contatosEmcompia;

    public void addContatoSeValido(ContatoProspecto pContato) {

        if (pContato != null && !contatosEmcompia.contains(pContato)) {
            if (!UtilCRCStringValidador.isNuloOuEmbranco(pContato.getEmail())) {

                contatosEmcompia.add(pContato);

            }
        }

    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (contatosEmcompia == null) {
            contatosEmcompia = new ArrayList<>();
            try {
                Map<Pessoa, Long> contEmp = getEnvioEmail().getContatos().stream()
                        .filter(ct -> ((ct instanceof ContatoProspecto) && (ct.getProspecto() != null)))
                        .collect(Collectors.groupingBy(ContatoProspecto::getProspecto, Collectors.counting()));
                Map<UsuarioCRM, Long> contColaboradores;
                contColaboradores = getEnvioEmail().getContatos().stream()
                        .filter(ct -> ((ct instanceof ContatoColaborador) && (ct.getComoContatoColaborador().getUsuario() != null)))
                        .map(ct -> ct.getComoContatoColaborador())
                        .collect(Collectors.groupingBy(ContatoColaborador::getUsuario, Collectors.counting()));

                boolean multiplaspessoas = contEmp.size() > 1;
                boolean destinoExterno = !contEmp.isEmpty();
                boolean destinoInterno = !destinoExterno && !contColaboradores.isEmpty();

                JSONObject resposta = new JSONObject();

                if (destinoExterno) {
                    if (!multiplaspessoas) {
                        if (!contColaboradores.isEmpty()) {
                            //Adiciona todos colaboradores em cópia
                            getEnvioEmail().getContatos().stream().filter(ctc -> (ctc instanceof ContatoColaborador)).forEach(this::addContatoSeValido);
                        }
                        Pessoa pessoa = contEmp.keySet().stream().findFirst().get();
                        int indice = 0;
                        //adiociona todos os contatos exceto o principal em copia
                        for (ContatoProspecto c : getEnvioEmail().getContatos()) {

                            if (c != null && !(c instanceof ContatoColaborador) && c.getProspecto() != null && c.getProspecto().equals(pessoa)) {
                                if (indice > 0) {
                                    addContatoSeValido(c);
                                }
                                indice++;
                            }

                        }
                    } else {
                        // multiplas empresas deve ser enviado via Mautic o campo em cópia não se aplica
                    }
                    // Se marcou para enviar cópia para colaboradores do prospecto, adicionar todos
                    if (getEnvioEmail().isEnviarCopiaColaboradores()) {
                        getEnvioEmail().getProspecto().getUsuariosResponsaveis().stream().forEach(responsavel -> {
                            addContatoSeValido(responsavel.getContatoVinculado());
                        });
                    }
                }

                if (destinoInterno) {
                    int indice = 0;
                    //Todos os colaboradores exceto o primeiro serão em cópia
                    for (ContatoProspecto c : getEnvioEmail().getContatos()) {
                        if (indice > 0) {
                            addContatoSeValido(c);
                        }
                        indice++;
                    }
                }

                JSONArray contatosJson = new JSONArray();
                contatosEmcompia.stream().forEach(cEmcp -> {
                    JSONObject novoColaborador = new JSONObject();
                    novoColaborador.put("email", cEmcp.getEmail());
                    if (!UtilCRCStringValidador.isNuloOuEmbranco(cEmcp.getNome())) {
                        novoColaborador.put("nome", cEmcp.getNome());
                    } else {
                        novoColaborador.put("nome", cEmcp.getEmail());
                    }
                    contatosJson.add(novoColaborador);

                });
                resposta.put("contatos", contatosJson);
                getEnvioEmail().setHtmlMailCampoEmCopiaJson(resposta.toJSONString());
            } catch (Throwable t) {

            }
        }

        return getEnvioEmail().getHtmlMailCampoEmCopiaJson();
    }

    public EnvioEmail getEnvioEmail() {
        return (EnvioEmail) getCampoInst().getObjetoDoAtributo();
    }
}
