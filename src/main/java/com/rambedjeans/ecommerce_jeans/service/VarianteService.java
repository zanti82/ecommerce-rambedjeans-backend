package com.rambedjeans.ecommerce_jeans.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rambedjeans.ecommerce_jeans.dto.VarianteDTO;
import com.rambedjeans.ecommerce_jeans.model.Color;
import com.rambedjeans.ecommerce_jeans.model.Referencia;
import com.rambedjeans.ecommerce_jeans.model.Talla;
import com.rambedjeans.ecommerce_jeans.model.Variante;
import com.rambedjeans.ecommerce_jeans.repository.VarianteRepository;

@Service
public class VarianteService {

    private final VarianteRepository varianteRepository;
    private final ReferenciaService referenciaService;
    private final ColorService colorService;
    private final TallaService tallaService;

    //inyeccion de repo y servicios
    public VarianteService(
        VarianteRepository varianteRepository,
        ReferenciaService referenciaService,
        ColorService colorService,
        TallaService tallaService
    ) {
        this.varianteRepository = varianteRepository;
        this.referenciaService = referenciaService;
        this.colorService = colorService;
        this.tallaService = tallaService;
    }

    public Variante crearVariante(VarianteDTO dto) {

        // 🔹 traer entidades (igual que Gasto)
        Referencia referencia = referenciaService.getbyId(dto.getReferenciaId());
        Color color = colorService.getById(dto.getColorId());
        Talla talla = tallaService.getById(dto.getTallaId());

        // 🔹 generar SKU (ejemplo básico)
        String sku = referencia.getIdReferencia() + "-" 
                   + color.getIdColor() + "-" 
                   + talla.getIdTalla();

        // 🔹 crear objeto limpio
        Variante variante = new Variante(
            referencia,
            color,
            talla,
            dto.getStock(),
            sku,
            true
        );

        return varianteRepository.save(variante);
    }

    public List<Variante> getAll() {
        return varianteRepository.findAll();
    }

    public List<Variante> getAllActive() {
        return varianteRepository.findAll()
                .stream()
                .filter(Variante::getActivo)
                .toList();
    }

    public Variante getById(Integer id) {
        return varianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variante no encontrada"));
    }

    public Variante updateStock(Integer id, Integer stock) {

        if (stock == null || stock < 0) {
            throw new RuntimeException("Stock inválido");
        }
    
        Variante variante = varianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variante no encontrada"));
    
        variante.setStock(stock);
    
        return varianteRepository.save(variante);
    }

    public Variante update(Integer id, VarianteDTO dto) {

        Variante variante = varianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variante no encontrada"));
    
        Referencia referencia = referenciaService.getbyId(dto.getReferenciaId());
        Color color = colorService.getById(dto.getColorId());
        Talla talla = tallaService.getById(dto.getTallaId());
    
        variante.setReferencia(referencia);
        variante.setColor(color);
        variante.setTalla(talla);
        variante.setStock(dto.getStock());
    
        return varianteRepository.save(variante);
    }

    public Variante deactivate(Integer id) {

        Variante variante = varianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variante no encontrada"));
    
        variante.setActivo(false);
    
        return varianteRepository.save(variante);
    }

    public Variante activate(Integer id) {

        Variante variante = varianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variante no encontrada"));
    
        variante.setActivo(true);
    
        return varianteRepository.save(variante);
    }

    public void delete(Integer id) {

        if (!varianteRepository.existsById(id)) {
            throw new RuntimeException("Variante no encontrada");
        }
    
        varianteRepository.deleteById(id);
    }

    
}