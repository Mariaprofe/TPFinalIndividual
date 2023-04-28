import com.intellij.util.xmlb.annotations.Text;
import org.junit.Before;

import static org.junit.Assert.assertEquals;



public class PartidoText {
    private Equipo equipo1;
    private Equipo equipo2;
      private Partido   partido;
    @Before
    Public  void setup(){
        this.equipo1=new Equipo("Boca");
        this.equipo2=new Equipo("San Lorenzo")
         this.partido=new Partido(equipo1,equipo2,2,5);



    }
@Text
    public void testpartidoGanadorPerdedor(){
//escenario
        Equipo equipo1=new Equipo("Boca");
        Equipo equipo2=new Equipo("San Lorenzo");
        Partido partido=new Partido (equipo1,equipo2,2,4);

        ResultadoEnum resultadoObtenido1=partido.resultado(equipo1);
    ResultadoEnum resultadoObtenido2=partido.resultado(equipo2);
       // Evaluar
                   assertEquals((ResultadoEnum.PERDEDOR, resultadoObtenido1);

                     assertEquals((ResultadoEnum.GANADOR,resultadoObtenido2);


    }
    @Text
    public void testpartidoEmpatado(){
//escenario
        Equipo equipo1=new Equipo("Boca");
        Equipo equipo2=new Equipo("San Lorenzo");
        Partido partido=new Partido (equipo1,equipo2,3,3);

        ResultadoEnum resultadoObtenido1=partido.resultado(equipo1);
        ResultadoEnum resultadoObtenido2=partido.resultado(equipo2);
        // Evaluar
        assertEquals((ResultadoEnum.EMPATE, resultadoObtenido1);

        assertEquals((ResultadoEnum.EMPATE,resultadoObtenido2);


    }


}
