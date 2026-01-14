/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.apis.apiCORE;

import java.util.List;
import java.util.Map;

/**
 *
 * @author desenvolvedor
 */
public interface ItfConfigAPI {

    public Map<String, String> getPropriedades();

    public List<String> getAtributos();

    public String getValorAtributo(String pAtributo);

    public boolean condigurarAtributos(String... atributos);

    public void criarArquivoConfig();

    public void lerArquivoConfig();

    public String getAPIKey();

    public Object getClasseServicos();

}
