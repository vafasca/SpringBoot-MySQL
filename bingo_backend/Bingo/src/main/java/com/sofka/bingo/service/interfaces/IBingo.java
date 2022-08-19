package com.sofka.bingo.service.interfaces;

import com.sofka.bingo.domain.CartonBingo;
import com.sofka.bingo.domain.Player;
import com.sofka.bingo.domain.Room;

import java.util.List;

public interface IBingo {

    /**
     * Devuelve una lista de Contactos con todos contactos del sistema
     *
     * @return lista de contacto
     *
     * @author Bruno Alejandro Ortiz <baov1995@gmail.com>
     * @since 1.0.0
     */
//    public List<Player> getList();

    public Room getLastRoom(Room room);

    public CartonBingo getCarton(CartonBingo cartonBingo);

    public List<CartonBingo> getAllBalotas(CartonBingo cartonBingo, Player player);

    public List<CartonBingo> getAllBalotasJustOnePlayer(CartonBingo cartonBingo, Player player);

    public Player getPlayerBingoList(Player player);

    public Player registerPlayer(Player player, Room room);

    public CartonBingo createCarton(CartonBingo cartonBingo, Player player);

    public Room createRoom(Room room);

    public List<Player> showListPlayer(Player player);
    public List<Integer> apiRestJugadores(CartonBingo cartonBingo);

    public List<Player> listBolas(Player player);

}
