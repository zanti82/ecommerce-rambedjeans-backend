package com.rambedjeans.ecommerce_jeans.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rambedjeans.ecommerce_jeans.dto.DetalleDTO;
import com.rambedjeans.ecommerce_jeans.dto.FacturaDTO;
import com.rambedjeans.ecommerce_jeans.model.DetalleFactura;
import com.rambedjeans.ecommerce_jeans.model.EstadoFactura;
import com.rambedjeans.ecommerce_jeans.model.EstadoPago;
import com.rambedjeans.ecommerce_jeans.model.Factura;
import com.rambedjeans.ecommerce_jeans.model.Usuario;
import com.rambedjeans.ecommerce_jeans.model.Variante;
import com.rambedjeans.ecommerce_jeans.repository.FacturaRepository;

@Service
public class FacturaService {

    private FacturaRepository facturaRepository;
    private UsuarioService usuarioService;
    private VarianteService varianteService;
    

   
    //inyeccion
    public FacturaService(FacturaRepository facturaRepository, 
            UsuarioService usuarioService,
            VarianteService varianteService) {
        this.facturaRepository = facturaRepository;
        this.usuarioService = usuarioService;
        this.varianteService = varianteService;
    }

    
    //crear facturas con detalles

    public Factura create(FacturaDTO dto) {
  

        //Buscar usuario
        Usuario usuario = usuarioService.getById(dto.getUsuarioId());
        
        //Crear factura y asigno el usuario
        Factura factura = new Factura();
        factura.setUsuario(usuario);
    
        List<DetalleFactura> detallesFact = new ArrayList<>();
        BigDecimal totalFact = BigDecimal.ZERO;

            // Recorrer productos
            for (DetalleDTO item : dto.getDetalles()) {

                // 🔹 Buscar variante
                Variante variante = varianteService.getById(item.getVarianteId());
                
                //capturamos la cantiada del item
                Integer cantidad = item.getCantidad();

                // Validar stock (si tienes stock en variante)
                if (variante.getStock() < cantidad) {
                    throw new RuntimeException("Stock insuficiente para la variante: " + variante.getReferencia());
                }

                //Obtener precio (desde referencia)
                BigDecimal precio = variante.getReferencia().getPrecioBase();
                
                //Calcular subtotal
                BigDecimal subtotal = precio.multiply(BigDecimal.valueOf(cantidad));

                //Crear detalle
                DetalleFactura detalleItem = new DetalleFactura();
                detalleItem.setFactura(factura);
                detalleItem.setVariante(variante);
                detalleItem.setCantidad(cantidad);
                detalleItem.setPrecioUnitario(precio);
                detalleItem.setSubtotal(subtotal);

                //al lista detalles anexamos el item calculado
                detallesFact.add(detalleItem);

                //sumamoas al total el subtotal dle item 
                totalFact = totalFact.add(subtotal);

                // Descontar stock
                variante.setStock(variante.getStock() - cantidad);
            }

        // Asignar totales
        factura.setTotal(totalFact);
        factura.setDetalles(detallesFact);
        factura.setFecha(LocalDateTime.now());
        factura.setEstado(EstadoFactura.ACTIVA);
        factura.setEstadoPago(EstadoPago.PENDIENTE);

        // Guardar cascade guarda detalles
        return facturaRepository.save(factura);
    }

    public List<Factura> getAll() {
        return facturaRepository.findAll();
    }

    public List<Factura> getAllActive() {
       return facturaRepository.findByEstado(EstadoFactura.ACTIVA);
    }

    public List<Factura> getAllAnuladas() {
        return facturaRepository.findByEstado(EstadoFactura.ANULADA);
    }

    public Factura getById(Integer id) {
        return facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variante no encontrada"));
    }

    //anular

    public Factura anular(Integer id) { 

    //buscamos la factura
    Factura factura = facturaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
     if (factura.getEstado() == EstadoFactura.ANULADA) {
       
            throw new RuntimeException("Ya está anulada");
        }
    
        for (DetalleFactura d : factura.getDetalles()) {
            Variante v = d.getVariante();
            v.setStock(v.getStock() + d.getCantidad());
        }
    
        factura.setEstado(EstadoFactura.ANULADA);
    
        return facturaRepository.save(factura);
    }

    //PAGAR FACTURA

    public Factura marcarComoPagada(Integer id) {

    Factura factura = facturaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

    // ❌ No pagar si está anulada
    if (factura.getEstado() == EstadoFactura.ANULADA) {
        throw new RuntimeException("No se puede pagar una factura anulada");
    }

    // ❌ Evitar doble pago
    if (factura.getEstadoPago() == EstadoPago.PAGADO) {
        throw new RuntimeException("La factura ya está pagada");
    }

    factura.setEstadoPago(EstadoPago.PAGADO);

    return facturaRepository.save(factura);
    }

    //PAGAR FACTURA

    public Factura marcarComoRechazada(Integer id) {

        Factura factura = facturaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
    
           
        // ❌ Evitar dobel recahzo
        if (factura.getEstadoPago() == EstadoPago.RECHAZADO) {
            throw new RuntimeException("La factura ya está rechazada");
        }
    
        factura.setEstadoPago(EstadoPago.RECHAZADO);
    
        return facturaRepository.save(factura);
        }
    

    //busacar facturas pagas pendinete o rechadadas
    public List<Factura> getPagadas() {
        return facturaRepository.findByEstadoPago(EstadoPago.PAGADO);
    }
    public List<Factura> getPedndientes() {
        return facturaRepository.findByEstadoPago(EstadoPago.PENDIENTE);
    }
    public List<Factura> getRechazadas() {
        return facturaRepository.findByEstadoPago(EstadoPago.RECHAZADO);
    }
    
}
