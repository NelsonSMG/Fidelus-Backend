package com.github.arqweb.fidelus.ejb;

import com.github.arqweb.fidelus.model.BolsaPuntos;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class TareaPlanificadaDAO {

    @Inject
    private BolsaPuntosDAO bolsaPuntosDAO;

    @Schedule(minute = "*/1", hour = "*", persistent = false)
    public void atSchedule() throws InterruptedException {
        System.out.println("Probando tarea planificada");
    }

}
