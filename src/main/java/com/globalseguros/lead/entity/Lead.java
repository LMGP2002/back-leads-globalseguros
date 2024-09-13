package com.globalseguros.lead.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.globalseguros.lead.enums.Ciudad;
import com.globalseguros.lead.validator.TrueOnly;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "global_lead")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campo para almacenar la fecha de creación del Lead
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDate fechaCreacion;

    // Campo nombre del cliente y sus validaciones
    @Column(name = "nombre_cliente", nullable = false, length = 100)
    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 100, message = "El nombre del cliente no debe exceder los 100 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚÑáéíóúñ\\s]+$", message = "El nombre del cliente solo debe contener letras, espacios y acentos")
    private String nomCliente;

    // Campo nit y sus validaciones
    @Column(nullable = false)
    @NotBlank(message = "El NIT es obligatorio")
    @Pattern(regexp = "^[^#¿?,]*$", message = "No se permiten los caracteres # ¿ ? ,")
    private String nit;

    // Campo nombre del punto y sus validaciones
    @Column(name = "nombre_punto", nullable = false)
    @Pattern(regexp = "^[^#¿?,]*$", message = "No se permiten los caracteres # ¿ ? ,")
    private String nomPunto;

    // Campo nombre del equipo y sus validaciones
    @Column(name = "nombre_equipo", nullable = false)
    @Pattern(regexp = "^[^#¿?,]*$", message = "No se permiten los caracteres # ¿ ? ,")
    private String nomEquipo;

    // Campo ciudad y sus validaciones
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Ciudad ciudad;

    // Campo RTC y sus validaciones
    @Column(nullable = false)
    @Pattern(regexp = "\\d+", message = "El campo RTC solo debe contener dígitos (0-9)")
    private String rtc;

    // Campo promotor y sus validaciones
    @Column(nullable = false)
    @Pattern(regexp = "^[^#¿?,]*$", message = "No se permiten los caracteres # ¿ ? ,")
    private String promotor;

    // Campo usuario y sus validaciones
    @Column(nullable = false)
    @Pattern(regexp = "^[^#¿?,]*$", message = "No se permiten los caracteres # ¿ ? ,")
    private String usuario;

    // Campo tratamiento de datos y sus validaciones
    @Column(name = "tratamiento_datos", nullable = false)
    @TrueOnly(message = "Debe aceptar el tratamiento de datos para continuar")
    private Boolean tratamientoDatos;
}
