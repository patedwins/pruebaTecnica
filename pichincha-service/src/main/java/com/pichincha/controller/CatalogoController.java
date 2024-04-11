/*
 * Copyright (c) 2021.
 *
 * Superintendencia de Econom&iacute;a Popular y Solidaria
 * Todos los derechos reservados
 */

package com.pichincha.controller;

import ec.gob.ec.core.util.rest.EndPointPrefix;
import com.pichincha.api.service.enums.MessagesEnum;
import com.pichincha.api.service.exception.SepsException;
import com.pichincha.api.service.exception.util.MessageProcessorUtil;
import com.pichincha.oracle.entity.CatalogoOracleEntity;
import com.pichincha.postgres.entity.CatalogoEntity;
import com.pichincha.postgres.entity.GrupoCatalogoEntity;
import com.pichincha.service.IBaseCatalogoService;
import com.pichincha.vo.CatalogoVO;
import com.pichincha.vo.request.CatalogoRequestVO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * CatalogoController.
 *
 * @author cfreire on 2022/08/23.
 * @version 1.0.0
 */
@RestController
@RequestMapping("/catalogo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Bearer")
public class CatalogoController {

    private final transient IBaseCatalogoService baseCatalogoService;
    private final transient MessageProcessorUtil messageProcessor;

    /**
     * Controller
     *
     * @param baseCatalogoService
     * @param messageProcessor
     */

    public CatalogoController(IBaseCatalogoService baseCatalogoService, MessageProcessorUtil messageProcessor) {
        this.baseCatalogoService = baseCatalogoService;
        this.messageProcessor = messageProcessor;
    }

    /**
     * Permite obtener el catálogo por nemónico y grupo.
     *
     * @return Una @{@link List} de @{@link CatalogoEntity}.
     */
    @PostMapping(value = "/obtenerCatalogoPorNemonicoYGrupo"
            , consumes = {MimeTypeUtils.APPLICATION_JSON_VALUE}
            , produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public CatalogoEntity obtenerCatalogoPorNemonicoYGrupo(@RequestBody CatalogoRequestVO catalogoRequestVO) {
        return baseCatalogoService.obtenerCatalogoPorNemonicoYGrupo(catalogoRequestVO.getNemonicoCatalogo(),
                catalogoRequestVO.getNemonicoGrupo());
    }

    /**
     * Permite obtener una lista de catálogos des una lista de códigos.
     *
     * @param codCatalogos
     * @return
     */
    @PostMapping(value = "/obtenerCatalogoListado"
            , produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<CatalogoEntity> findAllByCodCatalogoInAndEstRegistro(@RequestBody List<Integer> codCatalogos) {
        return baseCatalogoService.findAllByCodCatalogoInAndEstRegistro(codCatalogos);
    }

    /**
     * Permite obtener una lista de @{@link CatalogoVO}.
     *
     * @param nomGrupoCatalogo
     * @param nomNemonico
     * @return
     */
    @GetMapping(value = EndPointPrefix.obtenerLista + "/grupoCatalogo/{nomGrupoCatalogo}/catalogoPadre/{nomNemonico}", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Collection<CatalogoVO> obtenerCatalogoLista(@PathVariable String nomGrupoCatalogo, @PathVariable String nomNemonico) {
        return baseCatalogoService.obtenerCatalogoPorNemonicoYGrupoOracle(nomGrupoCatalogo, nomNemonico);
    }

    /**
     * Permite consultar un catálogo.
     *
     * @param id
     * @return
     */
    @GetMapping(value = EndPointPrefix.obtenerPorId + "/{id}", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public CatalogoOracleEntity obtenerCatalogoPorId(@PathVariable Long id) {
        Optional<CatalogoOracleEntity> catalog = baseCatalogoService.findById(id);
        if (catalog.isPresent()) {
            return catalog.get();
        } else {
            throw new SepsException(
                    HttpStatus.NOT_FOUND,
                    messageProcessor.getMessage(MessagesEnum.ERROR_404));
        }
    }

    /**
     * Find all group catalogs.
     *
     * @return a @{@link GrupoCatalogoEntity} list.
     */
    @GetMapping(value = EndPointPrefix.obtenerLista + "GrupoCatalogo", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<GrupoCatalogoEntity> findAll() {
        return baseCatalogoService.findAll();
    }

    /**
     * Find all catalog group by id. (Postgres)
     *
     * @param id is the catalog group code.
     * @return a list of @{@link CatalogoEntity}.
     */
    @GetMapping(value = EndPointPrefix.obtenerPorId + "GrupoCatalogo/{id}", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<CatalogoEntity> findAllByCodigoGrupoCatalogo(@PathVariable Long id) {
        return baseCatalogoService.findAllByCodigoGrupoCatalogo(id);
    }

}
