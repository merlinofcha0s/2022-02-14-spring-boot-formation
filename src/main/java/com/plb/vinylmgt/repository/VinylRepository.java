package com.plb.vinylmgt.repository;

import com.plb.vinylmgt.domain.Vinyl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.NamedNativeQueries;
import java.util.List;

public interface VinylRepository extends JpaRepository<Vinyl, Long> {
    Vinyl findOneBySongName(String songName);
    List<Vinyl> findAllBySongName(String songName, Pageable pageable);
    List<Vinyl> findAllByUserEmail(String email);
}
