/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicartagena.seminario.modelo.crud;

import co.edu.unicartagena.seminario.modelo.entidades.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author VICTOR CETRE
 */
@Repository
public interface CursosCrud extends JpaRepository <Curso, Integer> {

}
