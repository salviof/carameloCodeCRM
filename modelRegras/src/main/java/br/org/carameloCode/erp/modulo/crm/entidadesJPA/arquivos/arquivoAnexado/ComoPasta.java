/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.org.carameloCode.erp.modulo.crm.entidadesJPA.arquivos.arquivoAnexado;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoTemCor;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoTemIDUnico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoTemIcone;
import java.util.List;

/**
 *
 * @author salvio
 */
public interface ComoPasta extends ComoTemIcone, ComoTemCor, ComoTemIDUnico, ComoEntidadeSimplesSomenteLeitura {

    @Override
    public String getIcone();

    /**
     *
     * @return Código da Pasta
     */
    public Long getId();

    @Override
    public String getNome();

    public int getQuantidade();

    public boolean isCompartilharComConvidados();

    public boolean isTemSolicitacaoParaMim();

}
