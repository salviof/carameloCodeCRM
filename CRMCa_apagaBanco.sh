#!/bin/bash
source ./CRMCa_SBProjeto.prop
docker exec  devopsTestes1   /bin/bash -c  'mysqladmin -uroot -psenhaDev#123 drop '$NOME_BANCO' -f '
