INSERT INTO COUPLE (IDCOUPLE, firstElement, secondElement) VALUES(1, 1,6);
INSERT INTO COUPLE (IDCOUPLE, firstElement, secondElement) VALUES(2, 1,2);
INSERT INTO COUPLE (IDCOUPLE, firstElement, secondElement) VALUES(3, 6,2);
INSERT INTO COUPLE (IDCOUPLE, firstElement, secondElement) VALUES(4, 2,4);
INSERT INTO COUPLE (IDCOUPLE, firstElement, secondElement) VALUES(5, 5,8);
INSERT INTO CONNECTEDCOUPLES (ID,idConnectedCouple,IDCOUPLE) VALUES(1,1, 1);
INSERT INTO CONNECTEDCOUPLES (ID,idConnectedCouple,IDCOUPLE) VALUES(2,1, 2);
INSERT INTO CONNECTEDCOUPLES (ID,idConnectedCouple,IDCOUPLE) VALUES(3,1, 3);
INSERT INTO CONNECTEDCOUPLES (ID,idConnectedCouple,IDCOUPLE) VALUES(4,1, 4);
INSERT INTO CONNECTEDCOUPLES (ID,idConnectedCouple,IDCOUPLE) VALUES(5,2, 5);
INSERT INTO GROUPCONNECTEDCOUPLES (idConnectedCouple) VALUES(1);
INSERT INTO GROUPCONNECTEDCOUPLES (idConnectedCouple) VALUES(2);