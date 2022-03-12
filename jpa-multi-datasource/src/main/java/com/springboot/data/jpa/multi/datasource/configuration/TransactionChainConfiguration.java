package com.springboot.data.jpa.multi.datasource.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionChainConfiguration {

    /**
     we will create ChainedTransactionManager by two PlatformTransactionManager
     which are `primaryTransactionManager` and `secondaryTransactionManager`
     because we want to revert transaction if one of them happens issue in saving data.
     **/
    @Bean(name = "chainedTransactionManager")
    public ChainedTransactionManager transactionChainConfiguration(
            @Qualifier("primaryTransactionManager") PlatformTransactionManager primaryTransaction,
            @Qualifier("secondaryTransactionManager") PlatformTransactionManager secondaryTransaction) {
        return new ChainedTransactionManager(primaryTransaction, secondaryTransaction);
    }

}
