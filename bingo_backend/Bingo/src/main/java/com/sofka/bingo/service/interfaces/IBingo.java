package com.sofka.bingo.service.interfaces;

import com.sofka.bingo.domain.CartonBingo;
import com.sofka.bingo.domain.Room;
import com.sofka.bingo.domain.Player;


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
    public List<Player> getList();

    public Room getRoomList(Room room);

    public Player registerPlayer(Player player, Room room);

    public CartonBingo createCarton(CartonBingo cartonBingo);

    public Room createRoom(Room room);
}
