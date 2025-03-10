package tum.seba.persistence.repository;

import java.util.List;

import tum.seba.persistence.entity.Device;

public interface DeviceRepositoryCustom {

	public List<Device> findDevicesByStudentUsernameWithCriteriaAPI(String username);

	public List<Device> findDevicesByStudentUsernameWithQuerydsl(String username);

}
