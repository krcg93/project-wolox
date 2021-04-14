package retrive.transaction.TransactionBalance;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import project.wolox.domain.service.UsersService;
import project.wolox.domain.service.dependency.UsersI;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public class TransactionBalanceUnitaria {
    @Inject
    private ModelMapper mapper;

    UsersI usersI;
    UsersService usersService;

    @BeforeEach
    void IniTest(){
        usersI = Mockito.mock(UsersI.class);
        usersService = new UsersService(usersI);
    }

   /* @Test
    void transactionBalanceOk(){
        Mockito.when(transactionBalanceI.getTransactionBalance()).then(item ->getTransactionBalance());

        StepVerifier.create(transactionBalanceService.getTransactionBalance())
                .consumeNextWith(retrieveTransactionBalance -> {
                    assertNotNull(retrieveTransactionBalance);
                    RetrieveTransactionBalanceDto retrieveTransactionBalanceDto = mapper.map(retrieveTransactionBalance, RetrieveTransactionBalanceDto.class);
                    assertEquals("276", retrieveTransactionBalanceDto.getResponseSize());
                })
                .verifyComplete();
    }

    @Test
    void cancelarAsistenciaError(){
        Mockito.when(transactionBalanceI.getTransactionBalance())
                .then(item -> null);

        StepVerifier.create(transactionBalanceService.getTransactionBalance())
                .expectError()
                .verify();
    }

    private Mono<RetrieveTransactionBalance> getTransactionBalance() {
        RetrieveTransactionBalanceDto retrieveTransactionBalanceDto = new RetrieveTransactionBalanceDto();
        RetrieveTransactionsDto retrieveTransactionsDto = new RetrieveTransactionsDto();
        AccountDto accountDto = new AccountDto();
        accountDto.setType("CUENTA_DE_AHORRO");
        accountDto.setNumber("10115431236");
        DataDto dataDto = new  DataDto();
        dataDto.setAccount(accountDto);
        retrieveTransactionsDto.setData();
        retrieveTransactionBalanceDto.setResponseSize(retrieveTransactionsDto.getData().getResponseSize());
        retrieveTransactionBalanceDto.setFlagMoreRecords(retrieveTransactionsDto.getData().getFlagMoreRecords());
        retrieveTransactionBalanceDto.setAccount(retrieveTransactionsDto.getData().getAccount());
        retrieveTransactionBalanceDto.setTransaction(retrieveTransactionsDto.getData().getTransaction());
        retrieveTransactionBalanceDto.setRetriveBalance(retriveBalanceObjDto.getBalances());
        retrieveTransactionBalanceDto.setCustomer(retrieveTransactionsDto.getData().getCustomer());
        retrieveTransactionBalanceDto.setOffice(retrieveTransactionsDto.getData().getOffice());
        retrieveTransactionBalanceDto.setStatus(retrieveTransactionsDto.getStatus());
        return CancelarAsistenciaRp.create(result);
    }*/
}
