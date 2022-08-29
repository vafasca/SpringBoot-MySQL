package com.sofka.bingo.service;

import com.sofka.bingo.domain.CartonBingo;
import com.sofka.bingo.domain.Player;
import com.sofka.bingo.domain.Room;
import com.sofka.bingo.repository.CartonBingoRepository;
import com.sofka.bingo.repository.PlayerRepository;
import com.sofka.bingo.repository.RoomRepository;
import com.sofka.bingo.service.interfaces.IBingo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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

    @Override()
    @Transactional(readOnly = true)
    public List<CartonBingo> getAllBalotas(CartonBingo cartonBingo, Player player) {
        List<CartonBingo> listBalotas = cartonBingoRepository.findCartonBingoByCartBalota();// probando con parametro  2 de filtro
        return listBalotas;
    }

    @Override
    public List<CartonBingo> getAllBalotasJustOnePlayer(CartonBingo cartonBingo, Player player) {
        List<CartonBingo> listBalotas = cartonBingoRepository.findJustPlayer(1);// probando con parametro  2 de filtro, NO FUNCIONA
        return listBalotas;
    }


    @Override
    public Player getPlayerBingoList(Player player) {
        List<Player> playerBingoList = playerRepository.getPlayersById();
        int tamPlayers = playerBingoList.size()-1;
        return playerBingoList.get(tamPlayers);
    }

    //PROBANDO
    @Override
    public Player getIndexPlayer(Player player, String idMongo) {
        //List<Player> playerBingoList = playerRepository.getIndexPlayer();
        int index = playerRepository.getIndexPlayer(idMongo);
        List<Player> playerList = playerRepository.getPlayersById();
        return playerList.get(index-1);
    }
    //


    @Override
    @Transactional
    public Player registerPlayer(Player player, Room room) {
        Room ult = getLastRoom(room);
        player.setRoomRoom(ult);
        //player.setRoomRoom(getLastRoom(ultimo));
        return playerRepository.save(player);
    }

    @Override
    @Transactional
    public CartonBingo createCarton(CartonBingo cartonBingo, Player player) {
        cartonBingo.setPlayerIdplayer(getPlayerBingoList(player));
        return cartonBingoRepository.save(cartonBingo);
    }

    @Override
    @Transactional
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> showListPlayer(Player player) {
        List<Player> playerBingoList = playerRepository.getPlayersById();
        //int tamPlayers = playerBingoList.size()-1;
        //return playerBingoList.get(tamPlayers);
        return playerBingoList;
    }

    @Override
    public List<Integer> apiRestJugadores(CartonBingo cartonBingo) {
       RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9090/api/v1/playerBingo/"; //SE QUITO http://localhost:9090/api/v1/playerBingo
        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
        LinkedHashMap<Integer, Integer> LHM = (LinkedHashMap<Integer, Integer>) response.getBody();
        List<Integer> list3 = new ArrayList<>();
        List<Object> list = new ArrayList<>(LHM.values()); //agrego los datos del primer LinkedHashMap a un List
        LinkedHashMap<Integer, Integer> LHM2 = (LinkedHashMap<Integer, Integer>) list.get(2);
        List<Object> list2 = new ArrayList<>(LHM2.values()); //agrego a un List el id, el id del jugador mongo y las balotas
        Object idObject = list2.get(0);
        Object idMongoObject = list2.get(1);
        Object balotasObject = list2.get(2);
        //CONVIERTO LOS OBJETOS A INTEGER, STRING, ARRAYLIST
        int idInSql = (Integer) idObject; //ahora es un numero intteger
        String idInMongo = idMongoObject.toString(); //ahora es un string
        ArrayList arrayBalotas = (ArrayList) balotasObject; // ahora es un array list
        //
        for (int j = 0; j < arrayBalotas.size(); j++) {
            LinkedHashMap<Integer, Integer> LHM3 = (LinkedHashMap<Integer, Integer>) arrayBalotas.get(j);
            for (Map.Entry<Integer, Integer> i :
                    LHM3.entrySet()) {
                list3.add(i.getValue());
            }
            list3.remove(j); // elimina el id de cada balota y me deja la lista3 solo con las balotas de tipo integer
        }
        return list3;
    }

    @Override
    public List<Player> listBolas(Player player){

        return null;
    }


}
