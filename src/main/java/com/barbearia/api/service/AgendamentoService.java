package com.barbearia.api.service;

import com.barbearia.api.dto.AgendamentoRequestDTO;
import com.barbearia.api.dto.AgendamentoResponseDTO;
import com.barbearia.api.dto.RelatorioComissaoDTO;
import com.barbearia.api.model.Agendamento;
import com.barbearia.api.model.Barbeiro;
import com.barbearia.api.model.Cliente;
import com.barbearia.api.model.StatusAgendamento;
import com.barbearia.api.repository.AgendamentoRepository;
import com.barbearia.api.repository.BarbeiroRepository;
import com.barbearia.api.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final ClienteRepository clienteRepository;
    private final BarbeiroRepository barbeiroRepository;

    @Transactional
    public AgendamentoResponseDTO agendar(AgendamentoRequestDTO dados) {
        var cliente = clienteRepository.findById(dados.clienteId())
                .orElseThrow(EntityNotFoundException::new);

        var barbeiro = barbeiroRepository.findById(dados.barbeiroId())
                .orElseThrow(EntityNotFoundException::new);

        boolean horarioOcupado = repository.existsByBarbeiroIdAndDataAndHoraInicio(
                dados.barbeiroId(), dados.data(), dados.horaInicio());

        if (horarioOcupado) {
            throw new RuntimeException("Este barbeiro já possui um agendamento neste horário!");
        }

        var agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setBarbeiro(barbeiro);
        agendamento.setData(dados.data());
        agendamento.setHoraInicio(dados.horaInicio());
        agendamento.setHoraFim(dados.horaFim());
        agendamento.setValorServico(dados.valorServico());

        return new AgendamentoResponseDTO(repository.save(agendamento));

    }

    public List<AgendamentoResponseDTO> listarTodos(){
        return repository.findAll().stream().map(AgendamentoResponseDTO::new).toList();
    }

    @Transactional
    public void cancelar(Long id) {
        var agendamento = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        agendamento.setStatus(StatusAgendamento.CANCELADO);
    }

    public AgendamentoResponseDTO buscarPorId(Long id){
        var agendamento = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return new AgendamentoResponseDTO(agendamento);
    }

    @Transactional
    public void deletar(Long id){
        if (!repository.existsById(id)){
            throw new RuntimeException("Agendamento não encontrado!");
        }
        repository.deleteById(id);
    }

    public List<AgendamentoResponseDTO> buscarPorData(LocalDate data){
        return repository.findByData(data).stream().map(AgendamentoResponseDTO::new)
                .toList();
    }

    public List<AgendamentoResponseDTO> buscarPorBarbeiro(Long barbeiroId){
        return repository.findByBarbeiroId(barbeiroId).stream().map(AgendamentoResponseDTO::new)
                .toList();
    }

    @Transactional
    public AgendamentoResponseDTO concluirAgendamento(Long id){
        var agendamento = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        agendamento.setStatus(StatusAgendamento.COMCLUIDO);
        return  new AgendamentoResponseDTO(repository.save(agendamento));
    }

    public List<AgendamentoResponseDTO> buscarConcluidosPorData(LocalDate data){
        return repository.findByStatusAndData(StatusAgendamento.COMCLUIDO, data).stream().map(AgendamentoResponseDTO::new)
                .toList();
    }

    public BigDecimal calcularComissaoTotal(List<Agendamento> concluidos){
        return concluidos.stream().map(a ->{
            BigDecimal valor = a.getValorServico();
            BigDecimal percentual = BigDecimal.valueOf(a.getBarbeiro().getPercentualComissao());
            return valor.multiply(percentual)
                    .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public RelatorioComissaoDTO gerarRelatorioDeComissaoPorData(LocalDate data){
        List<Agendamento> concluidos = repository.findByStatusAndData(StatusAgendamento.COMCLUIDO, data);
        BigDecimal faturamento = concluidos.stream().map(Agendamento::getValorServico)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal comissao = calcularComissaoTotal(concluidos);

        return new  RelatorioComissaoDTO(faturamento, comissao, concluidos.size());
    }

}