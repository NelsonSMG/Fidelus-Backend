package com.github.arqweb.fidelus.ejb;

import com.github.arqweb.fidelus.model.BolsaPuntos;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class TareaPlanificadaDAO {

    @Inject
    private BolsaPuntosDAO bolsaPuntosDAO;

    @Schedule(minute = "*", hour = "*/24", persistent = false)
    public void actBolsaPuntos() throws InterruptedException {
        List<BolsaPuntos> bolsaPuntos = bolsaPuntosDAO.listar();
        System.out.print("Actualizacion planificada de bolsa de puntos");
        if(bolsaPuntos != null && !bolsaPuntos.isEmpty()) {
            List<BolsaPuntos> bolsasVencidas = bolsaPuntos.stream().filter(
                    bolsa -> bolsa.getFechaVencimiento().before(new Date())).collect(Collectors.toList());
            for(BolsaPuntos bolsa : bolsasVencidas) {
                bolsa.setSaldo(0);
                bolsaPuntosDAO.actualizar(bolsa);
            }
        }
    }

}
