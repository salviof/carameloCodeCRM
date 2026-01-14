/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonString;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author SalvioF
 */
public class DadosProspectoGoogle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String jsonEmpresa;
    private String jsonDetalhes;
    @Transient
    private JsonObject objEmpresa;
    @Transient
    private JsonObject objDetalhes;
    @Transient
    private Pessoa prospecto;

    @Deprecated
    public DadosProspectoGoogle() {
    }

    public DadosProspectoGoogle(JsonObject pJsonEmpresa, JsonObject pJsonDetalhes) {
        objEmpresa = pJsonEmpresa;
        objDetalhes = pJsonDetalhes;
        jsonEmpresa = UtilCRCJson.getTextoByJsonObjeect(pJsonEmpresa);
        jsonDetalhes = UtilCRCJson.getTextoByJsonObjeect(pJsonDetalhes);
        id = (long) this.hashCode();
    }

    private JsonObject getObjEmpresa() {
        if (objEmpresa == null) {
            try {
                JSONParser parser = new JSONParser();
                objEmpresa = (JsonObject) parser.parse(jsonEmpresa);
            } catch (Throwable t) {
                return null;
            }
        }
        return objEmpresa;

    }

    private JsonObject getObjDetalhes() {

        if (objEmpresa == null) {
            try {
                //(JsonObject) parser.parse(jsonDetalhes);
                objDetalhes = UtilCRCJson.getJsonObjectByTexto(jsonDetalhes);
            } catch (Throwable t) {
                return null;
            }
        }
        return objDetalhes;

    }

    public Pessoa getProspecto() {
        return gerarProspecto();
    }

    public Pessoa gerarProspecto() {
        if (prospecto == null) {
            prospecto = new PessoaJuridica();
            prospecto.setNome((String) getObjEmpresa().getString("name"));
            prospecto.setEndereco((String) getObjEmpresa().getString("formatted_address"));

            JsonObject detalhes = getObjDetalhes();
            if (detalhes.containsKey("website")) {
                prospecto.getCPinst("site").setValor((String) detalhes.getString("website"));
            }
            if (detalhes.containsKey("formatted_phone_number")) {
                prospecto.setTelefonePrincipal((String) detalhes.getString("formatted_phone_number"));
            }
            if (detalhes.containsKey("icon")) {
                String icone = (String) detalhes.getString("icon");

            }

            final StringBuilder observacoesGooglePlace = new StringBuilder("nota->" + getObjEmpresa().getInt("rating"));
            observacoesGooglePlace.append("<br/>");
            JsonArray fotos = (JsonArray) getObjEmpresa().getJsonArray("photos");
            if (fotos != null) {
                observacoesGooglePlace.append(" Qtd Fotos: " + fotos.size());
            } else {
                observacoesGooglePlace.append(" sem fotos ");
            }
            JsonArray tipos = getObjEmpresa().getJsonArray("types");
            if (tipos != null) {
                observacoesGooglePlace.append("<br/> Tipo: ");
                if (tipos.isEmpty()) {
                    observacoesGooglePlace.append(" não definido");
                } else {
                    getObjEmpresa().getJsonArray("types").forEach(vl -> {
                        String valor = ((JsonString) vl).getString();
                        observacoesGooglePlace.append(valor);
                        observacoesGooglePlace.append(" | ");
                    });

                }
            }
            if (getObjEmpresa().containsKey("business_status")) {
                observacoesGooglePlace.append("<br/> Status " + getObjEmpresa().getString("business_status"));
            }
            prospecto.setObservacao(observacoesGooglePlace.toString());
        }
        return prospecto;
    }

    public Long getId() {
        return id;
    }

}
