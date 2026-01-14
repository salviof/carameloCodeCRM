/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoCampoDinamico;

import com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp.TesteCRMCarameloCode;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.DadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.dadosDinamicos.TipoDadoCRM;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.crm.prospecto.PessoaJuridica;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.dao.consultaDinamica.ConsultaDinamicaDeEntidade;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author salvio
 */
public class ValorLogicoTesteDadoDinamico extends TesteCRMCarameloCode {

    public DadoCRM getDadoCRM(Class pClasseValorlogico) {
        PessoaJuridica pessoaJuridica
                = (PessoaJuridica) new ConsultaDinamicaDeEntidade(PessoaJuridica.class, getEM())
                        .addcondicaoCampoIgualA("cnpj", "32.371.685/0001-79").getPrimeiroRegistro();
        assertNotNull("Cliente não encontrado", pessoaJuridica);

        Optional<DadoCRM> dadoTipo = pessoaJuridica.getDadosColetados().stream().filter(dado -> dado.getTipoDadoCRM()
                .getValorPadrao().contains(pClasseValorlogico.getSimpleName() + ".class"))
                .findFirst();

        if (dadoTipo.isPresent()) {
            return dadoTipo.get();
        }

        List<TipoDadoCRM> tiposdado = UtilSBPersistencia.getListaTodos(TipoDadoCRM.class, getEM());
        Optional<TipoDadoCRM> tipoDAdoPEsquisa = tiposdado.stream().filter(tipoDAdo
                -> !UtilCRCStringValidador.isNuloOuEmbranco(tipoDAdo.getValorPadrao())
                && tipoDAdo
                        .getValorPadrao().contains(pClasseValorlogico.getSimpleName() + ".class"))
                .findFirst();
        if (tipoDAdoPEsquisa.isPresent()) {
            DadoCRM dado = new DadoCRM();
            dado.setProspectoEmpresa(pessoaJuridica);
            dado.setTipoDadoCRM(tipoDAdoPEsquisa.get());

            return dado;
        }
        return null;
    }

}
