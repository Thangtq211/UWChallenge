package com.uw.challenge;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootApplication
public class NetworkImplTest {
    private static final Logger log = LoggerFactory.getLogger(NetworkImplTest.class);
    @Autowired
    private NetworkImpl networkImpl;

    @TestConfiguration
    static class NetworkImplTestContextConfiguration {

        @Bean
        public NetworkImpl networkImpl() {
            return new NetworkImpl();
        }
    }


    @BeforeClass
    public static void beforeClass() throws SQLException {


    }

    @Test
    public void TestAddMinusNetworkElements() {
        try {
            for (int i = 1; i < 9; i++) networkImpl.add(-i);
        } catch (IllegalArgumentException illegalException) {

        }
        networkImpl.deleteAll();
    }

    @Test
    public void TestAddBiggerMaxIntegerNetworkElements() {
        try {
            for (int i = 1; i < 9; i++) networkImpl.add(Integer.MAX_VALUE);
        } catch (IllegalArgumentException illegalException) {

        }
        networkImpl.deleteAll();
    }

    @Test
    public void TestSingleConnectedCoupleNetworkElements() {
        for (int i = 1; i < 9; i++) networkImpl.add(i);
        networkImpl.connect(1, 6);
        assertThat(networkImpl.query(1, 6), is(true));
        networkImpl.deleteAll();
    }

    @Test
    public void TestValidateNetworkElements() {
        try {
            networkImpl.validateElements(1, 6);
        } catch (NoSuchElementException noSuchElementException) {

        }

    }

    @Test
    public void TestSingleUnConnectedCoupleNetworkElements() {
        for (int i = 1; i < 9; i++) networkImpl.add(i);
        assertThat(networkImpl.query(1, 6), is(false));
    }

    @Test
    public void TestExistedConnectionsWithNetworkElements() {
        for (int i = 1; i < 9; i++) networkImpl.add(i);
        networkImpl.connect(1, 6);
        assertThat(networkImpl.query(1, 6), is(true));
        networkImpl.connect(1, 2);
        assertThat(networkImpl.query(1, 2), is(true));
        networkImpl.connect(6, 2);
        assertThat(networkImpl.query(6, 2), is(true));
        networkImpl.connect(2, 4);
        assertThat(networkImpl.query(2, 4), is(true));
        networkImpl.connect(5, 8);
        assertThat(networkImpl.query(5, 8), is(true));

        assertThat(networkImpl.query(1, 4), is(true));

        log.info("dumping all couples:" + networkImpl.toString());

        networkImpl.printAllInformationFromDB();
        networkImpl.deleteAll();

    }

    @Test
    public void TestNonExistedConnectionsWithNetworkElements() {
        for (int i = 1; i < 9; i++) networkImpl.add(i);

        assertThat(networkImpl.query(1, 3), is(false));
        assertThat(networkImpl.query(1, 5), is(false));
        assertThat(networkImpl.query(1, 7), is(false));
        assertThat(networkImpl.query(1, 8), is(false));

        assertThat(networkImpl.query(2, 5), is(false));
        assertThat(networkImpl.query(2, 3), is(false));
        assertThat(networkImpl.query(2, 7), is(false));
        assertThat(networkImpl.query(2, 8), is(false));

        assertThat(networkImpl.query(3, 4), is(false));
        assertThat(networkImpl.query(3, 5), is(false));
        assertThat(networkImpl.query(3, 6), is(false));
        assertThat(networkImpl.query(3, 7), is(false));
        assertThat(networkImpl.query(3, 8), is(false));

        assertThat(networkImpl.query(4, 5), is(false));
        assertThat(networkImpl.query(4, 7), is(false));
        assertThat(networkImpl.query(4, 8), is(false));

        assertThat(networkImpl.query(5, 6), is(false));
        assertThat(networkImpl.query(5, 7), is(false));

        assertThat(networkImpl.query(6, 7), is(false));
        assertThat(networkImpl.query(6, 8), is(false));

        assertThat(networkImpl.query(7, 8), is(false));
        networkImpl.deleteAll();


    }
}
