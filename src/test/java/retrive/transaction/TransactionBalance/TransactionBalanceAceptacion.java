package retrive.transaction.TransactionBalance;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public class TransactionBalanceAceptacion {
    @Inject
    @Client("/ohs-asistencia")
    HttpClient client;

    @Inject
    private ModelMapper mapper;

    /*@Test
    public void cancelarAsistencia() {
        StepVerifier.create(client.retrieve(HttpRequest.PUT("/asistencia/cancelar/202008190000918/0", ""), CancelarAsistenciaRpDto.class))
                .consumeNextWith(permisoZenDriveDto ->{
                    assertNotNull(permisoZenDriveDto);
                }).expectComplete()
                .verify();
    }

    @Test
    public void cancelarAsistenciaError(){
        StepVerifier.create(client.retrieve(HttpRequest.POST("/asistencia/11111", ""), CancelarAsistenciaRpDto.class))
                .expectError()
                .verify();
    }*/
}
