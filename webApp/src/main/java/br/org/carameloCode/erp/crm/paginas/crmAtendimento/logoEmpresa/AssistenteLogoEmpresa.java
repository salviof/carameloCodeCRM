/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.crm.paginas.crmAtendimento.logoEmpresa;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.prospecto.Pessoa;
import com.super_bits.marketing.Util.navegador.Navegador;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.formularios.interfaces.ItfPaginaAtual;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author sfurbino
 */
@Named
@ViewScoped
public class AssistenteLogoEmpresa implements Serializable {

    private int index = 0;
    private Navegador buscaImagens;
    private Pessoa pessoa;

    @Inject
    private ItfPaginaAtual paginaAtual;

    public void proximo() {
        index++;
        if (index > 2) {
            index = 0;
        }
    }

    public void anterior() {
        index--;
        if (index < 0) {
            index = 2;
        }
    }

    public int getIndex() {
        return index;
    }

    public boolean isTemNavegacaoAtiva() {
        if (buscaImagens == null) {
            return false;
        }
        return (buscaImagens.isFoiCarregadoDocumento());

    }

    @PostConstruct
    public void inicio() {
        pessoa = (Pessoa) paginaAtual.getInfoPagina().getComoPaginaEntidade().getEntidadeSelecionada();
        if (UtilCRCStringValidador.isNuloOuEmbranco(pessoa.getCPinst("site").getValor())) {
            SBCore.enviarAvisoAoUsuario("Nenhum site foi cadastrado para o prospecto");

        } else {

            buscaImagens = new Navegador(pessoa.getCPinst("site").getValor().toString());

        }

    }

    public Navegador getBuscaImagens() {
        return buscaImagens;
    }

}
