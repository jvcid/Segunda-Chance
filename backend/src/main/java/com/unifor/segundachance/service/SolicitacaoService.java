

package com.unifor.segundachance.service;

import com.unifor.segundachance.dto.request.SolicitacaoRequestDTO;
import com.unifor.segundachance.dto.response.SolicitacaoResponseDTO;
import com.unifor.segundachance.entity.Anuncio;
import com.unifor.segundachance.entity.Solicitacao;
import com.unifor.segundachance.entity.User;
import com.unifor.segundachance.entity.enums.StatusAnuncio;
import com.unifor.segundachance.entity.enums.StatusSolicitacao;
import com.unifor.segundachance.exception.BusinessException;
import com.unifor.segundachance.exception.DuplicateResourceException;
import com.unifor.segundachance.exception.ForbiddenOperationException;
import com.unifor.segundachance.exception.ResourceNotFoundException;
import com.unifor.segundachance.repository.AnuncioRepository;
import com.unifor.segundachance.repository.SolicitacaoRepository;
import com.unifor.segundachance.security.AuthenticatedUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;
    private final AnuncioRepository anuncioRepository;
    private final AuthenticatedUserService authenticatedUserService;

    public SolicitacaoService(
            SolicitacaoRepository solicitacaoRepository,
            AnuncioRepository anuncioRepository,
            AuthenticatedUserService authenticatedUserService
    ) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.anuncioRepository = anuncioRepository;
        this.authenticatedUserService = authenticatedUserService;
    }

    @Transactional
    public SolicitacaoResponseDTO create(
            SolicitacaoRequestDTO dto
    ) {
        Anuncio anuncio = buscarAnuncio(dto.getAnuncioId());

        User authenticatedUser =
                authenticatedUserService.getAuthenticatedUser();

        validarSolicitacaoDoProprioAnuncio(
                anuncio,
                authenticatedUser
        );

        if (anuncio.getStatus() != StatusAnuncio.DISPONIVEL) {
            throw new BusinessException(
                    "Este anúncio não está disponível para novas solicitações"
            );
        }

        boolean jaExiste =
                solicitacaoRepository.existsByAnuncioIdAndUserId(
                        anuncio.getId(),
                        authenticatedUser.getId()
                );

        if (jaExiste) {
            throw new DuplicateResourceException(
                    "Você já solicitou este anúncio"
            );
        }

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setAnuncio(anuncio);
        solicitacao.setUser(authenticatedUser);
        solicitacao.setMensagem(dto.getMensagem());
        solicitacao.setStatus(StatusSolicitacao.PENDENTE);
        solicitacao.setCreatedAt(LocalDateTime.now());

        Solicitacao saved =
                solicitacaoRepository.save(solicitacao);

        return toResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<SolicitacaoResponseDTO> findAll() {
        return solicitacaoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<SolicitacaoResponseDTO> findEnviadas() {
        User authenticatedUser =
                authenticatedUserService.getAuthenticatedUser();

        return solicitacaoRepository
                .findByUserIdOrderByCreatedAtDesc(
                        authenticatedUser.getId()
                )
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<SolicitacaoResponseDTO> findRecebidas() {
        User authenticatedUser =
                authenticatedUserService.getAuthenticatedUser();

        return solicitacaoRepository
                .findByAnuncioUserIdOrderByCreatedAtDesc(
                        authenticatedUser.getId()
                )
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public SolicitacaoResponseDTO findById(Integer id) {
        return toResponseDTO(buscarSolicitacao(id));
    }

    @Transactional
    public SolicitacaoResponseDTO update(
            Integer id,
            SolicitacaoRequestDTO dto
    ) {
        Solicitacao solicitacao = buscarSolicitacao(id);

        User authenticatedUser =
                authenticatedUserService.getAuthenticatedUser();

        validarSolicitante(
                solicitacao,
                authenticatedUser
        );

        validarSolicitacaoPendenteParaAlteracao(solicitacao);

        solicitacao.setMensagem(dto.getMensagem());

        Solicitacao updated =
                solicitacaoRepository.save(solicitacao);

        return toResponseDTO(updated);
    }

    @Transactional
    public void delete(Integer id) {
        Solicitacao solicitacao = buscarSolicitacao(id);

        User authenticatedUser =
                authenticatedUserService.getAuthenticatedUser();

        validarSolicitante(
                solicitacao,
                authenticatedUser
        );

        validarSolicitacaoPendenteParaExclusao(solicitacao);

        solicitacaoRepository.delete(solicitacao);
    }

    @Transactional
    public SolicitacaoResponseDTO aprovar(Integer id) {
        Solicitacao solicitacao = buscarSolicitacao(id);

        User authenticatedUser =
                authenticatedUserService.getAuthenticatedUser();

        validarDonoDoAnuncio(
                solicitacao,
                authenticatedUser
        );

        validarSolicitacaoPendenteParaResposta(solicitacao);

        Anuncio anuncio = solicitacao.getAnuncio();

        if (anuncio.getStatus() != StatusAnuncio.DISPONIVEL) {
            throw new BusinessException(
                    "O anúncio não está mais disponível"
            );
        }

        solicitacao.setStatus(StatusSolicitacao.APROVADA);
        anuncio.setStatus(StatusAnuncio.RESERVADO);

        rejeitarOutrasSolicitacoes(
                anuncio.getId(),
                solicitacao.getId()
        );

        anuncioRepository.save(anuncio);

        Solicitacao updated =
                solicitacaoRepository.save(solicitacao);

        return toResponseDTO(updated);
    }

    @Transactional
    public SolicitacaoResponseDTO rejeitar(Integer id) {
        Solicitacao solicitacao = buscarSolicitacao(id);

        User authenticatedUser =
                authenticatedUserService.getAuthenticatedUser();

        validarDonoDoAnuncio(
                solicitacao,
                authenticatedUser
        );

        validarSolicitacaoPendenteParaResposta(solicitacao);

        solicitacao.setStatus(StatusSolicitacao.REJEITADA);

        Solicitacao updated =
                solicitacaoRepository.save(solicitacao);

        return toResponseDTO(updated);
    }

    private void validarSolicitacaoDoProprioAnuncio(
            Anuncio anuncio,
            User authenticatedUser
    ) {
        boolean isOwner = anuncio.getUser()
                .getId()
                .equals(authenticatedUser.getId());

        if (isOwner) {
            throw new BusinessException(
                    "Você não pode solicitar o próprio anúncio"
            );
        }
    }

    private void validarSolicitante(
            Solicitacao solicitacao,
            User authenticatedUser
    ) {
        boolean isRequester = solicitacao.getUser()
                .getId()
                .equals(authenticatedUser.getId());

        if (!isRequester) {
            throw new ForbiddenOperationException(
                    "Você não tem permissão para alterar esta solicitação"
            );
        }
    }

    private void validarDonoDoAnuncio(
            Solicitacao solicitacao,
            User authenticatedUser
    ) {
        boolean isOwner = solicitacao.getAnuncio()
                .getUser()
                .getId()
                .equals(authenticatedUser.getId());

        if (!isOwner) {
            throw new ForbiddenOperationException(
                    "Somente o dono do anúncio pode responder esta solicitação"
            );
        }
    }

    private void validarSolicitacaoPendenteParaAlteracao(
            Solicitacao solicitacao
    ) {
        if (solicitacao.getStatus()
                != StatusSolicitacao.PENDENTE) {

            throw new BusinessException(
                    "Somente solicitações pendentes podem ser alteradas"
            );
        }
    }

    private void validarSolicitacaoPendenteParaExclusao(
            Solicitacao solicitacao
    ) {
        if (solicitacao.getStatus()
                != StatusSolicitacao.PENDENTE) {

            throw new BusinessException(
                    "Somente solicitações pendentes podem ser excluídas"
            );
        }
    }

    private void validarSolicitacaoPendenteParaResposta(
            Solicitacao solicitacao
    ) {
        if (solicitacao.getStatus()
                != StatusSolicitacao.PENDENTE) {

            throw new BusinessException(
                    "Somente solicitações pendentes podem ser respondidas"
            );
        }
    }

    private void rejeitarOutrasSolicitacoes(
            Integer anuncioId,
            Integer solicitacaoAprovadaId
    ) {
        List<Solicitacao> outrasSolicitacoes =
                solicitacaoRepository.findByAnuncioId(anuncioId);

        outrasSolicitacoes.stream()
                .filter(solicitacao ->
                        !solicitacao.getId()
                                .equals(solicitacaoAprovadaId)
                )
                .filter(solicitacao ->
                        solicitacao.getStatus()
                                == StatusSolicitacao.PENDENTE
                )
                .forEach(solicitacao ->
                        solicitacao.setStatus(
                                StatusSolicitacao.REJEITADA
                        )
                );

        solicitacaoRepository.saveAll(outrasSolicitacoes);
    }

    private Solicitacao buscarSolicitacao(Integer id) {
        return solicitacaoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Solicitação não encontrada"
                        )
                );
    }

    private Anuncio buscarAnuncio(Integer id) {
        return anuncioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Anúncio não encontrado"
                        )
                );
    }

    private SolicitacaoResponseDTO toResponseDTO(
            Solicitacao solicitacao
    ) {
        return new SolicitacaoResponseDTO(
                solicitacao.getId(),
                solicitacao.getMensagem(),
                solicitacao.getStatus(),
                solicitacao.getCreatedAt(),
                solicitacao.getAnuncio().getId(),
                solicitacao.getAnuncio().getTitulo(),
                solicitacao.getUser().getId(),
                solicitacao.getUser().getName()
        );
    }
}