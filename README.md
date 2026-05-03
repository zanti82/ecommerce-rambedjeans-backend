# ecommerce-rambedjeans-backend
API REST for RAMBED JEANS e-commerce  with Spring Boot, MySQL y JWT

# E-commerce de Jeans - Backend

API REST para sistema de comercio electrónico de jeans para hombre y mujer.

## 🛠️ Stack Tecnológico

- Java 17
- Spring Boot 3.2
- Spring Security + JWT
- MySQL 8
- Maven

## 📋 Funcionalidades

- [ ] CRUD de productos
- [ ] Sistema de autenticación (clientes y administradores)
- [ ] Carrito de compras persistente
- [ ] Procesamiento de pedidos
- [ ] Panel de administración

## 🚀 Instalación Local
```bash
# Clonar repositorio
git clone https://github.com/zanti82/ecommerce-rambedjeans-backend.git

# Configurar base de datos
# Crear BD en MySQL llamada 'ecommerce_jeans'

# Configurar credenciales en src/main/resources/application.properties

# Ejecutar
mvn spring-boot:run
```

## 📝 Estado del Proyecto

En desarrollo activo (Fase 1/5)

## 👤 Autor

Tu Nombre - [LinkedIn](https://www.linkedin.com/in/santiago-a-ramirez-h/) - [Email](zanti82@gmail.com)

## Ejemplo para postam

POST REFERENCIAS
 {

 "idReferencia": "4999",
  "nombreReferencia": "Jean Recto Fit Clasico",
  "descripcion": "Jean de corte Recto para hombre",
  "estiloReferencia": "Recto",
  "precioBase": 99000,
  "genero": "HOMBRE",
  "activo": true

 }
 POST COLORES
 {  
        "activo": true,
        "codigoHex": "6B7C3A",
        "nombreColor": "Azul dirty amarillo"
  
}

POST TALLAS
{
 
   "nombreTalla": "32",
  "activo": true
 
}

POST USUARIOS

{
  
  "identificacion": "1001234567",
  "tipoDocumento": "CC",
  "nombre": "Juan Perez",
  "telefono": "3001234567",
  "direccion": "Calle 123 #45-67",
  "correo": "perez@mail.com",
  "password": "123456",
  "rol": "USER",
  "activo": true
}


