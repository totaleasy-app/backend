package app.totaleasy.backend.rest.controller;

import app.totaleasy.backend.rest.dto.id.PapelUsuarioIdDTO;
import app.totaleasy.backend.rest.dto.retrieval.PapelUsuarioRetrievalDTO;
import app.totaleasy.backend.rest.mapper.PapelUsuarioMapper;
import app.totaleasy.backend.rest.service.PapelUsuarioService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/papeis-usuarios")
@RequiredArgsConstructor
public class PapelUsuarioController {

    private final PapelUsuarioService papelUsuarioService;

    private final PapelUsuarioMapper papelUsuarioMapper;

    @GetMapping(params = {"username", "nomePapel"})
    @ResponseStatus(value = HttpStatus.OK)
    public PapelUsuarioRetrievalDTO findPapelUsuario(@Valid PapelUsuarioIdDTO id) {
        return this.papelUsuarioMapper.toPapelUsuarioRetrievalDTO(this.papelUsuarioService.findById(id));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<PapelUsuarioRetrievalDTO> findPapeisUsuarios() {
        return this.papelUsuarioService
            .findAll()
            .stream()
            .map(this.papelUsuarioMapper::toPapelUsuarioRetrievalDTO)
            .toList();
    }
}
