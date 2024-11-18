import com.example.seguros.model.Seguro
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.Date

@RestController
@RequestMapping("/seguros")
class SeguroController() {

    @Autowired
    private lateinit var seguroService: SeguroService

    @PostMapping
    fun addSeguro(@RequestBody seguro: Seguro): ResponseEntity<String> {
        return ResponseEntity.ok(seguroService.addSeguro(seguro))
    }

    @GetMapping
    fun getSeguros(): ResponseEntity<List<Seguro>>{
        return ResponseEntity.ok(seguroService.getSeguros())
    }

    @GetMapping("/{id}")
    fun getSeguro(@PathVariable id : String): ResponseEntity<Seguro> {
        val seg = seguroService.getSeguro(id.toInt())
        return if (seg!=null) ResponseEntity.ok(seg) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteSeguro(@PathVariable id: Int):ResponseEntity<String>{
        seguroService.deleteSeguro(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{id}")
    fun editSeguro(
        @PathVariable id : Int,
        @PathVariable nif : String,
        @PathVariable nombre : String,
        @PathVariable ape1 : String,
        @PathVariable ape2 : String,
        @PathVariable edad : Int,
        @PathVariable hijos : Int,
        @PathVariable fecha : Date,
        @PathVariable sexo : String,
        @PathVariable casado : Boolean,
        @PathVariable embarazada : Boolean
    ): ResponseEntity<String>{
        return ResponseEntity.ok(seguroService.editSeguro(id,nif,nombre,ape1,ape2,edad,hijos,fecha,sexo,casado,embarazada))
    }
}