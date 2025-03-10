package tum.seba.persistence.service;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import tum.seba.persistence.entity.Backpack;
import tum.seba.persistence.entity.Device;
import tum.seba.persistence.entity.Lecture;
import tum.seba.persistence.entity.Student;
import tum.seba.persistence.exception.EntityNotFoundException;
import tum.seba.persistence.exception.UniqueConstraintViolationException;
import tum.seba.persistence.repository.BackpackRepository;
import tum.seba.persistence.repository.DeviceRepository;
import tum.seba.persistence.repository.LectureRepository;
import tum.seba.persistence.repository.StudentRepository;

@Service
public class AdminService {


	@Autowired
	private BackpackRepository backpackRepository;

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private LectureRepository lectureRepository;
	


	public Backpack saveBackpack(Backpack newBackpack) {
		return backpackRepository.save(newBackpack);
	}

	public Iterable<Backpack> findAllBackpacks() {
		return backpackRepository.findAll();
	}

	public Backpack findBackpackById(int backpackId) {
		return backpackRepository.findById(backpackId).orElse(null);
	}

	public void deleteBackpackById(int backpackId) {
		try {
			backpackRepository.deleteById(backpackId);
		} catch (Exception e) {
			System.err.println("Unable to delete Backpack with ID: " + backpackId);
		}
	}

	public Device saveDevice(Device newDevice) {
		return deviceRepository.save(newDevice);
	}

	public Iterable<Device> findAllDevices() {
		return deviceRepository.findAll();
	}

	public Device findDeviceById(int deviceId) {
		return deviceRepository.findById(deviceId).orElse(null);
	}

	public void deleteDeviceById(int deviceId) {
		try {
			deviceRepository.deleteById(deviceId);
		} catch (Exception e) {
			System.err.println("Unable to delete Device with ID: " + deviceId);
		}
	}

	public Lecture saveLecture(Lecture newLecture) {
		return lectureRepository.save(newLecture);
	}

	public Iterable<Lecture> findAllLectures() {
		return lectureRepository.findAll();
	}

	public Lecture findLectureById(int lectureId) {
		return lectureRepository.findById(lectureId).orElse(null);
	}

	public void deleteLectureById(int lectureId) {
		try {
			lectureRepository.deleteById(lectureId);
		} catch (Exception e) {
			System.err.println("Unable to delete Lecture with ID: " + lectureId);
		}
	}


	// Query 3 JPQL
	public Iterable<Device> findDevicesByStudentUsernameWithJPQL(String username) {
		return deviceRepository.findDevicesByStudentUsernameWithJPQL(username);
	}

	// Query 3 CriteriaAPI
	public Iterable<Device> findDevicesByStudentUsernameWithCriteriaAPI(String username) {
		return deviceRepository.findDevicesByStudentUsernameWithCriteriaAPI(username);
	}

	// Query 3 Querydsl
	public Iterable<Device> findDevicesByStudentUsernameWithQuerydsl(String username) {
		return deviceRepository.findDevicesByStudentUsernameWithQuerydsl(username);
	}

}
