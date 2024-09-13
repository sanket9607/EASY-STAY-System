package com.myproject.ce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.ce.entity.Rooms;
import com.myproject.ce.repository.RoomsDao;

@Service
public class RoomService {

	@Autowired
	private RoomsDao roomsDao;

	public Rooms getRoom(final int id) {
		return roomsDao.getRoom(id);
	}

	public void addRooms(final Rooms room) {
		roomsDao.addRooms(room);
	}

	public List<Rooms> getRooms() {
		return roomsDao.getRooms();
	}

	public void updateRooms(final Rooms room) {
		roomsDao.updateRooms(room);
	}

	public void deleteRooms(final int room_no) {
		roomsDao.deleteRooms(room_no);
	}

}
