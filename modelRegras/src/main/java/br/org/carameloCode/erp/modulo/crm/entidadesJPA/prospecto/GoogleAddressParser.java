/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import java.util.Optional;

/**
 *
 * @author salvio
 */
public class GoogleAddressParser {

    public static class EnderecoGooglePlace {

        private final String numeroEComplemento;
        private final String rua;
        private final String ruaComNumeroEComplemento;
        private final String complemento;
        private final String cep;

        public EnderecoGooglePlace(String pRua, String pNumero, String complemento, String pCep) {

            this.ruaComNumeroEComplemento = new StringBuilder().append(pRua).append(" ").append(pNumero).append(" ").append(complemento).toString();
            this.complemento = new StringBuilder().append(pRua).append(" ").append(pNumero).append(" ").append(complemento).toString();
            this.numeroEComplemento = new StringBuilder().append(pNumero).append(" ").append(complemento).toString();
            this.rua = new StringBuilder().append(pRua).toString();
            this.cep = pCep;
        }

        public String getNumeroEComplemento() {
            return numeroEComplemento;
        }

        public String getRua() {
            return rua;
        }

        public String getRuaComNumeroEComplemento() {
            return ruaComNumeroEComplemento;
        }

        public String getComplemento() {
            return complemento;
        }

        public String getCep() {
            return cep;
        }

    }

    /**
     * Parseia address_components vindo do JSON do Google Places
     */
    public static EnderecoGooglePlace parseFromAddressComponents(JsonArray addressComponents) {
        if (addressComponents == null || addressComponents.isEmpty()) {
            return new EnderecoGooglePlace("", "", "", "");
        }

        Optional<String> rua = getFirstByType(addressComponents, "route");
        Optional<String> numero = getFirstByType(addressComponents, "street_number");
        Optional<String> premise = getFirstByType(addressComponents, "premise");
        Optional<String> subpremise = getFirstByType(addressComponents, "subpremise");
        Optional<String> sublocality = getFirstByType(addressComponents, "sublocality_level_1", "sublocality");
        Optional<String> cep = getFirstByType(addressComponents, "postal_code");
        if (!cep.isPresent() || cep.get().isEmpty()) {
            return new EnderecoGooglePlace("", "", "", "");
        }
        // Monta Rua + Número
        String ruaComNumero = rua.orElse("");
        if (numero.isPresent()) {
            ruaComNumero = ruaComNumero.isEmpty()
                    ? numero.get()
                    : ruaComNumero + ", " + numero.get();
        }

        // Monta Complemento
        StringBuilder comp = new StringBuilder();

        subpremise.ifPresent(s -> appendComplemento(comp, s));   // Sala 503, Apto 101, etc.
        premise.ifPresent(s -> appendComplemento(comp, s));      // Edifício, Condomínio
        sublocality.ifPresent(s -> appendComplemento(comp, s));  // Bairro

        return new EnderecoGooglePlace(rua.orElse(""), numero.orElse(""), subpremise.orElse(""), cep.orElse(""));
    }

    // ====================== MÉTODOS AUXILIARES ======================
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

    private static void appendComplemento(StringBuilder sb, String value) {
        if (sb.length() > 0) {
            sb.append(" - ");
        }
        sb.append(value);
    }
}
