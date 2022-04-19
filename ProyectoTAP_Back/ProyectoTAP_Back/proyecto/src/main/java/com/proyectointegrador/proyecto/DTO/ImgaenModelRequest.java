package com.proyectointegrador.proyecto.DTO;

import javax.persistence.Lob;
import java.io.Serializable;

public class ImgaenModelRequest implements Serializable {
    private Long id;
    private String name;

    private String type;

    @Lob
    private byte[] pic;
}
