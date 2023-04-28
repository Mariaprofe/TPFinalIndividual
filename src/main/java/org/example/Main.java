
package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.example.Equipo;
import org.example.Participante;
import org.example.Partido;
import org.example.*;
public class Main {

    public static void main(String[] arg) throws IOException, SQLException {

//leer resultado.


        Collection<Partido> partidos = new ArrayList<Partido>();
        Collection<Pronostico> pronosticos = new ArrayList<Pronostico>();
        Collection<Integer> puntosObtenido= new ArrayList<Integer>();
        Collection<String> listaparticipan= new ArrayList<String>();
        //Conectar Base de datos


        String ruta = ("F:\\Curso Java\\TPPartefinal1\\src\\main\\java\\org\\example\\resultadostest1.csv");


        Path pathResultados = Paths.get(ruta);

        List<String> lineasResultados = null;
        try {

            lineasResultados = Files.readAllLines(pathResultados);
        } catch (IOException e) {
            System.out.println("No se pudo leer la linea de resultados...");
            System.out.println(e.getMessage());
            System.exit(1);
        }
        boolean primera = true;
        for (String lineaResultado : lineasResultados) {
            if (primera) {
                primera = false;
            } else {
                // Argentina,1,2,Arabia Saudita
                String[] campos = lineaResultado.split(",");
                Ronda nro = new Ronda(campos[0]);

                Equipo equipo1 = new Equipo(campos[1]);
                Equipo equipo2 = new Equipo(campos[4]);
                Partido partido = new Partido(nro, equipo1, equipo2);


                partido.setGolesEq1(Integer.parseInt(campos[2]));
                partido.setGolesEq2(Integer.parseInt(campos[3]));


                partidos.add(partido);

                System.out.println("RONDA:    "+ nro.getNro());

            }

        }
        // Leer pronostico//
        int puntos = 0; // total puntos pesona


        // Path pathPronostico = Paths.get(args[1]);

        //llama a Conectar

/*
        String ruta1 = ("F:\\Curso Java\\TPPartefinal1\\src\\main\\java\\org\\example\\pronosticotest1.csv");

        Path pathPronostico = Paths.get(ruta1);
        List<String> lineasPronostico = null;
        try {
            lineasPronostico = Files.readAllLines(pathPronostico);
        } catch (IOException e) {
            System.out.println("No se pudo leer la linea de resultados...");
            System.out.println(e.getMessage());
            System.exit(1);
        }
        primera = true;


        for (String lineaPronostico : lineasPronostico) {
            if (primera) {
                primera = false;
            } else {
                //consultar a pronostico
*/
                PronosticoRepositorio cc = new PronosticoRepositorio();
                Connection consulta = cc.conexion();
                ResultadoEnum resultado = null;
                Partido partido = null;



                try {
                  //  String[] campos = null;

                   // List<String> lineaPronostico = null;
                    Statement selectStatement = consulta.createStatement();
                    ResultSet resultSet = selectStatement.executeQuery("SELECT * FROM  pronosticotest1");
String ganador;
int puntajetotal=0;


                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String  participas = resultSet.getString("participante");
                        String equipo1 = resultSet.getString("equipo1");
                        String gana1 = resultSet.getString("gana1");
                        String empate = resultSet.getString("empate");
                        String gana2 = resultSet.getString("gana2");
                        String equipo2 = resultSet.getString("equipo2");
                        Integer puntosObtenidos = resultSet.getInt("puntosObtenidos");
                      //  System.out.println("id: " + id + " Participante: " + participas + "Equipo1: " + equipo1 + "gana1" + gana1 + "empate" + empate + "gana2" + gana2 + "equipo2" + equipo2+"puntos"+puntosObtenidos );
                        Participante participa = new Participante(participas);

                         listaparticipan.add(participas);
                         puntosObtenido.add(puntosObtenidos);
                         if (puntajetotal<puntosObtenidos) {
                           puntajetotal=puntosObtenidos;
                           ganador=participas;
                             System.out.println("GANADOR:   "+ ganador + "    PUNTOS OBTENIDOS:  " + puntajetotal);
                         }
                        // sumar los puntos correspondientes


           /* String[] campos = lineaPronostico.split(",");
               Equipo equipo1 = new Equipo(campos[2]);
              Equipo equipo2 = new Equipo(campos[7]);*/


                        for (Partido partidoCol : partidos) {
                            if (partidoCol.getEquipo1().getNombre(
                            ).equals(equipo1)//.getNombre())
                                    && partidoCol.getEquipo2().getNombre(
                            ).equals(equipo2))//.getNombre()))
                            {

                                partido = partidoCol;

                            }


                            if ("X".equals(resultSet.getString("gana1"))) {
                           String equipo = equipo1;
                                resultado = ResultadoEnum.GANADOR;

                            }
                            if ("X".equals(resultSet.getString("empate"))) {
                              String  equipo = equipo1;
                                resultado = ResultadoEnum.EMPATE;

                            }
                            if ("X".equals(resultSet.getString("gana2"))) {
                            String equipo = equipo1;
                                resultado = ResultadoEnum.PERDEDOR;
                            }


                        }

                    }


                } catch (SQLException e) {
                    System.err.println(e.getMessage());

                }








                consulta.close();

               System.out.println(listaparticipan);
                System.out.println(puntosObtenido);





            }
        }








