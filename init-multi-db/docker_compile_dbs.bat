docker exec -i postgres-db psql -U postgres -d postgres < 01-init.sql
docker exec -i postgres-db psql -U postgres -d cartelera < 06-create_cartelera.sql
docker exec -i postgres-db psql -U postgres -d funciones < 07-create_funciones.sql
docker exec -i postgres-db psql -U postgres -d gestion < 08-create_gestion.sql