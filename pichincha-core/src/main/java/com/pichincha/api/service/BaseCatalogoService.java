/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.api.service;

import com.pichincha.oracle.entity.CatalogoOracleEntity;
import com.pichincha.oracle.entity.GrupoCatalogoOracleEntity;
import com.pichincha.oracle.repository.ICatalogoOracleRepository;
import com.pichincha.oracle.repository.IGrupoCatalogoOracleRepository;
import com.pichincha.postgres.entity.CatalogoEntity;
import com.pichincha.postgres.entity.GrupoCatalogoEntity;
import com.pichincha.postgres.repository.IBaseCatalogoRepository;
import com.pichincha.postgres.repository.IBaseGrupoCatalogoRepository;
import com.pichincha.service.IBaseCatalogoService;
import com.pichincha.util.Constantes;
import com.pichincha.vo.CatalogoVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Base catálogo service implementation.
 *
 * @author cfreire on 2022/08/22.
 * @version 1.0.0
 */
@Service
public class BaseCatalogoService implements IBaseCatalogoService {

    private final transient IBaseCatalogoRepository baseCatalogoRepository;
    private final transient IBaseGrupoCatalogoRepository baseGrupoCatalogoRepository;
    private final transient ICatalogoOracleRepository rsfpsCatalogoRepository;
    private final transient IGrupoCatalogoOracleRepository rsfpsGrupoCatalogoRepository;

    /**
     * Constructor
     *  @param baseCatalogoRepository
     * @param baseGrupoCatalogoRepository
     * @param rsfpsCatalogoRepository
     * @param rsfpsGrupoCatalogoRepository
     */
    public BaseCatalogoService(IBaseCatalogoRepository baseCatalogoRepository, IBaseGrupoCatalogoRepository baseGrupoCatalogoRepository, ICatalogoOracleRepository rsfpsCatalogoRepository, IGrupoCatalogoOracleRepository rsfpsGrupoCatalogoRepository) {
        this.baseCatalogoRepository = baseCatalogoRepository;
        this.baseGrupoCatalogoRepository = baseGrupoCatalogoRepository;
        this.rsfpsCatalogoRepository = rsfpsCatalogoRepository;
        this.rsfpsGrupoCatalogoRepository = rsfpsGrupoCatalogoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public CatalogoEntity obtenerCatalogoPorNemonicoYGrupo(String nemonicoCatalogo, String nemonicoGrupo) {
        return baseCatalogoRepository.obtenerCatalogoPorNemonicoYGrupo(nemonicoCatalogo, nemonicoGrupo, Constantes.ESTADO_ACTIVO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CatalogoEntity> findAllByCodCatalogoInAndEstRegistro(List<Integer> codCatalogos) {
        return baseCatalogoRepository.findAllByCodCatalogoInAndEstRegistro(codCatalogos, Constantes.ESTADO_ACTIVO);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<CatalogoVO> obtenerCatalogoPorNemonicoYGrupoOracle(String nomGrupoCatalogo, String nomNemonico) {

        Collection<CatalogoVO> result = new ArrayList<>();
        Optional<GrupoCatalogoOracleEntity> grupoCatalogo = rsfpsGrupoCatalogoRepository.findByNomGrupoCatalogo(nomGrupoCatalogo);
        Optional<CatalogoOracleEntity> codigoCatalogoPadre = rsfpsCatalogoRepository.findAllByNomNemonico(nomNemonico);

        Collection<CatalogoOracleEntity> catalogos = obtenerCatalogos(grupoCatalogo, codigoCatalogoPadre);

        if (!catalogos.isEmpty()) {
            for (CatalogoOracleEntity catalogo : catalogos) {
                CatalogoVO rsfpsCatalogoVO = new CatalogoVO();
                rsfpsCatalogoVO.setCodCatalogo(catalogo.getCodCatalogo());
                rsfpsCatalogoVO.setCodCatalogoPadre(codigoCatalogoPadre.get().getCodCatalogo());
                rsfpsCatalogoVO.setCodGrupoCatalogo(grupoCatalogo.get().getCodGrupoCatalogo());
                rsfpsCatalogoVO.setNomNemonico(catalogo.getNomNemonico());
                rsfpsCatalogoVO.setNomCatalogo(catalogo.getNomCatalogo());
                rsfpsCatalogoVO.setNumOrden(catalogo.getNumOrden());
                rsfpsCatalogoVO.setNomCodigoExterno(catalogo.getNomCodigoExterno());
                rsfpsCatalogoVO.setEstRegistro(catalogo.getEstRegistro());
                rsfpsCatalogoVO.setNomAplicacion(catalogo.getNomAplicacion());
                rsfpsCatalogoVO.setVisibleSasof(catalogo.getVisibleSasof());
                result.add(rsfpsCatalogoVO);
            }
        }
        return result;
    }

    private Collection<CatalogoOracleEntity> obtenerCatalogos(Optional<GrupoCatalogoOracleEntity> grupoCatalogo, Optional<CatalogoOracleEntity> codigoCatalogoPadre) {
        if (grupoCatalogo.isPresent() && codigoCatalogoPadre.isPresent()) {
            return rsfpsCatalogoRepository.findByRsfpsGrupoCatalogoAndRsfpsCatalogoAndEstRegistro(
                    grupoCatalogo.get().getCodGrupoCatalogo(),
                    codigoCatalogoPadre.get().getCodCatalogo(),
                    Constantes.ESTADO_ACTIVO, Constantes.ESTADO_VISIBLE
            );
        }
        return null;
    }

    /**
     * Consulta el catálogo por id.
     *
     * @param id codigo del catálogo.
     * @return
     */
    @Override
    public Optional<CatalogoOracleEntity> findById(Long id) {
        return rsfpsCatalogoRepository.findAllByCodCatalogo(id);
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link GrupoCatalogoEntity} list.
     */
    @Override
    public List<GrupoCatalogoEntity> findAll() {
        return baseGrupoCatalogoRepository.findAll();
    }

    /**
     * Find all caltalogs by group catalog code.
     *
     * @param id
     * @return
     */
    @Override
    public List<CatalogoEntity> findAllByCodigoGrupoCatalogo(Long id) {
        return baseCatalogoRepository.findAllByCodigoGrupoCatalogo(Integer.valueOf(id.toString()), Constantes.ESTADO_ACTIVO);
    }

}