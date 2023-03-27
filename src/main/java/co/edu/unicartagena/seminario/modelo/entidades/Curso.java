/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicartagena.seminario.modelo.entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author VICTOR CETRE
 */
@Entity
@Data
@Table(name = "CURSOS")
@NoArgsConstructor
@AllArgsConstructor
public class Curso implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Nombre", nullable = false, length = 200)
    private String nombre;
    @Column(name = "Precio", precision = 22, scale = 0)
    private Double precio;
    @Column(name = "Hora")
    private Integer hora;
    @Column(name = "Nivel", length = 200)
    private String nivel;
    @Column(name = "fechaInscripcionInicial")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInscripcionInicial;
    @Column(name = "fechaInscripcionFinal")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInscripcionFinal;
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;
    @Column(name = "fechaCierre")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCierre;
    @Column(name = "numAlumnos")
    private Integer numAlumnos;
    @Column(name = "descripcion", length = 200)
    private String descripcion;
    @Column(name = "modalidad", length = 200)
    private String modalidad;
    
    @Override
    public String toString() {
        String formatoFechaInsIni = new SimpleDateFormat("yyyy-MM-dd").format(fechaInscripcionInicial);
        String formatoFechaInsFin = new SimpleDateFormat("yyyy-MM-dd").format(fechaInscripcionFinal);
        String formatoFechaIni = new SimpleDateFormat("yyyy-MM-dd").format(fechaInicio);
        String formatoFechaCierre = new SimpleDateFormat("yyyy-MM-dd").format(fechaCierre);
        String datos = "ID: "+id+"\n"
                + "NOMBRE: "+nombre+"\n"
                + "PRECIO: "+precio+"\n"
                + "HORA: "+hora+"\n"
                + "NIVEL: "+nivel+"\n"
                + "FECHA INCRIPCION INICIAL: "+fechaInscripcionInicial+"\n"
                + "FECHA INSCRIPCION FINAL: "+fechaInscripcionFinal+"\n"
                + "FECHA INICIO: "+fechaInicio+"\n"
                + "FECHA CIERRE: "+fechaCierre+"\n"
                + "NUMERO ALUMNOS: "+numAlumnos+"\n"
                + "DESCRIPCION: "+descripcion+"\n"
                + "MODALIDAD: "+modalidad+"\n";  
        return datos;
    }
}
