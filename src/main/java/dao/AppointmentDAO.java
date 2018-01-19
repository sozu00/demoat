package dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import model.Appointment;

public interface AppointmentDAO extends PagingAndSortingRepository<Appointment, Integer>{

}
