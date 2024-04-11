/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.postgres.repository;

import com.pichincha.postgres.entity.CatalogoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Base catálogo repository interfaz.
 *
 * @author cfreire on 2022/08/23.
 * @version 1.0.0
 */
public interface IBaseCatalogoRepository extends CrudRepository<CatalogoEntity, Integer> {

    /**
     * Consulta utilizada para obtener el catálogo por nemonico y grupo.
     *
     * @param nemCatalogo
     * @param nemGrupo
     * @param estRegistro
     * @return
     */
    @Query("SELECT c FROM CatalogoEntity c " +
            "WHERE "
            + "c.nomNemonico = :nemCatalogo AND "
            + "c.estRegistro = :estRegistro "
            + "AND c.codGrupoCatalogo.nomNemonico = :nemGrupo "
            + "AND c.codGrupoCatalogo.estRegistro = :estRegistro"
    )
    CatalogoEntity obtenerCatalogoPorNemonicoYGrupo(@Param("nemCatalogo") String nemCatalogo,
                                                    @Param("nemGrupo") String nemGrupo,
                                                    @Param("estRegistro") String estRegistro);

    /**
     * Consulta utilizada para retornar un listado de catálogos desde una lista de códigos de catálogo.
     *
     * @param codCatalogos
     * @param estado
     * @return
     */
    List<CatalogoEntity> findAllByCodCatalogoInAndEstRegistro(List<Integer> codCatalogos, String estado);

    /**
     * findAllByCodigoGrupoCatalogo
     *
     * @param id
     * @param estRegistro
     * @return
     */
    @Query("SELECT c FROM CatalogoEntity c " +
              "WHERE "
              + "c.estRegistro = :estRegistro "
              + "AND c.codGrupoCatalogo.id = :id "
              + "AND c.codGrupoCatalogo.estRegistro = :estRegistro"
      )
      List<CatalogoEntity> findAllByCodigoGrupoCatalogo(
              @Param("id")Integer id,
              @Param("estRegistro") String estRegistro);
}
