/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.apis.googleDocs;

import com.super_bits.apis.apiCORE.ConfigAPIAbstrato;

/**
 *
 * @author desenvolvedor
 */
public class ConfigApiGoogleDocs extends ConfigAPIAbstrato {

    @Override
    public ServicosGoogleDocs getClasseServicos() {
        return new ServicosGoogleDocs();

    }

}
