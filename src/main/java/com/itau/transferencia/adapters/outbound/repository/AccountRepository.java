//
//import com.itau.transferencia.application.core.domain.AccountModel;
//import org.socialsignin.spring.data.dynamodb.repository.Query;
//import org.socialsignin.spring.data.dynamodb.repository.ReactiveDynamoDBRepository;
//import reactor.core.publisher.Flux;
//
//public interface AccountRepository extends ReactiveDynamoDBRepository<AccountModel, Integer> {
//
//    @Query("accountId = :accountId")
//    Flux<AccountModel> findByAccountId(Integer accountId);
//}