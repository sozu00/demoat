package dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import model.Paciente;

public interface PacienteDAO extends PagingAndSortingRepository<Paciente, Integer>{

}
