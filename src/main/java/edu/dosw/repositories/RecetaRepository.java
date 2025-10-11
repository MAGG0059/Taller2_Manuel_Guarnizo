package edu.dosw.repositories;

import edu.dosw.model.Receta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecetaRepository extends MongoRepository<Receta, String> {
    List<Receta> findByChef_Tipo(String tipo);
    List<Receta> findByChef_TipoAndChef_Temporada(String tipo, int temporada);
    List<Receta> findByIngredientesContaining(String ingrediente);
}
