package cl.teatromoro.suscripciones.repository;

import cl.teatromoro.suscripciones.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}