/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.infnet.projeto.managedBean;

import com.infnet.projeto.data.Curso;
import com.infnet.projeto.service.CursoClient;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Kehlt
 */
@ManagedBean
@ViewScoped
public class CursoMBean extends BaseMBean{

    private List<Curso> cursos = CursoClient.getAllCursos();

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }    
}
