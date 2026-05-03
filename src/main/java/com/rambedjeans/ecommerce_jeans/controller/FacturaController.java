package com.rambedjeans.ecommerce_jeans.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rambedjeans.ecommerce_jeans.dto.FacturaDTO;
import com.rambedjeans.ecommerce_jeans.model.Factura;
import com.rambedjeans.ecommerce_jeans.service.FacturaService;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    //crear facturas
    // /api/facturas POST
    @PostMapping
    public ResponseEntity<?> create(@RequestBody FacturaDTO dto) {

        try {
            Factura factura = facturaService.create(dto);

            return ResponseEntity.status(HttpStatus.CREATED).body(factura);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //lisatr todas las fact
    // /api/facturas GET
    @GetMapping
    public ResponseEntity<List<Factura>> getAll() {
        return ResponseEntity.ok(facturaService.getAll());
    }

    //lisatr activas
    // api/facturas/activas --GET
    @GetMapping("/activas")
    public ResponseEntity<List<Factura>> getAllActive() {
        return ResponseEntity.ok(
            facturaService.getAllActive()
        );
    }

    //anular facturas PATCH
    @PatchMapping("/{id}/anular")
    public ResponseEntity<?> anular(@PathVariable Integer id) {

        try {
            Factura factura = facturaService.anular(id);

            return ResponseEntity.ok(
                Map.of(
                    "facturaId", factura.getId(),
                    "estado", factura.getEstado().name(),
                    "mensaje", "Factura anulada correctamente"
                )
            );

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                Map.of("error", e.getMessage())
            );
        }
    }

    //listar la anulada
    // api/facturas/anuladas

    @GetMapping("/anuladas")
    public ResponseEntity<List<Factura>> getAllAnuladas() {
        return ResponseEntity.ok(
            facturaService.getAllAnuladas()
        );
    }

    //PAGAR FACTURAS
    // api/facturas/id/pagar
    @PatchMapping("/{id}/pagar")
    public ResponseEntity<?> pagar(@PathVariable Integer id) {

    try {
        Factura factura = facturaService.marcarComoPagada(id);

        return ResponseEntity.ok(
            Map.of(
                "facturaId", factura.getId(),
                "estadoPago", factura.getEstadoPago().name(),
                "mensaje", "Factura pagada correctamente"
            )
        );

    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(
            Map.of("error", e.getMessage())
        );
    }
    }

    // api/facturas/id/rechazar
    @PatchMapping("/{id}/rechazar")
    public ResponseEntity<?> rechazar(@PathVariable Integer id) {

    try {
        Factura factura = facturaService.marcarComoRechazada(id);

        return ResponseEntity.ok(
            Map.of(
                "facturaId", factura.getId(),
                "estadoPago", factura.getEstadoPago().name(),
                "mensaje", "Factura rechazada correctamente"
            )
        );

    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(
            Map.of("error", e.getMessage())
        );
    }
    }

    //listar facturas pagadas rechazadas pendientes

    @GetMapping("/pagadas")
    public ResponseEntity<List<Factura>> getPagadas() {
        return ResponseEntity.ok(facturaService.getPagadas());
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<Factura>> getPendeintes() {
        return ResponseEntity.ok(facturaService.getPedndientes());
    }

    @GetMapping("/rechazadas")
    public ResponseEntity<List<Factura>> getRechadas() {
        return ResponseEntity.ok(facturaService.getRechazadas());
    }


}
    



    

