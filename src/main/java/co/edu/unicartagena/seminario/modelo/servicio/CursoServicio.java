/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicartagena.seminario.modelo.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unicartagena.seminario.modelo.crud.CursosCrud;
import co.edu.unicartagena.seminario.modelo.entidades.Curso;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author VICTOR CETRE
 */
@Service
@Slf4j
public class CursoServicio {
    @Autowired
    private CursosCrud cursoCrud;
    
    /**
     * Permite guardar un Curso en la BD
     * @param curso el curso a guardar
     * @throws Exception  mensaje en caso de que este curso ya exista
     */
    public void guardarCurso(Curso curso) throws Exception{
        if(cursoCrud.existsById(curso.getId())){
            log.info("El Curso ya existe: " + curso);
            throw new Exception("El Curso " + curso.getId()+" ya exite");
        }
        log.info("El Curso fue guardado: " + curso);
        cursoCrud.save(curso);
    }
    /**
     * Consulta todos los cursos de la BD
     * @return una lista con todos los cursos o una lista vacia sino hay.
     */
    public List<Curso> listarTodosLosCurso(){
        return cursoCrud.findAll();
    }
    /**
     * Verifica que el curso exista, para poder editarlo
     * @param curso
     * @throws Exception mensaje en caso de que no exista
     */
    public void editarCurso(Curso curso) throws Exception {
        if(!cursoCrud.existsById(curso.getId())){
            log.info("El Curso no existe: " + curso.getId());
            throw new Exception("El Curso "+curso.getId()+" no existe");
        }
        else {
            log.info("El Curso fue actualizado: " + curso);
            cursoCrud.save(curso);
        }
    }
    /**
     * Verifica que el curso exista, para poder eliminarlo
     * @param id
     * @throws Exception mensaje en caso de que no exista
     */
    public void eliminarCurso(Integer id) throws Exception{
        if(!cursoCrud.existsById(id)){
            log.info("El Curso no existe: " + id);
            throw new Exception("El Curso "+ id +" no existe");
        }
        else {
            log.info("El Curso fue actualizado: " + id);
            cursoCrud.deleteById(id);
        }
    }
    /**
     * Busca el curso por ID
     * @param id
     * @return returna el curso (id)
     * @throws Exception mensaje en caso de que el curso no exista
     */
    public Curso buscarPorId (Integer id) throws Exception {
        Curso curso = cursoCrud.findById(id).orElse(null);
        if (curso == null) {
            log.info("El Curso no existe: " + id);
            throw new Exception ("El Curso "+id+" no existe");
        }
        else {
            log.info("El Curso fue encontrado: " + curso);
            return curso;
        }
    }
    /**
     * Cuenta todos los cursos
     * @return 
     */
    public long contarCursos (){
        return cursoCrud.count();
    }
}
