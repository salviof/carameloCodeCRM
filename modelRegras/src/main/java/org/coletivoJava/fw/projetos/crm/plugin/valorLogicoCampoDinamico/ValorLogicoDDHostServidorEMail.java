package org.coletivoJava.fw.projetos.crm.plugin.valorLogicoCampoDinamico;

import br.org.coletivoJava.integracoes.restMavmail.api.pojo.RegistroDNSMav;
import br.org.coletivoJava.integracoes.servicoDeEmail.ServicoDeEmailImplementacaoBeta;
import br.org.carameloCode.erp.modulo.crm.entidadesJPA.dadosDinamicos.DadoCRM;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import java.net.URL;
import java.util.List;

public class ValorLogicoDDHostServidorEMail
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoDDHostServidorEMail(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public static String getServidorDoCliente(String pDominio) {
        ServicoDeEmailImplementacaoBeta servicoEmailMav = new ServicoDeEmailImplementacaoBeta();
        List<RegistroDNSMav> registros = servicoEmailMav.getRegistrosDNS(pDominio);
        if (registros == null) {
            return null;
        }
        RegistroDNSMav registroWebmail = registros.stream().filter(reg -> (reg.getTipo().equals("CNAME") && reg.getNome().contains("webmail."))).findFirst().get();
        String servidor = registroWebmail.getValor();
        return servidor;

    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (!isCacheAtivado()) {
            if (getDadoDinamico().getValor() == null) {
                String site = getDadoDinamico().getProspectoEmpresa().getCPinst("site").getValor().toString();
                if (!UtilCRCStringValidador.isNuloOuEmbranco(site)) {
                    try {
                        String urlSite = site;
                        URL url = new URL(urlSite);
                        String servidor = getServidorDoCliente(url.getHost());
                        if (servidor.startsWith("bhs")) {
                            if (servidor.contains("01")) {
                                getDadoDinamico().setValorEmpacotado("mail01-ssl.m9.network");
                            }
                            if (servidor.contains("02")) {
                                getDadoDinamico().setValorEmpacotado("mail02-ssl.m9.network");
                            }
                            if (servidor.contains("03")) {
                                getDadoDinamico().setValorEmpacotado("mail03-ssl.m9.network");
                            }
                            if (servidor.contains("04")) {
                                getDadoDinamico().setValorEmpacotado("mail04-ssl.m9.network");
                            }
                            if (servidor.contains("05")) {
                                getDadoDinamico().setValorEmpacotado("mail05-ssl.m9.network");
                            }

                        }

                    } catch (Throwable t) {

                    }
                }
                setCacheSegundosPadrao(60);
            }
        }
        return getDadoDinamico().getValorEnpacotado();
    }

    public DadoCRM getDadoDinamico() {
        return (DadoCRM) getCampoInst().getObjetoDoAtributo();
    }
}
