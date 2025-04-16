package mk.ukim.finki.emt.emt_lab.repository;

import mk.ukim.finki.emt.emt_lab.model.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host,Long> {
}
