package com.sofka.bingo.service;

import com.sofka.bingo.domain.CartonBingo;
import com.sofka.bingo.domain.Room;
import com.sofka.bingo.domain.Player;
import com.sofka.bingo.repository.CartonBingoRepository;
import com.sofka.bingo.repository.PlayerRepository;
import com.sofka.bingo.repository.RoomRepository;
import com.sofka.bingo.service.interfaces.IBingo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BingoService implements IBingo {

    @Autowired
    private CartonBingoRepository cartonBingoRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public List<Player> getList() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Room getRoomList(Room room) {
        List<Room> roomList = roomRepository.findLastRoom();
        int tam = roomList.size()-1;
        Room ultRoom = roomList.get(tam);
        return ultRoom;
    }

    @Override
    public Player registerPlayer(Player player, Room room) {
        player.setRoomRoom(getRoomList((room)));
        return playerRepository.save(player);
    }

    @Override
    @Transactional
    public CartonBingo createCarton(CartonBingo cartonBingo) {
        return cartonBingoRepository.save(cartonBingo);
    }

    @Override
    @Transactional
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

}
