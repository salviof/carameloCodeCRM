package br.org.carameloCode.erp.modulo.crm.implemetation.model.envioemail;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.mail.envioEmail.envioEmail.EnvioEmail;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoColaborador.ContatoColaborador;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoPessoal.ContatoPessoal;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import com.super_bits.marketing.Util.UtilCRCEmailAvancado;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValorLogicoEnvioEmail;
import br.org.carameloCode.erp.modulo.crm.api.model.envioemail.ValoresLogicosEnvioEmail;
import org.json.simple.JSONObject;

@ValorLogicoEnvioEmail(calculo = ValoresLogicosEnvioEmail.HTMLMAILCAMPOPARAJSON)
public class ValorLogicoEnvioEmailHtmlMailCampoParaJson
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoEnvioEmailHtmlMailCampoParaJson(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    private List<ContatoProspecto> contatosCampoPara;

    public void addContatoSeValido(ContatoProspecto pContato) {

        if (pContato != null && !contatosCampoPara.contains(pContato)) {
            if (!UtilCRCStringValidador.isNuloOuEmbranco(pContato.getEmail())) {

                contatosCampoPara.add(pContato);

            }
        }

    }

    @Override
    public Object getValor(Object... pEntidade) {
        try {
            if (contatosCampoPara == null) {
                contatosCampoPara = new ArrayList<>();
                Map<Pessoa, Long> contEmp = getEnvioEmail().getContatos().stream()
                        .filter(ct -> ((ct instanceof ContatoProspecto) && (ct.getProspecto() != null)))
                        .collect(Collectors.groupingBy(ContatoProspecto::getProspecto, Collectors.counting()));
                Map<UsuarioCRM, Long> contColaboradores = getEnvioEmail().getContatos().stream()
                        .filter(ct -> ct != null && ct.getComoContatoColaborador() != null && ct.getComoContatoColaborador().getUsuario() != null)
                        .map(ctt -> ctt.getComoContatoColaborador())
                        .collect(Collectors.groupingBy(ContatoColaborador::getUsuario, Collectors.counting()));
                List<ContatoPessoal> contPrivado = new ArrayList();
                getEnvioEmail().getContatos().stream()
                        .filter(ct -> ((ct instanceof ContatoPessoal) && (ct.getProspecto() == null)))
                        .map(ct -> ct.getComoContatoPrivado()).forEach(contPrivado::add);

                boolean multiplaspessoas = contEmp.size() > 1;
                boolean destinoExterno = true;
                boolean destinoInterno = false;

                destinoExterno = !contEmp.isEmpty() || !contPrivado.isEmpty();
                destinoInterno = !destinoExterno && !contColaboradores.isEmpty();

                JSONObject resposta = new JSONObject();

                if (destinoExterno) {
                    if (!contEmp.isEmpty()) {
                        if (!multiplaspessoas) {

                            Pessoa pessoa = contEmp.keySet().stream().findFirst().get();
                            int indice = 0;
                            //adiociona o primeiro contato do prispecto
                            addContatoSeValido(getEnvioEmail().getContatos().stream()
                                    .filter(c -> c != null && !(c instanceof ContatoColaborador) && c.getProspecto() != null
                                    && c.getProspecto().equals(pessoa))
                                    .findFirst().get());

                        } else {
                            // multiplas empresas deve ser enviado via Mautic
                            throw new UnsupportedOperationException("O envio de e-mails para multiplos leads, deve ser realizado por campanhas do mautic");
                        }
                    }

                }
                contPrivado.forEach(pessoal -> {
                    addContatoSeValido(pessoal);
                });

                if (destinoInterno) {
                    int indice = 0;
                    // adiciona o primeiro contato com e-mail como contato principal
                    addContatoSeValido(getEnvioEmail().getContatos().stream().filter(ct -> (ct instanceof ContatoColaborador)
                            && !UtilCRCStringValidador.isNuloOuEmbranco(ct.getEmail())).findFirst().get());;
                    for (ContatoProspecto c : getEnvioEmail().getContatos()) {
                        if (indice > 0) {
                            addContatoSeValido(c);
                        }
                        indice++;
                    }
                }

                resposta = UtilCRCEmailAvancado.getJsonFromListaContato(contatosCampoPara);
                getEnvioEmail().setHtmlMailCampoParaJson(resposta.toJSONString());
            }
        } catch (Throwable t) {

        }
        return getEnvioEmail().getHtmlMailCampoParaJson();
    }

    public EnvioEmail getEnvioEmail() {
        return (EnvioEmail) getCampoInst().getObjetoDoAtributo();
    }
}
