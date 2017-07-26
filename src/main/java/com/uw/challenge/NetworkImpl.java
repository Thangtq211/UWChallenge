package com.uw.challenge;

import com.uw.challenge.entity.ConnectedCouples;
import com.uw.challenge.entity.Couple;
import com.uw.challenge.entity.Element;
import com.uw.challenge.entity.GroupConnectedCouples;
import com.uw.challenge.repository.ConnectedCouplesRepository;
import com.uw.challenge.repository.CoupleRepository;
import com.uw.challenge.repository.ElementRepository;
import com.uw.challenge.repository.GroupConnectedCouplesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @task Implement a class NetworkImpl.
 * The constructor should take a positive integer value indicating the number of elements in the set.
 * Passing in an invalid value should throw an exception.
 * <p>
 * The class should also provide two public methods, connect and query.
 * <p>
 * The first method, connect will take two integers indicating the elements to connect.
 * This method should throw exceptions as appropriate.
 * <p>
 * The second method, query will also take two integers and should also throw exceptions as appropriate.
 * <p>
 * It should return true if the elements are connected, directly or indirectly, and false if the elements are not connected.
 * <p>
 * The class can have as many private or protected members as needed for a good implementation.
 * Also note that the exceptions mentioned are SPL exceptions and you should not define them.
 */
public class NetworkImpl implements Network {

    private static final Logger log = LoggerFactory.getLogger(NetworkImpl.class);
    @Autowired
    DataSource dataSource;
    @Autowired
    ElementRepository elementRepository;
    @Autowired
    CoupleRepository coupleRepository;
    @Autowired
    ConnectedCouplesRepository connectedCouplesRepository;
    @Autowired
    GroupConnectedCouplesRepository groupConnectedCouplesRepository;


    public NetworkImpl() {

    }

    /**
     * adding an element to the map.
     *
     * @param value to add
     */
    public void add(int value) {
        Validation.checkElements(value);
        Element element = new Element(value);
        elementRepository.save(element);
    }

    /**
     * making a connect between two existed elements.
     * Throw exceptions if these elements are not existed or not valid.
     *
     * @param firstElement
     * @param secondElement
     */
    public void connect(int firstElement, int secondElement) {
        log.info("firstElement:" + firstElement + " secondElement:" + secondElement);
        validateElements(firstElement, secondElement);
        Couple couple = new Couple(firstElement, secondElement);
        Couple saveCouple = coupleRepository.save(couple);
        for (GroupConnectedCouples groupConnectedCouples : groupConnectedCouplesRepository.findAll()) {
            List<ConnectedCouples> connectedCouplesList
                    = connectedCouplesRepository.findByIdConnectedCouple(groupConnectedCouples.getId());
            for (ConnectedCouples connectedCouples : connectedCouplesList) {
                Couple foundCouple = coupleRepository.findOne(connectedCouples.getCoupleId());
                if (foundCouple != null) {
                    if (foundCouple.isMatch(couple)) {
                        ConnectedCouples newConnectedCouples = new ConnectedCouples();
                        newConnectedCouples.setCoupleId(saveCouple.getId());
                        newConnectedCouples.setId(groupConnectedCouples.getId());
                        connectedCouplesRepository.save(newConnectedCouples);
                        return;
                    }
                }
            }
        }

        GroupConnectedCouples groupConnectedCouples = new GroupConnectedCouples();
        groupConnectedCouplesRepository.save(groupConnectedCouples);

        ConnectedCouples connectedCouples = new ConnectedCouples();
        connectedCouples.setCoupleId(saveCouple.getId());
        connectedCouples.setId(groupConnectedCouples.getId());
        connectedCouplesRepository.save(connectedCouples);
    }

    /**
     * query a connected between two existed elements.
     * Throw exceptions if these elements are not existed or not valid.
     *
     * @param firstElement
     * @param secondElement
     * @return return true if the elements are connected directly or indirectly, and false if the elements are not connected
     */
    public boolean query(int firstElement, int secondElement) {
        validateElements(firstElement, secondElement);

        if (groupConnectedCouplesRepository.count() == 0) return false;
        Couple couple = new Couple(firstElement, secondElement);
        if (hasCouple(couple))
            return true;
        return false;
    }

    /**
     * check if a couple existed in the database.
     * @param couple
     * @return
     */
    public boolean hasCouple(Couple couple) {
        boolean existedFistElement = false;
        boolean existedSecondElement = false;
        for (GroupConnectedCouples groupConnectedCouples : groupConnectedCouplesRepository.findAll()) {
            List<ConnectedCouples> connectedCouplesList
                    = connectedCouplesRepository.findByIdConnectedCouple(groupConnectedCouples.getId());
            for (ConnectedCouples connectedCouples : connectedCouplesList) {
                //directly
                Couple foundCouple = coupleRepository.findOne(connectedCouples.getCoupleId());
                if (foundCouple != null) {
                    if (foundCouple.isMatch(couple)) {
                        return true;
                    }
                }
                //indirectly
                if (foundCouple.hasElement(couple.getFirstElement())) existedFistElement = true;
                if (foundCouple.hasElement(couple.getSecondElement())) existedSecondElement = true;
            }
        }
        return existedFistElement && existedSecondElement;
    }

    /**
     * validating elements. Throw exceptions if these elements are not existed or not valid.
     *
     * @param firstElement
     * @param secondElement
     */
    public void validateElements(int firstElement, int secondElement) {
        Validation.checkElements(firstElement, secondElement);
        if (elementRepository.findByValue(firstElement).size()==0 || elementRepository.findByValue(secondElement).size()==0)
            throw new NoSuchElementException("One of two or two elements is not existed in the elements list.");
    }

    /**
     * print all information for couples, connected couples, group connected couples from the database.
     */
    public void printAllInformationFromDB() {
        log.info("print all Couples ....");
        for (Couple couple : coupleRepository.findAll()) {
            log.info(" valueCouple:" + couple.toString());
        }
        log.info("print all connectedCouples ....");
        for (ConnectedCouples connectedCouples : connectedCouplesRepository.findAll()) {
            log.info(" valueConnectedCouples:" + connectedCouples.toString());
        }

        log.info("print all connectedCouples ....");
        for (GroupConnectedCouples groupConnectedCouples : groupConnectedCouplesRepository.findAll()) {
            log.info(" valueGroupConnectedCouples:" + groupConnectedCouples.toString());
        }

    }

    /**
     * clear all data from the database.
     */
    public void deleteAll() {
        coupleRepository.deleteAll();
        connectedCouplesRepository.deleteAll();
        groupConnectedCouplesRepository.deleteAll();
    }
}
