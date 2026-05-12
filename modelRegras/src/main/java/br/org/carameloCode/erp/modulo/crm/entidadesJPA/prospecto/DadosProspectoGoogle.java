package br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.SBCore.modulos.objetos.validador.ErroValidacao;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonString;
import java.util.Optional;
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

    private static boolean hasType(JsonObject component, String... desiredTypes) {
        if (!component.containsKey("types") || component.isNull("types")) {
            return false;
        }

        JsonArray typesArray = component.getJsonArray("types");
        if (typesArray == null) {
            return false;
        }

        for (String desired : desiredTypes) {
            for (int i = 0; i < typesArray.size(); i++) {
                if (desired.equals(typesArray.getString(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Optional<String> getFirstByType(JsonArray components, String... types) {
        for (JsonObject comp : components.getValuesAs(JsonObject.class)) {
            if (hasType(comp, types)) {
                String longName = comp.getString("long_name", null);
                if (longName != null && !longName.isEmpty()) {
                    return Optional.of(longName);
                }
            }
        }
        return Optional.empty();
    }

    public PessoaJuridica gerarProspectoComValidacao() throws ErroValidacao {

        PessoaJuridica prospectoValidado = new PessoaJuridica();
        prospectoValidado.setNome((String) getObjEmpresa().getString("name"));

        String endereco = (String) getObjEmpresa().getString("formatted_address");

        JsonArray partesDoEndereco = getObjDetalhes().getJsonArray("address_components");

        GoogleAddressParser.EnderecoGooglePlace enderecoGooogle = GoogleAddressParser.parseFromAddressComponents(partesDoEndereco);

        if (enderecoGooogle != null && !enderecoGooogle.getCep().isEmpty()) {
            try {
                String cep = enderecoGooogle.getCep();
                prospectoValidado.getCPinst("localizacao").getComoCampoLocalizacao().setCep(cep);
                if (!enderecoGooogle.getRuaComNumeroEComplemento().isEmpty()) {
                    prospectoValidado.getCPinst("localizacao").getComoCampoLocalizacao().setLogradouro(enderecoGooogle.getRua());
                    prospectoValidado.getCPinst("localizacao").getComoCampoLocalizacao().setComplemento(enderecoGooogle.getNumeroEComplemento());
                }

            } catch (Throwable t) {
                prospectoValidado.setEndereco((String) getObjEmpresa().getString("formatted_address"));
            }

        } else {
            prospectoValidado.setEndereco((String) getObjEmpresa().getString("formatted_address"));
        }
        JsonObject detalhes = getObjDetalhes();
        if (detalhes.containsKey("website")) {

            prospectoValidado.getCPinst("site").setValorSeValido((String) detalhes.getString("website"));

        }
        if (detalhes.containsKey("formatted_phone_number")) {
            prospectoValidado.getCPinst("telefonePrincipal").setValorSeValido((String) detalhes.getString("formatted_phone_number"));
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
        prospectoValidado.setObservacao(observacoesGooglePlace.toString());

        return prospectoValidado;
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
