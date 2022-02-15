package com.plb.vinylmgt.service;

import com.plb.vinylmgt.domain.Vinyl;
import com.plb.vinylmgt.repository.VinylRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VinylService {

    private final VinylRepository vinylRepository;

    public VinylService(VinylRepository vinylRepository) {
        this.vinylRepository = vinylRepository;
    }

    public List<Vinyl> getVinylsByUser(String email) {
        return vinylRepository.findAllByUserEmail(email);
    }

    public Optional<Vinyl> getById(Long id) {
        return vinylRepository.findById(id);
    }

    public Vinyl save(Vinyl vinyl) {
        return vinylRepository.save(vinyl);
    }
}
