docker exec -i postgres-db psql -U postgres -d postgres < 01-init.sql
docker exec -i postgres-db psql -U postgres -d ticketing < 02-create_ticketing.sql
docker exec -i postgres-db psql -U postgres -d reserva < 03-create_reserva.sql
docker exec -i postgres-db psql -U postgres -d pagos < 04-create_pagos.sql
docker exec -i postgres-db psql -U postgres -d suscripciones < 05-create_suscripciones.sql
docker exec -i postgres-db psql -U postgres -d cartelera < 06-create_cartelera.sql
docker exec -i postgres-db psql -U postgres -d funciones < 07-create_funciones.sql
docker exec -i postgres-db psql -U postgres -d gestion < 08-create_gestion.sql
docker exec -i postgres-db psql -U postgres -d usuarios < 09-create_usuarios.sql
docker exec -i postgres-db psql -U postgres -d notificaciones < 10-create_notificaciones.sql
<<<<<<< HEAD
=======
docker exec -i postgres-db psql -U postgres -d feedback < 11-create_feedback.sql
docker exec -i postgres-db psql -U postgres -d cafeteria < 12-create_cafeteria.sql
docker exec -i postgres-db psql -U postgres -d merchandising < 13-create_merchandising.sql
>>>>>>> f2a52777114bd406ec5049a084b553c4601114be
docker exec -i postgres-db psql -U postgres -d personal < 14-create_personal.sql
docker exec -i postgres-db psql -U postgres -d informes < 15-create_informes.sql
docker exec -i postgres-db psql -U postgres -d marketing < 16-create_marketing.sql