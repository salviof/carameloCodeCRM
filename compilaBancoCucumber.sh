#!/bin/bash
source ./SBProjeto.prop
SLUG_REQUISITO=$1

docker exec devopsTestes1 bash -c '
    mysqldump -al --force -u root -psenhaDev#123 '"${NOME_BANCO}"' \
    > "/devopsDBScript/'"${SLUG_REQUISITO}"'.cucumber.sql"
'

echo "Backup realizado com sucesso: ${SLUG_REQUISITO}.cucumber.sql"