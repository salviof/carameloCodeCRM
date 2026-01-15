/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.Casa_Nova.Intranet_Marketing_Digital.configAppp;

import br.org.coletivoJava.fw.erp.implementacao.erpintegracao.model.parametros.ParametroListaRestful;
import br.org.carameloCode.erp.modulo.crm.config.ConfigPercistenciaCrmCarameloCode;

import br.org.carameloCode.erp.modulo.crm.entidadesJPA.Atividade.pesquisaAtividade.PesquisaAtividade;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCEmail;
import com.super_bits.modulosSB.SBCore.modulos.email.ConfigEmailServersProjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import testesFW.TesteJunitJPAModuloERP;

/**
 *
 * É recomendável que seu projeto tenha uma classse basica abstrata de testes,
 * onde os testes genericos posssam ser criados, e o setup do teste possa ser
 * universalizado, utilize este exemplo para aprimorair a classe de testes do
 * projeto
 *
 * Estas informações podem te ajudar nesta tarefa:
 *
 * O Sistema oferece 3 classes abstratas nativas para testes com métodos para
 * axiliar nesta tarefa.
 *
 * TesteJunit,
 *
 * que obriga criar um método para configurar o ambiente,(SBCore.configurar)
 * possui o metodo lancar exececao, para exibição de relatório de erro mantendo
 * a compatibilidade com o Junit, e importa todos os Asserts do JUNIT
 *
 * TesteAcoes,
 *
 * para testar ações do Sistema (Importante certificar que as ações estejam
 * corretamente configuradas antes iniciar um projeto)
 *
 *
 * TesteJunitPercistencia
 *
 * possui um entityManager principal do projeto no padrão singleton (Singleton
 * no sentido literal signfica coisa única), e é um padrão de desenvolvimento
 * que checa se a coisa é nula e se não for instancia, no modo estático.
 *
 *
 * Este exemplo foi criado pensando no seu aprendizado, divirta-se!
 *
 * @author sfurbino
 */
public class TesteCRMCarameloCode extends TesteJunitJPAModuloERP {

    @Override
    public void configAmbienteDesevolvimento() {

        //Nas classes de ambiente padrão do sistema  modo desenvolvimento significa execuções via JUNIT, HOmologação Jetty na sua maquina, e Produção na Web
        SBCore.configurar(new ConfiguradorCoreCRM_CRC(), SBCore.ESTADO_APP.DESENVOLVIMENTO);

        SBPersistencia.configuraJPA(new ConfigPercistenciaCrmCarameloCode(), true, true);
        UtilCRCEmail.configurar(new ConfigEmailServersProjeto("mail.casanovadigital.com.br", "contato@casanovadigital.com.br", "acasadigital@2017"));
        MapaObjetosProjetoAtual.adcionarObjeto(PesquisaAtividade.class);

        MapaObjetosProjetoAtual.adcionarObjeto(ParametroListaRestful.class);
        //Configuração do arquivo de persistencia
    }

}
