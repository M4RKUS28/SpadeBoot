package tum.seba.persistence.repository;

import java.util.List;

import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tum.seba.persistence.entity.Device;
import tum.seba.persistence.entity.QDevice;
import tum.seba.persistence.entity.QStudent;
import tum.seba.persistence.entity.Student;

public class DeviceRepositoryCustomImpl implements DeviceRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Device> findDevicesByStudentUsernameWithCriteriaAPI(String username) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Device> cquery = cb.createQuery(Device.class);
		Root<Device> root = cquery.from(Device.class);
		cquery.select(root);
		Join<Device, Student> student = root.join("owner");
		
		Predicate devicesByUsername = cb.equal(student.get("username"), username);
		cquery.where(devicesByUsername);
		
		TypedQuery<Device> query = em.createQuery(cquery);
		List<Device> results = query.getResultList();
		return results;
	}

	@Override
	public List<Device> findDevicesByStudentUsernameWithQuerydsl(String username) {
		JPAQuery<Device> query = new JPAQuery<Device>(em);
		QDevice device = QDevice.device;
		QStudent student = QStudent.student;
		return query.from(device).join(device.owner, student).where(student.username.eq(username)).fetch();
	}

}
