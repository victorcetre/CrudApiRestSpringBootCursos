/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicartagena.seminario.controladores;

import co.edu.unicartagena.seminario.modelo.entidades.Curso;
import co.edu.unicartagena.seminario.modelo.servicio.CursoServicio;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author VICTOR CETRE
 */
@Controller
@RequestMapping("/crud")
@Slf4j
public class CursoControlador {
    
    @Autowired
    private CursoServicio cursoServicio;
    
    @GetMapping
    public String mostrarInicio (){
        return "index";
    }
    
    @GetMapping("/magregar")
    public String mostrarAgregar (){
        return "agregar";
    }
    
    @PostMapping
    public String agregar(Curso curso,Model datos) {
        log.info("El Curso fue guardado: " + curso);
        log.info("NOMBRE: " + curso.getNombre().toUpperCase());
        try {
            cursoServicio.guardarCurso(curso);
            long total = cursoServicio.contarCursos();
            datos.addAttribute("mensaje", "Curso guardato, Total: "+total);
            return "index";            
        } catch (Exception error){
            datos.addAttribute("mensaje",error.getMessage());
            return "agregar";
        }
    }
    
    @GetMapping("/mlistar")
    public String listarTodo(Model datos){
        List<Curso> cursos = cursoServicio.listarTodosLosCurso();
        datos.addAttribute("cursos", cursos);
        return "listar";
    }
    
    @PostMapping("/xid")
    public String buscarPorId(Curso curso, Model datos){
        try {
            cursoServicio.buscarPorId(curso.getId());
            datos.addAttribute("curso", curso);
             log.info("Curso encontrado: " + curso);
            return "buscar";
        } catch (Exception error) {
            datos.addAttribute("mensaje", error.getMessage());
            datos.addAttribute("curso", curso);
            return "buscar";
        }
    }
    
    @GetMapping("/buscarxid")
    public String mostrarBuscarId () {
        return "buscar";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, Model datos){
        try {
            cursoServicio.eliminarCurso(id);
            List<Curso> cursos = cursoServicio.listarTodosLosCurso();
            datos.addAttribute("cursos", cursos);
            return "listar";
        } catch (Exception ex) {
            datos.addAttribute("mensaje",ex.getMessage());
            return "listar";
        }
    }
    
    @GetMapping("/editar/{id}")
    public String editar(Curso curso, Model datos) throws Exception{
        log.info("Editando datos del Curso");
        curso = cursoServicio.buscarPorId(curso.getId());
        datos.addAttribute("curso", curso);
        return "editar";
    }
    
    @PostMapping("/guardar")
    public String guardar(Curso curso) throws Exception{
        cursoServicio.editarCurso(curso);
        return "index";
    }
}
