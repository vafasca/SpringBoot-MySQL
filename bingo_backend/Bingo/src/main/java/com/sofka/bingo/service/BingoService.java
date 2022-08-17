package com.sofka.bingo.service;

import com.sofka.bingo.domain.CartonBingo;
import com.sofka.bingo.domain.Player;
import com.sofka.bingo.domain.Room;
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

//    @Override
//    public List<Player> getList() {
//        return null;
//    }

    @Override
    @Transactional(readOnly = true)
    public Room getLastRoom(Room room) {
        List<Room> roomList = roomRepository.getRoomList();
        int tam = roomList.size()-1;
        System.out.println(roomList.get(0).getId());
        long idult = roomList.get(tam).getId();
        Room ult = roomList.get(tam);
        //Room sala = roomList;
        //Room ultRoom = roomList.get(0);
        return ult;
    }

    @Override
    @Transactional(readOnly = true)
    public CartonBingo getCarton(CartonBingo cartonBingo) {
        List<CartonBingo> cartonList = cartonBingoRepository.findCartonBingoByCartBalota();
        int tam = cartonList.size()-1;
        CartonBingo ultCarton = cartonList.get(tam);
        return ultCarton;
    }


    @Override
    public int getPlayerBingoList(Player player) {
        List<Player> playerBingoList = playerRepository.getPlayersById();
        int tamPlayers = playerBingoList.size()-1;
        return tamPlayers;
    }

    @Override
    @Transactional
    public Player registerPlayer(Player player, Room room) {
        //Room ultimo = getLastRoom(room);
        //player.setRoomRoom(getLastRoom(ultimo));
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
