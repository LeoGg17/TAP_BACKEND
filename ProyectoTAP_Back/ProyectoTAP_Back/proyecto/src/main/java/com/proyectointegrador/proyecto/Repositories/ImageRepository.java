package com.proyectointegrador.proyecto.Repositories;

import com.proyectointegrador.proyecto.Models.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
}
