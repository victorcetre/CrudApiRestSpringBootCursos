/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicartagena.seminario.controladores;

import co.edu.unicartagena.seminario.modelo.entidades.Curso;
import co.edu.unicartagena.seminario.modelo.servicio.CursoServicio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author VICTOR CETRE
 */
@RestController
@RequestMapping("/cursos")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class CursosCrontrolador {
    
    @Autowired
    private CursoServicio cursoServicio;
    
    @GetMapping
    @Transactional (readOnly = true)
    public ResponseEntity<?> listasCursos(){
       List<Curso> lista = cursoServicio.listarTodosLosCurso();
       return new ResponseEntity<>(lista,HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> guardarCruso (@RequestBody Curso curso){
        try {
            cursoServicio.guardarCurso(curso);
            Map<String, Object> msj = new HashMap<String, Object>();
            long total = cursoServicio.contarCursos();
            msj.put("mensaje","Curso guardado");
            msj.put("total", total);             
            return new ResponseEntity<>(msj, HttpStatus.CREATED); 
        } catch (Exception error) {
            log.info("GUARDAR CURSO: "+error.getMessage());
            Map<String, Object> msj = new HashMap<String, Object>();
            msj.put("error", error.getMessage());
            return new ResponseEntity<>(msj, HttpStatus.FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> editarCurso(@PathVariable Integer id, @RequestBody Curso curso){
        try {
            curso.setId(id);
            cursoServicio.editarCurso(curso);
            Map<String, Object> msj = new HashMap<String, Object>();
            msj.put("mensaje","Curso editado");
            msj.put("curso",curso);  
            return new ResponseEntity<>(msj,HttpStatus.ACCEPTED);
        }
        catch(Exception error) {
            log.info("EDITAR CURSO: "+error.getMessage());
            Map<String, Object> msj = new HashMap<String, Object>();
            msj.put("error", error.getMessage());
            return new ResponseEntity<>(msj, HttpStatus.FOUND);
        }
    }
    
    @PutMapping
    public ResponseEntity<?> editarCurso(@RequestBody Curso curso){
        try {
            cursoServicio.editarCurso(curso);
            Map<String, Object> msj = new HashMap<String, Object>();
            msj.put("mensaje","Curso editado");
            msj.put("curso",curso);  
            return new ResponseEntity<>(msj,HttpStatus.ACCEPTED);
        }
        catch(Exception error) {
            log.info("EDITAR CURSO: "+error.getMessage());
            Map<String, Object> msj = new HashMap<String, Object>();
            msj.put("error", error.getMessage());
            return new ResponseEntity<>(msj, HttpStatus.FOUND);
        }
    }
    
    @GetMapping("/xid")
    public ResponseEntity<?> buscarCursoPorId(@PathParam (value = "id") Integer id){
        try {
            Curso curso = cursoServicio.buscarPorId(id);
            Map<String, Object> msj = new HashMap();
            msj.put("mensaje","Curso encontrado");
            msj.put("curso",curso);  
            return new ResponseEntity<>(msj,HttpStatus.ACCEPTED);
        }
        catch(Exception ex) {
            log.info("BUSCAR CURSO POR ID: "+ex.getMessage());
            Map<String, Object> msj = new HashMap<String, Object>();
            msj.put("error", ex.getMessage());
            return new ResponseEntity<>(msj, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCursoPorIdV2(@PathVariable Integer id){
        try {
            Curso curso = cursoServicio.buscarPorId(id);
            Map<String, Object> msj = new HashMap();
            msj.put("mensaje","Curso encontrado");
            msj.put("curso",curso);  
            return new ResponseEntity<>(msj,HttpStatus.ACCEPTED);
        }
        catch(Exception ex) {
            log.info("BUSCAR CURSO POR ID: "+ex.getMessage());
            Map<String, Object> msj = new HashMap<String, Object>();
            msj.put("error", ex.getMessage());
            return new ResponseEntity<>(msj, HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCurso(@PathVariable Integer id){
        try {
            cursoServicio.eliminarCurso(id);
            Map<String, Object> msj = new HashMap<String, Object>();
            msj.put("mensaje","Curso eliminado");
            long total = cursoServicio.contarCursos();
            msj.put("total",total);  
            return new ResponseEntity<>(msj,HttpStatus.ACCEPTED);
        }
        catch(Exception ex) {
            log.info("ELIMINAR CURSO POR ID: "+ex.getMessage());
            Map<String, Object> msj = new HashMap<String, Object>();
            msj.put("error", ex.getMessage());
            return new ResponseEntity<>(msj, HttpStatus.NOT_FOUND);
        }
    }    
}
