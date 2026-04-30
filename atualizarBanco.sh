#!/bin/bash
cp -f /home/superBits/devTools/bancoDeDados/CRMCasaNovaOnline.sql /home/superBits/devTools/DBDiff/referencia/
cp -f /home/superBits/devTools/bancoDeDados/CRMCarameloCode.Homologacao.sql /home/superBits/devTools/DBDiff/novoBanco/
cd /home/superBits/desenvolvedor/geradorScriptDiferencaMysql
docker-compose up -d mysqlab
sleep 5
docker-compose up dbdiff

