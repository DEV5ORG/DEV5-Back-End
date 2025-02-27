package com.dev5.backenddev5.Service;

import com.dev5.backenddev5.Repository.PagoRepository;

public class PagoService {
    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }
}
