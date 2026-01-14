/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.integracoes.typebot;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.formulario.TipoFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.formulario.resposta.RespostaFormulario;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.porteEmpresa.Porte;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.Pessoa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.TipoEmpresa;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.contatoProspecto.ContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.origemProspecto.OrigemProspecto;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.usuariosEPermissao.usuario.UsuarioCRM;
import br.org.carameloCode.erp.modulo.crm.api.dominio.acoes.crmAtendimento.ModuloCRMAtendimento;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringTelefone;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import groovy.json.JsonBuilder;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import br.org.carameloCode.erp.modulo.crm.api.model.contatoprospecto.CPContatoProspecto;
import br.org.carameloCode.erp.modulo.crm.api.model.pessoa.CPPessoa;
import br.org.carameloCode.erp.modulo.crm.api.model.respostaformulario.CPRespostaFormulario;
import br.org.carameloCode.erp.modulo.crm.api.model.tipoformulario.CPTipoFormulario;

/**
 *
 * @author salvio
 */
public class UtilCRMTypeBot {

    private final String VARIAVEL_LEAD_NOME = "NOME";
    private final String VARIAVEL_LEAD_TELEFONE = "TELEFONE";
    private final String VARIAVEL_LEAD_EMAIL = "EMAIL";
    private final String VARIAVEL_LEAD_REFERENCIA = "REFERENCIA";

    private static Map<String, String> referencias;

    protected static Map<String, String> getReferencias() {

        if (referencias == null) {
            referencias = new HashMap<>();
            referencias.put("wagner", "salvio@casanovadigital.com.br");
            referencias.put("salvio", "salvio@casanovadigital.com.br");
            referencias.put("camila", "camila@casanovadigital.com.br");
        }
        return referencias;
    }

    public static String getJsonLeituraHumanaValor(JsonObject dadosRequisicao) {
        StringBuilder texto = new StringBuilder();
        if (dadosRequisicao.containsKey("NOME")) {
            texto.append("Nome: " + dadosRequisicao.getString("NOME"));
        }
        if (dadosRequisicao.containsKey("TELEFONE")) {
            texto.append("telefone: " + dadosRequisicao.getString("NOME"));
        }
        if (dadosRequisicao.containsKey("EMPRESA")) {
            texto.append("Empresa: " + dadosRequisicao.getString("NOME"));
        }
        if (dadosRequisicao.containsKey("REFERENCIA")) {
            texto.append("Nome: " + dadosRequisicao.getString("REFERENCIA"));
        }
        return texto.toString();
    }

    private static Pessoa gerarNovoContatoRespostaFormulario(JsonObject pRespostaJson, TipoFormulario pTipoFormulario) throws ErroRegraDeNegocio, ErroValidacao {
        PessoaJuridica novoContato = new PessoaJuridica();

        Map<String, String> variaveis = parseVariablesWithJakarta(pRespostaJson.getJsonArray("variables"));

        if (variaveis.containsKey("EMPRESA")) {
            novoContato.getCPinst(CPPessoa.nome).setValorSeValido(variaveis.get("EMPRESA"));
        } else {
            novoContato.getCPinst(CPPessoa.nome).setValorSeValido("Empreendimento de " + variaveis.get("NOME"));
        }
        UsuarioCRM usuarioAtendenteREsponsavel = UtilCRMTypeBot.getUsuarioAtendimento(variaveis);
        if (usuarioAtendenteREsponsavel != null) {
            novoContato.setUsuarioCriou(usuarioAtendenteREsponsavel);
            novoContato.setUsuarioResponsavel(usuarioAtendenteREsponsavel);
        }

        if (pTipoFormulario.getOrigemPadrao() != null) {
            novoContato.setOrigem((OrigemProspecto) UtilSBPersistencia.getRegistroByID(OrigemProspecto.class,
                    5l));
        }
        if (!variaveis.containsKey("porte")) {
            novoContato
                    .setPorte((Porte) UtilSBPersistencia.getRegistroByID(Porte.class,
                            4l));
        } else {
            novoContato.setPorte(UtilSBPersistencia.getRegistroByID(Porte.class, Long.valueOf(variaveis.get("porte"))));
        }

        //Melhor implementação possível: se não tiver o id do json no chat, vê se tem um padrão, se não tiver padrão, define como tipo 4
        TipoEmpresa tipo = null;
        if (variaveis.containsKey("tipoEmpresa")) {
            tipo = UtilSBPersistencia.getRegistroByID(TipoEmpresa.class, Long.valueOf(variaveis.get("porte")));
        }
        if (tipo == null) {
            if (pTipoFormulario.getTipoEmpresa() != null) {
                novoContato.setTipoEmpresa(pTipoFormulario.getTipoEmpresa());
            } else {

                novoContato
                        .setTipoEmpresa((TipoEmpresa) UtilSBPersistencia.getRegistroByID(TipoEmpresa.class,
                                4l));
            }
        }

        novoContato.getCPinst(CPPessoa.responsavel).setValorSeValido(variaveis.get("NOME"));
        novoContato.getCPinst(CPPessoa.contatoprincipal).getValor();

        if (variaveis.containsKey("TELEFONE")) {
            novoContato.getContatoPrincipal().getCPinst(CPContatoProspecto.celular).setValorSeValido(variaveis.get("TELEFONE"));
        }
        if (variaveis.containsKey("EMAIL")) {
            novoContato.getContatoPrincipal().getCPinst(CPContatoProspecto.email).setValorSeValido(variaveis.get("EMAIL"));
        }
        if (variaveis.containsKey("NOME")) {
            novoContato.getContatoPrincipal().getCPinst(CPContatoProspecto.nome).setValorSeValido(variaveis.get("NOME"));
        }

        ItfRespostaAcaoDoSistema resposta = ModuloCRMAtendimento.prospectoSalvar(novoContato);
        if (!resposta.isSucesso()) {
            throw new ErroRegraDeNegocio("Falha castrando novo Lead" + resposta.getMensagens().get(0).getMenssagem());
        }
        return (Pessoa) resposta.getRetorno();
    }

    public static Map<String, String> parseVariablesWithJakarta(JsonArray variaveis) {
        Map<String, String> map = new HashMap<>();

        if (variaveis != null) {
            for (JsonObject var : variaveis.getValuesAs(JsonObject.class)) {
                String name = var.getString("name");
                // getString retorna null se o campo não existir ou for null
                String value = var.containsKey("value") && !var.isNull("value")
                        ? var.getString("value")
                        : "";
                map.put(name, value);
            }
        }

        return map;
    }

    /**
     * Exemplo Json:
     *
     * {
     * "id": "{{conversation.id}}", "formId": "{{formCode}}", "state":
     * "finished", "conversationId": "{{conversation.id}}", "startedAt":
     * "{{metadata.startTime}}", "finishedAt": "{{metadata.endTime}}",
     * "variables": {{variables}} }
     *
     * @return
     */
    public static synchronized RespostaFormulario gerarRespostaByJson(JsonObject pResposta, TipoFormulario formulario, boolean permitirPessoaJaCadastrada) throws ErroRegraDeNegocio, ErroValidacao {
        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        RespostaFormulario respostaGerada = null;
        String codigoResposta = null;

        try {
            if (pResposta.containsKey("id")) {
                codigoResposta = pResposta.getString("id");
            }
            if (codigoResposta == null) {
                throw new ErroRegraDeNegocio("id da conversa não foi enviado");
            }
            ConsultaDinamicaDeEntidade respostaJacadastrada = new ConsultaDinamicaDeEntidade(RespostaFormulario.class, em);
            respostaJacadastrada.addcondicaoCampoIgualA(CPRespostaFormulario.codigoresposta, codigoResposta);
            respostaGerada = respostaJacadastrada.getPrimeiroRegistro();

            if (respostaGerada != null) {
                return respostaGerada;
            }

            JsonArray variaveisJsonArray = pResposta.getJsonArray("variables");
            Pessoa pessoa = null;
            boolean usuarioExistente = false;
            if (!permitirPessoaJaCadastrada) {
                pessoa = gerarNovoContatoRespostaFormulario(pResposta, formulario);
            } else {

                Map<String, String> variaveis = parseVariablesWithJakarta(variaveisJsonArray);
                String telefone = null;
                if (variaveis.containsKey("TELEFONE")) {
                    telefone = variaveis.get("TELEFONE");
                }
                String email = null;
                if (variaveis.containsKey("EMAIL")) {
                    email = variaveis.get("EMAIL");
                }
                if (telefone == null && email == null) {
                    throw new ErroRegraDeNegocio("Identificador telefone, ou email é obrigatório");
                }
                ContatoProspecto contato = null;
                if (telefone != null) {
                    telefone = UtilCRCStringTelefone.gerarNumeroTelefoneInternacional(telefone);
                    ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ContatoProspecto.class, em);
                    consulta.addcondicaoCampoIgualA(CPContatoProspecto.celularformatointernacional, telefone);
                    contato = consulta.getPrimeiroRegistro();
                }
                if (telefone == null || contato != null) {
                    if (email != null) {
                        ConsultaDinamicaDeEntidade consulta = new ConsultaDinamicaDeEntidade(ContatoProspecto.class, em);
                        consulta.addcondicaoCampoIgualA(CPContatoProspecto.email, email);
                        contato = consulta.getPrimeiroRegistro();
                    }
                }
                if (contato == null) {

                    pessoa = gerarNovoContatoRespostaFormulario(pResposta, formulario);

                } else {
                    usuarioExistente = true;
                    pessoa = contato.getProspecto();
                }

            }

            if (pessoa == null) {
                throw new ErroRegraDeNegocio("Falha no armazenamento do Contato");
            }

            respostaGerada = new RespostaFormulario();
            respostaGerada.setNome("Resp Cod" + codigoResposta);
            respostaGerada.setPessoaExistente(usuarioExistente);
            respostaGerada.setPessoa(UtilSBPersistencia.loadEntidade(pessoa, em));
            respostaGerada.setCodigoResposta(codigoResposta);
            respostaGerada.setJsonResposta(UtilCRCJson.getTextoByJsonObjeect(pResposta));
            String codigoFormulario = pResposta.getString("typebotId");
            if (codigoFormulario == null) {
                throw new ErroRegraDeNegocio("O formCode não foi encontrado no Json com a  resposta");
            }

            TipoFormulario tipo = new ConsultaDinamicaDeEntidade(TipoFormulario.class, em).addcondicaoCampoIgualA(CPTipoFormulario.codigotypebot, codigoFormulario).getPrimeiroRegistro();
            if (tipo == null) {
                throw new ErroRegraDeNegocio("Falha procurando formCode" + codigoFormulario);
            }
            respostaGerada.setTipoFormulario(tipo);

            respostaGerada = UtilSBPersistencia.mergeRegistro(respostaGerada, em);

            return respostaGerada;
        } catch (ErroRegraDeNegocio | ErroValidacao t) {
            throw t;
        } finally {
            if (respostaGerada != null) {
                System.out.println(respostaGerada.getPessoa().getNome());
                System.out.println(respostaGerada.getCodigoResposta());
                if (respostaGerada.getTipoFormulario() != null) {
                    System.out.println(respostaGerada.getTipoFormulario().getCodigoTypebot());
                    System.out.println(respostaGerada.getTipoFormulario().getUltimas50RespCodigos());
                }
                System.out.println(respostaGerada.getPessoa().getContatosProspecto().size());

                if (respostaGerada.getPessoa().getContatoPrincipal() != null) {
                    System.out.println(respostaGerada.getPessoa().getContatoPrincipal().getEmail());
                }
            }

            UtilSBPersistencia.fecharEM(em);
        }

    }

    public static UsuarioCRM getUsuarioAtendimento(Map<String, String> pVariaveis) {
        UsuarioCRM usuarioAtendenteREsponsavel = null;
        String emailUsuarioREferencia;
        if (pVariaveis.containsKey("REFERENCIA")) {
            String ref = pVariaveis.get("REFERENCIA");
            if (ref != null) {
                emailUsuarioREferencia = getReferencias().get(ref);
                if (emailUsuarioREferencia == null) {
                    emailUsuarioREferencia = "salvio@casanovadigital.com.br";
                }
                if (emailUsuarioREferencia != null) {
                    try {
                        usuarioAtendenteREsponsavel = (UsuarioCRM) SBCore.getServicoPermissao().getUsuarioByEmail(emailUsuarioREferencia);

                    } catch (Throwable t) {

                    }
                }
            }

        } else {
            emailUsuarioREferencia = "camila@casanovadigital.com.br";
            usuarioAtendenteREsponsavel = (UsuarioCRM) SBCore.getServicoPermissao().getUsuarioByEmail(emailUsuarioREferencia);
        }

        return usuarioAtendenteREsponsavel;
    }

}
