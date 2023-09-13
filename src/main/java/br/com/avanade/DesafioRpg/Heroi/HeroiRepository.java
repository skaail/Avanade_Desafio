package br.com.avanade.DesafioRpg.Heroi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroiRepository extends JpaRepository<Heroi, Long> {
}
