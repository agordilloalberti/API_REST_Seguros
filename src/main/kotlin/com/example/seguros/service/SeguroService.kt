import com.example.seguros.model.Seguro
import com.example.seguros.repository.SeguroRepository
import java.util.Date
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SeguroService {

    @Autowired
    private lateinit var seguroRepository: SeguroRepository

    fun checkData(
        nif: String?,
        nombre: String?,
        ape1:String?,
        ape2:String?,
        edad:Int?,
        hijos:Int?,
        fecha:Date?,
        sexo:String?,
        casado:Boolean?,
        embarazada:Boolean?):String{

        if (nif == null){
            return "nif no puede estar vacio"
        }

        val cn = checkNif(nif)
        if (cn!="0"){
            return cn
        }

        if (nombre.isNullOrBlank() || nombre.isEmpty()) {
            return "Nombre no debe estar vacío"
        }

        if (ape1.isNullOrBlank()||ape1.isEmpty()){
            return "Apellido 1 no debe estar vacio"
        }

        if (edad==null || edad<0){
            return "Edad no puede ser menor de 0"
        }

        if (edad<17){
            return "No es posible ser menor de edad para hacer un seguro"
        }

        if (sexo.isNullOrBlank() || sexo.isEmpty()){
            return "Sexo no puede estar vacio"
        }

        if (hijos == null){
            return "El número de hijos no puede ser nulo"
        }

        if (casado == null || (!casado && hijos>0)){
            return "Si no se esta casado no se pueden tener hijos"
        }

        if (embarazada == null || (embarazada && sexo.lowercase()!="mujer")){
            return "Solo las mujeres pueden estar embarazadas"
        }

        return "0"
    }

    private fun checkNif(nif: String): String {
        if (nif.matches(Regex.fromLiteral("[a-z]|[A-Z]|[0-9])[0-9]{7}([a-z]|[A-Z]|[0-9]"))){
            return "0"
        }
        return "No es un nif valido"
    }

    fun addSeguro(seguro: Seguro):String
    {
        val res = checkData(
            seguro.nif,
            seguro.nombre,
            seguro.ape1,
            seguro.ape2,
            seguro.edad,
            seguro.numHijos,
            seguro.fechaCreacion,
            seguro.sexo,
            seguro.casado,
            seguro.embarazada)

        if (res != "0"){
            return res
        }
        seguroRepository.save(seguro)
        return "Seguro añadido con exito"
    }

    fun deleteSeguro(id:Int?):String
    {
        if (id==null || id<0){
            return "Id no puede ser nulo o menor de 0"
        }

        if (seguroRepository.findByIdOrNull(id)==null){
            return "No exite un seguro con id $id"
        }

        seguroRepository.deleteById(id)
        return "0"
    }

    fun getSeguros():MutableList<Seguro>{
        return seguroRepository.findAll()
    }

    fun getSeguro(id: Int):Seguro?{
        return seguroRepository.findByIdOrNull(id)
    }

    fun editSeguro(id: Int,
                   nif: String?,
                   nombre: String?,
                   ape1:String?,
                   ape2:String?,
                   edad:Int?,
                   hijos:Int?,
                   fecha:Date?,
                   sexo:String?,
                   casado:Boolean?,
                   embarazada:Boolean?): String
    {
        val seguro = seguroRepository.findByIdOrNull(id) ?: return "No se ha encontrado el seguro"

        val nift = nif ?: seguro.nif

        val nombret = nombre?: seguro.nombre

        val ape1t = ape1?: seguro.ape1

        val ape2t = ape2?: seguro.ape2

        val edadt = edad?: seguro.edad

        val hijost = hijos?: seguro.numHijos

        val fechat = fecha?: seguro.fechaCreacion

        val sexot = sexo?: seguro.sexo

        val casadot = casado?: seguro.casado

        val embarazadat = embarazada?: seguro.embarazada

        val res = checkData(nift, nombret, ape1t, ape2t, edadt, hijost, fechat, sexot, casadot, embarazadat)
        if (res!="0"){
            return res
        }
        seguroRepository.deleteById(id)
        seguroRepository.save(Seguro(id,nift,nombret,ape1t,ape2t,edadt,hijost,fechat,sexot,casadot,embarazadat))
        return "0"
    }
}