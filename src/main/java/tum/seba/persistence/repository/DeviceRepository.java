package tum.seba.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tum.seba.persistence.entity.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer>, DeviceRepositoryCustom {

	@Query("SELECT d FROM Device d, Student s WHERE d.owner = s.id and s.username = ?1")
	public List<Device> findDevicesByStudentUsernameWithJPQL(String username);

}
