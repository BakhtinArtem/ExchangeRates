package bakhtin.solution.ExchangeRate.Entities.Repos;

import bakhtin.solution.ExchangeRate.Entities.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepo extends JpaRepository<ExchangeRate, Long> {
}
