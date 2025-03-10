package de.poker.spade.repository;

import java.util.List;

import de.poker.spade.entity.Device;

public interface DeviceRepositoryCustom {

	public List<Device> findDevicesByStudentUsernameWithCriteriaAPI(String username);

	public List<Device> findDevicesByStudentUsernameWithQuerydsl(String username);

}
