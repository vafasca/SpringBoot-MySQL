package com.sofka.bingo.controller;


import com.sofka.bingo.domain.CartonBingo;
import com.sofka.bingo.domain.Player;
import com.sofka.bingo.domain.Room;
import com.sofka.bingo.service.BingoService;
import com.sofka.bingo.utility.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * Controlador para la libreta
 *
 * @version 1.0.0 2022-08-16
 * @author Bruno Ortiz <baov1995@gmail.com>
 * @since 1.0.0
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class roomController {

    /**
     * Servicio para el manejo del bingo
     */
    @Autowired
    private BingoService bingoService;


    /**
     * Variable para el manejo de las respuestas de las API
     */
    private Response response = new Response();

    /**
     * Manejo del código HTTP que se responde en las API
     */
    private HttpStatus httpStatus = HttpStatus.OK;

    @PostMapping(path = "/api/v1/room")
    public ResponseEntity<Response> crearRoomBingo(Room room){
        response.restart();
        try {
            log.info("Carton a crear: {}", room);
            response.data = bingoService.createRoom(room);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @PostMapping(path = "/api/v1/cartonBingo")
    public ResponseEntity<Response> crearCartonBingo(@RequestBody CartonBingo cartonBingo, Player player){
        response.restart();
        try {
            log.info("Carton a crear: {}", cartonBingo);
            response.data =bingoService.createCarton(cartonBingo, player);
            httpStatus = HttpStatus.CREATED;
        }catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @PostMapping(path = "/api/v1/newPlayer")
    public ResponseEntity<Response> regPlayer(@RequestBody Player player, Room room)  { //agregar el @Requestbody
        response.restart();
        try {
            log.info("Player a agregar: {}", player);

            response.data = bingoService.registerPlayer(player, room);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @GetMapping(path = "/api/v1/room")
    public ResponseEntity<Response> LastRoomBingo(Room room){
        response.restart();
        try {
            response.data =bingoService.getLastRoom(room);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @GetMapping(path = "/api/v1/balotas")
    public ResponseEntity<Response> listCarton(CartonBingo cartonBingo){
        response.restart();
        try {
            response.data =bingoService.getCarton(cartonBingo);
            httpStatus = HttpStatus.OK;
        }catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @GetMapping(path = "/api/v1/listBalotas")// todas las balotas l ultimo jugador
    public ResponseEntity<Response> listBalotas(CartonBingo cartonBingo, Player player){
        response.restart();
        try {
            response.data =bingoService.getAllBalotas(cartonBingo, player);
            httpStatus = HttpStatus.OK;
        }catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @GetMapping(path = "/api/v1/listBalotas1")
    public ResponseEntity<Response> listBalotasJustOne(CartonBingo cartonBingo, Player player){
        response.restart();
        try {
            response.data =bingoService.getAllBalotasJustOnePlayer(cartonBingo, player);
            httpStatus = HttpStatus.OK;
        }catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    //probando
    @GetMapping(path = "/api/v1/playerBingo/{idMongo}")
    public ResponseEntity<Response> listPlayerBingo(@PathVariable(value="idMongo") String idMongo, Player player){//ultimo jugador
        response.restart();
        try {
            response.data = bingoService.getIndexPlayer(player, idMongo);
            httpStatus = HttpStatus.OK;
        }catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }
    //

//    @GetMapping(path = "/api/v1/playerBingo")
//    public ResponseEntity<Response> listPlayerBingo(Player player){//ultimo jugador
//        response.restart();
//        try {
//            response.data = bingoService.getPlayerBingoList(player);
//            httpStatus = HttpStatus.OK;
//        }catch (Exception exception) {
//            getErrorMessageInternal(exception);
//        }
//        return new ResponseEntity(response, httpStatus);
//    }

    /**
     * Lista completa de jugadores
     * @param player
     * @return
     */
    @GetMapping(path = "/api/v1/listarJugadores")
    public ResponseEntity<Response> jugadoresList(Player player){//todos los jugadores
        response.restart();
        try {
            response.data = bingoService.showListPlayer(player);
            httpStatus = HttpStatus.OK;
        }catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }



    /**
     * GET CON API REST TEMPLATE
     */
    @GetMapping(path = "api/v1/pobar")
    public ResponseEntity<Response> test(CartonBingo cartonBingo, Player player){
        response.restart();
        try {
            //bingoService.listBolas(player);
            bingoService.apiRestJugadores(cartonBingo);
            httpStatus = HttpStatus.OK;
        }catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Administrador para las excepciones del sistema
     *
     * @param exception Objeto Exception
     *
     * @author Bruno Ortiz <baov1995@gmail.com>
     * @since 1.0.0
     */
    private void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Administrador para las excepciones a nivel de SQL con respecto al manejo del acceso a los datos
     *
     * @param exception Objeto DataAccessException
     *
     * @author Bruno Ortiz <baov1995@gmail.com>
     * @since 1.0.0
     */
    private void getErrorMessageForResponse(DataAccessException exception) {
        response.error = true;
        if(exception.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) exception.getRootCause();
            var sqlErrorCode = sqlEx.getErrorCode();
            switch (sqlErrorCode) {
                case 1062:
                    response.message = "El dato ya está registrado";
                    break;
                case 1452:
                    response.message = "El usuario indicado no existe";
                    break;
                default:
                    response.message = exception.getMessage();
                    response.data = exception.getCause();
            }
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            response.message = exception.getMessage();
            response.data = exception.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
