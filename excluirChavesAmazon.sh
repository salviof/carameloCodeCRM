#!/bin/bash
set -euo pipefail

USUARIO_EXCECAO="usuarioTemporario"

for user in $(aws iam list-users --query 'Users[].UserName' --output text); do
  if [ "$user" != "$USUARIO_EXCECAO" ]; then
    for key in $(aws iam list-access-keys \
      --user-name "$user" \
      --query 'AccessKeyMetadata[].AccessKeyId' \
      --output text); do

      echo "Deleting key $key from user $user"
      aws iam delete-access-key \
        --user-name "$user" \
        --access-key-id "$key"
    done
  fi
done



