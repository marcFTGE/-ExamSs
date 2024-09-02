package be.iccbxl.pid.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TroupeRepository extends JpaRepository<Troupe, Long> {

}
