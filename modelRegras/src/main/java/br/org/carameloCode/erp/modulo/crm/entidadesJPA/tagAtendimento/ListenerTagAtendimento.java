/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.tagAtendimento;

import br.org.coletivoJava.integracoes.restMautic.implementacao.IntegracaoRestMauticAtribuirTagContato;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import java.io.Serializable;

/**
 *
 * @author salvio
 */
public class ListenerTagAtendimento extends ListenerEntidadePadrao implements Serializable {

    @Override
    public void acaoAntesDePersistir(ComoEntidadeSimples pEntidade) {
        super.acaoAntesDePersistir(pEntidade);
        IntegracaoRestMauticAtribuirTagContato.limparCache();
    }

    @Override
    public void acaoAntesDeAtualizar(ComoEntidadeSimples pEntidade) {
        super.acaoAntesDeAtualizar(pEntidade);
        IntegracaoRestMauticAtribuirTagContato.limparCache();
    }

}
