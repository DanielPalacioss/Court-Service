#!/bin/bash
echo "Restaurando base de datos desde /dump..."
mongorestore --username "$MONGO_INITDB_ROOT_USERNAME" \
             --password "$MONGO_INITDB_ROOT_PASSWORD" \
             --authenticationDatabase admin \
             --nsFrom="canchasdb.*" --nsTo="$DB_NAME.*" \
             /dump
echo "Restauración completada."
