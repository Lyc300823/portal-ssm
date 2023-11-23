package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.Room;
import com.leozhang.portalssm.util.Result;

import java.util.List;

public interface RoomService {
    Result getListForPage(int pno, int psize, String name, Long areaId, String
            phone, String sortField, String sortType);

    Room selectRoomById(Long id);
    public void updateRoom(Room room);

}
