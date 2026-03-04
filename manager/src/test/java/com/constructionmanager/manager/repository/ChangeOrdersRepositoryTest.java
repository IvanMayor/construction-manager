package com.constructionmanager.manager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ChangeOrdersRepositoryTest {

    @Autowired
    private ChangeOrdersRepository changeOrdersRepository;
}
